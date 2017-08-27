package br.com.reclameufba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import Controller.UsuarioController;
import modelo.Usuario;

public class LoginActivity extends Activity {
    private EditText campoUsuario, campoSenha;
    private Context context;
    private UsuarioController usuarioController;
    private AlertDialog.Builder alert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        usuarioController = UsuarioController.getInstance(context);
        campoUsuario = (EditText) findViewById(R.id.login_usuario);
        campoSenha = (EditText) findViewById(R.id.login_senha);
        try {
            testaInicializacao();
        } catch (Exception e) {
            exibeDialogo("Erro inicializando banco de dados");
            e.printStackTrace();
        }
    }

    public void testaInicializacao() throws Exception {
        if (usuarioController.findAll().isEmpty()) {
            Usuario usuario = new Usuario(null, "adm", "adm");
            usuarioController.insert(usuario);
        }

    }


    public void exibeDialogo(String mensagem) {
        alert = new AlertDialog.Builder(context);
        alert.setPositiveButton("OK", null);
        alert.setMessage(mensagem);
        alert.create().show();
    }
    public void validar(View view) {
        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();
        try {
            boolean isValid = usuarioController.validacao(usuario, senha);
            if (isValid) {
                exibeDialogo("Usuario e senha validados com sucesso!");
            } else {
                exibeDialogo("Verifique usuario e senha!");
            }
        } catch (Exception e) {
            exibeDialogo("Erro validando usuario e senha");
            e.printStackTrace();
        }
    }

}