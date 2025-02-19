package edu.ean.feriaempresarial.model;

/**
 * An interface that defines a contract for entities that have a unique identifier.
 * Any class implementing this interface must provide an implementation for retrieving its unique ID.
 */
public interface IIdentifiable {

    /**
     * Retrieves the unique identifier of the entity.
     *
     * @return The unique identifier as a {@code String}.
     */
    String getId();
}
