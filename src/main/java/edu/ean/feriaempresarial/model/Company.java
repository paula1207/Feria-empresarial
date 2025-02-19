package edu.ean.feriaempresarial.model;

import java.util.Objects;

/**
 * Represents a company participating in the Feria Empresarial event.
 * This class implements the {@link IIdentifiable} interface, ensuring each company
 * has a unique identifier based on its name.
 */
public class Company implements IIdentifiable {
    
    /** The name of the company (serves as a unique identifier). */
    private String name;
    
    /** The industry sector the company belongs to. */
    private String sector;
    
    /** The contact email of the company. */
    private String email;

    /**
     * Constructs a new {@code Company} instance with the specified name, sector, and email.
     *
     * @param name   The name of the company (used as an identifier).
     * @param sector The sector or industry the company operates in.
     * @param email  The contact email of the company.
     */
    public Company(String name, String sector, String email) {
        this.name = name;
        this.sector = sector;
        this.email = email;
    }

    /**
     * Gets the name of the company.
     *
     * @return The name of the company.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the sector or industry of the company.
     *
     * @return The sector of the company.
     */
    public String getSector() {
        return sector;
    }

    /**
     * Gets the contact email of the company.
     *
     * @return The email of the company.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Determines if this company is equal to another object.
     * Two companies are considered equal if they have the same name.
     *
     * @param o The object to compare with this company.
     * @return {@code true} if the given object is a {@code Company} with the same name, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name);
    }

    /**
     * Computes the hash code of the company based on its name.
     *
     * @return The hash code of the company.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Gets the unique identifier of this company.
     * The identifier is always the company name.
     *
     * @return The name of the company as its unique identifier.
     */
    @Override
    public String getId() {
        return name;
    }
}
