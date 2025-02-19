package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.Company;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Stand;
import edu.ean.feriaempresarial.model.StandCompanyUtil;
import edu.ean.feriaempresarial.model.StandOccupancy;
import edu.ean.feriaempresarial.model.Visit;
import edu.ean.feriaempresarial.model.Visitor;

/**
 * Represents the visitors report screen in the Feria Empresarial application.
 * This screen generates a report displaying visitors and the stands they have visited,
 * including the associated companies. Users can view detailed visit information
 * and navigate back to the reports menu.
 */
public class VisitorsReportScreen implements IScreen {
    private EntityRegister<Visitor> visitorRegister;
    private EntityRegister<Visit> visitRegister;
    private EntityRegister<StandOccupancy> occupancyRegister;

    public VisitorsReportScreen(EntityRegister<Visitor> visitorRegister,
                                EntityRegister<StandOccupancy> occupancyRegister, 
                                EntityRegister<Visit> visitRegister) {
        this.visitorRegister = visitorRegister;
        this.visitRegister = visitRegister;
        this.occupancyRegister = occupancyRegister;
    }

    @Override
    public void show() {
        System.out.println("Reporte de visitantes y stands visitados");
        
        for (Visitor visitor : visitorRegister.getEntities()) {
            System.out.print(visitor.getName() + " - Stands visitados: ");
            for (Visit visit : visitRegister.getEntities()) {
                if (visit.getVisitor().equals(visitor)) {
                    Stand stand = visit.getStand();
                    Optional<Company> company = StandCompanyUtil.findCompanyForStand(stand, occupancyRegister);
                    System.out.print(stand.getId() + " (" + company.get().getName() + "), ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void update(AppState appState) {
        appState.setScreen(new ReportsScreen());
    }
    
}
