package edu.ean.feriaempresarial.model;

/**
 * Represents a stand in the Feria Empresarial event.
 * Each stand has a unique identifier, a location, and a size.
 * Implements {@link IIdentifiable} to ensure it can be uniquely identified.
 */
public class Stand implements IIdentifiable {
    
    /** The unique identifier of the stand. */
    private String id;
    
    /** The location of the stand within the event. */
    private String location;
    
    /** The size category of the stand (e.g., small, medium, large). */
    private String size;

    /**
     * Constructs a new {@code Stand} instance.
     *
     * @param id        The unique identifier of the stand.
     * @param location  The location of the stand within the event.
     * @param size      The size category of the stand.
     */
    public Stand(String id, String location, String size) {
        this.id = id;
        this.location = location;
        this.size = size;
    }

    /**
     * Retrieves the unique identifier of the stand.
     *
     * @return The stand's unique identifier.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Retrieves the location of the stand.
     *
     * @return The location of the stand.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Retrieves the size category of the stand.
     *
     * @return The size category of the stand.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets a new identifier for the stand.
     *
     * @param id The new unique identifier for the stand.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets a new location for the stand.
     *
     * @param location The new location of the stand.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets a new size category for the stand.
     *
     * @param size The new size category of the stand.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Determines if this stand is equal to another object.
     * Two stands are considered equal if they have the same identifier.
     *
     * @param o The object to compare with this stand.
     * @return {@code true} if the given object is a {@code Stand} with the same identifier, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stand)) return false;
        Stand stand = (Stand) o;
        return id.equals(stand.id);
    }

    /**
     * Computes the hash code of the stand based on its unique identifier.
     *
     * @return The hash code of the stand.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
