package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.Company;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Stand;
import edu.ean.feriaempresarial.model.StandOccupancy;

enum ListCompaniesScreenMode {
    LIST_COMPANIES,
    DELETE_COMPANY,
    EDIT_COMPANY,
    SELECT_COMPANY
}

/**
 * Represents the screen for listing, selecting, editing, and deleting companies
 * in the Feria Empresarial application. This screen dynamically adjusts its behavior
 * based on the selected mode, allowing users to view registered companies, remove them,
 * edit their details, or select a company for further actions.
 */
public class ListCompaniesScreen implements IScreen {
    private EntityRegister<Company> companyRegister = new EntityRegister<>();
    private EntityRegister<StandOccupancy> standOccupancyRegister = new EntityRegister<>();
    private ListCompaniesScreenMode mode;

    private int step = 0;

    private Optional<RegisterCompanyScreen> registerCompanyScreen = Optional.empty();
    private Optional<Company> editingCompany = Optional.empty();

    public ListCompaniesScreen(EntityRegister<Company> companyRegister, 
                                EntityRegister<StandOccupancy> standOccupancyRegister, 
                                ListCompaniesScreenMode mode) {
        this.companyRegister = companyRegister;
        this.standOccupancyRegister = standOccupancyRegister;
        this.mode = mode;
    }

    public Optional<Company> getEditingCompany() {
        return editingCompany;
    }

    private Optional<Stand> findStandForCompany(Company company) {
        Optional<StandOccupancy> standOccupancy = standOccupancyRegister.getEntities().stream()
            .filter(stand -> stand.getCompany().equals(company))
            .findFirst();

        if (standOccupancy.isPresent()) {
            return Optional.of(standOccupancy.get().getStand());
        } else {
            return Optional.empty();
        }
    }

    private void showList() {
        if (mode == ListCompaniesScreenMode.LIST_COMPANIES) {
            System.out.println("Feria Empresarial - Listado de empresas");
        }
        
        if (companyRegister.getEntities().isEmpty()) {
            System.out.println("No hay empresas registradas");
        } else {
            for (Company company : companyRegister.getEntities()) {
                Optional<Stand> stand = findStandForCompany(company);
                String standId = stand.isPresent() ? stand.get().getId() : "Sin stand";
                System.out.println(company.getName() + " - " + company.getSector() + " - " + company.getEmail() + " - " + standId);
            }
        }
    }

    private void showDelete() {
        System.out.println("Feria Empresarial - Eliminar empresa");

        showList();

        System.out.println("Ingrese el nombre de la empresa a eliminar:");
    }

    private void showEdit() {
        switch (step) {
            case 0:
                System.out.println("Feria Empresarial - Editar empresa");
                showList();
                System.out.println("Ingrese el nombre de la empresa a editar:");
                break;
            case 1: case 2: case 3:
                if (registerCompanyScreen.isPresent()) {
                    registerCompanyScreen.get().show();
                }
                break;
        }
    }

    private void showSelect() {
        System.out.println("Feria Empresarial - Seleccionar empresa");
        showList();
        System.out.println("Ingrese el nombre de la empresa:");
    }

    public void show() {
        switch (mode) {
            case LIST_COMPANIES:
                showList();
                break;
            case DELETE_COMPANY:
                showDelete();
                break;
            case EDIT_COMPANY:
                showEdit();
                break;
            case SELECT_COMPANY:
                showSelect();
                break;
        }
    }

    private void updateList(AppState appState) {
        appState.setScreen(new CompaniesMenuScreen());
    }

    private void updateDelete(AppState appState) {
        if (step == 0) {
            String companyName = appState.getUserInput();
            Optional<Company> company = companyRegister.get(companyName);

            if (company.isEmpty()) {
                System.out.println("Empresa no encontrada");
            } else {
                companyRegister.remove(company.get());
                System.out.println("Empresa eliminada");
            }

            step++;
        } else {
            updateList(appState);
        }
    }

    private void updateSelect(AppState appState) {
        String companyName = appState.getUserInput();
        Optional<Company> company = companyRegister.get(companyName);

        if (company.isEmpty()) {
            System.out.println("Empresa no encontrada");
        } else {
            editingCompany = company;
            System.out.println("Empresa seleccionada");
        }
    }

    private void updateEdit(AppState appState) {
        if (step == 0) {
            String companyName = appState.getUserInput();
            Optional<Company> company = companyRegister.get(companyName);

            if (company.isEmpty()) {
                System.out.println("Empresa no encontrada");
            } else {
                registerCompanyScreen = Optional.of(new RegisterCompanyScreen(companyRegister, RegisterCompanyScreenMode.COMPANY_EDIT));
                editingCompany = company;
            }

            step++;
        } else if (step == 3) {
            if (registerCompanyScreen.isPresent()) {
                registerCompanyScreen.get().update(appState);
                registerCompanyScreen.get().update(appState);
                Optional<Company> company = registerCompanyScreen.get().getEditingCompany();
                if (company.isPresent()) {
                    companyRegister.edit(editingCompany.get(), company.get());
                    System.out.println("Empresa actualizada");
                }
            }
            step++;
            updateList(appState);
        } else if (step < 3) {
            if (registerCompanyScreen.isPresent()) {
                registerCompanyScreen.get().update(appState);
                step++;
            } 
        } else if (step > 3) {
            updateList(appState);
        }
    }

    public void update(AppState appState) {
        switch (mode) {
            case LIST_COMPANIES:
                updateList(appState);
                break;
            case DELETE_COMPANY:
                updateDelete(appState);
                break;
            case EDIT_COMPANY:
                updateEdit(appState);
                break;
            case SELECT_COMPANY:
                updateSelect(appState);
                break;
        }
    }
}
