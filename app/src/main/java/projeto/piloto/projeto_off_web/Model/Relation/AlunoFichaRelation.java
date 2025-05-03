package projeto.piloto.projeto_off_web.Model.Relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.Model.Entidade.Ficha;

public class AlunoFichaRelation {

  @Embedded
  private Aluno aluno;
  @Relation(
          parentColumn = "id",
          entityColumn = "aluno"
  )
  private Ficha ficha;

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public Ficha getFicha() {
    return ficha;
  }

  public void setFicha(Ficha ficha) {
    this.ficha = ficha;
  }
}
