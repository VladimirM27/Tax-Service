package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.utils.ReportFactory;
import com.motrechko.taxservice.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcReportDAOTest {

    private ReportDAO reportDAO;
    private UserDAO userDAO;
    @BeforeEach
    void setup(){
        reportDAO = DAOFactory.getInstance().getReportDAO();
        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @Test
    void should_CorrectCreate_When_CorrectInput() throws MySQLException, ParseException {
        Report report = ReportFactory.createRandomReport();
        User user = UserFactory.createRandomUser();
        assertTrue(userDAO.create(user));
        report.setIdUser(user.getId());
        assertTrue(reportDAO.create(report));
        Report createdReport = reportDAO.getReportById(report.getIdReport());
        assertNotNull(createdReport);
        assertEquals(report, createdReport);
        reportDAO.delete(createdReport.getIdReport());
        userDAO.delete(user.getId());

    }


}
