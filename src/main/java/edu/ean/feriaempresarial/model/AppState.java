package edu.ean.feriaempresarial.model;

import edu.ean.feriaempresarial.views.IScreen;
import edu.ean.feriaempresarial.views.MainMenuScreen;

/**
 * Represents the application's current state, managing entities, user input, and screen navigation.
 * This class acts as a central hub for storing and modifying various entities and transitioning
 * between different screens.
 */
public class AppState {
    
    /** Indicates whether the application is running. */
    private boolean isRunning = true;
    
    /** The current active screen in the application. */
    private IScreen currentScreen = new MainMenuScreen();
    
    /** Stores the last user input received. */
    private String userInput;

    /** Register to store company entities. */
    private EntityRegister<Company> companyRegister = new EntityRegister<>();
    
    /** Register to store stand entities. */
    private EntityRegister<Stand> standRegister = new EntityRegister<>();
    
    /** Register to store visitor entities. */
    private EntityRegister<Visitor> visitorRegister = new EntityRegister<>();
    
    /** Register to store stand occupancy records. */
    private EntityRegister<StandOccupancy> standOccupancyRegister = new EntityRegister<>();
    
    /** Register to store visit records. */
    private EntityRegister<Visit> visitRegister = new EntityRegister<>();

    /**
     * Checks if the application is running.
     *
     * @return {@code true} if the application is running, otherwise {@code false}.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Retrieves the register of companies.
     *
     * @return The {@link EntityRegister} containing companies.
     */
    public EntityRegister<Company> getCompanyRegister() {
        return companyRegister;
    }

    /**
     * Retrieves the register of stands.
     *
     * @return The {@link EntityRegister} containing stands.
     */
    public EntityRegister<Stand> getStandRegister() {
        return standRegister;
    }

    /**
     * Retrieves the register of visitors.
     *
     * @return The {@link EntityRegister} containing visitors.
     */
    public EntityRegister<Visitor> getVisitorRegister() {
        return visitorRegister;
    }

    /**
     * Retrieves the register of stand occupancies.
     *
     * @return The {@link EntityRegister} containing stand occupancy records.
     */
    public EntityRegister<StandOccupancy> getStandOccupancyRegister() {
        return standOccupancyRegister;
    }

    /**
     * Retrieves the register of visits.
     *
     * @return The {@link EntityRegister} containing visit records.
     */
    public EntityRegister<Visit> getVisitRegister() {
        return visitRegister;
    }

    /**
     * Sets the application's running state.
     *
     * @param isRunning {@code true} to keep the application running, {@code false} to stop it.
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Sets the user input received from the interface.
     *
     * @param userInput The user input as a string.
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Retrieves the last user input.
     *
     * @return The user input as a string.
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Displays the current screen by calling its {@code show()} method.
     */
    public void showScreen() {
        currentScreen.show();
    }

    /**
     * Transitions to the main menu screen.
     */
    public void showMainMenu() {
        currentScreen = new MainMenuScreen();
    }

    /**
     * Sets the application's current screen.
     *
     * @param screen The new screen to be displayed.
     */
    public void setScreen(IScreen screen) {
        currentScreen = screen;
    }

    /**
     * Updates the current screen based on the given option.
     *
     * @param option The option selected by the user.
     */
    public void update(String option) {
        userInput = option;
        currentScreen.update(this);
    }

    /**
     * Loads test data for companies, stands, and visitors into their respective registers.
     * If an exception occurs during data loading, an error message is displayed.
     */
    public void loadCompanyStandsVisitorsTestData() {
        try {
            companyRegister.add(new Company("EAN", "Educacion", "luisgui@ean.com"));
            companyRegister.add(new Company("Google", "Tech", "larry@google.com"));
            companyRegister.add(new Company("Microsoft", "Tech", "bill@microsoft.com"));
    
            standRegister.add(new Stand("L1", "Pabellon A", "pequeño"));
            standRegister.add(new Stand("L2", "Pabellon A", "pequeño"));
            standRegister.add(new Stand("L3", "Pabellon B", "mediano"));
    
            visitorRegister.add(new Visitor("1234567890", "Juan", "juan@gmail.com"));
            visitorRegister.add(new Visitor("0987654321", "Pedro", "pedro@gmail.com"));
            visitorRegister.add(new Visitor("1357924680", "Maria", "maria@gmail.com"));
        } catch (Exception e) {
            System.out.println("Error cargando datos de prueba");
            e.printStackTrace();
        }
    }

    /**
     * Loads test data for stand occupancies and visits, resetting the registers before adding new data.
     * If an exception occurs during data loading, an error message is displayed.
     */
    public void loadStandOccupancyVisitsTestData() {
        try {
            // Reset all registers
            companyRegister = new EntityRegister<>();
            standRegister = new EntityRegister<>();
            visitorRegister = new EntityRegister<>();
            standOccupancyRegister = new EntityRegister<>();
            visitRegister = new EntityRegister<>();

            // Load company, stand, and visitor test data
            loadCompanyStandsVisitorsTestData();

            // Add test stand occupancies
            standOccupancyRegister.add(new StandOccupancy(standRegister.get("L1").get(), companyRegister.get("EAN").get()));
            standOccupancyRegister.add(new StandOccupancy(standRegister.get("L2").get(), companyRegister.get("Google").get()));
            standOccupancyRegister.add(new StandOccupancy(standRegister.get("L3").get(), companyRegister.get("Microsoft").get()));

            // Add test visit records
            visitRegister.add(new Visit(visitorRegister.get("1357924680").get(), standRegister.get("L1").get(), "13-01-2025 14:30:00", "Muy bueno", 5));
            visitRegister.add(new Visit(visitorRegister.get("1234567890").get(), standRegister.get("L1").get(), "13-01-2025 15:30:00", "Esta bien", 4));
            visitRegister.add(new Visit(visitorRegister.get("0987654321").get(), standRegister.get("L2").get(), "13-01-2025 16:30:00", "Excelente", 5));
            visitRegister.add(new Visit(visitorRegister.get("1357924680").get(), standRegister.get("L3").get(), "13-01-2025 17:30:00", "Muy interesante", 4));
            visitRegister.add(new Visit(visitorRegister.get("1234567890").get(), standRegister.get("L3").get(), "13-01-2025 18:30:00", "Regular", 3));
        } catch (Exception e) {
            System.out.println("Error cargando datos de prueba");
            e.printStackTrace();
        }
    }
}
