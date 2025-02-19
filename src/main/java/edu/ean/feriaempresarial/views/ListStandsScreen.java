package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.Company;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Stand;
import edu.ean.feriaempresarial.model.StandOccupancy;

enum ListStandsScreenMode {
    LIST_STANDS,
    LIST_FREE_STANDS,
    LIST_OCCUPIED_STANDS,
    DELETE_STAND,
    EDIT_STAND,
    SELECT_STAND,
    SELECT_OCCUPIED_STAND,
    SELECT_FREE_STAND
}

/**
 * Represents the screen for listing, selecting, editing, and deleting stands 
 * in the Feria Empresarial application. This screen dynamically adjusts its behavior
 * based on the selected mode, allowing users to view all stands, filter by occupancy status, 
 * remove stands, edit stand details, or select a stand for further actions.
 */
public class ListStandsScreen implements IScreen {
    private EntityRegister<Stand> standRegister = new EntityRegister<>();
    private EntityRegister<StandOccupancy> standOccupancyRegister = new EntityRegister<>();

    private ListStandsScreenMode mode;
    private int step = 0;

    private Optional<RegisterStandScreen> registerStandScreen = Optional.empty();
    private Optional<Stand> editingStand = Optional.empty();

    public ListStandsScreen(EntityRegister<Stand> standRegister, 
                            EntityRegister<StandOccupancy> standOccupancyRegister,
                            ListStandsScreenMode mode) {
        this.standRegister = standRegister;
        this.standOccupancyRegister = standOccupancyRegister;
        this.mode = mode;
    }

    public Optional<Stand> getEditingStand() {
        return editingStand;
    }

    private Optional<Company> findCompanyForStand(Stand stand) {
        Optional<StandOccupancy> standOccupancy = standOccupancyRegister.getEntities().stream()
            .filter(occupancy -> occupancy.getStand().equals(stand))
            .findFirst();

        if (standOccupancy.isPresent()) {
            return Optional.of(standOccupancy.get().getCompany());
        } else {
            return Optional.empty();
        }
    }

    private void showList() {
        if (mode == ListStandsScreenMode.LIST_STANDS) {
            System.out.println("Feria Empresarial - Listado de stands");
        }
        
        if (standRegister.getEntities().isEmpty()) {
            System.out.println("No hay stands registrados");
        } else {
            for (Stand stand : standRegister.getEntities()) {
                Optional<Company> company = findCompanyForStand(stand);
                String companyId = company.isPresent() ? company.get().getId() : "Sin empresa";
                System.out.println(stand.getId() + " - " + stand.getLocation() + " - " + stand.getSize() + " - " + companyId);
            }
        }
    }

    private void showFreeStands() {
        System.out.println("Feria Empresarial - Listado de stands libres");

        for (Stand stand : standRegister.getEntities()) {
            if (!standOccupancyRegister.getEntities().stream().anyMatch(occupancy -> occupancy.getStand().equals(stand))) {
                System.out.println(stand.getId() + " - " + stand.getLocation() + " - " + stand.getSize());
            }
        }
    }

    private void showOccupiedStands() {
        System.out.println("Feria Empresarial - Listado de stands ocupados");

        for (Stand stand : standRegister.getEntities()) {
            if (standOccupancyRegister.getEntities().stream().anyMatch(occupancy -> occupancy.getStand().equals(stand))) {
                System.out.println(stand.getId() + " - " + stand.getLocation() + " - " + stand.getSize());
            }
        }
    }

    private void showDelete() {
        System.out.println("Feria Empresarial - Eliminar stand");

        showList();

        System.out.println("Ingrese el id del stand a eliminar:");
    }

    private void showEdit() {
        switch (step) {
            case 0:
                System.out.println("Feria Empresarial - Editar stand");
                showList();
                System.out.println("Ingrese el id del stand a editar:");
                break;
            case 1: case 2: case 3:
                if (registerStandScreen.isPresent()) {
                    registerStandScreen.get().show();
                }
                break;
        }
    }

    private void showSelect(ListStandsScreenMode mode) {
        System.out.println("Feria Empresarial - Seleccionar stand");

        switch (mode) {
            case SELECT_OCCUPIED_STAND:
                showOccupiedStands();
                break;
            case SELECT_FREE_STAND:
                showFreeStands();
                break;
            default:
                showList();
                break;
        }

        System.out.println("Ingrese el id del stand:");
    }

    private void updateSelect(AppState appState) {
        String standId = appState.getUserInput();
        Optional<Stand> stand = standRegister.get(standId);
        if (stand.isPresent()) {
            editingStand = stand;
        } else {
            System.out.println("Stand no encontrado");
        }
    }

    @Override
    public void show() {
        switch (mode) {
            case LIST_STANDS:
                showList();
                break;
            case LIST_FREE_STANDS:
                showFreeStands();
                break;
            case LIST_OCCUPIED_STANDS:
                showOccupiedStands();
                break;
            case DELETE_STAND:
                showDelete();
                break;
            case EDIT_STAND:
                showEdit();
                break;
            case SELECT_STAND: case SELECT_OCCUPIED_STAND: case SELECT_FREE_STAND:
                showSelect(mode);
                break;
        }
    }

    private void updateList(AppState appState) {
        appState.setScreen(new StandsMenuScreen());
    }

    private void updateDelete(AppState appState) {
        if (step == 0) {
            String standId = appState.getUserInput();
            Optional<Stand> stand = standRegister.get(standId);
            if (stand.isPresent()) {
                standRegister.remove(stand.get());
                System.out.println("Stand eliminado");
            } else {
                System.out.println("Stand no encontrado");
            }
            step++;
        } else {
            updateList(appState);
        }
    }

    private void updateEdit(AppState appState) {
        if (step == 0) {
            String standId = appState.getUserInput();
            Optional<Stand> stand = standRegister.get(standId);
            if (stand.isPresent()) {
                editingStand = stand;
                registerStandScreen = Optional.of(new RegisterStandScreen(standRegister, RegisterStandScreenMode.STAND_EDIT));
            } else {
                System.out.println("Stand no encontrado");
            }
            step++;
        } else if (step == 3) {
            if (registerStandScreen.isPresent()) {
                registerStandScreen.get().update(appState);
                registerStandScreen.get().update(appState);
                Optional<Stand> stand = registerStandScreen.get().getEditingStand();
                if (stand.isPresent()) {
                    standRegister.edit(editingStand.get(), stand.get());
                    System.out.println("Stand actualizado");
                }
            }
            step++;
            updateList(appState);
        } else if (step < 3) {
            if (registerStandScreen.isPresent()) {
                registerStandScreen.get().update(appState);
                step++;
            } 
        } else if (step > 3) {
            updateList(appState);
        }
    }

    @Override
    public void update(AppState appState) {
        switch (mode) {
            case LIST_STANDS:
                updateList(appState);
                break;
            case LIST_FREE_STANDS:
                updateList(appState);
                break;
            case LIST_OCCUPIED_STANDS:
                updateList(appState);
                break;
            case DELETE_STAND:
                updateDelete(appState);
                break;
            case EDIT_STAND:
                updateEdit(appState);
                break;
            case SELECT_STAND: case SELECT_FREE_STAND: case SELECT_OCCUPIED_STAND:
                updateSelect(appState);
                break;
        }
    }
    
}
