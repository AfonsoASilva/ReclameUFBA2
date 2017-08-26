package Controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import br.com.reclameufba.InserePostActivity;
import br.com.reclameufba.R;
import modelo.Post;


public class PostController extends Activity {
    private Post post;
    private EditText titulo;
    private EditText descricao;
    private EditText localizacao;
    private final ImageView foto;

    public ImageView getFoto() {
        return foto;
    }

    private TextView like;
    private TextView deslike;

    public PostController(InserePostActivity activity) {

        post= new Post();
        titulo= (EditText) activity.findViewById(R.id.formulario_titulo);
        descricao= (EditText) activity.findViewById(R.id.formulario_descricao);
        foto= (ImageView) activity.findViewById(R.id.formulario_foto);
        localizacao= (EditText) activity.findViewById(R.id.formulario_localizacao);
    }
    public Post recebePost(){
        post.setTitulo(titulo.getText().toString());
        post.setDescricao(descricao.getText().toString());
        post.setCaminhoFoto((String)foto.getTag());
        post.setLocalizacao(localizacao.getText().toString());
        byte[] bytes = getBytes(foto.getDrawingCache());
        post.setPhotoBytes(bytes);
        return post;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public void descrevePost(Post post) {

        this.post = post;
        titulo.setText(post.getTitulo());
        descricao.setText(post.getDescricao());
        localizacao.setText(post.getLocalizacao());
        like.setText(post.getLike());
        deslike.setText(post.getDeslike());
        carregaImagem(post.getCaminhoFoto());
   }
   public void somaLike(Post post){
       this.post= post;
       int cont= post.getLike() + 1;
       post.setLike(cont);
   }
    public void carregaImagem(String caminhoFoto){
        if(caminhoFoto != null){
            Bitmap bitmap= BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido= Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            foto.setImageBitmap(bitmapReduzido);
            foto.setScaleType(ImageView.ScaleType.FIT_XY);
            foto.setTag(caminhoFoto);
        }
    }
}
