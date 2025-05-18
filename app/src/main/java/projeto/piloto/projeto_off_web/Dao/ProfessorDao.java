package projeto.piloto.projeto_off_web.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import projeto.piloto.projeto_off_web.Model.Entidade.Professor;

@Dao
public interface ProfessorDao {

  @Query("SELECT * FROM professores WHERE id = :id")
  Professor buscarPorId(int id);
}
