package projeto.piloto.projeto_off_web.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Material;
import projeto.piloto.projeto_off_web.R;

public class MateriaisAdapter extends RecyclerView.Adapter<MateriaisAdapter.ViewHolder> {

  private List<Material> materiais;
  private Context context;

  public MateriaisAdapter(Context context,List<Material> materiais) {
    this.materiais = materiais;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_arquivo,parent,false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Material item = materiais.get(position);
    holder.vincula(item);

    holder.itemView.setOnClickListener(v -> {
      try {
        Uri uri = Uri.parse(item.getUri());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(intent);
      } catch (Exception e) {
        Toast.makeText(context, "Erro ao abrir o PDF: " + e.getMessage(),
                Toast.LENGTH_LONG).show();
        e.printStackTrace();
      }
    });
  }

  @Override
  public int getItemCount() {
    return materiais.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView nome;

    ViewHolder(View v) {
      super(v);
      nome = v.findViewById(R.id.text1);
    }

    public void vincula(Material material){
      nome.setText(material.getNome());
    }
  }
}