package projeto.piloto.projeto_off_web.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Material;
import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.Util.Util;

public class MateriaisAdapter extends RecyclerView.Adapter<MateriaisAdapter.ViewHolder> {

  private List<Material> materiais;
  private Context context;
  private OffWebDb offWebDb;

  public MateriaisAdapter(Context context,List<Material> materiais) {
    this.materiais = materiais;
    this.context = context;
    offWebDb = OffWebDb.getInstance(context);
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

    holder.itemView.setOnLongClickListener(view -> {
      exibirDialogMsg(context,"Atenção","Deseja excluir este material ?",item,position);
      return true;
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

  public void exibirDialogMsg(Context context, String titulo, String mensagem, Material material,Integer posicao) {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
    alertDialogBuilder.setCancelable(false);
    alertDialogBuilder.setMessage(mensagem);
    alertDialogBuilder.setTitle(titulo);
    alertDialogBuilder.setPositiveButton("OK",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface arg0, int arg1) {
                new Thread(() -> {
                  offWebDb.materialDao().remover(material);
                  List<Material> listaMateriaisAtualizados = offWebDb.materialDao().buscarMaterialPorTurma(material.getTurma());

                  if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                      materiais.clear();
                      materiais.addAll(listaMateriaisAtualizados);
                      notifyDataSetChanged();
                    });
                  }

                }).start();


              }
            });
    alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
      }
    });
    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }

  public void atualizarRecyclerView(Integer posicao) {
    this.materiais.remove(posicao);
    notifyDataSetChanged();
  }
}