package projeto.piloto.projeto_off_web.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import projeto.piloto.projeto_off_web.Model.Entidade.Login;

@Dao
public interface LoginDao {

  @Query("SELECT * FROM login WHERE email = :email AND senha = :senha")
  Login realizarLogin(String email, String senha);

  @Insert
  void inserir(Login login);

  @Query("SELECT * FROM login WHERE email = :email")
  Login contaExiste(String email);

  @Update
  void atualizar(Login login);
}
