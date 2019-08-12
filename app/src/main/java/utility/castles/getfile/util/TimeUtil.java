package utility.castles.getfile.util;

import android.text.format.DateFormat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import utility.castles.getfile.App;
import utility.castles.getfile.R;

public class TimeUtil {

    public static String getGmtTimeZone() {
        Calendar mCal = Calendar.getInstance();
//        CharSequence s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
//        Log.d("mCalTime", s.toString());
        String strCurrentTime = mCal.getTime().toString();
        int iGMTposition = strCurrentTime.indexOf("GMT");
        Log.d("mCalTime", String.valueOf(iGMTposition));
        String strGMT = strCurrentTime.substring(iGMTposition + 3, iGMTposition + 9);
        Log.d("GMT : ", strGMT);
        return strGMT;
    }



    public static long changeZoneTime(String strCurrentTzTime) {
        Date date = new Date();
        Log.d("DATETime", String.valueOf(date.getTime()));
        Calendar mCal = Calendar.getInstance();
        CharSequence s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
        Log.d("mCalTime", s.toString());
        Log.d("mCalTime", mCal.getTime().toString());
        Log.d("mCalTime", String.valueOf(mCal.getTimeInMillis()));
        Date dateTmp = null;
        //旧的就是当前的时区，新的就是目标的时区
        TimeZone oldZone = TimeZone.getDefault();
        TimeZone newZone = TimeZone.getTimeZone("GMT");
        Log.d("Time zone","="+oldZone.getDisplayName());
        Log.d("Time zone","="+oldZone.getRawOffset());
        Log.d("Time zone","="+newZone.getRawOffset());
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(mCal.getTimeInMillis() - timeOffset);
        }
        Log.d("Time zone", "=" + dateTmp.getTime());
        Log.d("Time zone", "=" + transTimeToDate(String.valueOf(dateTmp.getTime())));
        return dateTmp.getTime();
    }

    public static String transTimeToDate(String strTime) {
        Calendar calTime = Calendar.getInstance();
        calTime.setTimeInMillis(Long.valueOf(strTime));
        SimpleDateFormat dateFormat = new SimpleDateFormat(App.getInstance().getString(R.string.full_date), Locale.getDefault());
        return dateFormat.format(calTime.getTime());

    }

    public static long changeMillisTimeToGMT(long lMillisTime , String strGMT) {
        Date dateTmp = null;
        //旧的就是当前的时区，新的就是目标的时区
        TimeZone oldZone = TimeZone.getDefault();
        TimeZone newZone = TimeZone.getTimeZone("GMT");
        int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
        dateTmp = new Date(lMillisTime - timeOffset);

        return dateTmp.getTime();
    }

}
