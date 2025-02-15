package edu.ean.feriaempresarial.views;

import edu.ean.feriaempresarial.AppState;

public class MainMenu implements IScreen {

    @Override
    public void show() {
        System.out.println("Feria Empresarial - Main Menu");
        System.out.println("1. Empresas");
        System.out.println("2. Stands");
        System.out.println("3. Visitantes");
        System.out.println("4. Reportes");
        System.out.println("5. Cargar datos de prueba");
        System.out.println("6. Salir");
    }

    @Override
    public void update(AppState appState) {
        System.out.println("Recibida la opción: " + appState.getUserInput());
        // compare the string appState.getUserInput() to the strings "5"

        switch (appState.getUserInput()) {
            case "1":
                appState.setScreen(new CompaniesMenu(appState.getStandOccupancyRegister()));
                break;
            case "2":
                appState.setScreen(new StandsMenu());
                break;
            case "3":
                appState.setScreen(new VisitorsMenu());
                break;
            case "4":
                appState.setScreen(new ReportsScreen());
                break;
            case "5":
                appState.loadTestData();
                break;
            case "6":
                System.out.println("Saliendo de la aplicación");
                appState.setRunning(false);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
