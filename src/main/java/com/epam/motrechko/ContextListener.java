package com.epam.motrechko;

import com.epam.motrechko.db.dao.DAOFactory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();


        //Obtain a DB-pool
        DAOFactory.setDaoFactoryFCN("com.epam.motrechko.db.mysql.MySQLDAOFactory");
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
