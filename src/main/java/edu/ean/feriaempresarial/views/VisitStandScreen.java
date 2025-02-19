package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Stand;
import edu.ean.feriaempresarial.model.StandOccupancy;
import edu.ean.feriaempresarial.model.Visit;
import edu.ean.feriaempresarial.model.Visitor;

/**
 * Represents the screen for registering a visitor's visit to a stand 
 * in the Feria Empresarial application. This screen guides users through 
 * selecting a visitor, selecting a stand, entering a comment, and providing 
 * a rating for the visit. Once completed, the visit is recorded, and the 
 * user is returned to the visitors menu.
 */
public class VisitStandScreen implements IScreen {      
    private EntityRegister<Visit> visitRegister;

    private ListVisitorsScreen listVisitorsScreen;
    private Optional<Visitor> selectedVisitor = Optional.empty();
    private Optional<Stand> selectedStand = Optional.empty();
    private ListStandsScreen listStandsScreen;

    private int step = 0;

    private String comment;
    private int rating;

    public VisitStandScreen(EntityRegister<Visitor> visitorRegister, 
                            EntityRegister<StandOccupancy> standOccupancyRegister, 
                            EntityRegister<Stand> standRegister, 
                            EntityRegister<Visit> visitRegister) {
        this.visitRegister = visitRegister;
        this.listVisitorsScreen = new ListVisitorsScreen(visitorRegister, ListVisitorsScreenMode.SELECT_VISITOR);
        this.listStandsScreen = new ListStandsScreen(standRegister, standOccupancyRegister, ListStandsScreenMode.SELECT_OCCUPIED_STAND);
    }

    private void showSelectVisitor() {
        listVisitorsScreen.show();
    }

    private void showSelectStand() {
        listStandsScreen.show();
    }

    private void showCommentInput() {
        System.out.println("Ingrese un comentario para la visita:");
    }

    private void showRatingInput() {
        System.out.println("Ingrese una calificación para la visita [1-5]:");
    }

    @Override
    public void show() {
        switch (step) {
            case 0:
                showSelectVisitor();
                break;
            case 1:
                showSelectStand();
                break;
            case 2:
                showCommentInput();
                break;
            case 3:
                showRatingInput();
                break;
            case 4:
                System.out.println("Visita registrada");
                break;
        }
    }

    private void reset() {
        step = 0;
        selectedVisitor = Optional.empty();
        selectedStand = Optional.empty();
        comment = null;
        rating = 0;
    }

    private void registerVisit() {
        String currentDateString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        try {
            visitRegister.add(new Visit(selectedVisitor.get(), selectedStand.get(), currentDateString, comment, rating));
        } catch (Exception e) {
            System.out.println("No se pudo registrar la visita");
        }
    }

    @Override
    public void update(AppState appState) {
        switch (step) {
            case 0:
                listVisitorsScreen.update(appState);
                if (listVisitorsScreen.getEditingVisitor().isPresent()) {
                    selectedVisitor = listVisitorsScreen.getEditingVisitor();
                } else {
                    appState.setScreen(new VisitorsMenuScreen());
                }
                break;
            case 1:
                listStandsScreen.update(appState);
                if (listStandsScreen.getEditingStand().isPresent()) {
                    selectedStand = listStandsScreen.getEditingStand();
                } else {
                    appState.setScreen(new VisitorsMenuScreen());
                }
                break;
            case 2:
                if (selectedVisitor.isPresent() && selectedStand.isPresent()) {
                    comment = appState.getUserInput();
                } else {
                    reset();
                    step = -1;
                }
                break;
            case 3:
                if (selectedVisitor.isPresent() && selectedStand.isPresent()) {
                    rating = Integer.parseInt(appState.getUserInput());
                    registerVisit();
                } else {
                    reset();
                    step = -1;
                }
                break;
            case 4:
                reset();
                appState.setScreen(new VisitorsMenuScreen());
                break;
        }
        step++;
    }
    
}
