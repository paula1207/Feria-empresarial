package edu.ean.feriaempresarial.views;

import edu.ean.feriaempresarial.AppState;

public class VisitorsMenu implements IScreen {
    public void show() {
        System.out.println("Feria Empresarial - Visitantes");
        System.out.println("1. Listar visitantes");
        System.out.println("2. Agregar visitante");
        System.out.println("3. Actualizar visitante");
        System.out.println("4. Eliminar visitante");
        System.out.println("5. Visitar stand");
        System.out.println("6. Volver al menú principal");
    }

    @Override
    public void update(AppState appState) {
        switch (appState.getUserInput()) {
            case "6":
                appState.setScreen(new MainMenu());
                break;
            case "1":
                appState.setScreen(new ListVisitorsScreen(appState.getVisitorRegister(), ListVisitorsScreenMode.LIST_VISITORS));
                break;
            case "2":
                appState.setScreen(new RegisterVisitorScreen(appState.getVisitorRegister()));
                break;
            case "3":
                appState.setScreen(new ListVisitorsScreen(appState.getVisitorRegister(), ListVisitorsScreenMode.EDIT_VISITOR));
                break;
            case "4":
                appState.setScreen(new ListVisitorsScreen(appState.getVisitorRegister(), ListVisitorsScreenMode.DELETE_VISITOR));
                break;
            case "5":
                appState.setScreen(new VisitStandScreen(appState.getVisitorRegister(), 
                                                        appState.getStandOccupancyRegister(), 
                                                        appState.getStandRegister(),
                                                        appState.getVisitRegister()));
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
