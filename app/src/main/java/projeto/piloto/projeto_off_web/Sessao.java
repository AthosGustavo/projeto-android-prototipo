package projeto.piloto.projeto_off_web;

import android.app.Application;
import android.content.Context;

import projeto.piloto.projeto_off_web.Model.Entidade.Login;
import projeto.piloto.projeto_off_web.Model.Entidade.Professor;

public class Sessao extends Application {

  private Professor professorLogado;
  private Login login;

  public static Sessao getInstance(Context context) {
    return (Sessao) context.getApplicationContext();
  }

  public Professor getProfessorLogado() {
    return professorLogado;
  }

  public void setProfessorLogado(Professor professorLogado) {
    this.professorLogado = professorLogado;
  }

  public Login getLogin() {
    return login;
  }

  public void setLogin(Login login) {
    this.login = login;
  }
}
