import com.google.gson.annotations.SerializedName;

public class MediaFile {
    @SerializedName("n")
    private String name;

    @SerializedName("mod")
    private long lastModified;

    @SerializedName("ls")
    private long unknown;

    @SerializedName("s")
    private long size;

    public String getName() {
        return name;
    }

    public long getLastModified() {
        return lastModified;
    }

    public long getSize() {
        return size;
    }
}
