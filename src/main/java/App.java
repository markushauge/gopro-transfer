import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App {
    public static void main(String[] args) throws Exception {
        Path directory = Paths.get("./files");

        GoProService goPro = new Retrofit.Builder()
            .baseUrl("http://10.5.5.9:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoProService.class);

        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }

        MediaList mediaList = goPro
            .getMediaList()
            .execute()
            .body();

        for (Media media : mediaList.getMedia()) {
            for (MediaFile file : media.getFiles()) {
                Path target = directory.resolve(file.getName());
                long size = file.getSize();

                if (!Files.exists(target) && size < Constants.GB) {
                    System.out.printf("Downloading %s%n", file.getName());

                    ResponseBody responseBody = goPro
                        .getFile(media.getDirectory(), file.getName())
                        .execute()
                        .body();

                    try (InputStream input = responseBody.byteStream()) {
                        Files.copy(input, target);
                    }

                    responseBody.close();
                }
            }
        }
    }
}
