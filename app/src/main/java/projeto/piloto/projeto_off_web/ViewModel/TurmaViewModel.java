package projeto.piloto.projeto_off_web.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import projeto.piloto.projeto_off_web.Model.Entidade.Professor;

public class TurmaViewModel extends ViewModel {

  private final MutableLiveData<ExecutorService> executorService = new MutableLiveData<>();
  private final MutableLiveData<Professor> professor = new MutableLiveData<>();

  public ExecutorService getExecutorService(){
    return Executors.newCachedThreadPool();
  }

  public Professor professor(){
    return professor.getValue();
  }

  public void setProfessor(Professor professor){
    this.professor.setValue(professor);
  }
}
