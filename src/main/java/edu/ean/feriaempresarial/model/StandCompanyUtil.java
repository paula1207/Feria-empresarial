package edu.ean.feriaempresarial.model;

import java.util.Optional;

/**
 * Utility class that provides methods for finding relationships between stands and companies
 * based on occupancy records.
 */
public class StandCompanyUtil {

    /**
     * Finds the stand occupied by a given company.
     *
     * @param company The company for which to find the assigned stand.
     * @param standOccupancyRegister The register containing stand occupancy records.
     * @return An {@link Optional} containing the found {@link Stand}, or empty if no stand is assigned to the company.
     */
    public static Optional<Stand> findStandForCompany(Company company, EntityRegister<StandOccupancy> standOccupancyRegister) {
        Optional<StandOccupancy> standOccupancy = standOccupancyRegister.getEntities().stream()
            .filter(stand -> stand.getCompany().equals(company))
            .findFirst();

        return standOccupancy.map(StandOccupancy::getStand);
    }

    /**
     * Finds the company occupying a given stand.
     *
     * @param stand The stand for which to find the assigned company.
     * @param standOccupancyRegister The register containing stand occupancy records.
     * @return An {@link Optional} containing the found {@link Company}, or empty if no company is assigned to the stand.
     */
    public static Optional<Company> findCompanyForStand(Stand stand, EntityRegister<StandOccupancy> standOccupancyRegister) {
        for (StandOccupancy occupancy : standOccupancyRegister.getEntities()) {
            if (occupancy.getStand().equals(stand)) {
                return Optional.of(occupancy.getCompany());
            }
        }
        return Optional.empty();
    }
}
