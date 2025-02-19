package edu.ean.feriaempresarial.model;

/**
 * Represents the occupancy of a stand by a company in the Feria Empresarial event.
 * Implements {@link IIdentifiable} to provide a unique identifier for each occupancy record.
 */
public class StandOccupancy implements IIdentifiable {
    
    /** The stand that is occupied. */
    private Stand stand;
    
    /** The company occupying the stand. */
    private Company company;

    /**
     * Constructs a new {@code StandOccupancy} instance.
     *
     * @param stand   The stand that is occupied.
     * @param company The company occupying the stand.
     */
    public StandOccupancy(Stand stand, Company company) {
        this.stand = stand;
        this.company = company;
    }

    /**
     * Retrieves the stand associated with this occupancy.
     *
     * @return The {@link Stand} being occupied.
     */
    public Stand getStand() {
        return stand;
    }

    /**
     * Retrieves the company occupying the stand.
     *
     * @return The {@link Company} occupying the stand.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Retrieves the unique identifier for this occupancy.
     * The identifier is a combination of the stand's ID and the company's ID, separated by a hyphen.
     *
     * @return The unique identifier as a {@code String}.
     */
    @Override
    public String getId() {
        return stand.getId() + "-" + company.getId();
    }

    /**
     * Determines if this {@code StandOccupancy} is equal to another object.
     * Two occupancies are considered equal if they have the same unique identifier.
     *
     * @param obj The object to compare with this occupancy.
     * @return {@code true} if the given object is a {@code StandOccupancy} with the same identifier, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StandOccupancy) {
            StandOccupancy other = (StandOccupancy) obj;
            return other.getId().equals(getId());
        }
        return false;
    }
}
