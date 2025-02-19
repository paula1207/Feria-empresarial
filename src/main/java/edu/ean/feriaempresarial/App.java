package edu.ean.feriaempresarial;

import java.util.Scanner;
import edu.ean.feriaempresarial.model.AppState;

/**
 * The main entry point for the Feria Empresarial application.
 * This class manages the application loop, processing user input and updating the application state.
 */
public class App {
    
    /** The global application state, managing screens and entities. */
    private static AppState state = new AppState();

    /**
     * Clears the console screen to improve readability of the application's user interface.
     * Supports both Windows and Unix-based operating systems.
     */
    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
            } else {
                new ProcessBuilder("clear")
                    .inheritIO()
                    .start()
                    .waitFor();
            }
        } catch (final Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * The main method that starts the application.
     * It runs an interactive loop, continuously displaying the current screen
     * and processing user input until the application is terminated.
     *
     * @param args Command-line arguments (not used).
     * @throws Exception If an unexpected error occurs during execution.
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (App.state.isRunning()) {
            clearConsole();
            // Display the menu options
            App.state.showScreen();
            System.out.print("Option: ");

            String userInput = scanner.nextLine();
            App.state.update(userInput);
        }
        scanner.close();
    }
}
