package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.JSON.JSONController;
import com.motrechko.taxservice.XML.SAXController;
import com.motrechko.taxservice.enums.FileType;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.service.ReportService;
import com.motrechko.taxservice.utils.FIleUtils;
import com.motrechko.taxservice.utils.SessionUtils;
import com.motrechko.taxservice.validator.FileValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@MultipartConfig

public class FileCommand extends FrontCommand {
    private static final Logger logger = LogManager.getLogger(FileCommand.class);
    private static final String UPLOAD_PATH = "/uploads";

    /**
     * Processes the file, validates the format, saves it to the server, and parses it into a report.
     * @return a CommandResponse containing the target and the path to the JSP page.
     * @throws ServletException if there is an error in the servlet.
     * @throws IOException if there is an error in the input/output.
     */
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            Part filePart = request.getPart("file");
            FileValidator fileValidator = new FileValidator(filePart);
            if (!fileValidator.validate()) {
                SessionUtils.setError(request,"Wrong file format, make sure it is JSON or XML");
                return new CommandResponse(Target.JSP, FrontConstant.ERROR);
            }

            String uploadPath = FIleUtils.saveFile(filePart,
                    servletContext.getRealPath(UPLOAD_PATH));
            Report report = null;
            if (fileValidator.getFileType() == FileType.XML) {
                SAXController saxController = new SAXController(uploadPath);
                if (!saxController.validateXMLSchema(servletContext)) {
                    SessionUtils.setError(request,"file is not valid XML schema");
                    return new CommandResponse(Target.JSP, FrontConstant.ERROR);
                }
                report = saxController.parse();
            } else if (fileValidator.getFileType() == FileType.JSON && JSONController.validateJSONSchema(servletContext, uploadPath)) {
                report = JSONController.parseJSON(uploadPath);
            }

            ReportService reportService = new ReportService();
            reportService.create(report);
            return new CommandResponse(Target.COMMAND, "Reports");
        } catch (ReportException e) {
            logger.error("Error while adding a report", e);
            SessionUtils.setError(request,"Error while adding a report");
            return new CommandResponse(Target.JSP, FrontConstant.ERROR);
        }
    }
}
