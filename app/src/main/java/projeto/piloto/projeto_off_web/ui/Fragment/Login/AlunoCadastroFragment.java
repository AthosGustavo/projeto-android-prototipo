package projeto.piloto.projeto_off_web.ui.Fragment.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.Model.Entidade.Ficha;
import projeto.piloto.projeto_off_web.Model.Entidade.TurmaAluno;
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
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    fragmentCadastroAlunoBinding = FragmentCadastroAlunoBinding.inflate(inflater, container, false);
    return fragmentCadastroAlunoBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    configuraBtnCadastrar();

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

    String nomeAluno = fragmentCadastroAlunoBinding.nomeAluno.getText().toString();
    String curso = fragmentCadastroAlunoBinding.curso.getText().toString();
    Float horasUsoInternet = Float.valueOf(fragmentCadastroAlunoBinding.horasUsoInternet.getText().toString());
    Integer codigoTurma = Integer.valueOf(fragmentCadastroAlunoBinding.codigoTurma.getText().toString());
    String descricao = fragmentCadastroAlunoBinding.descricao.getText().toString();

    viewModel.getExecutorService().execute(() -> {
      Long aluno = offWebDb.alunoDao().inserir(new Aluno(nomeAluno,curso));

      offWebDb.fichaDao().inserir(new Ficha(horasUsoInternet,descricao,aluno.intValue()));
      offWebDb.turmaAlunoDao().inserir(new TurmaAluno(aluno.intValue(),codigoTurma));
    });
  }

}