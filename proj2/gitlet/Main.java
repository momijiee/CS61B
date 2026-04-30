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
                // TODO: handle the `init` command

                break;
            }
            case "add":
                // TODO: handle the `add [filename]` command
                break;
            case "commit":
                // handle `commit [message]` command
                validateNumArgs(args, 2);
                String message = args[1];

                break;
            case "checkout":

                break;
            case "log":

                break;
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
