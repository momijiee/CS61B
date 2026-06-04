package gitlet;

import org.junit.Assert;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author momiji
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // Handle empty args
        if (args.length == 0) {
            printThenExit("Please enter a command.");
        }

        // Handle the first arg
        String firstArg = args[0];
        switch (firstArg) {
            case "init": {
                // Handle the `init` command
                validateNumArgs(args, 1);

                Repository.init();

                break;
            }
            case "add": {
                // handle the `add [filename]` command
                validateNumArgs(args, 2);

                String filename = args[1];

                Repository.add(filename);

                break;
            }
            case "commit": {
                // handle `commit [message]` command
                validateNumArgs(args, 2);

                String message = args[1];

                Repository.commit(message);

                break;
            }
            case "checkout":
                if (args.length == 3 && args[1].equals("--")) {
                    String filename = args[2];
                    Repository.checkoutFile(filename);
                } else if (args.length == 4 && args[2].equals("--")) {
                    String commitHash = args[1];
                    String filename = args[3];
                    Repository.checkoutFile(commitHash, filename);
                }
                break;
            case "log": {
                validateNumArgs(args, 1);

                Repository.log();

                break;
            }
            default:
                printThenExit("No command with that name exists.");
        }
    }

    private static void validateNumArgs(String[] args, int n) {
        if (args.length != n) {
            printThenExit("Incorrect operands.");
        }
    }

    private static void printThenExit(String message) {
        System.out.println(message);
        System.exit(0);
    }
}
