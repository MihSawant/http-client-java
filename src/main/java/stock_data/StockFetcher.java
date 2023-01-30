package stock_data;


import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StockFetcher {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        var time = System.currentTimeMillis();
        var writer = Files.newBufferedWriter(Path.of("response_data.txt"));

        HttpRequest request = HttpRequest.newBuilder().uri(
                URI.create("https://margincalculator.angelbroking.com/OpenAPI_File/files/OpenAPIScripMaster.json"))
                .build();

        var client = HttpClient.newHttpClient();

         client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).
                get()
                .body()
                .lines().forEach(line -> writeFile(writer, line));

        System.out.printf("Time Taken: %dms", System.currentTimeMillis() - time);






    }

    private static void writeFile(BufferedWriter writer, String line) {
        try{
            writer.write(line);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
