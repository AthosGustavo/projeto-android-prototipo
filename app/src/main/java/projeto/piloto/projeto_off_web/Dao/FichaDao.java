package projeto.piloto.projeto_off_web.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import projeto.piloto.projeto_off_web.Model.Entidade.Ficha;

@Dao
public interface FichaDao {

  @Query("SELECT * FROM fichas WHERE aluno = :aluno")
  LiveData<Ficha> buscarFichaPorAluno(Integer aluno);

  @Insert
  void inserir(Ficha ficha);

}
