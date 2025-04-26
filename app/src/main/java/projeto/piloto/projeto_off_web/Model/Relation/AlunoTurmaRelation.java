package projeto.piloto.projeto_off_web.Model.Relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;

public class AlunoTurmaRelation {

  @Embedded
  private Aluno aluno;
  @Relation(
    parentColumn = "id",
    entityColumn = "aluno"
  )
  private List<Turma> turmas;
}
