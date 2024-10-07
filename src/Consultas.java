import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consultas {
    public Moedas buscaMoedas(String base, String seguinte, double valor) {
        URI converte = URI.create(
                "https://v6.exchangerate-api.com/v6/92c66f645bafa2435ca46005/pair/"+
                        base + "/" + seguinte +"/"+valor);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(converte)
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moedas.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui Realizar a conversão");
        }
    }
}