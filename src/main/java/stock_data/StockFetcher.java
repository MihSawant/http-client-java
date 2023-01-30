package stock_data;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StockFetcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        var time = System.currentTimeMillis();
        var writer = Files.newBufferedWriter(Path.of("response_data.txt"));

        HttpRequest request = HttpRequest.newBuilder().uri(
                URI.create("https://margincalculator.angelbroking.com/OpenAPI_File/files/OpenAPIScripMaster.json"))
                .build();

        var client = HttpClient.newHttpClient();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        response.lines().forEach(line -> {
            try{
                writer.write(line);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        });
        System.out.printf("Time Taken: %dms", System.currentTimeMillis() - time);






    }
}
