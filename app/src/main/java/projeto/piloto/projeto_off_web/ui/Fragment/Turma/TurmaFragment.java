package projeto.piloto.projeto_off_web.ui.Fragment.Turma;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projeto.piloto.projeto_off_web.Adapter.ListaAlunosAdapter;
import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;
import projeto.piloto.projeto_off_web.databinding.FragmentTurmaBinding;
import projeto.piloto.projeto_off_web.ui.Activity.AlunoActivity;


public class TurmaFragment extends Fragment{

  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private FragmentTurmaBinding fragmentTurmaBinding;
  private ViewModel viewModel;
  private ListaAlunosAdapter listaAlunosAdapter;
  private OffWebDb offWebDb;

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public TurmaFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment TurmaFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static TurmaFragment newInstance(String param1, String param2) {
    TurmaFragment fragment = new TurmaFragment();
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
    viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);
    offWebDb = OffWebDb.getInstance(getContext());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    fragmentTurmaBinding = FragmentTurmaBinding.inflate(inflater, container, false);

    configuraRecyclerView();
    return fragmentTurmaBinding.getRoot();

  }


  private void configuraRecyclerView() {
    RecyclerView recyclerView = fragmentTurmaBinding.recyclerViewListaAlunos;

    viewModel.getExecutorService().execute(() -> {
      listaAlunosAdapter = new ListaAlunosAdapter(getContext(), offWebDb.turmaAlunoDao().buscarAlunos(), new ListaAlunosAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Aluno aluno) {
          Intent intent = new Intent(getContext(), AlunoActivity.class);
          intent.putExtra("aluno",aluno);
          startActivity(intent);
        }
      });

      requireActivity().runOnUiThread(() -> {
        recyclerView.setAdapter(listaAlunosAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      });
    });
  }

}