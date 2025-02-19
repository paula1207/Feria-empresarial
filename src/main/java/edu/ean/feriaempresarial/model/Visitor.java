package edu.ean.feriaempresarial.model;

/**
 * Represents a visitor attending the Feria Empresarial event.
 * Each visitor has a unique identifier, a name, and an email address.
 * Implements {@link IIdentifiable} to ensure each visitor can be uniquely identified.
 */
public class Visitor implements IIdentifiable {
    
    /** The unique identifier of the visitor. */
    private String id;
    
    /** The name of the visitor. */
    private String name;
    
    /** The email address of the visitor. */
    private String email;

    /**
     * Constructs a new {@code Visitor} instance.
     *
     * @param id    The unique identifier of the visitor.
     * @param name  The name of the visitor.
     * @param email The email address of the visitor.
     */
    public Visitor(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Retrieves the name of the visitor.
     *
     * @return The visitor's name as a {@code String}.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the email address of the visitor.
     *
     * @return The visitor's email as a {@code String}.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the unique identifier of the visitor.
     *
     * @return The visitor's unique identifier as a {@code String}.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Determines if this {@code Visitor} is equal to another object.
     * Two visitors are considered equal if they have the same unique identifier.
     *
     * @param o The object to compare with this visitor.
     * @return {@code true} if the given object is a {@code Visitor} with the same identifier, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitor)) return false;
        Visitor visitor = (Visitor) o;
        return id.equals(visitor.id);
    }

    /**
     * Computes the hash code of the visitor based on the unique identifier.
     *
     * @return The hash code of the visitor.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
