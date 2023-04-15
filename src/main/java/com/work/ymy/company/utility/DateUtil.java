package com.work.ymy.company.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDateFormatYYYYMMDD(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT_YYYYMMDD);
        return simpleDateFormat.format(new Date());
    }

}
