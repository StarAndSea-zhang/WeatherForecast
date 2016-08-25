package com.zy.fengchun.weatherforecast.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by fengchun on 2016/7/27.
 */
public class NetworkUtils {

    /**
     * 检查网络是否有用
     * @param context
     * @return
     */
    public static boolean checkNetWorkEnable(Context context) {
        
        NetworkInfo localNetworkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        //网络可用
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
        {
            return true;
        }

        return false;
    }

    /**
     * 得到IP地址
     * @param context
     * @return
     */
    public static String getIpAddress(Context context) {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
       switch (localNetworkInfo.getType()){
           //wifi情况下IP地址
           case ConnectivityManager.TYPE_WIFI:
               WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
               WifiInfo wifiInfo = wifiManager.getConnectionInfo();
               // 获取32位整型IP地址
               int ipAddress = wifiInfo.getIpAddress();

               //返回整型地址转换成“*.*.*.*”地址
               return String.format("%d.%d.%d.%d",
                       (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
                       (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
           //3G情况下IP地址
           case ConnectivityManager.TYPE_MOBILE:
               try {
                   for (Enumeration<NetworkInterface> en = NetworkInterface
                           .getNetworkInterfaces(); en.hasMoreElements();) {
                       NetworkInterface intf = en.nextElement();
                       for (Enumeration<InetAddress> enumIpAddr = intf
                               .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                           InetAddress inetAddress = enumIpAddr.nextElement();
                           if (!inetAddress.isLoopbackAddress()
                                   && inetAddress instanceof Inet4Address) {
                               return inetAddress.getHostAddress().toString();
                           }
                       }
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
               break;
           default:
               return null;
       }
        return null;
    }
}
