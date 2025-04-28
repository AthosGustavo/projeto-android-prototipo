package projeto.piloto.projeto_off_web.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Turma;

@Dao
public interface TurmaDao {

  @Insert
  void inserir(Turma turma);

  @Query("SELECT * FROM turmas")
  List<Turma> buscarTurmas();
}
