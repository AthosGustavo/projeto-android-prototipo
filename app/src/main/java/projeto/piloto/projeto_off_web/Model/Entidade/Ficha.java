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
  public Ficha(Float horasUsoInternet,String descricao, Integer aluno) {
    this.horasUsoInternetDia = horasUsoInternet;
    this.aluno = aluno;
    this.descricao = descricao;
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
}
