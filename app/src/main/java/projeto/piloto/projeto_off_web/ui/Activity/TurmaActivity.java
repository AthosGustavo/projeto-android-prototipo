package projeto.piloto.projeto_off_web.ui.Activity;

import android.os.Bundle;
import android.view.View;

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
import projeto.piloto.projeto_off_web.ViewModel.TurmaViewModel;
import projeto.piloto.projeto_off_web.databinding.ActivityTurmaBinding;
import projeto.piloto.projeto_off_web.ui.Fragment.Turma.CriarTurmaFragment;
import projeto.piloto.projeto_off_web.ui.Fragment.Turma.ListaTurmaFragment;

public class TurmaActivity extends AppCompatActivity implements ListaTurmaFragment.OnCriarTurmaListener{

  private ActivityTurmaBinding activityTurmaBinding;
  private CriarTurmaFragment criarTurmaFragment;
  private ListaTurmaFragment listaTurmaFragment;
  private TurmaViewModel turmaViewModel;
  
  
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

    turmaViewModel = new ViewModelProvider(this).get(TurmaViewModel.class);
    criarTurmaFragment = new CriarTurmaFragment();
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

  @Override
  public void onCriarTurmaClicked() {
    setFragment(criarTurmaFragment);
  }

  public void mockProfessor(){
    Professor professor = new Professor(1,"Alberto Silva","Direito Administrativo");
    turmaViewModel.setProfessor(professor);
  }

}