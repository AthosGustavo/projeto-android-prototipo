package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "professores")
public class Professor {

  @PrimaryKey
  private Integer id;
  private String foto;
  private String nome;
  private String sobrenome;
  private Integer idade;
  private String disciplina;

  public Professor(Integer id, String foto, String nome, String sobrenome, Integer idade, String disciplina) {
    this.id = id;
    this.foto = foto;
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.idade = idade;
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

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public Integer getIdade() {
    return idade;
  }

  public void setIdade(Integer idade) {
    this.idade = idade;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }
}
