package projeto.piloto.projeto_off_web.ui.Fragment.Turma;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import projeto.piloto.projeto_off_web.Adapter.ListaTurmaAdapter;
import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;
import projeto.piloto.projeto_off_web.databinding.FragmentListaTurmaBinding;
import projeto.piloto.projeto_off_web.ui.Activity.CriarTurmaActivity;
import projeto.piloto.projeto_off_web.ui.Activity.ListaAlunosActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaTurmaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaTurmaFragment extends Fragment {
  
  private FragmentListaTurmaBinding fragmentListaTurmaBinding;
  private IListaTurmaListener iListaTurmaListener;
  private ViewModel viewModel;
  private OffWebDb offWebDb;
  ListaTurmaAdapter listaTurmaAdapter;
  
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private String mParam1;
  private String mParam2;

  public ListaTurmaFragment() {
    
  }

  public static ListaTurmaFragment newInstance(String param1, String param2) {
    ListaTurmaFragment fragment = new ListaTurmaFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

/*  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    //iListaTurmaListener = (IListaTurmaListener) context;
  }*/

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
    fragmentListaTurmaBinding = FragmentListaTurmaBinding.inflate(inflater,container,false);
    return fragmentListaTurmaBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    configuraRecyclerView();
    configuraBtnCriarTurma();
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onResume() {
    super.onResume();
    new Thread(() -> {
      List<Turma> turmas = offWebDb.turmaDao().buscarTurmas();
      requireActivity().runOnUiThread(() -> {
        listaTurmaAdapter.atualizar(turmas); // Atualiza a lista e notifica o adapter
      });
    }).start();
  }

  private void configuraRecyclerView(){
    RecyclerView recyclerView = fragmentListaTurmaBinding.recyclerViewListaTurma;

    viewModel.getExecutorService().execute(() -> {
      listaTurmaAdapter = new ListaTurmaAdapter(getContext(), offWebDb.turmaDao().buscarTurmas(), new ListaTurmaAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Turma turma) {
          Intent intent = new Intent(getContext(), ListaAlunosActivity.class);
          intent.putExtra("turma", turma);
          startActivity(intent);
        }
      });

      requireActivity().runOnUiThread(() -> {
        recyclerView.setAdapter(listaTurmaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      });
    });

  }

  private void configuraBtnCriarTurma(){
    fragmentListaTurmaBinding.btnCriarTurma.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getActivity(), CriarTurmaActivity.class);
        startActivity(intent);
      }
    });
  }

  public interface IListaTurmaListener {


  }


}