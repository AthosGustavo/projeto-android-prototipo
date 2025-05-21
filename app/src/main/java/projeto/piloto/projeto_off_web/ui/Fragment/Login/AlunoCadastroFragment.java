package projeto.piloto.projeto_off_web.ui.Fragment.Login;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import java.util.Arrays;
import java.util.List;

import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.Model.Entidade.Ficha;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;
import projeto.piloto.projeto_off_web.Model.Entidade.TurmaAluno;
import projeto.piloto.projeto_off_web.Util.Util;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;
import projeto.piloto.projeto_off_web.databinding.FragmentCadastroAlunoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlunoCadastroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlunoCadastroFragment extends Fragment {

  private FragmentCadastroAlunoBinding fragmentCadastroAlunoBinding;
  private OffWebDb offWebDb;
  private ViewModel viewModel;
  private List<Turma> listaTurma;
  private Integer turmaSelecionada;
  private ProfessorLoginFragment.IComunicacaoTelaPrincipal iComunicacaoTelaPrincipal;

  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public AlunoCadastroFragment() {
    // Required empty public constructor
  }

  public static AlunoCadastroFragment newInstance(String param1, String param2) {
    AlunoCadastroFragment fragment = new AlunoCadastroFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }

    offWebDb = OffWebDb.getInstance(getContext());
    viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);
    carregarTurmas();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    fragmentCadastroAlunoBinding = FragmentCadastroAlunoBinding.inflate(inflater, container, false);




    return fragmentCadastroAlunoBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ArrayAdapter<Turma> adapter = new ArrayAdapter<>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listaTurma
    );

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    fragmentCadastroAlunoBinding.codigoTurma.setAdapter(adapter);

// Listener para capturar seleção
    fragmentCadastroAlunoBinding.codigoTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        turmaSelecionada = listaTurma.get(position).getId();

      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        // Nenhuma seleção
      }
    });

    configuraBtnCadastrar();

  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.iComunicacaoTelaPrincipal = (ProfessorLoginFragment.IComunicacaoTelaPrincipal) context;
  }

  public void configuraBtnCadastrar(){
    fragmentCadastroAlunoBinding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        cadastrarAluno();
      }
    });
  }



  public void cadastrarAluno(){

    try{
      String nomeAluno = fragmentCadastroAlunoBinding.nomeAluno.getText().toString();
      String curso = fragmentCadastroAlunoBinding.curso.getText().toString();
      Float horasUsoInternet = Float.valueOf(fragmentCadastroAlunoBinding.horasUsoInternet.getText().toString());
      //Integer codigoTurma = Integer.valueOf(fragmentCadastroAlunoBinding.codigoTurma.getText().toString());
      String descricao = fragmentCadastroAlunoBinding.descricao.getText().toString();

      int perguntaUmId = fragmentCadastroAlunoBinding.perguntaUm.getCheckedRadioButtonId();
      int perguntaDoisId = fragmentCadastroAlunoBinding.perguntaDois.getCheckedRadioButtonId();
      int perguntaTresId = fragmentCadastroAlunoBinding.perguntaTres.getCheckedRadioButtonId();
      int perguntaQuatroId = fragmentCadastroAlunoBinding.perguntaQuatro.getCheckedRadioButtonId();
      int perguntaCincoId = fragmentCadastroAlunoBinding.perguntaCinco.getCheckedRadioButtonId();

      RadioButton perguntaUm = fragmentCadastroAlunoBinding.getRoot().findViewById(perguntaUmId);
      RadioButton perguntaDois = fragmentCadastroAlunoBinding.getRoot().findViewById(perguntaDoisId);
      RadioButton perguntaTres = fragmentCadastroAlunoBinding.getRoot().findViewById(perguntaTresId);
      RadioButton perguntaQuatro = fragmentCadastroAlunoBinding.getRoot().findViewById(perguntaQuatroId);
      RadioButton perguntaCinco = fragmentCadastroAlunoBinding.getRoot().findViewById(perguntaCincoId);

      viewModel.getExecutorService().execute(() -> {
        Long aluno = offWebDb.alunoDao().inserir(new Aluno(nomeAluno,curso));

        offWebDb.fichaDao().inserir(new Ficha(
                horasUsoInternet,
                descricao,
                aluno.intValue(),
                perguntaUm.getText().toString(),
                perguntaDois.getText().toString(),
                perguntaTres.getText().toString(),
                perguntaQuatro.getText().toString(),
                perguntaCinco.getText().toString()
        ));
        offWebDb.turmaAlunoDao().inserir(new TurmaAluno(aluno.intValue(),turmaSelecionada));
      });

    }catch(Exception e){
      Util.exibirDialogMsg(getContext(),"Atenção","Todos os campos devem ser preenchidos");
    }

    Util.exibirDialogMsg(getContext(),"","Aluno cadastrado com sucesso !");
    iComunicacaoTelaPrincipal.chamaTelaLoginProfessor();
  }

  public void carregarTurmas(){

    new Thread(() -> {
      listaTurma = offWebDb.turmaDao().buscarTurmas();
    }).start();

  }

}