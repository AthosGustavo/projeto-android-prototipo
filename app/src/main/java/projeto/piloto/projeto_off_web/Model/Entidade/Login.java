package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class Login {

  @PrimaryKey
  private Integer id;
  private String email;
  private String senha;
  private Integer professor;

  public Login(String email, String senha) {
    this.email = email;
    this.senha = senha;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Integer getProfessor() {
    return professor;
  }

  public void setProfessor(Integer professor) {
    this.professor = professor;
  }
}
