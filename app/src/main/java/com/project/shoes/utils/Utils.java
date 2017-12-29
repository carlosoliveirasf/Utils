package com.project.shoes.utils;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by brunorodrigues on 4/2/15.
 */
public class Utils {

    public static Date getNowDate() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        f.setTimeZone(TimeZone.getTimeZone("GMT"));
        String s = f.format(new Date());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();

        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String convertDateToDateFormat(Long date){
        date = date + TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis());

        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateReturn = f.format(new Date(date));

        return dateReturn;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        Boolean bool = (netInfo != null && netInfo.isConnectedOrConnecting());

        return bool;
    }

    public static void createDialog(Context context, String title, String message, DialogInterface.OnClickListener listenerYes, DialogInterface.OnClickListener listenerNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("sim", listenerYes);
        builder.setNegativeButton("não", listenerNo);

        builder.show();
    }

    public static void createAlert(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setNegativeButton("ok", listener);

        builder.show();
    }

    public static boolean isServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
