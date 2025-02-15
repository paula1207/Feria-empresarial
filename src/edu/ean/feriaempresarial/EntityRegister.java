package edu.ean.feriaempresarial;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import edu.ean.feriaempresarial.views.EntityAlreadyExistsException;

public class EntityRegister<T extends IIdentifiable> {
    private List<T> entities = new ArrayList<>();

    public Optional<T> get(String id) {
        for (T entity : entities) {
            if (entity.getId().equals(id)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    Iterator<T> iterator() {
        return entities.iterator();
    }
    
    public void remove(String id) {
        entities.removeIf(entity -> entity.getId().equals(id));
    }

    public void edit(String id, T updated) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId().equals(id)) {
                entities.set(i, updated);
                return;
            }
        }
    }

    public void add(T entity) throws EntityAlreadyExistsException {
        if (entities.contains(entity)) {
            throw new EntityAlreadyExistsException("Entity already exists");
        }
        entities.add(entity);
    }

    public void remove(T entity) {
        entities.remove(entity);
    }

    public void edit(T old, T updated) {
        int index = entities.indexOf(old);
        entities.set(index, updated);
    }

    public List<T> getEntities() {
        return entities;
    }
    
}
