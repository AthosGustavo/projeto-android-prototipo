package projeto.piloto.projeto_off_web.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import projeto.piloto.projeto_off_web.Model.Entidade.Professor;

@Dao
public interface ProfessorDao {

  @Query("SELECT * FROM professores WHERE id = :id")
  Professor buscarPorId(int id);

  @Insert
  Long inserir(Professor professor);

  @Update
  void atualizar(Professor professor);
}
