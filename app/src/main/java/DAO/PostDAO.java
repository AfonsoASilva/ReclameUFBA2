package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Controller.PostController;
import modelo.Post;

public class PostDAO  extends SQLiteOpenHelper {
    public PostDAO(Context context) {
        super(context, "Posts", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Posts (id INTEGER PRIMARY KEY, " +
                "id_usuario INTEGER, " +
                "titulo TEXT NOT NULL, " +
                "descricao TEXT, " +
                 "like INTEGER, " +
                "deslike INTEGER, " +
                "localizacao TEXT"+
                "dataHora TIMESTAMP, " +
                "bytesFoto BLOB); ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Post";
        db.execSQL(sql);
        onCreate(db);
    }
    public void insere(Post post) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("titulo", post.getTitulo());
        dados.put("descricao", post.getDescricao());
        dados.put("like", post.getLike());
        dados.put("deslike", post.getDeslike());
        dados.put("dataHora", String.valueOf(post.getDataHora()));
        dados.put("localizacao", "UFBA");
        dados.put("bytesFoto", post.getPhotoBytes());

        db.insert("Posts", null, dados);
    }
    public List<Post> buscaposts() {
        String sql = "SELECT * FROM Posts;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Post> posts = new ArrayList<Post>();
        while (c.moveToNext()) {
            Post post = new Post();

            post.setId(c.getLong(c.getColumnIndex("id")));
            post.setTitulo(c.getString(c.getColumnIndex("titulo")));
            post.setDescricao(c.getString(c.getColumnIndex("descriacao")));
            post.setLike(c.getInt(c.getColumnIndex("like")));
            post.setDeslike(c.getInt(c.getColumnIndex("deslike")));
            post.setLocalizacao(c.getString(c.getColumnIndex("localizacao")));
            post.setDataHora(Timestamp.valueOf(c.getString(c.getColumnIndex("dataHora")))); // Verificar como extrair a hora
            post.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            posts.add(post);
        }
        c.close();
        return posts;
    }
    public void deleta (Post post){
        SQLiteDatabase db = getWritableDatabase();

        String[] params={
                post.getId().toString()
        };

        db.delete("Posts", "id= ?",params);

    }
    public void altera(Post post){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("titulo", post.getTitulo());
        dados.put("descricao", post.getDescricao());
        dados.put("like", post.getLike());
        dados.put("deslike", post.getDeslike());
        dados.put("localizacao", post.getLocalizacao());
        dados.put("bytesFoto", post.getPhotoBytes());;

        String[] params={
                post.getId().toString()
        };

        db.update("Posts", dados,"id = ?", params);
    }
}
