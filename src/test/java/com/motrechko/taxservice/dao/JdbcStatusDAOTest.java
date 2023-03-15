package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcStatusDAOTest {
    private StatusDAO statusDAO;

    @BeforeEach
    void setup(){
        statusDAO = DAOFactory.getInstance().getStatusDAO();
    }

    @Test
    void should_CorrectCreate_When_InputCorrect() throws MySQLException {
        Status status = Status.ACCEPTED;
        assertTrue(statusDAO.create(status));
        statusDAO.delete(status);
    }

}
