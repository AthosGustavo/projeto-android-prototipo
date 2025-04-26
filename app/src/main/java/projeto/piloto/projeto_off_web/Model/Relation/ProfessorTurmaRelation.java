package projeto.piloto.projeto_off_web.Model.Relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;

public class ProfessorTurmaRelation {

  @Embedded
  private Professor professor;
  @Relation(
    parentColumn = "id",
    entityColumn = "professor"
  )

  private Turma turma;
}
