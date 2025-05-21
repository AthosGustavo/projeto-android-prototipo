package projeto.piloto.projeto_off_web.Util;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
public class Util {

  public static void exibirDialogMsg(Context context, String titulo, String mensagem) {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
    alertDialogBuilder.setCancelable(false);
    alertDialogBuilder.setMessage(mensagem);
    alertDialogBuilder.setTitle(titulo);
    alertDialogBuilder.setPositiveButton("OK",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface arg0, int arg1) {
              }
            });
    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }



}
