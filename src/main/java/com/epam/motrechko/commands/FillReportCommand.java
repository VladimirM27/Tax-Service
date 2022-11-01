package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.ReportDAO;
import com.epam.motrechko.db.entity.Report;
import com.epam.motrechko.db.mysql.MySQLException;
import com.epam.motrechko.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class FillReportCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            int idReport  = Integer.parseInt(request.getParameter("idReport"));
            ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();
            Report report = reportDAO.getReportById(idReport);
            request.setAttribute("report",report);
            return new CommandResponse(Target.JSP, FrontConstant.TAX_FORM_EDIT);
        } catch (MySQLException e) {
            throw new RuntimeException(e);
        }
    }
}
