package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.Company;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Stand;
import edu.ean.feriaempresarial.model.StandOccupancy;
import edu.ean.feriaempresarial.model.Visit;
import edu.ean.feriaempresarial.model.Visitor;

/**
 * Represents the reviews screen in the Feria Empresarial application.
 * This screen allows users to select a company and view visitor comments and ratings 
 * for its associated stands. It calculates and displays the average rating for the company.
 */
public class ReviewsScreen implements IScreen {
    private EntityRegister<Visit> visitRegister;
    private EntityRegister<StandOccupancy> occupancyRegister;
    private ListCompaniesScreen listCompaniesScreen;

    private int step = 0;

    private Company selectedCompany;

    public ReviewsScreen(EntityRegister<Visit> visitRegister, EntityRegister<Company> companyRegister, EntityRegister<StandOccupancy> occupancyRegister) {
        this.visitRegister = visitRegister;
        this.occupancyRegister = occupancyRegister;
        this.listCompaniesScreen = new ListCompaniesScreen(companyRegister, occupancyRegister, ListCompaniesScreenMode.SELECT_COMPANY);
    }

    private Optional<Company> findCompanyForStand(Stand stand) {
        for (StandOccupancy occupancy : occupancyRegister.getEntities()) {
            if (occupancy.getStand().equals(stand)) {
                return Optional.of(occupancy.getCompany());
            }
        }

        return Optional.empty();
    }

    private void showRatingsForCompany() {
        Float avgRating = 0.0f;
        Integer totalRatings = 0;
        System.out.println("Comentarios de la empresa " + selectedCompany.getName());
        for (Visit visit : visitRegister.getEntities()) {
            Stand stand = visit.getStand();
            Visitor visitor = visit.getVisitor();
            Optional<Company> company = findCompanyForStand(stand);
            if (company.isPresent() && company.get().equals(selectedCompany)) {
                System.out.println("Visitante: " + visitor.getName());
                System.out.println("Comentario: " + visit.getComment());
                System.out.println("Calificación: " + visit.getRating());
                avgRating += visit.getRating();
                totalRatings++;
            }
        }

        if (totalRatings == 0) {
            System.out.println("No hay comentarios para esta empresa");
        } else {
            avgRating /= totalRatings;
            System.out.println("Calificación promedio: " + avgRating);
        }
    }

    @Override
    public void show() {
        switch (step) {
            case 0:
                listCompaniesScreen.show();
                break;
            case 1:
                showRatingsForCompany();
                break;
        }
    }

    @Override
    public void update(AppState appState) {
        switch (step) {
            case 0:
                listCompaniesScreen.update(appState);
                if (listCompaniesScreen.getEditingCompany().isPresent()) {
                    selectedCompany = listCompaniesScreen.getEditingCompany().get();
                    step = 1;
                }
                break;
            case 1:
                appState.setScreen(new CompaniesMenuScreen());
                break;
        }
    }
    
}
