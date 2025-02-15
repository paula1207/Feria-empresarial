package edu.ean.feriaempresarial;

import java.util.Objects;

public class Company implements IIdentifiable {
    private String name;
    private String sector;
    private String email;

    public Company(String name, String sector, String email) {
        this.name = name;
        this.sector = sector;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSector() {
        return sector;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String getId() {
        return name;
    }
}
