package projeto.piloto.projeto_off_web.Model.Relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import projeto.piloto.projeto_off_web.Model.Entidade.Login;
import projeto.piloto.projeto_off_web.Model.Entidade.Professor;

public class LoginProfessorRelation {

  @Embedded
  private Professor professor;
  @Relation(
          parentColumn = "id",
          entityColumn = "professor"
  )
  private Login login;
}
