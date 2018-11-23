package filmesdb.usjt.br.filmesdb.model;

import java.io.Serializable;

public class Filme implements Serializable {
    private int id;
    private String titulo, genero, sinopse, diretor, dataLancamento, poster;
    private double popularidade;

    public Filme() {}

    public Filme(int id, String titulo, String genero, String sinopse, String diretor, String dataLancamento, double popularidade, String poster) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopse = sinopse;
        this.diretor = diretor;
        this.dataLancamento = dataLancamento;
        this.popularidade = popularidade;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(double popularidade) {
        this.popularidade = popularidade;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", diretor='" + diretor + '\'' +
                ", dataLancamento='" + dataLancamento + '\'' +
                ", poster='" + poster + '\'' +
                ", popularidade=" + popularidade +
                '}';
    }
}
