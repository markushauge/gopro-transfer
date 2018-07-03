import com.google.gson.annotations.SerializedName;

public class Media {
    @SerializedName("d")
    private String directory;

    @SerializedName("fs")
    private MediaFile[] files;

    public String getDirectory() {
        return directory;
    }

    public MediaFile[] getFiles() {
        return files;
    }
}
