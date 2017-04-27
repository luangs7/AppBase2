package br.com.luan.clubeprime.model;

/**
 * Created by Dev_Maker on 30/01/2017.
 */
public class Evento {
    private String title;
    private String descricao;
    private String foto;
    private String link;
    private Integer resource;

    public Evento() {
    }

    public Evento(String title, String descricao, Integer resource) {
        this.title = title;
        this.resource = resource;
        this.descricao = descricao;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
