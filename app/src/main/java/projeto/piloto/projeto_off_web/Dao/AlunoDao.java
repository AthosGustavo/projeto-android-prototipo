package projeto.piloto.projeto_off_web.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.Model.Relation.AlunoFichaRelation;

@Dao
public interface AlunoDao {

  @Query("SELECT * FROM alunos")
  List<Aluno> buscarAlunos();

  @Insert
  Long inserir(Aluno aluno);

  @Transaction
  @Query("SELECT * FROM alunos WHERE id = :alunoId")
  LiveData<AlunoFichaRelation> buscarFichaPorAluno(Integer alunoId);

}
