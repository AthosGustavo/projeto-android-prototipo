package projeto.piloto.projeto_off_web.ui.Fragment.Turma;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;
import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.ViewModel.TurmaViewModel;
import projeto.piloto.projeto_off_web.databinding.FragmentCriarTurmaBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CriarTurmaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CriarTurmaFragment extends Fragment {


  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private FragmentCriarTurmaBinding fragmentCriarTurmaBinding;
  private OffWebDb offWebDb;
  private TurmaViewModel turmaViewModel;


  public CriarTurmaFragment() {
    // Required empty public constructor
  }

  public static CriarTurmaFragment newInstance(String param1, String param2) {
    CriarTurmaFragment fragment = new CriarTurmaFragment();
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
    turmaViewModel = new ViewModelProvider(requireActivity()).get(TurmaViewModel.class);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    fragmentCriarTurmaBinding = FragmentCriarTurmaBinding.inflate(inflater,container,false);

    configuraBtnCriarTurma();


    return fragmentCriarTurmaBinding.getRoot();
  }

  public void configuraBtnCriarTurma(){
    fragmentCriarTurmaBinding.btnCriarTurma.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Professor professor = turmaViewModel.professor();

        turmaViewModel.getExecutorService().execute(() -> {
          offWebDb.TurmaDao().inserir(
                  new Turma("TURMA DE TESTE",professor.getId())
          );
        });


      }
    });
  }
}