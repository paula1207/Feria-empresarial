package edu.ean.feriaempresarial.views;

import edu.ean.feriaempresarial.model.AppState;

/**
 * Represents the stands menu screen in the Feria Empresarial application.
 * This screen provides options for listing stands, filtering by occupancy status, 
 * adding, updating, and deleting stands, as well as assigning stands to companies.
 * Users can navigate back to the main menu from this screen.
 */
public class StandsMenuScreen implements IScreen {
    public void show() {
        System.out.println("Feria Empresarial - Stands");
        System.out.println("1. Listar stands");
        System.out.println("2. Listar stands ocupados");
        System.out.println("3. Listar stands libres");
        System.out.println("4. Agregar stand");
        System.out.println("5. Actualizar stand");
        System.out.println("6. Eliminar stand");
        System.out.println("7. Asignar stand a empresa");
        System.out.println("8. Volver al menú principal");
    }

    @Override
    public void update(AppState appState) {
        switch (appState.getUserInput()) {
            case "8":
                appState.setScreen(new MainMenuScreen());
                break;
            case "1":
                appState.setScreen(new ListStandsScreen(appState.getStandRegister(), 
                                                        appState.getStandOccupancyRegister(),
                                                        ListStandsScreenMode.LIST_STANDS));
                break;
            case "2":
                appState.setScreen(new ListStandsScreen(appState.getStandRegister(), 
                                                        appState.getStandOccupancyRegister(),
                                                        ListStandsScreenMode.LIST_OCCUPIED_STANDS));
                break;
            case "3":
                appState.setScreen(new ListStandsScreen(appState.getStandRegister(), 
                                                        appState.getStandOccupancyRegister(),
                                                        ListStandsScreenMode.LIST_FREE_STANDS));
                break;
            case "4":
                appState.setScreen(new RegisterStandScreen(appState.getStandRegister()));
                break;
            case "5":
                appState.setScreen(new ListStandsScreen(appState.getStandRegister(), 
                                                        appState.getStandOccupancyRegister(),
                                                        ListStandsScreenMode.EDIT_STAND));
                break;
            case "6":
                appState.setScreen(new ListStandsScreen(appState.getStandRegister(), 
                                                        appState.getStandOccupancyRegister(),
                                                        ListStandsScreenMode.DELETE_STAND));
                break;
            case "7":
                appState.setScreen(new AssignStandToCompanyScreen(appState.getCompanyRegister(), 
                                                                    appState.getStandRegister(), 
                                                                    appState.getStandOccupancyRegister(),
                                                                    false));
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
