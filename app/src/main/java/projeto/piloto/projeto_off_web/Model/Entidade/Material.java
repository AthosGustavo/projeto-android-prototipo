package projeto.piloto.projeto_off_web.Model.Entidade;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "materiais")
public class Material {

  @PrimaryKey
  private Integer id;
  private String nome;
  private Integer turma;
  private String uri;


  public Material(String nome,Integer turma, String uri) {
    this.nome = nome;
    this.turma = turma;
    this.uri = uri.toString();

  }

  @Ignore
  public Material() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTurma() {
    return turma;
  }

  public void setTurma(Integer turma) {
    this.turma = turma;
  }

  public String getUri() {
    return uri.toString();
  }

  public void setUri(Uri uri) {
    this.uri = uri.toString();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}
