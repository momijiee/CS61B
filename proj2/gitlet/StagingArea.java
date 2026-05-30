package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

import static gitlet.Utils.*;

public class StagingArea implements Serializable {

    private static final File STAGING_AREA_DIR = join(Repository.GITLET_DIR, "INDEX");

    public HashMap<String, String> stagedToAdd;

    public HashSet<String> stagedToDelete;

    private StagingArea() {
        stagedToAdd = new HashMap<>();
        stagedToDelete = new HashSet<>();
    }

    public static void initStagingArea() {
        writeObject(STAGING_AREA_DIR, new StagingArea());
    }

    public static StagingArea getStagingArea() {
        return Utils.readObject(STAGING_AREA_DIR, StagingArea.class);
    }

    public void addFile(String filename, Blob blob) {
        stagedToAdd.put(filename, blob.toSHA1());
    }

    public void removeFile(String filename) {
        stagedToDelete.add(filename);
    }
}
