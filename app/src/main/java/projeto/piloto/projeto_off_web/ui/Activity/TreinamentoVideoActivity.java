package projeto.piloto.projeto_off_web.ui.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import projeto.piloto.projeto_off_web.Adapter.VideoAdapter;
import projeto.piloto.projeto_off_web.R;

public class TreinamentoVideoActivity extends AppCompatActivity implements VideoAdapter.OnItemClickListener {

  private List<String> videoFiles = new ArrayList<>();
  private VideoAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_treinamento_video);

    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    try {
      AssetManager assetManager = getAssets();
      String[] files = assetManager.list("");
      for (String file : files) {
        if (file.endsWith(".mp4")) {
          videoFiles.add(file);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    adapter = new VideoAdapter(videoFiles, this);
    recyclerView.setAdapter(adapter);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish(); // Fecha a Activity ao clicar na seta
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onItemClick(String assetFileName) {
    try {
      // Copia o vídeo do assets para o cache interno
      File outFile = new File(getCacheDir(), assetFileName);
      if (!outFile.exists()) {
        InputStream in = getAssets().open(assetFileName);
        FileOutputStream out = new FileOutputStream(outFile);
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
          out.write(buffer, 0, read);
        }
        in.close();
        out.flush();
        out.close();
      }
      Intent intent = new Intent(this, VideoPlayerActivity.class);
      intent.putExtra("videoPath", outFile.getAbsolutePath());
      startActivity(intent);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}