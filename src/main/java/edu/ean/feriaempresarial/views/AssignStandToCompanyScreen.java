package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.Company;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Stand;
import edu.ean.feriaempresarial.model.StandOccupancy;

public class AssignStandToCompanyScreen implements IScreen {
    private ListCompaniesScreen listCompaniesScreen;
    private ListStandsScreen listStandsScreen;
    private EntityRegister<StandOccupancy> standOccupancyRegister = new EntityRegister<>();
    private int step = 0;
    private Optional<Company> editingCompany = Optional.empty();
    private Optional<Stand> editingStand = Optional.empty();
    
    private Boolean returnToCompaniesMenu = false;

    public AssignStandToCompanyScreen(EntityRegister<Company> companyRegister, 
                                        EntityRegister<Stand> standRegister,
                                        EntityRegister<StandOccupancy> standOccupancyRegister,
                                        Boolean returnToCompaniesMenu) {
        this.standOccupancyRegister = standOccupancyRegister;
        listCompaniesScreen = new ListCompaniesScreen(companyRegister, 
                                                        standOccupancyRegister, 
                                                        ListCompaniesScreenMode.SELECT_COMPANY);
        listStandsScreen = new ListStandsScreen(standRegister, 
                                                standOccupancyRegister, 
                                                ListStandsScreenMode.SELECT_FREE_STAND);
        this.returnToCompaniesMenu = returnToCompaniesMenu;
    }

    @Override
    public void show() {
        System.out.println("Feria Empresarial - Asignar empresa a un stand");
        if (step < 1) {
            listCompaniesScreen.show();
        } else if (step == 1) {
            listStandsScreen.show();
        } else {
            System.out.println("Stand asignado a empresa");
        }
    }

    @Override
    public void update(AppState appState) {
        
        switch (step) {
            case 0:
                
                listCompaniesScreen.update(appState);
                if (listCompaniesScreen.getEditingCompany().isPresent()) {
                    editingCompany = listCompaniesScreen.getEditingCompany();
                }   
                break;
            case 1:
                listStandsScreen.update(appState);
                break;
            case 2:
                listStandsScreen.update(appState);
                if (listStandsScreen.getEditingStand().isPresent()) {
                    editingStand = listStandsScreen.getEditingStand();                        
                    assignStandToCompany();
                    if (returnToCompaniesMenu) {
                        appState.setScreen(new CompaniesMenuScreen());
                    } else {
                        appState.setScreen(new StandsMenuScreen());
                    }
                } else {
                    step = -1;
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
        step++;
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
        editingCompany = Optional.empty();
        editingStand = Optional.empty();
    }
}
