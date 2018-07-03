import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GoProService {
    @GET("/gp/gpMediaList")
    Call<MediaList> getMediaList();

    @GET("/videos/DCIM/{directory}/{name}")
    Call<ResponseBody> getFile(
        @Path("directory") String directory,
        @Path("name") String name
    );
}
