package edu.ean.feriaempresarial.views;

import java.util.Optional;

import edu.ean.feriaempresarial.model.AppState;
import edu.ean.feriaempresarial.model.Company;
import edu.ean.feriaempresarial.model.EntityAlreadyExistsException;
import edu.ean.feriaempresarial.model.EntityRegister;

/**
 * Represents the screen for registering or editing company information 
 * in the Feria Empresarial application. This screen guides users through 
 * entering company details such as name, sector, and email. Depending on 
 * the mode, it either creates a new company or updates an existing one.
 */
public class RegisterCompanyScreen implements IScreen {
    private EntityRegister<Company> companyRegister = new EntityRegister<>();

    private int currentField = 0;
    private String companyName;
    private String companySector;
    private String companyEmail;
    private RegisterCompanyScreenMode mode;
    private boolean didFail = false;

    private Optional<Company> editingCompany = Optional.empty();

    public RegisterCompanyScreen(EntityRegister<Company> companyRegister, RegisterCompanyScreenMode mode) {
        this.companyRegister = companyRegister;
        this.mode = mode;
    }

    public RegisterCompanyScreen(EntityRegister<Company> companyRegister) {
        this(companyRegister, RegisterCompanyScreenMode.COMPANY_NEW);
    }

    public Optional<Company> getEditingCompany() {
        return editingCompany;
    }

    public void SaveAndExit(AppState appState) {
        Company company = new Company(companyName, companySector, companyEmail);

        try {
            companyRegister.add(company);
            System.out.println("Empresa registrada");
            appState.setScreen(new CompaniesMenuScreen());
        } catch (EntityAlreadyExistsException e) {
            didFail = true;
            System.out.println("La empresa ya existe");
            currentField = 0;
        }
    }

    public void show() {
        System.out.println("Feria Empresarial - Registro de empresas");

        switch (currentField) {
            case 0:
                System.out.println("Nombre de la empresa:");
                break;
            case 1:
                System.out.println("Sector de la empresa:");
                break;
            case 2:
                System.out.println("Email de la empresa:");
                break;
            default:
                if (didFail) {
                    System.out.println("La empresa ya existe. Intente de nuevo");
                } else {
                    System.out.println("Registro completado");
                }
                break;
        }
    }

    public void update(AppState appState) {
        switch (currentField) {
            case 0:
                companyName = appState.getUserInput();
                currentField++;
                break;
            case 1:
                companySector = appState.getUserInput();
                currentField++;
                break;
            case 2:
                companyEmail = appState.getUserInput();
                currentField++;
                break;
            default:
                if (mode == RegisterCompanyScreenMode.COMPANY_NEW) {
                    SaveAndExit(appState);
                } else {
                    editingCompany = Optional.of(new Company(companyName, companySector, companyEmail));
                }
                
                break;
        }
    }
}
