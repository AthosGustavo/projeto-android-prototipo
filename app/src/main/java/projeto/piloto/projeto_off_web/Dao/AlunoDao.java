package projeto.piloto.projeto_off_web.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;

@Dao
public interface AlunoDao {

  @Query("SELECT * FROM alunos")
  List<Aluno> buscarAlunos();
}
