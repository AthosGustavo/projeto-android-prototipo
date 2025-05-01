package projeto.piloto.projeto_off_web.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Turma;
import projeto.piloto.projeto_off_web.R;

public class ListaTurmaAdapter extends RecyclerView.Adapter<ListaTurmaAdapter.ViewHolder>{
  private Context context;
  private List<Turma> listaTurmas;
  private OnItemClickListener listener;

  public ListaTurmaAdapter(Context context, List<Turma> listaTurmas, OnItemClickListener listener) {
    this.context = context;
    this.listaTurmas = listaTurmas;
    this.listener = listener;
  }

  public interface OnItemClickListener {
    void onItemClick(Turma turma);
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(this.context);
    View view = layoutInflater.inflate(R.layout.item_turma, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Turma turma = listaTurmas.get(position);
    holder.vincula(turma);
    holder.bindListener(turma,listener);
  }

  @Override
  public int getItemCount() {
    return listaTurmas.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView nome;

    public ViewHolder(View view){
      super(view);
      this.nome = view.findViewById(R.id.nome);

    }

    public void vincula(Turma turma){
      this.nome.setText(turma.getNome());
    }

    public void bindListener(final Turma turma, final OnItemClickListener listener){
      itemView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
          listener.onItemClick(turma);
        }
      });
    }

  }
}
