package utility.castles.getfile.Define;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class DebugAppConstants {
    public static final class Permission {
        //permission
        public static final String[] PERMISSION_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //request code
        public static final int PERMISSION_STORAGE_REQUEST_CODE = 1;

        public static final String[] PERMISSION_GET_TERMINAL_INFO = {Manifest.permission.READ_SMS,
                Manifest.permission.READ_PHONE_NUMBERS,Manifest.permission.READ_PHONE_STATE};

        public static final int PERMISSION_GET_TERMINAL_INFO_CODE = 2;

        }
}
