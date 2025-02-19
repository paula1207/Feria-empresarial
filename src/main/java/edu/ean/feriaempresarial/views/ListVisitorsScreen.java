package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Visitor;

enum ListVisitorsScreenMode {
    LIST_VISITORS,
    DELETE_VISITOR,
    EDIT_VISITOR,
    SELECT_VISITOR
}

/**
 * Represents the screen for listing, selecting, editing, and deleting visitors 
 * in the Feria Empresarial application. This screen dynamically adjusts its behavior
 * based on the selected mode, allowing users to view registered visitors, remove them,
 * edit visitor details, or select a visitor for further actions.
 */
public class ListVisitorsScreen implements IScreen {
    private EntityRegister<Visitor> visitorRegister = new EntityRegister<>();
    private ListVisitorsScreenMode mode = ListVisitorsScreenMode.LIST_VISITORS;

    private int step = 0;

    private Optional<RegisterVisitorScreen> registerVisitorScreen = Optional.empty();
    private Optional<Visitor> editingVisitor = Optional.empty();

    public ListVisitorsScreen(EntityRegister<Visitor> visitorRegister, ListVisitorsScreenMode mode) {
        this.visitorRegister = visitorRegister;
        this.mode = mode;
    }

    public Optional<Visitor> getEditingVisitor() {
        return editingVisitor;
    }

    private void showSelect() {
        System.out.println("Feria Empresarial - Seleccionar visitante");

        showList();

        System.out.println("Ingrese el id del visitante a seleccionar:");
    }

    private void showList() {
        if (mode == ListVisitorsScreenMode.LIST_VISITORS) {
            System.out.println("Feria Empresarial - Listado de visitantes");
        }
        
        if (visitorRegister.getEntities().isEmpty()) {
            System.out.println("No hay visitantes registrados");
        } else {
            for (Visitor visitor : visitorRegister.getEntities()) {
                System.out.println(visitor.getId() + " - " + visitor.getName() + " - " + visitor.getEmail());
            }
        }
    }

    private void showDelete() {
        System.out.println("Feria Empresarial - Eliminar visitante");

        showList();

        System.out.println("Ingrese el nombre del visitante a eliminar:");
    }

    private void showEdit() {
        switch (step) {
            case 0:
                System.out.println("Feria Empresarial - Editar visitante");
                showList();
                System.out.println("Ingrese el nombre del visitante a editar:");
                break;
            case 1: case 2: case 3:
                if (registerVisitorScreen.isPresent()) {
                    registerVisitorScreen.get().show();
                }
                break;
        }
    }

    @Override
    public void show() {
        switch (mode) {
            case LIST_VISITORS:
                showList();
                break;
            case DELETE_VISITOR:
                showDelete();
                break;
            case EDIT_VISITOR:
                showEdit();
                break;
            case SELECT_VISITOR:
                showSelect();
                break;
        }
    }

    private void updateSelect(AppState appState) {
        String visitorId = appState.getUserInput();
        Optional<Visitor> visitor = visitorRegister.get(visitorId);
        if (visitor.isPresent()) {
            editingVisitor = visitor;
        } else {
            System.out.println("Visitante no encontrado");
        }
    }

    private void updateList(AppState appState) {
        appState.setScreen(new VisitorsMenuScreen());
    }

    private void updateDelete(AppState appState) {
        if (step == 0) {
            String visitorId = appState.getUserInput();
            Optional<Visitor> visitor = visitorRegister.get(visitorId);
            if (visitor.isPresent()) {
                visitorRegister.remove(visitor.get());
                appState.setScreen(new ListVisitorsScreen(visitorRegister, ListVisitorsScreenMode.DELETE_VISITOR));
            } else {
                System.out.println("El visitante no existe");
            }
            step++;
        } else {
            updateList(appState);
        }
    }

    private void updateEdit(AppState appState) {
        if (step == 0) {
            String visitorId = appState.getUserInput();
            Optional<Visitor> visitor = visitorRegister.get(visitorId);
            if (visitor.isPresent()) {
                editingVisitor = visitor;
                registerVisitorScreen = Optional.of(new RegisterVisitorScreen(visitorRegister, RegisterVisitorScreenMode.VISITOR_EDIT));
            } else {
                System.out.println("El visitante no existe");
            }
            step++;
        } else if (step == 3) {
            if (registerVisitorScreen.isPresent()) {
                registerVisitorScreen.get().update(appState);
                registerVisitorScreen.get().update(appState);
                Optional<Visitor> visitor = registerVisitorScreen.get().getEditingVisitor();
                if (visitor.isPresent()) {
                    visitorRegister.edit(editingVisitor.get(), visitor.get());
                    System.out.println("Visitante editado");
                }
            } 
            step++;
            updateList(appState);  
        } else if (step < 3) {
            if (registerVisitorScreen.isPresent()) {
                registerVisitorScreen.get().update(appState);
            }
            step++;
        } else if (step > 3) {
            updateList(appState);
        }
    }

    @Override
    public void update(AppState appState) {
        switch (mode) {
            case LIST_VISITORS:
                updateList(appState);
                break;
            case DELETE_VISITOR:
                updateDelete(appState);
                break;
            case EDIT_VISITOR:
                updateEdit(appState);
                break;
            case SELECT_VISITOR:
                updateSelect(appState);
                break;
        }
    }

}
