package edu.ean.feriaempresarial;

public class StandOccupancy implements IIdentifiable {
    private Stand stand;
    private Company company;

    public StandOccupancy(Stand stand, Company company) {
        this.stand = stand;
        this.company = company;
    }

    public Stand getStand() {
        return stand;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String getId() {
        return stand.getId() + "-" + company.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StandOccupancy) {
            StandOccupancy other = (StandOccupancy) obj;
            return other.getId().equals(getId());
        }

        return false;
    }
}
