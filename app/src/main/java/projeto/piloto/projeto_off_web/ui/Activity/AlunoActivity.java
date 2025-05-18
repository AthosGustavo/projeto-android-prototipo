package projeto.piloto.projeto_off_web.ui.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.Model.Entidade.Ficha;
import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;
import projeto.piloto.projeto_off_web.databinding.ActivityAlunoBinding;

public class AlunoActivity extends AppCompatActivity {

  private Aluno alunoClicado;
  private ActivityAlunoBinding activityAlunoBinding;
  private ViewModel viewModel;
  private OffWebDb offWebDb;
  private Ficha fichaAluno;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);

    activityAlunoBinding = ActivityAlunoBinding.inflate(getLayoutInflater());

    setContentView(activityAlunoBinding.getRoot());
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.aluno_activity), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    offWebDb = OffWebDb.getInstance(this);
    viewModel = new ViewModelProvider(this).get(ViewModel.class);
    this.alunoClicado = (Aluno) getIntent().getSerializableExtra("aluno");
    buscarFicha(alunoClicado);

  }

  private void buscarFicha(Aluno aluno) {
    offWebDb.fichaDao().buscarFichaPorAluno(aluno.getId()).observe(this, new Observer<Ficha>() {
      @Override
      public void onChanged(Ficha ficha) {
        fichaAluno = ficha;
        exibirInfoAluno(aluno);
      }
    });
  }


  private void exibirInfoAluno(Aluno aluno) {
    activityAlunoBinding.nomeAluno.setText(aluno.getNome());
    activityAlunoBinding.curso.setText(aluno.getCurso());
    activityAlunoBinding.horasUsoInternetDia.setText(fichaAluno.getHorasUsoInternetDia().toString());
    activityAlunoBinding.descricao.setText(fichaAluno.getDescricao());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish(); // Fecha a Activity ao clicar na seta
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}