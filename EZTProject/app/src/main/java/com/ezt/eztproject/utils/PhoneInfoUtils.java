package com.ezt.eztproject.utils;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @describe 收集信息采集类
 * @author Zhaop
 */
public class PhoneInfoUtils {

    private static final String TAG = "PhoneInfo";

    // 手机分辨率 宽 高
    public static int width;

    public static int height;

    /**
     * 获得channel id
     * @param ctx <meta-data android:name="SZICITY_CHANNEL" android:value="CHANNEL108"/>
     * @return
     */
    public static String getChanel(Context ctx, String key) {
        String CHANNELID = "000000";
        if (TextUtils.isEmpty(key)) {
            key = "SZICITY_CHANNEL";
        }
        try {
            ApplicationInfo ai = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
            Object value = ai.metaData.get(key);
            if (value != null) {
                CHANNELID = value.toString();
            }
        } catch (Exception e) {
        }
        Log.e(TAG, "CHANNELID:" + CHANNELID);
        return CHANNELID;
    }

    /**
     * 获取用户通话记录
     * @param cxt
     */
    public static void getTelRecord(Context cxt) {
        String str1 = "";
        String str2 = "";
        int type;
        Date date;
        String time = "";
        ContentResolver cr = cxt.getContentResolver();
        if (ActivityCompat.checkSelfPermission(cxt, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final Cursor cursor =
                cr.query(CallLog.Calls.CONTENT_URI, new String[]{CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME, CallLog.Calls.TYPE,
                        CallLog.Calls.DATE}, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        for(int i = 0; i < cursor.getCount(); i ++ ) {
            cursor.moveToPosition(i);
            str1 = cursor.getString(0);// 电话号码
            str2 = cursor.getString(1);// 名字
            type = cursor.getInt(2);// 1，打进来的电话。2， 拨打出去的，3，未接电话
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            date = new Date(Long.parseLong(cursor.getString(3)));
            time = sfd.format(date);
            Log.e(TAG, "str:" + str1 + " , str2:" + str2 + " , type:" + type + " , time:" + time);
        }
    }

    /**
     * 获取当前的手机号
     */
    public static String getTelNumber(Context ctx) {
        @SuppressWarnings("static-access")
        TelephonyManager tManager = (TelephonyManager)ctx.getSystemService(ctx.TELEPHONY_SERVICE);
        String telNum = tManager.getLine1Number();
        Log.e(TAG, "所获得的手机号：" + telNum);
        return telNum;
    }

    /**
     * 获取手机系统版本号
     * @param ctx
     * @return
     */
    public static String getSystemVersion(Context ctx) {
        TelephonyManager tManager = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String systemVersion = tManager.getDeviceSoftwareVersion();
        Log.e(TAG, "系统版本号：" + systemVersion);
        return systemVersion;
    }

    /**
     * 获取手机系统版本号
     * @return
     */
    public static String getSystemVersion() {
        Log.e(TAG, "系统版本号：" + android.os.Build.VERSION.RELEASE);
        return "Android" + android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机的品牌
     * @return
     */
    public static String getTelBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getTelModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取IMEI号
     */
    public static String getInfo(Context ctx) {
        TelephonyManager tManager = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tManager.getDeviceId();
    }

    /**
     * 获取屏幕分辨率
     * @param context
     */
    public static void getScreenResolution(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
        height = dm.heightPixels;
    }
}
