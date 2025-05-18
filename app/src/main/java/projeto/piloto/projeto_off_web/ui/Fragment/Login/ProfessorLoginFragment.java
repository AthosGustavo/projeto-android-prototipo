package projeto.piloto.projeto_off_web.ui.Fragment.Login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import projeto.piloto.projeto_off_web.Database.FirebaseAutenticacao;
import projeto.piloto.projeto_off_web.databinding.FragmentProfessorLoginBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfessorLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfessorLoginFragment extends Fragment {
  
  private FragmentProfessorLoginBinding fragmentProfessorLoginBinding;
  private FirebaseAutenticacao firebaseAutenticacaoAuth;
  private boolean cadastro = false;
  
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

    firebaseAutenticacaoAuth = new FirebaseAutenticacao(fragmentProfessorLoginBinding);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    fragmentProfessorLoginBinding = FragmentProfessorLoginBinding.inflate(inflater,container,false);
    clickAindaNaoTemConta();
    configuraBtnEntrarCadastrar();

    return fragmentProfessorLoginBinding.getRoot();

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
            firebaseAutenticacaoAuth.realizarCadastro(getActivity(),inputEmail,inputSenha,inputConfirmarSenha);
          } else {
            firebaseAutenticacaoAuth.realizarLogin(getActivity(),inputEmail,inputSenha);
          }
        }catch(IllegalArgumentException ex){
          Snackbar.make(fragmentProfessorLoginBinding.getRoot(), "Atenção, os campos de e-mail e senha devem ser preenchidos", Snackbar.LENGTH_LONG).show();
        }

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
}