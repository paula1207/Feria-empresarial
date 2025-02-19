package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.EntityAlreadyExistsException;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Stand;

enum RegisterStandScreenMode {
    STAND_NEW,
    STAND_EDIT
}

/**
 * Represents the screen for registering or editing stand information 
 * in the Feria Empresarial application. This screen guides users through 
 * entering stand details such as ID, location, and size. Depending on 
 * the mode, it either creates a new stand or updates an existing one.
 */
public class RegisterStandScreen implements IScreen {
    private EntityRegister<Stand> standRegister = new EntityRegister<>();
    private RegisterStandScreenMode mode;
    
    private int currentField = 0;
    private String standId;
    private String standLocation;
    private String standSize;
    private boolean didFail = false;

    private Optional<Stand> editingStand = Optional.empty();

    public RegisterStandScreen(EntityRegister<Stand> standRegister, RegisterStandScreenMode mode) {
        this.standRegister = standRegister;
        this.mode = mode;
    }

    public RegisterStandScreen(EntityRegister<Stand> standRegister) {
        this.standRegister = standRegister;
        this.mode = RegisterStandScreenMode.STAND_NEW;
    }

    public Optional<Stand> getEditingStand() {
        return editingStand;
    }

    public void SaveAndExit(AppState appState) {
        Stand stand = new Stand(standId, standLocation, standSize);
        try {
            standRegister.add(stand);
            System.out.println("Stand registrado");
            appState.setScreen(new StandsMenuScreen());
        } catch (EntityAlreadyExistsException e) {
            didFail = true;
            System.out.println("El stand ya existe");
            currentField = 0;
        }
    }

    @Override
    public void show() {
        System.out.println("Feria Empresarial - Registro de stands");

        switch (currentField) {
            case 0:
                System.out.println("Id del stand:");
                break;
            case 1:
                System.out.println("Ubicación del stand:");
                break;
            case 2:
                System.out.println("Tamaño del stand:");
                break;
            default:
                if (didFail) {
                    System.out.println("El stand ya existe. Intente de nuevo");
                } else {
                    System.out.println("Registro completado");
                }
                break;
        }
    }

    @Override
    public void update(AppState appState) {
        switch (currentField) {
            case 0:
                standId = appState.getUserInput();
                currentField++;
                break;
            case 1:
                standLocation = appState.getUserInput();
                currentField++;
                break;
            case 2:
                standSize = appState.getUserInput();
                currentField++;
                break;
            default:
                if (mode == RegisterStandScreenMode.STAND_NEW) {
                    SaveAndExit(appState);
                } else {
                    editingStand = Optional.of(new Stand(standId, standLocation, standSize));
                }
                break;
        }
    }
    
}
