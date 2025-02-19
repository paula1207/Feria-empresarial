package edu.ean.feriaempresarial.views;

import edu.ean.feriaempresarial.model.AppState;

/**
 * Represents the companies menu screen in the Feria Empresarial application.
 * This screen provides options for listing, adding, updating, and deleting companies,
 * as well as assigning companies to stands and viewing company reviews.
 * It handles user input to transition to the corresponding screens.
 */
public class CompaniesMenuScreen implements IScreen {
    
    public CompaniesMenuScreen() {
        
    }

    @Override
    public void show() {        
        System.out.println("Feria Empresarial - Empresas");
        System.out.println("1. Listar empresas");
        System.out.println("2. Agregar empresa");
        System.out.println("3. Actualizar empresa");
        System.out.println("4. Eliminar empresa");
        System.out.println("5. Asignar empresa a un stand");
        System.out.println("6. Ver comentarios y calificaciones de una empresa");
        System.out.println("7. Volver al menú principal");
    }

    @Override
    public void update(AppState appState) {
        switch (appState.getUserInput()) {
            case "1":
                appState.setScreen(new ListCompaniesScreen(appState.getCompanyRegister(), 
                                        appState.getStandOccupancyRegister(), 
                                        ListCompaniesScreenMode.LIST_COMPANIES));
                break;
            case "2":
                appState.setScreen(new RegisterCompanyScreen(appState.getCompanyRegister()));
                break;
            case "3":
                appState.setScreen(new ListCompaniesScreen(appState.getCompanyRegister(), 
                                    appState.getStandOccupancyRegister(), 
                                    ListCompaniesScreenMode.EDIT_COMPANY));
                break;
            case "4":
                appState.setScreen(new ListCompaniesScreen(appState.getCompanyRegister(), 
                                    appState.getStandOccupancyRegister(), 
                                    ListCompaniesScreenMode.DELETE_COMPANY));
                break;
            case "5":
                appState.setScreen(new AssignStandToCompanyScreen(appState.getCompanyRegister(), 
                                                                    appState.getStandRegister(), 
                                                                    appState.getStandOccupancyRegister(), 
                                                                    true));
                break;
            case "6":
                appState.setScreen(new ReviewsScreen(appState.getVisitRegister(), 
                                                        appState.getCompanyRegister(), 
                                                        appState.getStandOccupancyRegister()));
                break;
            case "7":
                appState.setScreen(new MainMenuScreen());
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    
}
