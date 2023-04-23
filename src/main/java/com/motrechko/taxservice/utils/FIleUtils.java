package com.motrechko.taxservice.utils;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FIleUtils {
    private FIleUtils() {
        throw new AssertionError("This class should not be instantiated.");
    }

    public static String saveFile(Part filePart, String uploadDirPath) throws IOException {
        String fileName = filePart.getSubmittedFileName();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String randomFileName = UUID.randomUUID() + extension;
        String filePath = uploadDirPath + File.separator + randomFileName;
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        filePart.write(filePath);
        return filePath;
    }
}
