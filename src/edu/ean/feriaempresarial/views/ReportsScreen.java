package edu.ean.feriaempresarial.views;

import edu.ean.feriaempresarial.AppState;

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
                appState.setScreen(new VisitorsReportScreen(appState.getVisitorRegister(), appState.getVisitRegister()));
                break;
            case "3":
                appState.setScreen(new MainMenu());
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    
}
