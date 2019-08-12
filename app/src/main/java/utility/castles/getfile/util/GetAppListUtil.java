package utility.castles.getfile.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import org.json.JSONObject;

import java.util.List;

import utility.castles.getfile.App;

public class GetAppListUtil {

    public static void getAppList(){

        final PackageManager packageManager = App.getInstance().getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES);
        for (ApplicationInfo appInfo : installedApplications) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                //for system
            } else {
                JSONObject JsonTmp = new JSONObject();

                appInfo.loadLabel(App.getInstance().getPackageManager()).toString().getBytes();
                String strName = appInfo.loadLabel(App.getInstance().getPackageManager()).toString();
                String strP_Name = appInfo.packageName;
                String strIT, strUT, strVer;
                int iVerCode;
                byte bType = (byte)0xFD;

//                        JsonTmp.put("Name", appInfo.loadLabel(App.getInstance().getPackageManager()).toString());
//                        JsonTmp.put("P_Name", appInfo.packageName);
//                        JsonTmp.put("Type", 0xFD);
                try {

                    PackageInfo pinfo = packageManager.getPackageInfo(appInfo.packageName, 0);
                    strIT = Long.toString(pinfo.firstInstallTime);
                    strUT = Long.toString(pinfo.lastUpdateTime);
                    strVer = pinfo.versionName;
                    iVerCode = pinfo.versionCode;
                    Log.d("install_before", strIT);
                    Log.d("install_before", TimeUtil.transTimeToDate(strIT));
                    Log.d("update_before", strUT);
                    Log.d("update_before", TimeUtil.transTimeToDate(strUT));
                    //////

                    strIT = String.valueOf(TimeUtil.changeMillisTimeToGMT(pinfo.firstInstallTime,"GMT"));
                    strUT = String.valueOf(TimeUtil.changeMillisTimeToGMT(pinfo.lastUpdateTime,"GMT"));
                    Log.d("install_after", strIT);
                    Log.d("install_after", TimeUtil.transTimeToDate(strIT));
                    Log.d("update_after", strUT);
                    Log.d("update_after", TimeUtil.transTimeToDate(strUT));
                    Log.d("--------------", "--------------");
                } catch (PackageManager.NameNotFoundException pm) {

                }

                JsonTmp = null;
            }
        }

    }
}
