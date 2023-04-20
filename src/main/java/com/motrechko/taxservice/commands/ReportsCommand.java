package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.ReportView;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.service.ReportService;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class ReportsCommand extends FrontCommand {

    private static final Logger logger = LogManager.getLogger(ReportsCommand.class);

    /**
     * Processes the user's report request and returns the appropriate CommandResponse.
     *
     * @return the CommandResponse for the report request.
     * @throws ServletException if an error occurs during request processing.
     * @throws IOException if an error occurs while writing the response.
     */
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            ReportService reportService = new ReportService();
            User user = (User) request.getSession(false).getAttribute("currentUser");
            List<ReportView> userReports = reportService.getUserReports(user.getId());
            request.getSession().setAttribute("reportViewList", userReports);
            logger.info("Retrieved {} reports for user with ID {}", userReports.size(), user.getId());
            return new CommandResponse(Target.JSP, FrontConstant.REPORTS_USER);

        } catch (ReportException e) {
            logger.error("An error occurred while retrieving user reports: {}", e.getMessage(), e);
            return new CommandResponse(Target.JSP, FrontConstant.ERROR);
        }
    }
}

