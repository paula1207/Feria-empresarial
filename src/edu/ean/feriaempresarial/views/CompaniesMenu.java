package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.AppState;
import edu.ean.feriaempresarial.Company;
import edu.ean.feriaempresarial.EntityRegister;
import edu.ean.feriaempresarial.Stand;
import edu.ean.feriaempresarial.StandOccupancy;

public class CompaniesMenu implements IScreen {
    private Optional<ListCompaniesScreen> listCompaniesScreen = Optional.empty();
    private Optional<ListStandsScreen> listStandsScreen = Optional.empty();

    private EntityRegister<StandOccupancy> standOccupancyRegister = new EntityRegister<>();

    private int step = 0;

    private Optional<Company> editingCompany = Optional.empty();
    private Optional<Stand> editingStand = Optional.empty();

    public CompaniesMenu(EntityRegister<StandOccupancy> standOccupancyRegister) {
        this.standOccupancyRegister = standOccupancyRegister;
    }

    private void showAssignStand() {
        System.out.println("Feria Empresarial - Asignar empresa a un stand");

        if (step < 2 && listCompaniesScreen.isPresent()) {
            listCompaniesScreen.get().show();
        } else if (step == 2 && listStandsScreen.isPresent()) {
            listStandsScreen.get().show();
        }
        
    }

    private void showMenu() {
        System.out.println("Feria Empresarial - Empresas");
        System.out.println("1. Listar empresas");
        System.out.println("2. Agregar empresa");
        System.out.println("3. Actualizar empresa");
        System.out.println("4. Eliminar empresa");
        System.out.println("5. Asignar empresa a un stand");
        System.out.println("6. Volver al menú principal");
    }

    @Override
    public void show() {
        if (listCompaniesScreen.isPresent()) {
            showAssignStand();
        } else {
            showMenu();
        }
    }

    private void assignStandToCompany() {
        if (editingCompany.isPresent() && editingStand.isPresent()) {
            try {
                standOccupancyRegister.add(new StandOccupancy(editingStand.get(), editingCompany.get()));
                System.out.println("Empresa asignada al stand");
            } catch (Exception e) {
                System.out.println("No se pudo asignar la empresa al stand");
            }
        } else {
            System.out.println("No se pudo asignar la empresa al stand");
        }
        step = 0;
        listCompaniesScreen = Optional.empty();
        listStandsScreen = Optional.empty();
        editingCompany = Optional.empty();
        editingStand = Optional.empty();
    }

    private void updateSelect(AppState appState) {
        
        switch (step) {
            case 0:
                listCompaniesScreen = Optional.of(new ListCompaniesScreen(appState.getCompanyRegister(), 
                                                    appState.getStandOccupancyRegister(), 
                                                    ListCompaniesScreenMode.SELECT_COMPANY));
                listStandsScreen = Optional.of(new ListStandsScreen(appState.getStandRegister(), 
                                                                    appState.getStandOccupancyRegister(), 
                                                                    ListStandsScreenMode.SELECT_STAND));
                
                break;
            case 1:
                if (listCompaniesScreen.isPresent()) {
                    listCompaniesScreen.get().update(appState);
                    if (listCompaniesScreen.get().getEditingCompany().isPresent()) {
                        editingCompany = listCompaniesScreen.get().getEditingCompany();
                    } else {
                        listCompaniesScreen = Optional.empty();
                        listStandsScreen = Optional.empty();
                        step = -1;
                    }
                }
                   
                break;
            case 2:
                if (listStandsScreen.isPresent()) {
                    listStandsScreen.get().update(appState);
                }
                break;
            case 3:
                if (listStandsScreen.isPresent()) {
                    listStandsScreen.get().update(appState);
                    if (listStandsScreen.get().getEditingStand().isPresent()) {
                        editingStand = listStandsScreen.get().getEditingStand();                        
                        assignStandToCompany();
                    } else {
                        listCompaniesScreen = Optional.empty();
                        listStandsScreen = Optional.empty();
                        step = -1;
                    }
                }
                break;
            
            default:
                System.out.println("Opción no válida");
                break;
        }
        step++;
    }

    @Override
    public void update(AppState appState) {
        if (listCompaniesScreen.isPresent()) {
            updateSelect(appState);
        } else {
            switch (appState.getUserInput()) {
                case "6":
                    appState.setScreen(new MainMenu());
                    break;
                case "5":
                    updateSelect(appState);
                    break;
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
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    
}
