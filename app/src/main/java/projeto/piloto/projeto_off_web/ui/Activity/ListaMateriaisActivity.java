package projeto.piloto.projeto_off_web.ui.Activity;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import projeto.piloto.projeto_off_web.Adapter.MateriaisAdapter;
import projeto.piloto.projeto_off_web.Adapter.MaterialItem;
import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Material;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;
import projeto.piloto.projeto_off_web.R;

public class ListaMateriaisActivity extends AppCompatActivity {

  private static final int PICK_PDF_FILE = 1;
  private RecyclerView recyclerView;
  private MateriaisAdapter materiaisAdapter;
  private Turma turma;
  private OffWebDb offWebDb;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_materiais);

    Intent intent = getIntent();
    turma = (Turma) intent.getSerializableExtra("turma");


    ExtendedFloatingActionButton fab = findViewById(R.id.fab_add_material);
    fab.setOnClickListener(v -> escolherPdf());

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    offWebDb = OffWebDb.getInstance(this);
    configuraRecyclerView();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish(); // Fecha a Activity ao clicar na seta
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void escolherPdf() {
    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
    intent.setType("application/pdf");
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    startActivityForResult(intent, PICK_PDF_FILE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PICK_PDF_FILE && resultCode == RESULT_OK && data != null) {
      Uri uri = data.getData();
      String nome = getFileName(uri);

      // Obter as flags corretas dos dados da intent
      int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
      if ((data.getFlags() & Intent.FLAG_GRANT_WRITE_URI_PERMISSION) != 0) {
        takeFlags |= Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
      }

      // Solicitar permissão persistente
      try {
        getContentResolver().takePersistableUriPermission(uri, takeFlags);
      } catch (SecurityException e) {
        Toast.makeText(this, "Não foi possível obter permissão persistente para o arquivo",
                Toast.LENGTH_LONG).show();
        return;
      }

      new Thread(() -> {
        offWebDb.materialDao().inserir(new Material(nome, turma.getId(), uri.toString()));

        runOnUiThread(() -> {
          // Atualiza a lista de materiais
          configuraRecyclerView();
        });
      }).start();
    }
  }

  private String getFileName(Uri uri) {
    String result = "Arquivo PDF";
    if (uri.getScheme().equals("content")) {
      try (android.database.Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
        if (cursor != null && cursor.moveToFirst()) {
          int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
          if (nameIndex >= 0) {
            result = cursor.getString(nameIndex);
          }
        }
      }
    }
    return result;
  }

  private void configuraRecyclerView() {
    new Thread(() -> {

      materiaisAdapter = new MateriaisAdapter(this, offWebDb.materialDao().buscarMaterialPorTurma(turma.getId()));

      runOnUiThread(() -> {
        recyclerView = findViewById(R.id.recycler_view_materiais);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(materiaisAdapter);
      });
    }).start();
  }
}