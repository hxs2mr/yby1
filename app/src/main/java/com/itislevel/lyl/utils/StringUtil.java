package com.itislevel.lyl.utils;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-20.09:44
 * path:com.itislevel.lyl.utils.StringUtil
 **/
public class StringUtil {

    //没判断合法性

    public static String maskPhone(String phone){
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }
    public static String maskIDCard(String phone){
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }


    public static String maskEmail(String email) {
        return email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
    }

    public static String maskZhCN(String name) {
//        return name.replaceAll("([\\d\\D]{1})(.*)", "$1**");
        //没判断合法性
        StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append(name.substring(0, 1));
        for (int i = 1; i < name.length(); i++) {
            stringBuilder.append("*");
        }
        //.append("****")
          //      .append(cardNo.substring(cardNo.length() - 4)).toString();
        return stringBuilder.toString();
    }

    public static String maskCardNo(String cardNo) {
            //前六后四
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(cardNo.substring(0, 6)).append("****")
                    .append(cardNo.substring(cardNo.length() - 4)).toString();
    }


    public static void main(String[] args) {

        System.out.println(maskPhone("18771632488"));
        System.out.println(maskIDCard("421302199208165464"));
        System.out.println(maskEmail("57749286@qq.com"));
        System.out.println(maskZhCN("凤"));
        System.out.println(maskZhCN("凤体"));
        System.out.println(maskZhCN("冯提莫"));
        System.out.println(maskCardNo("6228480402564890018"));


    }
}
