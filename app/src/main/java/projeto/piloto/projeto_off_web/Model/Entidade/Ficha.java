package projeto.piloto.projeto_off_web.Model.Entidade;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "fichas")
public class Ficha {

  @PrimaryKey
  private Integer id;
  private Integer aluno;
  private Double horasUsoInternetDia;
  private String descricao;

  public Ficha(Integer id, Integer aluno, Double horasUsoInternetDia, String descricao) {
    this.id = id;
    this.aluno = aluno;
    this.horasUsoInternetDia = horasUsoInternetDia;
    this.descricao = descricao;
  }

  @Ignore
  public Ficha() {

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

  public Double getHorasUsoInternetDia() {
    return horasUsoInternetDia;
  }

  public void setHorasUsoInternetDia(Double horasUsoInternetDia) {
    this.horasUsoInternetDia = horasUsoInternetDia;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
}
