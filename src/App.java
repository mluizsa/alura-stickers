import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
         /**
         * Acessar a conexão via http e buscar os top 250 filmes
         */

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=y1ksA0Gxl4fhcVn4oKU7IFvwaQAY15CHkBbroZ3D&start_date=2022-06-12&end_date=2022-06-14";
        
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);
        /**
         * Extrair só os dados que interessam (titulo, poster e classificação)
        */
        var parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);
        /** 
         * exibir e manipular os dados
         */
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String,String> filme : listaDeFilmes) {
            
            String urlImagem = 
                    //filme.get("image");
                    filme.get("url");
            String titulo    = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";
            
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo); 
            System.out.println();          
        }


    }
}

