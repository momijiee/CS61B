package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author momiji
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet"); // root dir
    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");
    public static final File HEAD_DIR = join(GITLET_DIR, "HEAD");

    /* TODO: fill in the rest of this class. */

    /** initialize the repository
     * assuming that a repo has been initialized when .gitlet is created,
     * and the directory inside are all created correctly (and never changed manually)
     */
    public static void init() {
        if (GITLET_DIR.exists()) {
            throw new RuntimeException("A Gitlet version-control system already exists in the current directory.");
        } else {
            initDIR();
            saveGitletObject(Commit.initCommit());
            initHEAD();
            StagingArea.initStagingArea();
        }
    }

    /** Add a file to staging area
     * raise an Error when the file does not exist
     */
    public static void add(String filename) {
        File f = join(CWD, filename);
        if (!f.exists()) {
            throw new RuntimeException("File does not exist.");
        }

        /* save the file into objects */
        String content = Utils.readContentsAsString(f);
        Blob blob = new Blob(content);
        saveGitletObject(blob); // do nothing if exact same object exist

        /* add the file to stagingArea */
        StagingArea.addFile(filename, blob);
    }

    public static void commit(String message) {

        HashMap<String, String> map = StagingArea.makeCommit();

        Commit newCommit = new Commit(message, getHeadCommit(), map);

        saveGitletObject(newCommit);
        updateHEAD(newCommit);
    }

    public static void log() {
        Commit headCommit = getHeadCommit();

        while (headCommit != null) {
            headCommit.print();
            headCommit = getParentCommit(headCommit);
        }
    }

    public static void checkoutFile(String filename) {
        checkout(getHeadCommit(), filename);
    }

    public static void checkoutFile(String commitHash, String filename) {
        Commit commit;
        if (commitHash.length() == 40) {
            commit = readCommit(commitHash);
        } else {
            // TODO: support abbreviate ids
            throw new RuntimeException("invalid commit id");
        }

        if (commit == null) {
            throw new RuntimeException("No commit with that id exists.");
        }

        checkout(commit, filename);
    }

    private static void checkout(Commit commit, String filename) {
        if (!commit.getMap().containsKey(filename)) {
            throw new RuntimeException("File does not exist in that commit.");
        }

        Blob b = readBlob(commit.getMap().get(filename));
        if (b == null) {
            throw new RuntimeException("File not found");
        }

        File f = join(CWD, filename);
        writeContents(f, b.getContent());
    }

    private static void initDIR() {
        GITLET_DIR.mkdir();
        OBJECTS_DIR.mkdir();
    }

    private static void initHEAD() {
        updateHEAD(Commit.initCommit());
    }

    private static void updateHEAD(Commit commit) {
        writeContents(HEAD_DIR, commit.toSHA1());
    }

    private static void saveGitletObject(GitletObject object) {
        String sha = object.toSHA1();
        String prefix = sha.substring(0, 2);
        String rest = sha.substring(2);

        if (!join(OBJECTS_DIR, prefix).exists()) {
            join(OBJECTS_DIR, prefix).mkdir();
        }

        File f = join(OBJECTS_DIR, prefix, rest);

        if (f.exists()) {
            return;
        }

        writeObject(f, (Serializable) object);
    }

    private static Commit readCommit(String sha) {
        File f = shaToFile(sha);

        if (f == null) {
            return null;
        }

        return readObject(f, Commit.class);
    }

    private static Blob readBlob(String sha) {
        File f = shaToFile(sha);

        if (f == null) {
            return null;
        }

        return readObject(f, Blob.class);
    }

    private static File shaToFile(String sha) {
        String prefix = sha.substring(0, 2);
        String rest = sha.substring(2);

        if (!join(OBJECTS_DIR, prefix).exists()) {
            return null;
        }

        File f = join(OBJECTS_DIR, prefix, rest);

        if (!f.exists()) {
            return null;
        }

        return f;
    }

    public static Commit getHeadCommit() {
        String head = readContentsAsString(HEAD_DIR);
        return readCommit(head);
    }

    private static Commit getParentCommit(Commit commit) {
        String parentHash = commit.getParent();

        if (parentHash == null) {
            return null;
        }
        return readCommit(parentHash);
    }
}
