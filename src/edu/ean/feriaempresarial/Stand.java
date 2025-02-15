package edu.ean.feriaempresarial;

public class Stand implements IIdentifiable {
    private String id;
    private String location;
    private String size;

    public Stand(String id, String location, String size) {
        this.id = id;
        this.location = location;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getSize() {
        return size;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stand)) return false;
        Stand stand = (Stand) o;
        return id.equals(stand.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
