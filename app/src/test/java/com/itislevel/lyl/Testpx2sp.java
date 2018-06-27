package com.itislevel.lyl;

import android.os.SystemClock;

import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.RegexUtil;
import com.itislevel.lyl.utils.SystemUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.RxExifTool;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/22.9:40
 * path:com.itislevel.lyl.Testpx2sp
 **/
public class Testpx2sp {

    @Test
    public void testpx2sp() {

        for (int i = 1; i < 100; i++) {
            System.out.println(SystemUtil.px2dp(1));
        }
    }
    @Test
    public void testDate2Timespan(){
        String s = null;
        try {
            s = DateUtil.dateToTimespan("2017-12-11 16:22:43");
            System.out.println(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTimespan0Date(){
//        String s = DateUtil.getFriendlytime(new Date(1512980563000L));
//        System.out.println(s);

//        String res = DateUtil.formatDate3String(System.currentTimeMillis(), 1517127754000L);
//        System.out.println(res);
//           String REGEX_MOBILE_EXACT  = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";


//        Pattern p = Pattern.compile(REGEX_MOBILE_EXACT);
//        Matcher m = p.matcher("18302562524");

        boolean mobileExact = RegexUtil.isMobileExact("18285174924");

//        System.out.println(m.matches()+"");
        System.out.println(mobileExact+"");

    }
}
