package projeto.piloto.projeto_off_web.Model.Relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import projeto.piloto.projeto_off_web.Model.Entidade.Material;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;

public class MaterialTurma {

  @Embedded
  private Material material;
  @Relation(
    parentColumn = "id",
    entityColumn = "turma"
  )
  private Turma turma;
}
