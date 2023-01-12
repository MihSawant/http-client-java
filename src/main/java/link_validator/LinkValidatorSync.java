package link_validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class LinkValidatorSync {

    private static void validateUrl(String url) throws IOException, InterruptedException{
        int code = HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder(URI.create(url)).build(),
                        HttpResponse.BodyHandlers.discarding())
                .statusCode();
        System.out.printf("%s is %s: code -> %d%n",url, code == 200 ? "Valid Link" : "Invalid Link", code);
    }
    public static void main(String[] args) {

        try(
                var br = Files.newBufferedReader(Path.of("urls.txt"))
        ){
            br.lines()
                    .forEach(url -> {
                        try{
                            validateUrl(url);
                        }catch(IOException | InterruptedException e){
                            System.out.println(e.getMessage());
                        }
                    });
        }catch(IOException e){
            System.out.println(e.getMessage());
        }


    }
}
