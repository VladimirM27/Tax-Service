package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.JSON.JSONController;
import com.motrechko.taxservice.XML.SAXController;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;


@MultipartConfig

public class FileCommand extends FrontCommand{
    private static final String UPLOAD_DIRECTORY = "upload-file";
    private static final String XML_FORMAT ="xml";
    private static final String JSON_FORMAT = "json";

    @Override
    public CommandResponse process() throws ServletException, IOException {

        if(validateFileFormat(request)){
            String uploadPath = saveFile(request,servletContext);
            Report report = null;
            if(getFileType().equals(XML_FORMAT)){
                if(SAXController.validateXMLSchema(servletContext,uploadPath)){
                    SAXController saxController = new SAXController(uploadPath);
                    report = saxController.parse();
                }else {
                    request.getSession().setAttribute("errorXML","true");
                }
            } else {
                if(JSONController.validateJSONSchema(servletContext,uploadPath)){
                    report = JSONController.parseJSON(uploadPath);
                }else {
                    request.getSession().setAttribute("errorJSON","true");
                }
            }

            try {
                ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();
                reportDAO.create(report);
                return new CommandResponse(Target.JSP,FrontConstant.REPORTS_USER);

            } catch (MySQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new CommandResponse(Target.JSP,FrontConstant.ERROR);

    }



    private void validateXML(String uploadPath){
        SAXController.validateXMLSchema(servletContext,uploadPath);
    }
    private  boolean validateFileFormat(HttpServletRequest request ) throws ServletException, IOException {
        String fileType = getFileType();
        return fileType.equals(JSON_FORMAT) || fileType.equals(XML_FORMAT);
    }

    private  String saveFile( HttpServletRequest request, ServletContext servletContext ) throws IOException, ServletException {
        Part filePart = request.getPart("file");
        String fileType = getFileType();
        String fileName = getRandomNumber(100,1000000) + "." + fileType;
        String uploadPath = servletContext.getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        String filePath = uploadPath + File.separator + fileName;
        for (Part part : request.getParts()) {
            part.write(filePath);
        }
        return filePath;
    }

    private  String getFileType() throws ServletException, IOException {
        Part filePart = request.getPart("file");
        return filePart.getContentType().split("/")[1];
    }

    private static int getRandomNumber(int min,int max){
        int range = max - min + 1;
        return  (int)(Math.random() * range) + min;
    }
}
