package edu.ean.feriaempresarial.views;

import edu.ean.feriaempresarial.model.AppState;

/**
 * Represents the reports menu screen in the Feria Empresarial application.
 * This screen provides options for generating reports on companies and their assigned stands,
 * as well as visitors and the stands they have visited. Users can navigate back to the main menu.
 */
public class ReportsScreen implements IScreen {
    @Override
    public void show() {
        System.out.println("Feria Empresarial - Reportes");
        System.out.println("1. Reporte de empresas y stands");
        System.out.println("2. Reporte de visitantes y stands visitados");
        System.out.println("3. Volver al menú principal");
    }

    @Override
    public void update(AppState appState) {
        switch (appState.getUserInput()) {
            case "1":
                appState.setScreen(new CompaniesReportScreen(appState.getCompanyRegister(), appState.getStandOccupancyRegister()));
                break;
            case "2":
                appState.setScreen(new VisitorsReportScreen(appState.getVisitorRegister(), 
                                                            appState.getStandOccupancyRegister(), 
                                                            appState.getVisitRegister()));
                break;
            case "3":
                appState.setScreen(new MainMenuScreen());
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    
}
