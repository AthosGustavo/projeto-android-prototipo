package projeto.piloto.projeto_off_web.ui.Fragment.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import projeto.piloto.projeto_off_web.Database.FirebaseAutenticacao;
import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Login;
import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.Sessao;
import projeto.piloto.projeto_off_web.Util.Util;
import projeto.piloto.projeto_off_web.ViewModel.ViewModel;
import projeto.piloto.projeto_off_web.databinding.FragmentProfessorLoginBinding;
import projeto.piloto.projeto_off_web.ui.Activity.TelaPrincipalActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfessorLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfessorLoginFragment extends Fragment {
  
  private FragmentProfessorLoginBinding fragmentProfessorLoginBinding;
  private FirebaseAutenticacao firebaseAutenticacaoAuth;
  private boolean cadastro = false;
  private OffWebDb offWebDb;
  private ViewModel viewModel;
  private IComunicacaoTelaPrincipal iComunicacaoTelaPrincipal;
  private Sessao sessao;
  
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public ProfessorLoginFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ProfessorLoginFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ProfessorLoginFragment newInstance(String param1, String param2) {
    ProfessorLoginFragment fragment = new ProfessorLoginFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }

    //firebaseAutenticacaoAuth = new FirebaseAutenticacao(fragmentProfessorLoginBinding);
    offWebDb = OffWebDb.getInstance(getContext());
    viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);
    sessao = Sessao.getInstance(getContext());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    fragmentProfessorLoginBinding = FragmentProfessorLoginBinding.inflate(inflater,container,false);
    clickAindaNaoTemConta();
    configuraBtnEntrarCadastrar();

    return fragmentProfessorLoginBinding.getRoot();

  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.iComunicacaoTelaPrincipal = (IComunicacaoTelaPrincipal) context;
  }

  private void configuraBtnEntrarCadastrar() {
    fragmentProfessorLoginBinding.btnEntrarCadastrarEntrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String inputConfirmarSenha = fragmentProfessorLoginBinding.inputConfirmarSenha.getText().toString();
        String inputSenha = fragmentProfessorLoginBinding.inputSenha.getText().toString();
        String inputEmail = fragmentProfessorLoginBinding.inputEmail.getText().toString();

        try{
          if (cadastro) {
            realizarCadastro(inputEmail,inputSenha,inputConfirmarSenha);
          } else {
            realizarLogin(inputEmail, inputSenha, new IProfessorLoginListener() {
              @Override
              public void onProfessorLoginSuccess() {
                Snackbar.make(fragmentProfessorLoginBinding.getRoot(), "Seja bem-vindo", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), TelaPrincipalActivity.class);
                startActivity(intent);
              }

              @Override
              public void onProfessorLoginFailure(String errorMessage) {
                Util.exibirDialogMsgErro(getContext(),"Atenção", errorMessage);
              }
            });
          }
        }catch(IllegalArgumentException ex){
          Util.exibirDialogMsgErro(getContext(),"Atenção", "Atenção, os campos de e-mail e senha devem ser preenchidos");
        }

        /*try{
          if (cadastro) {
            firebaseAutenticacaoAuth.realizarCadastro(getActivity(),inputEmail,inputSenha,inputConfirmarSenha);
          } else {
            firebaseAutenticacaoAuth.realizarLogin(getActivity(),inputEmail,inputSenha);
          }
        }catch(IllegalArgumentException ex){
          Snackbar.make(fragmentProfessorLoginBinding.getRoot(), "Atenção, os campos de e-mail e senha devem ser preenchidos", Snackbar.LENGTH_LONG).show();
        }*/

      }
    });
  }

  private void realizarLogin(String email, String senha, IProfessorLoginListener iProfessorLoginListener){

      viewModel.getExecutorService().execute(() -> {
        Login loginEncontrado = offWebDb.loginDao().realizarLogin(email, senha);

        requireActivity().runOnUiThread(() -> {

          if (Objects.nonNull(loginEncontrado)) {

            Professor professor = Objects.nonNull(loginEncontrado.getProfessor()) ? offWebDb.professorDao().buscarPorId(loginEncontrado.getProfessor()) : null;
            sessao.setProfessorLogado(professor);
            sessao.setLogin(loginEncontrado);
            iProfessorLoginListener.onProfessorLoginSuccess();
          } else {
            iProfessorLoginListener.onProfessorLoginFailure("E-mail ou senha incorretos");
          }
        });
      });



  }

  private void realizarCadastro(String email, String senha, String senhaRepetida) {
    viewModel.getExecutorService().execute(() -> {
      try{
        if(senha.equals(senhaRepetida)){
          viewModel.getExecutorService().execute(() -> {
            Login verificaContaExistente = offWebDb.loginDao().contaExiste(email);

            if(Objects.nonNull(verificaContaExistente)){
              requireActivity().runOnUiThread(() -> {
                Util.exibirDialogMsgErro(getContext(),"Atenção", "E-mail já cadastrado");
              });
            }else{
              offWebDb.loginDao().inserir(new Login(email,senha));

              requireActivity().runOnUiThread(() -> {
                fragmentProfessorLoginBinding.inputConfirmarSenha.setVisibility(View.GONE);
                fragmentProfessorLoginBinding.btnEntrarCadastrarEntrar.setText("Entrar");
                fragmentProfessorLoginBinding.textoMudaLoginCadastro.setText("Ainda não tem uma conta ?");
                cadastro = false;
                Snackbar.make(fragmentProfessorLoginBinding.getRoot(), "Conta criada com sucesso.", Snackbar.LENGTH_LONG).show();
              });

            }

          });




        }else{
          requireActivity().runOnUiThread(() -> {
            Util.exibirDialogMsgErro(getContext(),"Atenção", "As senhas não conferem");
          });
        }
      }catch (Exception e){
        requireActivity().runOnUiThread(() -> {
          Util.exibirDialogMsgErro(getContext(),"Atenção", "Erro inesperado");
        });
      }
    });
  }

  private void clickAindaNaoTemConta() {
    fragmentProfessorLoginBinding.textoMudaLoginCadastro.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        cadastro = !cadastro;
        if (cadastro) {
          fragmentProfessorLoginBinding.inputConfirmarSenha.setVisibility(View.VISIBLE);
          fragmentProfessorLoginBinding.btnEntrarCadastrarEntrar.setText("Cadastrar");
          fragmentProfessorLoginBinding.textoMudaLoginCadastro.setText("Já tem uma conta ?");
        } else {
          fragmentProfessorLoginBinding.inputConfirmarSenha.setVisibility(View.GONE);
          fragmentProfessorLoginBinding.btnEntrarCadastrarEntrar.setText("Entrar");
          fragmentProfessorLoginBinding.textoMudaLoginCadastro.setText("Ainda não tem uma conta ?");
        }
      }
    });
  }

  public interface IProfessorLoginListener {
    void onProfessorLoginSuccess();
    void onProfessorLoginFailure(String errorMessage);
  }

  public interface IComunicacaoTelaPrincipal{
    void chamaTelaLoginProfessor();
  }
}