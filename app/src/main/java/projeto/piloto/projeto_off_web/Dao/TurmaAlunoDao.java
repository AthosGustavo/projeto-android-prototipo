package projeto.piloto.projeto_off_web.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.Model.Entidade.TurmaAluno;

@Dao
public interface TurmaAlunoDao {

  @Query("SELECT al.* FROM alunos AS al " +
          "INNER JOIN turmasAlunos AS tl ON al.id = tl.aluno")
  List<Aluno> buscarAlunos();

  @Insert
  void inserir(TurmaAluno turmaAluno);


}
