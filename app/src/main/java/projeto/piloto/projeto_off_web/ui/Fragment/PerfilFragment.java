package projeto.piloto.projeto_off_web.ui.Fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.Objects;

import projeto.piloto.projeto_off_web.Database.OffWebDb;
import projeto.piloto.projeto_off_web.Model.Entidade.Professor;
import projeto.piloto.projeto_off_web.R;
import projeto.piloto.projeto_off_web.Sessao;

public class PerfilFragment extends Fragment {

  private ImageView imgPerfil;
  private EditText edtNome, edtSobrenome, edtIdade, edtDisciplina;
  private Button btnSalvar;
  private String caminhoFoto;
  private static final int REQUEST_CAMERA = 1;
  private Sessao sessao;
  private Professor professor;
  private OffWebDb offWebDb;
  private static final int REQUEST_CAMERA_PERMISSION = 100;
  private static final int REQUEST_IMAGE_CAPTURE = 101;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_perfil, container, false);

    imgPerfil = view.findViewById(R.id.imgPerfil);
    edtNome = view.findViewById(R.id.edtNome);
    edtSobrenome = view.findViewById(R.id.edtSobrenome);
    edtIdade = view.findViewById(R.id.edtIdade);
    edtDisciplina = view.findViewById(R.id.edtDisciplina);
    btnSalvar = view.findViewById(R.id.btnSalvar);


    offWebDb = OffWebDb.getInstance(getContext());
    btnAbrirCamera();
    btnSalvar.setOnClickListener(v -> salvarDados());
    sessao = Sessao.getInstance(getContext());
    professor = sessao.getProfessorLogado();
    carregarDadosProfessor();
    return view;
  }



  public void btnAbrirCamera(){
    imgPerfil.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
          ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
          // Se a permissão não foi concedida em um momento anterior, a linha de cima chamará a função onRequestPermissionsResult
          // de forma implícita para requisitar a permissão e se a permissão for autorizada, o próprio método vai abrir a câmera.
        }else{
          abrirCamera();
        }
      }
    });
  }

  private void abrirCamera() {

      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
        File fotoFile = criarArquivoFoto();
        if (fotoFile != null) {
          Uri fotoURI = FileProvider.getUriForFile(
                  requireContext(),
                  requireContext().getPackageName() + ".fileprovider",
                  fotoFile
          );
          intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoURI);
          startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
      }

  }

  private File criarArquivoFoto() {
    String nomeArquivo = "perfil_" + System.currentTimeMillis();
    File diretorio = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File foto = new File(diretorio, nomeArquivo + ".jpg");
    caminhoFoto = foto.getAbsolutePath();
    return foto;
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == REQUEST_CAMERA_PERMISSION) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        abrirCamera(); // Se a permissão foi concedida, abre a câmera
      }
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      Glide.with(this).load(caminhoFoto).into(imgPerfil); // Carrega a imagem de alta qualidade no ImageView
    }
  }




  private void carregarDadosProfessor() {

    if(Objects.nonNull(professor)){
      edtNome.setText(professor.getNome());
      edtSobrenome.setText(professor.getSobrenome());
      edtIdade.setText(professor.getIdade() != null ? professor.getIdade().toString() : "");
      edtDisciplina.setText(professor.getDisciplina());
      if (professor.getFoto() != null) {
        Glide.with(this).load(professor.getFoto()).into(imgPerfil);
      }
    }

  }

  private void salvarDados() {
    if(Objects.nonNull(professor)){
      professor.setNome(edtNome.getText().toString());
      professor.setSobrenome(edtSobrenome.getText().toString());
      professor.setIdade(Integer.valueOf(edtIdade.getText().toString()));
      professor.setDisciplina(edtDisciplina.getText().toString());
      professor.setFoto(caminhoFoto);

      new Thread(() -> {
        offWebDb.professorDao().atualizar(professor);
      });
    }else{
      Professor professor = new Professor();
      professor.setNome(edtNome.getText().toString());
      professor.setSobrenome(edtSobrenome.getText().toString());
      professor.setIdade(Integer.valueOf(edtIdade.getText().toString()));
      professor.setDisciplina(edtDisciplina.getText().toString());
      professor.setFoto(caminhoFoto);

      new Thread(() -> {
        Integer id = offWebDb.professorDao().inserir(professor);
        sessao.getLogin().setProfessor(id);
        offWebDb.loginDao().atualizar(sessao.getLogin());
      });
    }

  }

}