package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "alunos")
public class Aluno implements Serializable {

  @PrimaryKey
  private Integer id;
  private String nome;
  private String curso;

  public Aluno(String nome, String curso) {
    this.nome = nome;
    this.curso = curso;
  }

  @Ignore
  public Aluno() {

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

  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }
}
