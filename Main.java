import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // fazer uma conexão HTTP e buscar os top filmes;
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()); // BodyHanders of string para saber que &eacute; pra ler uma string
        String body = response.body();

        // extrair os dados que interessam (title, poster, rating) --> Parsear os dados;
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.listaDeFilmesparse(body);
        //System.out.println(listaDeFilmes.size());
        //System.out.println(listaDeFilmes.get(0));

        // exibir e manipular os dados da maneira que quisermos;
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("\u001b[1m" + "\u001b[36m" + filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            double rating = Double.parseDouble(filme.get("imDbRating")); //Convertendo a string em double
            int star = (int)rating;                                    // Convertendo o double em int
            for (int i=0; i<=star; i++) {                             // Escrevendo estrelas na classificação o "<=" é para arredondar conforme solicitado
                System.out.print("⭐");

            }
            System.out.println("\n");
        }


    }
}