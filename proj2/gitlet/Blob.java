package gitlet;

import java.io.Serializable;

public class Blob implements Serializable, GitletObject {

    private String content;

    public Blob(String content) {
        this.content = content;
    }

    public static String toSHA1(Blob blob) {
        return Utils.sha1(blob.content);
    }

    public String toSHA1() {
        return Blob.toSHA1(this);
    }
}
