package projeto.piloto.projeto_off_web.ui.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import projeto.piloto.projeto_off_web.Adapter.MateriaisAdapter;
import projeto.piloto.projeto_off_web.Adapter.MaterialItem;
import projeto.piloto.projeto_off_web.R;

public class ListaMateriaisActivity extends AppCompatActivity {

  private static final int PICK_PDF_FILE = 1;
  private RecyclerView recyclerView;
  private MateriaisAdapter adapter;
  private List<MaterialItem> materiais = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_materiais);

    recyclerView = findViewById(R.id.recycler_view_materiais);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new MateriaisAdapter(materiais);
    recyclerView.setAdapter(adapter);

    FloatingActionButton fab = findViewById(R.id.fab_add_material);
    fab.setOnClickListener(v -> escolherPdf());
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
      materiais.add(new MaterialItem(nome, uri));
      adapter.notifyDataSetChanged();
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
}