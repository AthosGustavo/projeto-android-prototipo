package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "materiais")
public class Material {

  @PrimaryKey
  private Integer id;
  private Integer turma;
  private String camingoArquivo;
  private String caminhoImagem;
  private String caminhoVideo;

  public Material(Integer id, Integer turma, String camingoArquivo, String caminhoImagem, String caminhoVideo) {
    this.id = id;
    this.turma = turma;
    this.camingoArquivo = camingoArquivo;
    this.caminhoImagem = caminhoImagem;
    this.caminhoVideo = caminhoVideo;
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

  public String getCamingoArquivo() {
    return camingoArquivo;
  }

  public void setCamingoArquivo(String camingoArquivo) {
    this.camingoArquivo = camingoArquivo;
  }

  public String getCaminhoImagem() {
    return caminhoImagem;
  }

  public void setCaminhoImagem(String caminhoImagem) {
    this.caminhoImagem = caminhoImagem;
  }

  public String getCaminhoVideo() {
    return caminhoVideo;
  }

  public void setCaminhoVideo(String caminhoVideo) {
    this.caminhoVideo = caminhoVideo;
  }
}
