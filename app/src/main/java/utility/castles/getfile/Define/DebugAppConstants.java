package utility.castles.getfile.Define;

import android.Manifest;

public class DebugAppConstants {
    public static final class Permission {
        //permission
        public static final String[] PERMISSION_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //request code
        public static final int PERMISSION_STORAGE_REQUEST_CODE = 1;
    }
}
