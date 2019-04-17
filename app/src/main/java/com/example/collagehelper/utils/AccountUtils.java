package com.example.collagehelper.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liang on 2019/04/13
 * 注册账号是否合法的相关判断工具类
 */
public class AccountUtils {
    /**
     * 判断手机号格式是否合法
     * @param phone 手机号
     * @return
     */
    public static boolean isPhoneLegal(String phone){
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 判断密码格式是否合法 数字+字母组成
     * @param pwd
     * @return
     */
    public static boolean isPwdLegal(String pwd){
        String regNum1 = "^[a-zA-Z]+[0-9]+[a-zA-Z0-9]+";
        String regNum2 = "^[a-zA-Z]+[0-9]+";
        String regLetter1 = "^[0-9]+[a-zA-Z]+[a-zA-Z0-9]+";
        String regLetter2 = "^[0-9]+[a-zA-Z]+";
        Pattern p1 = Pattern.compile(regNum1);
        Matcher m1 = p1.matcher(pwd);
        Pattern p2 = Pattern.compile(regLetter1);
        Matcher m2 = p2.matcher(pwd);
        Pattern p3 = Pattern.compile(regLetter2);
        Matcher m3 = p3.matcher(pwd);
        Pattern p4 = Pattern.compile(regNum2);
        Matcher m4 = p4.matcher(pwd);
        return m1.matches() || m2.matches() || m3.matches() || m4.matches();
    }
}
