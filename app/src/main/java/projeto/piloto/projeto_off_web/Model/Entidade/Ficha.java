package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "fichas")
public class Ficha {

  @PrimaryKey
  private Integer id;
  private Integer aluno;
  private Float horasUsoInternetDia;
  private String descricao;
  private String respostaPerguntaUm;
  private String respostaPerguntaDois;
  private String respostaPerguntaTres;
  private String respostaPerguntaQuatro;
  private String respostaPerguntaCinco;

  public Ficha(Integer id, Integer aluno, Float horasUsoInternetDia, String descricao) {
    this.id = id;
    this.aluno = aluno;
    this.horasUsoInternetDia = horasUsoInternetDia;
    this.descricao = descricao;
  }

  @Ignore
  public Ficha() {

  }

  @Ignore
  public Ficha(Float horasUsoInternet,String descricao, Integer aluno,String respostaPerguntaUm,String respostaPerguntaDois,String respostaPerguntaTres,String respostaPerguntaQuatro,String respostaPerguntaCinco) {
    this.horasUsoInternetDia = horasUsoInternet;
    this.aluno = aluno;
    this.descricao = descricao;
    this.respostaPerguntaUm = respostaPerguntaUm;
    this.respostaPerguntaDois = respostaPerguntaDois;
    this.respostaPerguntaTres = respostaPerguntaTres;
    this.respostaPerguntaQuatro = respostaPerguntaQuatro;
    this.respostaPerguntaCinco = respostaPerguntaCinco;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAluno() {
    return aluno;
  }

  public void setAluno(Integer aluno) {
    this.aluno = aluno;
  }

  public Float getHorasUsoInternetDia() {
    return horasUsoInternetDia;
  }

  public void setHorasUsoInternetDia(Float horasUsoInternetDia) {
    this.horasUsoInternetDia = horasUsoInternetDia;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getRespostaPerguntaUm() {
    return respostaPerguntaUm;
  }

  public void setRespostaPerguntaUm(String respostaPerguntaUm) {
    this.respostaPerguntaUm = respostaPerguntaUm;
  }

  public String getRespostaPerguntaDois() {
    return respostaPerguntaDois;
  }

  public void setRespostaPerguntaDois(String respostaPerguntaDois) {
    this.respostaPerguntaDois = respostaPerguntaDois;
  }

  public String getRespostaPerguntaTres() {
    return respostaPerguntaTres;
  }

  public void setRespostaPerguntaTres(String respostaPerguntaTres) {
    this.respostaPerguntaTres = respostaPerguntaTres;
  }

  public String getRespostaPerguntaQuatro() {
    return respostaPerguntaQuatro;
  }

  public void setRespostaPerguntaQuatro(String respostaPerguntaQuatro) {
    this.respostaPerguntaQuatro = respostaPerguntaQuatro;
  }

  public String getRespostaPerguntaCinco() {
    return respostaPerguntaCinco;
  }

  public void setRespostaPerguntaCinco(String respostaPerguntaCinco) {
    this.respostaPerguntaCinco = respostaPerguntaCinco;
  }
}
