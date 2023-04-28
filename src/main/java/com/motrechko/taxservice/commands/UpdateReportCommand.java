package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.mapper.ReportMapper;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.service.ReportService;
import com.motrechko.taxservice.utils.SessionUtils;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateReportCommand extends FrontCommand{
    /**
     Processes the user's edit report request and returns the appropriate CommandResponse.
     @throws ServletException If there is an error in the servlet.
     @throws IOException If there is an error in input/output.
     */
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            Report report = ReportMapper.mapReport(request);
            ReportService reportService = new ReportService();
            reportService.update(report);
            return new CommandResponse(Target.COMMAND, "Reports");
        } catch (ReportException e) {
            SessionUtils.setError(request,"Oops, something went wrong");
            return new CommandResponse(Target.JSP, FrontConstant.ERROR);
        }
    }
}
