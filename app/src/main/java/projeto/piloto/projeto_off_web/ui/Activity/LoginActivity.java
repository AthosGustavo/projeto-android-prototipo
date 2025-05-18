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

import com.google.android.material.snackbar.Snackbar;

import projeto.piloto.projeto_off_web.Database.FirebaseAutenticacao;
import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.databinding.ActivityLoginBinding;
import projeto.piloto.projeto_off_web.databinding.FragmentProfessorLoginBinding;
import projeto.piloto.projeto_off_web.ui.Fragment.Login.AlunoCadastroFragment;
import projeto.piloto.projeto_off_web.ui.Fragment.Login.ProfessorLoginFragment;

public class LoginActivity extends AppCompatActivity implements ProfessorLoginFragment.IComunicacaoTelaPrincipal {

  private ActivityLoginBinding activityLoginBinding;
  private AlunoCadastroFragment alunoCadastroFragment;
  private ProfessorLoginFragment professorLoginFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());

    EdgeToEdge.enable(this);
    setContentView(activityLoginBinding.getRoot());
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(activityLoginBinding.activityLogin.getId()), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    alunoCadastroFragment = new AlunoCadastroFragment();
    professorLoginFragment = new ProfessorLoginFragment();
    activityLoginBinding.btnInterfaceAluno.setOnClickListener(listener);
    activityLoginBinding.btnInterfaceProfessor.setOnClickListener(listener);

  }

  @Override
  protected void onResume() {
    setFragment(professorLoginFragment);
    super.onResume();
  }

  private void setFragment(Fragment fragment){
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(activityLoginBinding.framelayoutInterfaceAlunoProfessor.getId(), fragment);
    fragmentTransaction.commit();
  }

  View.OnClickListener listener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {

      if(activityLoginBinding.btnInterfaceAluno.getId() == (v.getId())){
        setFragment(alunoCadastroFragment);
      }
      if(activityLoginBinding.btnInterfaceProfessor.getId() == (v.getId())){
        setFragment(professorLoginFragment);
      }
    }
  };


  @Override
  public void chamaTelaLoginProfessor() {

  }
}