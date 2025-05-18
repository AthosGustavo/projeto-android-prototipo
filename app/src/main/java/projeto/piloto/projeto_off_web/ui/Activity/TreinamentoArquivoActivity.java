package projeto.piloto.projeto_off_web.ui.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import projeto.piloto.projeto_off_web.Adapter.PdfAdapter;
import projeto.piloto.projeto_off_web.R;

public class TreinamentoArquivoActivity extends AppCompatActivity implements PdfAdapter.OnItemClickListener {

    private List<String> pdfFiles = new ArrayList<>();
    private PdfAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treinamento_arquivo);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPdf);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            AssetManager assetManager = getAssets();
            String[] files = assetManager.list("");
            for (String file : files) {
                if (file.endsWith(".pdf")) {
                    pdfFiles.add(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new PdfAdapter(pdfFiles, this);
        recyclerView.setAdapter(adapter);
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
            Uri uri = FileProvider.getUriForFile(
                this,
                getPackageName() + ".fileprovider",
                outFile
            );
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Abrir PDF com..."));
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao abrir PDF", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}