package edu.ean.feriaempresarial;

import org.junit.jupiter.api.Test;

import edu.ean.feriaempresarial.model.EntityAlreadyExistsException;
import edu.ean.feriaempresarial.model.EntityRegister;
import edu.ean.feriaempresarial.model.IIdentifiable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntityRegisterTest {

    private EntityRegister<IIdentifiable> register;
    private IIdentifiable entity1;
    private IIdentifiable entity2;

    @BeforeEach
    void setUp() {
        register = new EntityRegister<>();

        // Mocking entities
        entity1 = mock(IIdentifiable.class);
        entity2 = mock(IIdentifiable.class);

        when(entity1.getId()).thenReturn("1");
        when(entity2.getId()).thenReturn("2");
    }

    @Test
    void testAddEntitySuccessfully() throws EntityAlreadyExistsException {
        register.add(entity1);
        assertTrue(register.getEntities().contains(entity1));
    }

    @Test
    void testAddDuplicateEntityThrowsException() throws EntityAlreadyExistsException {
        register.add(entity1);
        assertThrows(EntityAlreadyExistsException.class, () -> register.add(entity1));
    }

    @Test
    void testGetExistingEntity() throws EntityAlreadyExistsException {
        register.add(entity1);
        Optional<IIdentifiable> result = register.get("1");

        assertTrue(result.isPresent());
        assertEquals(entity1, result.get());
    }

    @Test
    void testGetNonExistingEntityReturnsEmpty() {
        Optional<IIdentifiable> result = register.get("non-existing-id");
        assertTrue(result.isEmpty());
    }

    @Test
    void testRemoveEntityById() throws EntityAlreadyExistsException {
        register.add(entity1);
        register.remove("1");

        assertTrue(register.get("1").isEmpty());
    }

    @Test
    void testRemoveNonExistingEntityByIdDoesNothing() {
        register.remove("non-existing-id");
        assertTrue(register.getEntities().isEmpty());
    }

    @Test
    void testRemoveEntityObject() throws EntityAlreadyExistsException {
        register.add(entity1);
        register.remove(entity1);

        assertFalse(register.getEntities().contains(entity1));
    }

    @Test
    void testRemoveNonExistingEntityObjectDoesNothing() {
        register.remove(entity1);
        assertTrue(register.getEntities().isEmpty());
    }

    @Test
    void testEditEntityById() throws EntityAlreadyExistsException {
        register.add(entity1);

        IIdentifiable updatedEntity = mock(IIdentifiable.class);
        when(updatedEntity.getId()).thenReturn("1");

        register.edit("1", updatedEntity);

        List<IIdentifiable> entities = register.getEntities();
        assertEquals(1, entities.size());
        assertEquals(updatedEntity, entities.get(0));
    }

    @Test
    void testEditEntityByIdDoesNothingIfNotFound() {
        IIdentifiable updatedEntity = mock(IIdentifiable.class);
        when(updatedEntity.getId()).thenReturn("1");

        register.edit("non-existing-id", updatedEntity);

        assertTrue(register.getEntities().isEmpty());
    }

    @Test
    void testEditEntityByObject() throws EntityAlreadyExistsException {
        register.add(entity1);
        register.add(entity2);

        IIdentifiable updatedEntity = mock(IIdentifiable.class);
        when(updatedEntity.getId()).thenReturn("1");

        register.edit(entity1, updatedEntity);

        assertTrue(register.getEntities().contains(updatedEntity));
        assertFalse(register.getEntities().contains(entity1));
    }

    @Test
    void testEditEntityByObjectDoesNothingIfOldEntityNotFound() {
        IIdentifiable updatedEntity = mock(IIdentifiable.class);
        when(updatedEntity.getId()).thenReturn("1");

        register.edit(entity1, updatedEntity);

        assertTrue(register.getEntities().isEmpty());
    }

    @Test
    void testIteratorReturnsEntitiesCorrectly() throws EntityAlreadyExistsException {
        register.add(entity1);
        register.add(entity2);

        assertTrue(register.iterator().hasNext());
    }
}
