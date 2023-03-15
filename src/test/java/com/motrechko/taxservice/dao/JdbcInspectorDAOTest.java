package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.model.Inspector;
import com.motrechko.taxservice.utils.InspectorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


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
        inspectorDAO.delete(inspector.getIdInspector());
    }

    @Test
    void should_CorrectGetByEmail_When_CorrectInput() throws MySQLException {
        Inspector inspector = InspectorFactory.createRandomInspector();
        assertTrue(inspectorDAO.create(inspector));
        Inspector foundedInspector = inspectorDAO.getByEmail(inspector.getEmail());
        assertEquals(inspector,foundedInspector);
        inspectorDAO.delete(inspector.getIdInspector());
    }

    @Test
    void should_CorrectDeleteById_When_CorrectInput() throws MySQLException {
        Inspector inspector = InspectorFactory.createRandomInspector();
        assertTrue(inspectorDAO.create(inspector));
        inspectorDAO.delete(inspector.getIdInspector());
        Executable executable = ()-> inspectorDAO.getByEmail(inspector.getEmail());
        assertThrows( MySQLException.class, executable);
    }

}
