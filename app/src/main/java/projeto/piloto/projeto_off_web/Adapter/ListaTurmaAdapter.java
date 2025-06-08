package projeto.piloto.projeto_off_web.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;
import projeto.piloto.projeto_off_web.Model.Entidade.TurmaAluno;
import projeto.piloto.projeto_off_web.R;

public class ListaTurmaAdapter extends RecyclerView.Adapter<ListaTurmaAdapter.ViewHolder>{
  private Context context;
  private List<Turma> listaTurmas;
  private OnItemClickListener listener;
  private OffWebDb offWebDb;
  private List<TurmaAluno> turmaAluno;

  public ListaTurmaAdapter(Context context, List<Turma> listaTurmas, OnItemClickListener listener) {
    this.context = context;
    this.listaTurmas = listaTurmas;
    this.listener = listener;
    this.offWebDb = OffWebDb.getInstance(context);
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
    holder.vincula(turma,turma.getId().toString(),turma.getPeriodo().toString());
    holder.bindListener(turma,listener);
  }

  @Override
  public int getItemCount() {
    return listaTurmas.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView nome;
    private TextView periodo;
    private TextView codigo;

    public ViewHolder(View view){
      super(view);
      this.nome = view.findViewById(R.id.nome);
      this.codigo = view.findViewById(R.id.codigo);
      this.periodo = view.findViewById(R.id.periodo);
    }

    public void vincula(Turma turma,String codigo, String periodoTurma){
      this.nome.setText("Turma: " + turma.getNome());
      this.periodo.setText("Período: " + periodoTurma);
      this.codigo.setText("Código: " + codigo);
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

  public void atualizar(List<Turma> turmas) {

    this.listaTurmas.clear();
    this.listaTurmas.addAll(turmas);

    notifyDataSetChanged();
  }
}
