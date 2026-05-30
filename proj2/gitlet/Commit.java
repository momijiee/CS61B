package gitlet;

import java.io.Serializable;
import java.util.*;

/** Represents a gitlet commit object.
 *  It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author momiji
 */
public class Commit implements Serializable, GitletObject {
    /**
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private final String message;

    /* Created time */
    private final Date timestamp;

    private final String parent;

    private String secondParent = null;

    private HashMap<String, String> filenameToBlobMap; // <String> is the SHA-1 of A Blob

    public HashMap<String, String> getMap() {
        return filenameToBlobMap;
    }

    /** Create a Commit with new Date() */
    public Commit(String message, Commit parent, HashMap<String, String> files) {
        this.timestamp = new Date();
        this.message = message;
        this.parent = Commit.toSHA1(parent);
        filenameToBlobMap = files;
    }

    /* constructor for initCommit */
    public Commit(String message, Commit parent, Date date) {
        this.timestamp = date;
        this.message = message;
        this.filenameToBlobMap = new HashMap<>();
        this.parent = (parent == null) ? null : Commit.toSHA1(parent);
    }

    public static Commit initCommit() {
        return new Commit("initial commit", null, new Date(0));
    }

    /** hash function */
    public static String toSHA1(Commit commit) {
        StringBuilder sb = new StringBuilder();

        sb.append(commit.message);

        sb.append(commit.timestamp);

        if (commit.parent != null) {
            sb.append(commit.parent);
        } else {
            sb.append("null");
        }

        if (commit.secondParent != null) {
            sb.append(commit.secondParent);
        } else {
            sb.append("null");
        }

        if (!commit.filenameToBlobMap.isEmpty()) {
            List<String> list = new ArrayList<>(commit.filenameToBlobMap.keySet());
            Collections.sort(list);
            for (String blobKey : list) {
                sb.append(blobKey);
                sb.append(commit.filenameToBlobMap.get(blobKey));
            }
        } else {
            sb.append("null");
        }

        return Utils.sha1(sb.toString());
    }

    public String toSHA1() {
        return Commit.toSHA1(this);
    }

    public String getParent() {
        return parent;
    }

    public void print() {
        System.out.println("===");
        System.out.println("commit " + this.toSHA1());
        System.out.println("Date: " + this.timestamp);
        System.out.println(message);
    }
}
