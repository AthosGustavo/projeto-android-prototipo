package projeto.piloto.projeto_off_web.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MateriaisAdapter extends RecyclerView.Adapter<MateriaisAdapter.ViewHolder> {

  private List<MaterialItem> materiais;

  public MateriaisAdapter(List<MaterialItem> materiais) {
    this.materiais = materiais;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    MaterialItem item = materiais.get(position);
    holder.textView.setText(item.nome);
    holder.itemView.setOnClickListener(v -> {
      Context context = v.getContext();
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setDataAndType(item.uri, "application/pdf");
      intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      context.startActivity(intent);
    });
  }

  @Override
  public int getItemCount() {
    return materiais.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ViewHolder(View v) {
      super(v);
      textView = v.findViewById(android.R.id.text1);
    }
  }
}