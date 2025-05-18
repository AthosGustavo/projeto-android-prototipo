package projeto.piloto.projeto_off_web.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import projeto.piloto.projeto_off_web.Adapter.ListaAlunosAdapter;
import projeto.piloto.projeto_off_web.Database.OffWebDb;

import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;
import projeto.piloto.projeto_off_web.databinding.ActivityListaAlunosBinding;


public class ListaAlunosActivity extends AppCompatActivity {

  private ActivityListaAlunosBinding activityListaAlunosBinding;
  private ViewModel viewModel;
  private ListaAlunosAdapter listaAlunosAdapter;
  private OffWebDb offWebDb;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityListaAlunosBinding = ActivityListaAlunosBinding.inflate(getLayoutInflater());
    setContentView(activityListaAlunosBinding.getRoot());

    viewModel = new ViewModelProvider(this).get(ViewModel.class);
    offWebDb = OffWebDb.getInstance(this);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    configuraRecyclerView();
  }

  private void configuraRecyclerView() {
    RecyclerView recyclerView = activityListaAlunosBinding.recyclerViewListaAlunos;

    viewModel.getExecutorService().execute(() -> {
      listaAlunosAdapter = new ListaAlunosAdapter(
              this,
              offWebDb.turmaAlunoDao().buscarAlunos(),
              aluno -> {
                Intent intent = new Intent(this, AlunoActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
              }
      );

      runOnUiThread(() -> {
        recyclerView.setAdapter(listaAlunosAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      });
    });
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