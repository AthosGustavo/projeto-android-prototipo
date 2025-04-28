package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName ="turmasAlunos",
  foreignKeys = {
        @ForeignKey(entity = Aluno.class,parentColumns = "id", childColumns = "aluno"),
        @ForeignKey(entity = Turma.class,parentColumns = "id", childColumns = "turma")
  })
public class TurmaAluno {

  @PrimaryKey
  private Integer id;
  private Integer aluno;
  private Integer turma;

  public TurmaAluno(Integer aluno, Integer turma) {
    this.aluno = aluno;
    this.turma = turma;
  }

  public Integer getAluno() {
    return aluno;
  }

  public void setAluno(Integer aluno) {
    this.aluno = aluno;
  }

  public Integer getTurma() {
    return turma;
  }

  public void setTurma(Integer turma) {
    this.turma = turma;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
