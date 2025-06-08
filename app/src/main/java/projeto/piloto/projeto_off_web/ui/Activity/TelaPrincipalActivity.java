package projeto.piloto.projeto_off_web.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;
import projeto.piloto.projeto_off_web.databinding.ActivityTelaPrincipalBinding;
import projeto.piloto.projeto_off_web.ui.Fragment.Login.ProfessorLoginFragment;
import projeto.piloto.projeto_off_web.ui.Fragment.Login.ProfessorLoginFragment.IComunicacaoTelaPrincipal;
import projeto.piloto.projeto_off_web.ui.Fragment.PerfilFragment;
import projeto.piloto.projeto_off_web.ui.Fragment.TreinamentoFragment;
import projeto.piloto.projeto_off_web.ui.Fragment.Turma.ListaTurmaFragment;
import projeto.piloto.projeto_off_web.ui.Fragment.Turma.ListaTurmaFragment.IListaTurmaListener;

public class TelaPrincipalActivity extends AppCompatActivity  implements IListaTurmaListener {

  private ActivityTelaPrincipalBinding activityTelaPrincipalBinding;
  private ListaTurmaFragment listaTurmaFragment;
  private TreinamentoFragment treinamentoFragment;
  private PerfilFragment perfilFragment;
  private ViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);

    activityTelaPrincipalBinding = ActivityTelaPrincipalBinding.inflate(getLayoutInflater());
    setContentView(activityTelaPrincipalBinding.getRoot());
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    viewModel = new ViewModelProvider(this).get(ViewModel.class);
    listaTurmaFragment = new ListaTurmaFragment();
    treinamentoFragment = new TreinamentoFragment();
    perfilFragment = new PerfilFragment();
    activityTelaPrincipalBinding.btnMenuTurma.setOnClickListener(listener);
    activityTelaPrincipalBinding.btnMenuPerfil.setOnClickListener(listener);
    activityTelaPrincipalBinding.btnMenuTreinamento.setOnClickListener(listener);
    setFragment(listaTurmaFragment);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

  }

  private void setFragment(Fragment fragment){
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(activityTelaPrincipalBinding.containerTelaPrincipal.getId(), fragment);
    fragmentTransaction.commit();
  }

  View.OnClickListener listener = v -> {

    if (v.getId() == R.id.btn_menu_turma) {
      setFragment(listaTurmaFragment);
    }
    if(v.getId() == R.id.btn_menu_treinamento) {
      setFragment(treinamentoFragment);
    }
    if(v.getId() == R.id.btn_menu_perfil) {
      setFragment(perfilFragment);
    }
  };

  @Override
  public void chamarAbaPerfil() {
    setFragment(perfilFragment);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Quando o ícone de navegação (ic_deslogar) é clicado
    if (item.getItemId() == android.R.id.home) {
      // Implementar o logout
      // Limpar dados de sessão, preferências, etc. se necessário

      // Navegar para a tela de login
      Intent intent = new Intent(this, LoginActivity.class); // Substitua pelo nome correto da sua Activity de login
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpa pilha de activities
      startActivity(intent);
      finish(); // Encerra a activity atual
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}