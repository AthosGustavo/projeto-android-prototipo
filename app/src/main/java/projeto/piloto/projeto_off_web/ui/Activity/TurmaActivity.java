package projeto.piloto.projeto_off_web.ui.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;
import projeto.piloto.projeto_off_web.databinding.ActivityTurmaBinding;
import projeto.piloto.projeto_off_web.ui.Fragment.Turma.ListaTurmaFragment;
import projeto.piloto.projeto_off_web.ui.Fragment.Turma.ListaTurmaFragment.IListaTurmaListener;


public class TurmaActivity extends AppCompatActivity{

  private ActivityTurmaBinding activityTurmaBinding;
  private ListaTurmaFragment listaTurmaFragment;
  private ViewModel viewModel;
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    activityTurmaBinding = ActivityTurmaBinding.inflate(getLayoutInflater());
    setContentView(activityTurmaBinding.getRoot());
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.turma_activity), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    viewModel = new ViewModelProvider(this).get(ViewModel.class);
    listaTurmaFragment = new ListaTurmaFragment();
    mockProfessor();
  }

  @Override
  protected void onResume() {
    setFragment(listaTurmaFragment);
    super.onResume();
  }

  private void setFragment(Fragment fragment){
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(activityTurmaBinding.turmaActivity.getId(), fragment);
    fragmentTransaction.commit();
  }

/*  @Override
  public void criarTurma() {

  }*/

/*  @Override
  public void clicarTurma(Turma turma) {
    setFragment(turmaFragment);
  }*/

  public void mockProfessor(){
    Professor professor = new Professor(1,"Alberto Silva","Direito Administrativo");
    viewModel.setProfessor(professor);
  }

}