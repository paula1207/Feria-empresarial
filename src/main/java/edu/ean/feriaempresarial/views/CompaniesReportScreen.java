package edu.ean.feriaempresarial.views;

import java.util.List;
import java.util.stream.Collectors;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.Company;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.Stand;
import edu.ean.feriaempresarial.model.StandOccupancy;

/**
 * Represents the companies report screen in the Feria Empresarial application.
 * This screen generates a report displaying companies and their assigned stands.
 * It retrieves data from the company and stand occupancy registers and presents
 * the associations between companies and stands in a structured format.
 */
public class CompaniesReportScreen implements IScreen {
    private EntityRegister<Company> companyRegister;
    private EntityRegister<StandOccupancy> standOccupancyRegister;

    public CompaniesReportScreen(EntityRegister<Company> companyRegister, 
                                EntityRegister<StandOccupancy> standOccupancyRegister) {
        this.companyRegister = companyRegister;
        this.standOccupancyRegister = standOccupancyRegister;
    }

    @Override
    public void show() {
        System.out.println("Reporte de empresas y stands");
        
        for (Company company : companyRegister.getEntities()) {
            List<Stand> assignedStands = standOccupancyRegister.getEntities().stream()
                .filter(standOccupancy -> standOccupancy.getCompany().equals(company))
                .map(standOccupancy -> standOccupancy.getStand())
                .collect(Collectors.toList());

            System.out.print(company.getName() + " - Stands: ");
            for (Stand stand : assignedStands) {
                System.out.print(stand.getId() + ", ");
            }
            System.out.println();
        }
    }

    @Override
    public void update(AppState appState) {
        appState.setScreen(new ReportsScreen());
    }
    
}
