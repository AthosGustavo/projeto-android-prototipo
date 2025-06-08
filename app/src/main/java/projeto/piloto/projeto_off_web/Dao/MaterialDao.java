package projeto.piloto.projeto_off_web.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import projeto.piloto.projeto_off_web.Model.Entidade.Material;

@Dao
public interface MaterialDao {

  @Insert
  void inserir(Material material);

  @Query("SELECT * FROM materiais WHERE turma = :turma")
  List<Material> buscarMaterialPorTurma(Integer turma);
}
