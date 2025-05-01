package projeto.piloto.projeto_off_web.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.Objects;

import projeto.piloto.projeto_off_web.Dao.AlunoDao;
import projeto.piloto.projeto_off_web.Dao.FichaDao;
import projeto.piloto.projeto_off_web.Dao.LoginDao;
import projeto.piloto.projeto_off_web.Dao.MaterialDao;
import projeto.piloto.projeto_off_web.Dao.ProfessorDao;
import projeto.piloto.projeto_off_web.Dao.TurmaAlunoDao;
import projeto.piloto.projeto_off_web.Dao.TurmaDao;
import projeto.piloto.projeto_off_web.Model.Entidade.Aluno;
import projeto.piloto.projeto_off_web.Model.Entidade.Ficha;
import projeto.piloto.projeto_off_web.Model.Entidade.Login;
import projeto.piloto.projeto_off_web.Model.Entidade.Material;
import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;
import projeto.piloto.projeto_off_web.Model.Entidade.TurmaAluno;

@Database(entities = {Aluno.class, Ficha.class, Professor.class, Turma.class, Material.class,TurmaAluno.class, Login.class}, version = 4)
public abstract class OffWebDb extends RoomDatabase {



  public abstract FichaDao fichaDao();
  public abstract AlunoDao alunoDao();
  public abstract ProfessorDao professorDao();
  public abstract MaterialDao MaterialDao();
  public abstract TurmaDao turmaDao();
  public abstract TurmaAlunoDao turmaAlunoDao();
  public abstract LoginDao loginDao();

  private static OffWebDb OffWebDb;


    public static OffWebDb getInstance(Context context) {
      synchronized (OffWebDb.class) {
        if (Objects.isNull(OffWebDb)) {
          OffWebDb = Room.databaseBuilder(
                  context.getApplicationContext(),
                  OffWebDb.class, "offWeb.db").build();

        }
      }
      return OffWebDb;
    }
}

