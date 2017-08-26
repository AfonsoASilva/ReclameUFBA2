package modelo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {

    private Long id;
    private Long id_usuario;
    private String descricao;
    private String titulo;
    private int like;
    private int deslike;
    private Timestamp dataHora;
    private String caminhoFoto;
    private String localizacao;
    private byte[] photoBytes;

    public String getLocalizacao() {
        return localizacao;
    }

    public byte[] getPhotoBytes() {
        return photoBytes;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }


    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDeslike() {
        return deslike;
    }

    public void setDeslike(int deslike) {
        this.deslike = deslike;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
    @Override
    public String toString(){
        return this.getId()+"-"+this.getTitulo();
    }

    public void setPhotoBytes(byte[] photoBytes) {
        this.photoBytes = photoBytes;
    }
}
