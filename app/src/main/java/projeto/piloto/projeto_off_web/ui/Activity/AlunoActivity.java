package projeto.piloto.projeto_off_web.ui.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.R;

public class AlunoActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_aluno);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.aluno_activity), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    Aluno aluno = (Aluno) getIntent().getSerializableExtra("aluno");
    TextView nomeAluno = findViewById(R.id.nome_aluno);
    nomeAluno.setText(aluno.getNome());


  }
}