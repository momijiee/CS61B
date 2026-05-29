package gitlet;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author momiji
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private final String message;

    /* TODO: fill in the rest of this class. */
    private final Date timestamp;

    private List<Blob> files;

    private final Commit parent;

    private Commit secondParent;

    public Commit(String message, Commit parent) {
        timestamp = new Date();
        this.message = message;
        this.parent = parent;
    }

    public Commit(String message, Commit parent, Date date) {
        this.timestamp = date;
        this.message = message;
        this.parent = parent;
    }

    public static Commit initCommit() {
        return new Commit("initial commit", null, new Date(0));
    }

    public String toSHA1() {
        String SHAString;
        if (parent == null) {
            SHAString = message + timestamp.toString();
        } else {
            SHAString = message + timestamp.toString() + parent.toString();
        }
        return gitlet.Utils.sha1(SHAString);
    }
}
