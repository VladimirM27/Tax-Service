package com.motrechko.taxservice.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static Timestamp convertToSQL(String data) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        return getTimestamp(date1);
    }
    private static Timestamp getTimestamp(Date date) { return date == null ? null : new java.sql.Timestamp(date.getTime()); }

}
