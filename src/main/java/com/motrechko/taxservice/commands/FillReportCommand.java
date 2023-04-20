package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.enums.Target;
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
