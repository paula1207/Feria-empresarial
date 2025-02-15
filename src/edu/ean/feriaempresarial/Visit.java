package edu.ean.feriaempresarial;

public class Visit implements IIdentifiable {
    private Visitor visitor;
    private Stand stand;
    private String date;
    private String comment;
    private int rating;

    public Visit(Visitor visitor, Stand stand, String date, String comment, int rating) {
        this.visitor = visitor;
        this.stand = stand;
        this.date = date;
        this.comment = comment;
        this.rating = rating;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public Stand getStand() {
        return stand;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitor=" + visitor +
                ", stand=" + stand +
                '}';
    }

    @Override
    public String getId() {
        return visitor.getId() + "-" + stand.getId() + "-" + date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Visit) {
            Visit other = (Visit) obj;
            return other.getId().equals(getId());
        }

        return false;
    }
}
