package com.yao.memorandum.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String now(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("M月d日");
        return dateFormat.format(new Date());
    }
}
