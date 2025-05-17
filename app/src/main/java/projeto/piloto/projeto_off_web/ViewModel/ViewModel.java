package projeto.piloto.projeto_off_web.ViewModel;

import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.Model.Entidade.Turma;

public class ViewModel extends androidx.lifecycle.ViewModel {

  private final ExecutorService executorService = Executors.newCachedThreadPool();
  private final MutableLiveData<Professor> professor = new MutableLiveData<>();
  private Turma turma;

  public ExecutorService getExecutorService(){
    return executorService;
  }

  public Professor professor(){
    return professor.getValue();
  }

  public void setProfessor(Professor professor){
    this.professor.setValue(professor);
  }

  public Turma getTurma(){
    return this.turma;
  }
  public void setTurma(Turma turma){
    this.turma = turma;
  }
}
