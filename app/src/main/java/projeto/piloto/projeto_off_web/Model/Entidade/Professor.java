package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "professores")
public class Professor {

  @PrimaryKey
  private Integer id;
  private String nome;
  private String disciplina;

  public Professor(Integer id, String nome, String disciplina) {
    this.id = id;
    this.nome = nome;
    this.disciplina = disciplina;
  }

  @Ignore
  public Professor(String nome, String disciplina) {
    this.nome = nome;
    this.disciplina = disciplina;
  }

  @Ignore
  public Professor() {

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

  public String getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(String disciplina) {
    this.disciplina = disciplina;
  }
}
