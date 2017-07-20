package br.com.reclameufba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import Controller.PostController;
import DAO.PostDAO;
import modelo.Post;

public class InserePostActivity extends AppCompatActivity {
    private static final int COD_CAMERA= 678;
    private PostController postController;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_post);
        Button botaoSalvar = (Button) findViewById(R.id.menu_formulario_salvar);
        Button botaoCamera= (Button) findViewById(R.id.formulario_button_Camera);

        //recupera informações para edição
        Intent intent = getIntent();
        postController = new PostController(this);
        Post post = (Post) intent.getSerializableExtra("post");
        if (post != null) {
            postController.descrevePost(post);
        }
        //Função da foto
        botaoCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentCamera= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto= getExternalFilesDir(null)+ "/"+ System.currentTimeMillis() +".jpg";
                File arquivoFoto = new File(caminhoFoto);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(InserePostActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        arquivoFoto));
                startActivityForResult(intentCamera, COD_CAMERA);
            }

        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_salvar:
                Post post = postController.recebePost();

                PostDAO dao = new PostDAO(this);
                if (post.getId() == null) {
                    dao.insere(post);
                } else {
                    dao.altera(post);
                }

                dao.close();

                Toast.makeText(InserePostActivity.this, "Post " + post.getTitulo() + " salvo!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK && requestCode == COD_CAMERA){
            postController.carregaImagem(caminhoFoto);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}