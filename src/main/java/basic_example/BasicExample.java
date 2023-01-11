package basic_example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BasicExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Create new simple http client
        var client = HttpClient.newHttpClient();
        var time = System.currentTimeMillis();

        // build the request, by default GET request
        var request = HttpRequest.newBuilder(URI.create("https://api.chucknorris.io/jokes/random")).build();

        // call the send from client and pass request and get response in string
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // printing response payload
        System.out.println(response.body());
        System.out.printf("Time: %dms%n",(System.currentTimeMillis() - time));
    }
}
