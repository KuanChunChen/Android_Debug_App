package utility.castles.getfile.module.permission;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionCallback implements EasyPermissions.PermissionCallbacks{

    private static Context mContext;
    public PermissionCallback(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        //将请求结果传递EasyPermission库处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> strPremissions) {
        Log.d("cc","user OK!");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> strPremissions) {
//        ToastUtils.showToast(getApplicationContext(), "用户授权失败");
        Log.d("cc","user denied!");

        if (EasyPermissions.somePermissionPermanentlyDenied((Activity) mContext, strPremissions)) {
            new AppSettingsDialog.Builder((Activity) mContext).build().show();
        }

    }
}
