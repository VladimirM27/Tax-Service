package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.model.Inspector;
import com.motrechko.taxservice.utils.InspectorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JdbcInspectorDAOTest {
    private InspectorDAO inspectorDAO;

    @BeforeEach
    void setup(){
        inspectorDAO = DAOFactory.getInstance().getInspectorDAO();
    }

    @Test
    void should_CorrectCreate_When_CorrectInput() throws MySQLException {
        Inspector inspector = InspectorFactory.createRandomInspector();
        assertTrue(inspectorDAO.create(inspector));
        inspectorDAO.delete(inspector.getId());
    }

    @Test
    void should_CorrectGetByEmail_When_CorrectInput() throws MySQLException {
        Inspector inspector = InspectorFactory.createRandomInspector();
        assertTrue(inspectorDAO.create(inspector));
        Optional<Inspector> foundedInspector = inspectorDAO.getByEmail(inspector.getEmail());
        assertTrue(foundedInspector.isPresent());
        assertEquals(inspector,foundedInspector.get());
        inspectorDAO.delete(inspector.getId());
    }

    @Test
    void should_CorrectDeleteById_When_CorrectInput() throws MySQLException {
        Inspector inspector = InspectorFactory.createRandomInspector();
        assertTrue(inspectorDAO.create(inspector));
        inspectorDAO.delete(inspector.getId());
        Executable executable = ()-> inspectorDAO.getByEmail(inspector.getEmail());
        assertThrows( MySQLException.class, executable);
    }

}
