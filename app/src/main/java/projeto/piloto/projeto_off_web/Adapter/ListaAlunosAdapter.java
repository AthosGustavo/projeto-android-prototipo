package projeto.piloto.projeto_off_web.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.R;


public class ListaAlunosAdapter extends RecyclerView.Adapter<ListaAlunosAdapter.ViewHolder>{

  private List<Aluno> listaAlunos;
  private Context context;
  private OnItemClickListener listener;

  public ListaAlunosAdapter(Context context, List<Aluno> listaAlunos, ListaAlunosAdapter.OnItemClickListener listener) {
    this.context = context;
    this.listaAlunos = listaAlunos;
    this.listener = listener;
  }

  public interface OnItemClickListener {
    void onItemClick(Aluno aluno);
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(this.context);
    View view = layoutInflater.inflate(R.layout.item_aluno, parent, false);
    return new ListaAlunosAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Aluno aluno = listaAlunos.get(position);
    holder.vincula(aluno);
    holder.bindListener(aluno, listener);
  }

  @Override
  public int getItemCount() {
    return listaAlunos.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView alunoNome;

    public ViewHolder(@NonNull View view) {
      super(view);
      this.alunoNome = view.findViewById(R.id.nome_aluno);
    }

    public void vincula(Aluno aluno){
      alunoNome.setText(aluno.getNome());
    }

    public void bindListener(final Aluno aluno, final ListaAlunosAdapter.OnItemClickListener listener){
      itemView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
          listener.onItemClick(aluno);
        }
      });
    }
  }
}
