package net.ziling.crm.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Description:
 * 随机字符串生成器
 *
 * @author huaxin
 * @create 2018/01/10 21:06
 */
public class UUIDTools {

    /**
     * 得到一个32位的随机数的字符串
     * @return
     */
    public static String getUUIDId(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 获得当前系统的时间，并且带有格式：年-月-日 时：分：秒：毫秒
     * @return
     */
    public static String getUUIDByTimeWithSep(){
        String msg="";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
        msg+=sdf.format(date);
        return msg;
    }

    /**
     * 获得当前系统的时间，不带有格式
     * @return
     */
    public static String getUUIDByTime(){
        String msg="";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
        msg+=sdf.format(date);
        return msg;
    }

    /**
     * 获得当前系统的时间，不带有格式,然后没有秒信息
     * @return
     */
    public static String getUUIDByTime_M(){
        String msg="";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmss");
        msg+=sdf.format(date);
        System.out.println("msg:"+msg);
        return msg;
    }
}
