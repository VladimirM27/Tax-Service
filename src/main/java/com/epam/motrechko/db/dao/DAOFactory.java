package com.epam.motrechko.db.dao;

public abstract class DAOFactory {
    private static DAOFactory instance;
    protected static ConnectionPool connectionPool;
    public static synchronized DAOFactory getInstance(){
        if(instance == null){
            Class<?> clazz = null;
            try {
                var test = DAOFactory.daoFactoryFCN;
                clazz = Class.forName(DAOFactory.daoFactoryFCN);
                instance = (DAOFactory) clazz.getDeclaredConstructor().newInstance();
                instance.setPooledConnection();
            } catch (Exception e){
                //Logger;
            }
        }
        return instance;
    }

    private static String daoFactoryFCN;

    public static void setDaoFactoryFCN(String daoFactoryFCN){
        instance = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }

    public abstract void setPooledConnection();

    private  ConnectionPool getConnectionPool(){
        if(instance != null) return connectionPool;
        else {
            //log
            throw new IllegalArgumentException("Cannot get ConnectionPool");
        }
    }
    public abstract UserDAO getUserDAO();
    public abstract void close(AutoCloseable resource);
}
