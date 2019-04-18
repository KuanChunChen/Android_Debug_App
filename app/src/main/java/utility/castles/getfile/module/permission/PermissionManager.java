package utility.castles.getfile.module.permission;


/**
 * Created by Willy Chen on 2019/04/16.
 *
 * @author Willy Chen
 * @Purpose To get or check android permission.
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;


public class PermissionManager {


    private static Context mContext;

    /**
     * @purpose if you want to ask any function alone
     **/
    public PermissionManager(Context mContext){
        this.mContext = mContext;
    }

    /**
     * @param mContext       for it context
     * @param strPremissions all permission that would check and request
     * @param requestCode    this time's request code
     */
    public PermissionManager(Context mContext, String[] strPremissions, int requestCode) {

        this.mContext = mContext;
        if (!checkPermission(strPremissions)) {
            requestPermissions(strPremissions, requestCode);

        }
    }

    public boolean checkPermission(String[] strPremissions) {

        for (String strSinglePermission : strPremissions) {
            if (ActivityCompat.checkSelfPermission(mContext, strSinglePermission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;

    }

    public static boolean checkPermission(Context mContext,String[] strPremissions) {

        for (String strSinglePermission : strPremissions) {
            if (ActivityCompat.checkSelfPermission(mContext, strSinglePermission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;

    }


    /**
     * @param strPremissions the permission that need to open
     * @param  requestCode  the request code
     */
    public void requestPermissions(String[] strPremissions, int requestCode) {

        ActivityCompat.requestPermissions((Activity) mContext, strPremissions, requestCode);
    }

    public static void requestPermissions(Context mContext,String[] strPremissions, int requestCode) {

        ActivityCompat.requestPermissions((Activity) mContext, strPremissions, requestCode);
    }

}
