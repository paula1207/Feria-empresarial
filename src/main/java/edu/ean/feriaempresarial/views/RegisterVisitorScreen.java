package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.EntityAlreadyExistsException;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Visitor;

enum RegisterVisitorScreenMode {
    VISITOR_NEW,
    VISITOR_EDIT
}

/**
 * Represents the screen for registering or editing visitor information 
 * in the Feria Empresarial application. This screen guides users through 
 * entering visitor details such as ID, name, and email. Depending on 
 * the mode, it either creates a new visitor or updates an existing one.
 */
public class RegisterVisitorScreen implements IScreen {
    private EntityRegister<Visitor> visitorRegister = new EntityRegister<>();
    private RegisterVisitorScreenMode mode = RegisterVisitorScreenMode.VISITOR_NEW;

    private int currentField = 0;
    private String visitorId;
    private String visitorName;
    private String visitorEmail;
    private boolean didFail = false;

    private Optional<Visitor> editingVisitor = Optional.empty();
    
    public RegisterVisitorScreen(EntityRegister<Visitor> visitorRegister, RegisterVisitorScreenMode mode) {
        this.visitorRegister = visitorRegister;
        this.mode = mode;
    }

    public RegisterVisitorScreen(EntityRegister<Visitor> visitorRegister) {
        this.visitorRegister = visitorRegister;
        this.mode = RegisterVisitorScreenMode.VISITOR_NEW;
    }

    public Optional<Visitor> getEditingVisitor() {
        return editingVisitor;
    }

    public void SaveAndExit(AppState appState) {
        Visitor visitor = new Visitor(visitorId, visitorName, visitorEmail);
        try {
            visitorRegister.add(visitor);
            System.out.println("Visitante registrado");
            appState.setScreen(new VisitorsMenuScreen());
        } catch (EntityAlreadyExistsException e) {
            didFail = true;
            System.out.println("El visitante ya existe");
            currentField = 0;
        }
    }

    @Override
    public void show() {
        System.out.println("Feria Empresarial - Registro de visitantes");

        switch (currentField) {
            case 0:
                System.out.println("Id del visitante:");
                break;
            case 1:
                System.out.println("Nombre del visitante:");
                break;
            case 2:
                System.out.println("Email del visitante:");
                break;
            default:
                if (didFail) {
                    System.out.println("El visitante ya existe. Intente de nuevo");
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
                visitorId = appState.getUserInput();
                currentField++;
                break;
            case 1:
                visitorName = appState.getUserInput();
                currentField++;
                break;
            case 2:
                visitorEmail = appState.getUserInput();
                currentField++;
                break;
            default:
                if (mode == RegisterVisitorScreenMode.VISITOR_NEW) {
                    SaveAndExit(appState);
                } else {
                    editingVisitor = Optional.of(new Visitor(visitorId, visitorName, visitorEmail));
                }
                break;
        }
    }
    
}
