package filmesdb.usjt.br.filmesdb.model;

import java.util.ArrayList;

public class GeneroDAO {
    private static ArrayList<Genero> generos;
    public GeneroDAO() {}

    public static ArrayList<Genero> getGenero() {
        if (generos == null) {
            generos = new ArrayList<>();

            generos.add(new Genero(37, "Western"));
            generos.add(new Genero(10752, "Suspense"));
            generos.add(new Genero(10770, "Filme para TV"));
            generos.add(new Genero(878, "Ficção Científica"));
            generos.add(new Genero(10749, "Romance"));
            generos.add(new Genero(9648, "Mistério"));
            generos.add(new Genero(10402, "Musical"));
            generos.add(new Genero(27, "Horror"));
            generos.add(new Genero(36, "História"));
            generos.add(new Genero(14, "Fantasia"));
            generos.add(new Genero(10751, "Família"));
            generos.add(new Genero(18, "Drama"));
            generos.add(new Genero(99, "Documentário"));
            generos.add(new Genero(80, "Crime"));
            generos.add(new Genero(35, "Comédia"));
            generos.add(new Genero(16, "Animação"));
            generos.add(new Genero(12, "Aventura"));
            generos.add(new Genero(28, "Ação"));

        }

        return generos;
    }

    public static Genero buscaGenero(int idGenero) {
        Genero generoFilme = new Genero();
        for(Genero genero: generos) {
            if(genero.getId() == idGenero) {
               generoFilme.setId(genero.getId());
               generoFilme.setNome(genero.getNome());
            }
        }

        return generoFilme;
    }
}
