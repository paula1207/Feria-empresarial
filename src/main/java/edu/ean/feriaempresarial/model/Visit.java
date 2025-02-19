package edu.ean.feriaempresarial.model;

/**
 * Represents a visitor's visit to a stand at the Feria Empresarial event.
 * Each visit records details such as the visitor, the stand visited, the date, comments, and a rating.
 * Implements {@link IIdentifiable} to provide a unique identifier for each visit.
 */
public class Visit implements IIdentifiable {

    /** The visitor who made the visit. */
    private Visitor visitor;

    /** The stand that was visited. */
    private Stand stand;

    /** The date of the visit in string format. */
    private String date;

    /** A comment left by the visitor regarding their experience. */
    private String comment;

    /** The visitor's rating for the stand, on a predefined scale. */
    private int rating;

    /**
     * Constructs a new {@code Visit} instance.
     *
     * @param visitor The visitor who made the visit.
     * @param stand   The stand that was visited.
     * @param date    The date of the visit.
     * @param comment A comment left by the visitor.
     * @param rating  The visitor's rating of the stand.
     */
    public Visit(Visitor visitor, Stand stand, String date, String comment, int rating) {
        this.visitor = visitor;
        this.stand = stand;
        this.date = date;
        this.comment = comment;
        this.rating = rating;
    }

    /**
     * Retrieves the visitor who made the visit.
     *
     * @return The {@link Visitor} associated with this visit.
     */
    public Visitor getVisitor() {
        return visitor;
    }

    /**
     * Retrieves the stand that was visited.
     *
     * @return The {@link Stand} associated with this visit.
     */
    public Stand getStand() {
        return stand;
    }

    /**
     * Retrieves the date of the visit.
     *
     * @return The date of the visit as a {@code String}.
     */
    public String getDate() {
        return date;
    }

    /**
     * Retrieves the comment left by the visitor about the visit.
     *
     * @return The visitor's comment as a {@code String}.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Retrieves the rating given by the visitor to the stand.
     *
     * @return The rating as an {@code int}.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns a string representation of the visit, including the visitor and stand details.
     *
     * @return A string representing the visit.
     */
    @Override
    public String toString() {
        return "Visit{" +
                "visitor=" + visitor +
                ", stand=" + stand +
                '}';
    }

    /**
     * Retrieves the unique identifier for this visit.
     * The identifier is a combination of the visitor's ID, stand's ID, and the visit date.
     *
     * @return The unique identifier as a {@code String}.
     */
    @Override
    public String getId() {
        return visitor.getId() + "-" + stand.getId() + "-" + date;
    }

    /**
     * Determines if this {@code Visit} is equal to another object.
     * Two visits are considered equal if they have the same unique identifier.
     *
     * @param obj The object to compare with this visit.
     * @return {@code true} if the given object is a {@code Visit} with the same identifier, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Visit) {
            Visit other = (Visit) obj;
            return other.getId().equals(getId());
        }
        return false;
    }
}
