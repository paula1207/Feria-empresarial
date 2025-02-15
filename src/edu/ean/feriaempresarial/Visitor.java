package edu.ean.feriaempresarial;

public class Visitor implements IIdentifiable {
    private String id;
    private String name;
    private String email;

    public Visitor(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitor)) return false;
        Visitor visitor = (Visitor) o;
        return id.equals(visitor.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
