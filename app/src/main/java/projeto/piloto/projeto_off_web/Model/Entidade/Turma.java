package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "turmas")
public class Turma implements Serializable {

  @PrimaryKey
  private Integer id;
  private String nome;
  private Integer professor;


  public Turma(String nome, Integer professor) {
    this.nome = nome;
    this.professor = professor;
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

  @Override
  public String toString() {
    return nome;
  }

}
