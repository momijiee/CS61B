package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

import static gitlet.Utils.*;

public class StagingArea implements Serializable {

    private static final File STAGING_AREA_DIR = join(Repository.GITLET_DIR, "INDEX");

    public HashMap<String, String> stagedToAdd;

    public static void addFile(String filename, Blob blob) {
        StagingArea s = StagingArea.getStagingArea();

        Commit head = Repository.getHeadCommit();

        /* add file identical to current commit */
        if (head.getMap().get(filename) != null && head.getMap().get(filename).equals(blob.toSHA1())) {

            /* remove the map if exist */
            s.stagedToAdd.remove(filename);

        } else {

            /* add map */
            s.stagedToAdd.put(filename, blob.toSHA1());

        }
        StagingArea.saveStagingArea(s);

    }

    public static HashMap<String, String> makeCommit() {
        StagingArea s = StagingArea.getStagingArea();

        Commit headCommit = Repository.getHeadCommit();

        /* get a copy of file map */
        HashMap<String, String> map = new HashMap<>(headCommit.getMap());

        for (String file : s.stagedToAdd.keySet()) {
            map.put(file, s.stagedToAdd.get(file));
        }

        s.cleanArea();
        StagingArea.saveStagingArea(s);

        return map;
    }

    private StagingArea() {
        stagedToAdd = new HashMap<>();
    }

    public static void initStagingArea() {
        StagingArea s = new StagingArea();
        saveStagingArea(s);
    }

    public static void saveStagingArea(StagingArea area) {
        writeObject(STAGING_AREA_DIR, area);
    }

    public static StagingArea getStagingArea() {
        return Utils.readObject(STAGING_AREA_DIR, StagingArea.class);
    }

    public void cleanArea() {
        stagedToAdd.clear();
    }
}
