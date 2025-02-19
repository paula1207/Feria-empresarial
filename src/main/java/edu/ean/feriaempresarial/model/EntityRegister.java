package edu.ean.feriaempresarial.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * A generic register for managing entities that implement the {@link IIdentifiable} interface.
 * This class provides functionality to add, remove, edit, and retrieve entities based on their unique identifier.
 *
 * @param <T> The type of entity, which must implement {@link IIdentifiable}.
 */
public class EntityRegister<T extends IIdentifiable> {

    /** A list that stores the registered entities. */
    private List<T> entities = new ArrayList<>();

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id The unique identifier of the entity.
     * @return An {@link Optional} containing the entity if found, or empty if not found.
     */
    public Optional<T> get(String id) {
        for (T entity : entities) {
            if (entity.getId().equals(id)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns an iterator over the entities in the register.
     *
     * @return An iterator over the stored entities.
     */
    public Iterator<T> iterator() {
        return entities.iterator();
    }

    /**
     * Removes an entity from the register by its identifier.
     *
     * @param id The unique identifier of the entity to be removed.
     */
    public void remove(String id) {
        entities.removeIf(entity -> entity.getId().equals(id));
    }

    /**
     * Updates an entity in the register by replacing it with an updated version.
     *
     * @param id      The unique identifier of the entity to be updated.
     * @param updated The updated entity.
     */
    public void edit(String id, T updated) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId().equals(id)) {
                entities.set(i, updated);
                return;
            }
        }
    }

    /**
     * Adds a new entity to the register.
     *
     * @param entity The entity to be added.
     * @throws EntityAlreadyExistsException If the entity already exists in the register.
     */
    public void add(T entity) throws EntityAlreadyExistsException {
        if (entities.contains(entity)) {
            throw new EntityAlreadyExistsException("Entity already exists");
        }
        entities.add(entity);
    }

    /**
     * Removes a specified entity from the register.
     *
     * @param entity The entity to be removed.
     */
    public void remove(T entity) {
        entities.remove(entity);
    }

    /**
     * Updates an existing entity with a new version.
     *
     * @param old     The entity to be replaced.
     * @param updated The updated entity.
     */
    public void edit(T old, T updated) {
        int index = entities.indexOf(old);
        if (index == -1) {
            return;
        }
        entities.set(index, updated);
    }

    /**
     * Retrieves all entities stored in the register.
     *
     * @return A list of all registered entities.
     */
    public List<T> getEntities() {
        return entities;
    }
}
