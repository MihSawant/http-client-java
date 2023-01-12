package basic_example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class BasicExampleBuilder {

    private static final String URL = "https://api.exchangerate.host/latest";
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        var time = System.currentTimeMillis();
        String response = HttpClient.newHttpClient()
                .send(
                        HttpRequest.newBuilder().uri(URI.create(URL)).build(),
                        HttpResponse.BodyHandlers.ofString()
                ).body();

        System.out.println("From sync call : "+response);
        System.out.printf("Time taken by synchronous call :  %dms%n", System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        String responseAysnc = HttpClient.newHttpClient()
                .sendAsync(
                        HttpRequest.newBuilder().uri(URI.create(URL)).build(),
                        HttpResponse.BodyHandlers.ofString()
                ).get().body();
        System.out.println("From async call: "+response);
        System.out.printf("Time taken by synchronous call :  %dms%n", System.currentTimeMillis() - time);
    }
}
