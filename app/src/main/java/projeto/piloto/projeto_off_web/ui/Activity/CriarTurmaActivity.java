package projeto.piloto.projeto_off_web.ui.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;
import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;

public class CriarTurmaActivity extends AppCompatActivity {

  private EditText nomeTurma;
  private Button btnCriarTurma;
  private OffWebDb offWebDb;
  private ViewModel viewModel;
  private Professor professor = new Professor(1,"Alberto Silva","Direito Administrativo");


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_criar_turma);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    nomeTurma = findViewById(R.id.nomeTurma);
    btnCriarTurma = findViewById(R.id.btnCriarTurma);

    offWebDb = OffWebDb.getInstance(this);

    configuraBtnCriarTurma();

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish(); // Fecha a Activity ao clicar na seta
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void configuraBtnCriarTurma(){
    btnCriarTurma.setOnClickListener(v -> {
      System.out.println("teste");
      new Thread(() -> {
        offWebDb.turmaDao().inserir(
                new Turma(nomeTurma.getText().toString(), professor.getId())
        );
      }).start();
      finish(); // Fecha a Activity e retorna ao fragmento anterior
    });
  }
}