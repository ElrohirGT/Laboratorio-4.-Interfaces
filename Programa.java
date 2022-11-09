import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * Programa
 */
public class Programa {
    static final String ANSI_RESET = "\u001B[0m";

    static final String ANSI_BLACK = "\u001B[30m";

    static final String ANSI_RED = "\u001B[31m";

    static final String ANSI_GREEN = "\u001B[32m";

    static final String ANSI_YELLOW = "\u001B[33m";

    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";
    static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    static final int DIVIDER_LENGTH = 32;
    static final int DIVIDER_HALF_LENGTH = DIVIDER_LENGTH / 2;
    static final String DIVIDER = new String(new char[DIVIDER_LENGTH]).replace("\0", "=");
    static final String SUB_DIVIDER = new String(new char[DIVIDER_LENGTH]).replace("\0", "-");

    public static void main(String[] args) {

    }

    public static void animation(Object prefix, Iterable<Object> frames, int msDuration, int framesPerSecond) {
        var start = LocalTime.now();
        consoleWriteLine(prefix.toString());
        while (true) {
            for (Object frame : frames) {
                consoleWrite("\033[1A");// Move Up
                consoleWrite("\033[2K");// Clear line
                consoleWriteLine(prefix.toString() + frame);
                try {
                    Thread.sleep(1000 / framesPerSecond);
                } catch (Exception e) {
                    consoleWriteLine(e.toString(), ANSI_RED);
                }
                if ((LocalTime.now().getSecond() - start.getSecond()) * 1000 > msDuration) {
                    return;
                }
            }
        }
    }

    public static <T> void showInTable(Iterable<T> list,
            Function<T, LinkedHashMap<String, String>> convertToMap) {

        for (T itemT : list) {
            LinkedHashMap<String, String> rows = convertToMap.apply(itemT);
            for (String rowName : rows.keySet()) {
                consoleWrite(rowName + "\t", ANSI_CYAN);
                consoleWriteLine(rows.get(rowName));
            }
            consoleWriteLine(SUB_DIVIDER);
        }
    }

    public static void menuItem(Object identifier, Object option, String color, boolean isActive) {
        if (isActive) {
            printSpaceSeparated(identifier, option, color);
        }
    }

    public static void menuItem(Object identifier, Object option, String color) {
        printSpaceSeparated(identifier, option, color);
    }

    public static String formLabel(Object label, String color) {
        consoleWrite(label.toString() + ": ");
        return waitForInputColored(color);
    }

    public static String formLabel(Object label, String color, Function<String, Boolean> check) {
        String input = "";
        do {
            input = formLabel(label, color);
        } while (!check.apply(input));

        return input;
    }

    public static <T> T formLabel(Object label, String color, Function<String, Boolean> check,
            Function<String, T> conv) {
        String input = formLabel(label, color, check);
        return conv.apply(input);
    }

    public static String waitForInputColored(String color) {
        System.out.print(color);
        String s = System.console().readLine();
        System.out.print(ANSI_RESET);
        return s;
    }

    public static void printSpaceSeparated(Object identifier, Object option, String color) {
        consoleWrite(identifier + " ", color);
        consoleWriteLine(option.toString());
    }

    public static void printSpaceSeparatedFor(Object identifier, Object option, String color) {
        consoleWrite(identifier + " ");
        consoleWriteLine(option.toString(), color);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void consoleWriteLine(String message) {
        consoleWriteLine(message, "", "");
    }

    public static void consoleWriteLine(String message, String foreground) {
        consoleWriteLine(message, foreground, "");
    }

    public static void consoleWriteLine(String message, String foreground, String background) {
        System.out.println(background + foreground + message + ANSI_RESET);
    }

    public static void consoleWrite(Object message) {
        consoleWrite(message, "", "");
    }

    public static void consoleWrite(Object message, String foreground) {
        consoleWrite(message, foreground, "");
    }

    public static void consoleWrite(Object message, String foreground, String background) {
        System.out.print(background + foreground + message + ANSI_RESET);
    }
}