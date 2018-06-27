package com.itislevel.lyl.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-09.09:36
 * path:com.itislevel.lyl.utils.DecimalUtils
 **/
public class DecimalUtils {

    public static String format2(double value) {
//        DecimalFormat df = new DecimalFormat(".##");
//        return df.format(d);

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();

    }
}
