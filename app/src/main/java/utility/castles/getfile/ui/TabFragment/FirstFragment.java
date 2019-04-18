package utility.castles.getfile.ui.TabFragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import CTOS.CtCtms;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import utility.castles.getfile.GetfileMAin;
import utility.castles.getfile.R;
import utility.castles.getfile.module.permission.PermissionCallback;
import utility.castles.getfile.module.permission.PermissionManager;

import static CTOS.CtCtms.OPTION_DISABLE;
import static CTOS.CtCtms.OPTION_ENABLE;
import static utility.castles.getfile.Define.Define.d_DIR_PATH;
import static utility.castles.getfile.Define.Define.d_FILE_NAME;
import static utility.castles.getfile.Define.Define.d_FILE_TYPE;


public class FirstFragment extends Fragment implements View.OnClickListener, EasyPermissions.PermissionCallbacks{

    private View view;
    public String[] strarrSplit;
    GetfileMAin getfile = new GetfileMAin() ;
    private Button btnStart,btnGetSize,btnDelFile;
    private CtCtms mCtCtms;
    private TextView tVPath,tVStatus,tvDebugStatus;
    private PermissionManager mPermissionManager;
    private PermissionCallback mPermissionCallback;

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //Button btnStart,btnGetSize,btnDelFile;
        view = inflater.inflate(R.layout.fragment_1, null);
        init();
        //getfile.writeFile(d_FILE_NAME,d_FILE_TEST_STR);
        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("cc","onRequestPermissionsResult !");
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        //将请求结果传递EasyPermission库处理
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

        if (EasyPermissions.somePermissionPermanentlyDenied((Activity) getContext(), strPremissions)) {
            new AppSettingsDialog.Builder((Activity) getContext()).build().show();
        }

    }

    public void init() {
        mCtCtms = new CtCtms();
        btnStart = view.findViewById(R.id.frag1_btn1);
        btnGetSize = view.findViewById(R.id.frag1_btn3);
        btnDelFile = view.findViewById(R.id.frag1_btn4);
        tVPath = view.findViewById(R.id.frag1_text1);
        tVStatus = view.findViewById(R.id.frag1_text2);
        tvDebugStatus = view.findViewById(R.id.frag1_text3);
        btnStart.setOnClickListener(this);
        btnGetSize.setOnClickListener(this);
        btnDelFile.setOnClickListener(this);
        mPermissionManager = new PermissionManager(getContext());

        if (!mPermissionManager.checkPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE})) {

            mPermissionManager.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},200);

        }
    }

    @Override
    public void onClick(View v) {


        tVStatus.setMovementMethod(ScrollingMovementMethod.getInstance());
        switch (v.getId()) {
            case R.id.frag1_btn1:
                String strReadData = "";
                String strWriteData = "";


                strReadData = getfile.readFile(d_FILE_NAME, d_FILE_TYPE, d_DIR_PATH);
                //String[] strarrSplit = strReadData.split("\n");
                if (strReadData != "FileNotFoundException" && strReadData != "IOExecption") {


                    tVPath.setText("GetDataFrom : " + d_DIR_PATH + File.separator + d_FILE_NAME + d_FILE_TYPE);
                    tVPath.setBackgroundColor(Color.RED);
                    //tVPath.setText("StoreDataPosition : "+ Environment.getExternalStorageDirectory().getAbsolutePath());
                    // mCtCtms.setDebugEnable(OPTION_ENABLE);
                    tvDebugStatus.setBackgroundColor(Color.GREEN);
                    tvDebugStatus.setText("Debug Object Value :"+mCtCtms.getDebugStatus());
                    //writeFile Start
                    strWriteData = getfile.writeFile(d_FILE_NAME, strReadData);
                    if (strWriteData == "Suc!") {


                        strarrSplit = strReadData.split("\n");


                        tVStatus.setText("Store data to ：" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + d_FILE_NAME + d_FILE_TYPE + "\r\n" + "And it content is" + strReadData);
                        tVStatus.setBackgroundColor(Color.BLUE);

                    } else {
                        //失敗下顯示錯誤情況
                        tVPath.setText(strWriteData);
                        tVPath.setBackgroundColor(Color.RED);
                        tvDebugStatus.setBackgroundColor(Color.WHITE);

                    }
                } else {
                    tVPath.setText(strReadData);
                    tVPath.setBackgroundColor(Color.RED);
                    tvDebugStatus.setBackgroundColor(Color.WHITE);
                }

                break;
            case R.id.frag1_btn3:
                mCtCtms.setDebugEnable(OPTION_ENABLE);

                tVPath.clearComposingText();
                tVPath.setBackgroundColor(000000);
                tVStatus.clearComposingText();
                tVStatus.setBackgroundColor(000000);
//                String strSize = "";
//
//                strSize = getfile.getFileSize(d_FILE_NAME);
//                tVPath.setText("get File size from：" + Environment.getExternalStorageDirectory().getAbsolutePath() + d_FILE_NAME + d_FILE_TYPE + "==" + "file size:" + strSize);
//                tVStatus.setText("");
                tvDebugStatus.setBackgroundColor(Color.GREEN);
                tvDebugStatus.setText("Debug Object Value :"+mCtCtms.getDebugStatus());
                break;
            case R.id.frag1_btn4:
                Boolean boDel;
                mCtCtms.setDebugEnable(OPTION_DISABLE);

                boDel = getfile.delFile(d_FILE_NAME,d_FILE_TYPE,d_DIR_PATH);
                tVPath.setText("Delete File From:"+d_DIR_PATH + File.separator + d_FILE_NAME + d_FILE_TYPE);
                tVPath.setBackgroundColor(Color.RED);
                tVStatus.setText("Delete file (boolean):" + boDel);
                tVStatus.setBackgroundColor(Color.BLUE);
                tvDebugStatus.setBackgroundColor(Color.GREEN);
                tvDebugStatus.setText("Debug Object Value :"+mCtCtms.getDebugStatus());

                break;


        }

    }
}