package edu.ean.feriaempresarial;

import java.util.Scanner;

public class App {
    private static AppState state = new AppState();

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
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (App.state.isRunning()) {
            clearConsole();
            // Display the menu options
            App.state.showScreen();;
            System.out.print("Option: ");

            String userInput = scanner.nextLine();
            App.state.update(userInput);
        }
        scanner.close();
    }
}
