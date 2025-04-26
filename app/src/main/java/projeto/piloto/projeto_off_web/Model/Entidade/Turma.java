package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "turmas")
public class Turma {

  @PrimaryKey
  private Integer id;
  private String nome;
  private Integer professor;
  private Integer aluno;


  public Turma(Integer id, String nome, Integer professor, Integer aluno) {
    this.id = id;
    this.nome = nome;
    this.professor = professor;
    this.aluno = aluno;
  }

  @Ignore
  public Turma() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Integer getProfessor() {
    return professor;
  }

  public void setProfessor(Integer professor) {
    this.professor = professor;
  }

  public Integer getAluno() {
    return aluno;
  }

  public void setAluno(Integer aluno) {
    this.aluno = aluno;
  }
}
