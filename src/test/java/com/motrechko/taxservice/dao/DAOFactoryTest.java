package com.motrechko.taxservice.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DAOFactoryTest {
    @Test
    public void testGetInstance(){
        DAOFactory instance1 = DAOFactory.getInstance();
        DAOFactory instance2 = DAOFactory.getInstance();

        // Verify that the two instances are the same
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetUserDAO() {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        // Verify that the returned UserDAO is not null
        assertNotNull(userDAO);

    }

    @Test
    public void testGetReportDAO(){
        DAOFactory factory = DAOFactory.getInstance();
        ReportDAO reportDAO = factory.getReportDAO();

        // Verify that the returned ReportDAO is not null
        assertNotNull(reportDAO);
    }

    @Test
    public void testGetAdminDAO(){
        DAOFactory factory = DAOFactory.getInstance();
        AdminDAO adminDAO = factory.getAdminDAO();

        // Verify that the returned AdminDAO is not null
        assertNotNull(adminDAO);
    }



}
