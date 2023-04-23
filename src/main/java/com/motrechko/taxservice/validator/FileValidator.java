package com.motrechko.taxservice.validator;

import com.motrechko.taxservice.enums.FileType;
import jakarta.servlet.http.Part;

public class FileValidator {
    private final Part filePart;

    public FileValidator(Part filePart) {
        this.filePart= filePart;
    }

    /**
     * Validates a file by checking if it is in JSON or XML format.
     *
     * @return true if the file is in JSON or XML format, false otherwise.
     */
    public boolean validate() {
        String fileName = filePart.getSubmittedFileName();
        return fileName.endsWith(".xml") || fileName.endsWith(".json");
    }

    /**
     * Gets the file type of the file.
     *
     * @return the file type of the file.
     */
    public FileType getFileType() {
        String fileName = filePart.getSubmittedFileName();
        if (fileName.endsWith(".json")) {
            return FileType.JSON;
        } else if (fileName.endsWith(".xml")) {
            return FileType.XML;
        }
        return null;
    }
}
