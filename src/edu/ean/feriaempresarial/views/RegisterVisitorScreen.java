package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.AppState;
import edu.ean.feriaempresarial.EntityRegister;
import edu.ean.feriaempresarial.Visitor;

enum RegisterVisitorScreenMode {
    VISITOR_NEW,
    VISITOR_EDIT
}

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
            appState.setScreen(new VisitorsMenu());
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
