package utility.castles.getfile.ui.TabFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CTOS.CtCtms;
import CTOS.CtSystem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utility.castles.getfile.Controller.CheckAplicationStatus;
import utility.castles.getfile.Controller.GetTerminalInfo;
import utility.castles.getfile.Define.DebugAppConstants;
import utility.castles.getfile.Model.TerminalInfomation;
import utility.castles.getfile.R;
import utility.castles.getfile.module.battery.BatteryListener;
import utility.castles.getfile.module.permission.PermissionManager;
import utility.castles.getfile.util.MachineUtil;

import static utility.castles.getfile.Define.Define.d_CTMS_SERVICE_PACKAGE;

public class ThirdFragment extends Fragment implements View.OnClickListener {

    private static View view;
    private String strCMFVersion = null;

    @BindView(R.id.frag3_btn3) Button btn_Service;
    @BindView(R.id.frag3_btn2) Button btn_GET;
    @BindView(R.id.frag3_text1) TextView tv1;
    @BindView(R.id.frag3_btn1) Button btn_Return;
    @BindView(R.id.listView1) ListView listView1;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private CtCtms ct = new CtCtms();

    private List<TerminalInfomation> TerminalInfomationList
            = new ArrayList<TerminalInfomation>();

    private TerminalInfomation terminalInfomation = new TerminalInfomation();
    private Gson myInfo = new Gson();
    private CheckAplicationStatus myCheckAplicationStatus = new CheckAplicationStatus();
    private CtSystem mMtSystem = new CtSystem();
    private BatteryListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_3, null);



        unbinder = ButterKnife.bind(this, view);
        initPermission();
        initListview();
        init();
        return view;
    }

    public void init() {

        tv1.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_Return.setOnClickListener(this);
        btn_GET.setOnClickListener(this);
        btn_Service.setOnClickListener(this);
        listener = new BatteryListener(view.getContext());
        listener.register(new BatteryListener.BatteryStateListener() {
            @Override
            public void onStateChanged(Intent mIntent) {



                Log.e("zhang", "MainActivity --> onStateChanged--> ");
                int lv = mIntent.getIntExtra("level", 0);
                boolean boIsRunning = myCheckAplicationStatus.isServiceKeepRunning(view.getContext(), d_CTMS_SERVICE_PACKAGE);
                byte[] baOut = new byte[6];
                baOut[0] = 0x00;
                baOut[1] = 0x00;
                baOut[2] = 0x33;
                int iInstallFailReturn = -1;
                baOut = Integer.toString(iInstallFailReturn).getBytes();
                String Hex = Integer.toHexString(-1);

                tv1.setText(String.valueOf(lv) + "%" + "\r\n" + boIsRunning + "\r\n" );
                for (int i = 0; i < baOut.length; i++) {
                    Log.e("CTMS", "baOut" + "[" + i + "]：" + Integer.toHexString(baOut[i]));

                }


            }

            @Override
            public void onStateLow() {
                Log.e("zhang", "MainActivity --> onStateLow--> ");
                Toast.makeText(getContext(), "onStateLow", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStateOkay() {
                Log.e("zhang", "MainActivity --> onStateOkay--> ");
                Toast.makeText(getContext(), "onStateOkay", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatePowerConnected() {
                Log.e("zhang", "MainActivity --> onStatePowerConnected--> ");
                Toast.makeText(getContext(), "onStatePowerConnected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatePowerDisconnected() {
                Log.e("zhang", "MainActivity --> onStatePowerDisconnected--> ");
                Toast.makeText(getContext(), "onStatePowerDisconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.frag3_btn1:

                new AlertDialog.Builder(v.getContext())
                        .setTitle("警告！").setIcon(R.mipmap.ic_launcher)
                        .setMessage("確定要離開本程式？")
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                            }
                        }).setNegativeButton("離開", new DialogInterface.OnClickListener() {
                    /*設定跳出視窗的返回事件*/
                    public void onClick(DialogInterface dialoginterface, int i) {
                        System.exit(0);
                    }
                }).show();
                break;
            case R.id.frag3_btn2:

                String strCMF_version = "00280000000092210022";
                String strBios = strCMF_version.substring(0, 4);
                String strSuld = strCMF_version.substring(4, 8);
                String strKernel = strCMF_version.substring(8, 12);
                String strRootFS = strCMF_version.substring(12, 16);
                String strMainAP = strCMF_version.substring(16, 20);
                Log.i("MyData","CMF VR = " + strCMF_version + "\r\n" +
                        "Bios VR = " + strBios + "\r\n" +
                        "Suld VR = " + strSuld + "\r\n" +
                        "Kernel VR = " + strKernel + "\r\n" +
                        "RootFS VR = " + strRootFS + "\r\n"+
                        "MainAP VR = " + strMainAP + "\r\n");
                try {
                    Thread getCradleThread = new Thread(new Runnable() {
                        public void run() {
                            strCMFVersion = new MachineUtil(getContext()).getCradleVersion();


                        }
                    });
                    getCradleThread.start();
                    getCradleThread.join();


                    String ResBios = null, ResSuld = null, ResKernel = null, ResRootFs = null, ResMainAP = null;

                    if (strCMFVersion == null) {
                        Toast.makeText(getContext(), "string is null", Toast.LENGTH_LONG).show();
                        Log.e("My cradle ", "string is null");
                    } else {
                        Log.e("My cradle ", strCMFVersion);
                        tv1.setText(strCMFVersion);
                        Toast.makeText(getContext(), "My cradele =" + strCMFVersion, Toast.LENGTH_LONG).show();
                        String[] strArrayCradle = strCMFVersion.split("\\s+");
                        for (String each: strArrayCradle){
                            if(each.contains("BIOS")){
                                ResBios = each.substring(7, 11);
                                Log.i("BIOS : ---", "" + ResBios);
                            }else if(each.contains("SULD")){
                                ResSuld = each.substring(7, 11);
                                Log.i("SULD : ---", "" + ResSuld);
                            }else if(each.contains("KERNEL")){
                                ResKernel  = each.substring(9, 13);
                                Log.i("KERNEL : ---", "" + ResKernel);
                            }else if(each.contains("ROOTFS")){
                                ResRootFs = each.substring(9, 13);
                                Log.i("ROOTFS : ---", "" + ResRootFs);
                            }else if(each.contains("MainAP")){
                                ResMainAP  = each.substring(9, 13);
                                Log.i("MainAP : ---", "" + ResMainAP);
                            }
                        }


                        if (strBios.equals("0000")) {
                            ResBios = strBios;
                        }

                        if (strSuld.equals("0000")) {
                            ResSuld = strSuld;
                        }

                        if (strKernel.equals("0000")) {
                            ResKernel = strKernel;
                        }

                        if (strRootFS.equals("0000")) {
                            ResRootFs = strRootFS;
                        }

                        if (strMainAP.equals("0000")) {
                            ResMainAP = strMainAP;
                        }
                        Log.i("MyData2", "Bios VR = " + ResBios + "\r\n" +
                                "Suld VR = " + ResSuld + "\r\n" +
                                "Kernel VR = " + ResKernel + "\r\n" +
                                "RootFS VR = " + ResRootFs + "\r\n" +
                                "MainAP VR = " + ResMainAP + "\r\n");

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.frag3_btn3:
                Boolean boStatus = false;
                JSONObject jsonObject = new JSONObject();

                JSONArray jsonArray = new JSONArray();
                int iInstallFailReturn = 0;
                if(boStatus) {
                    try {
                        jsonObject.put("STATUS", 2);
                        jsonObject.put("TYPE", "Android App");
                        jsonObject.put("NAME", "com.itube.colorseverywhere");
                        jsonObject.put("VR", "3.8.10");
                        jsonObject.put("PATH", "/data/CTMS/Download/APK/30/itube.CAP");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        jsonObject.put("STATUS", 7);
                        jsonObject.put("TYPE", "CMF");
                        jsonObject.put("NAME", "CradleModuleFW");
                        jsonObject.put("VR", "0028F030003292210022");
                        jsonObject.put("PATH", "/data/CTMS/Download/Temp/57/SC_9221_sOff.zip.CAP");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                jsonArray.put(jsonObject);
                Log.d("My array ", jsonArray.toString());

                try {
                    byte[] baJson = jsonArray.toString(1).getBytes();
                    int iJsonlen = baJson.length;
                    byte[] balen = ByteBuffer.allocate(4).putInt(iJsonlen).array();
                    byte[] baOut = new byte[iJsonlen + 6];

                    if (iInstallFailReturn != 0) {
                        byte[] baErrorTemp = Integer.toString(iInstallFailReturn).getBytes();
                        for (int index = 0; index < 2; index++) {
                            baOut[index] = baErrorTemp[index];
                        }
                    }else{
                        baOut[0] = 0x00;
                        baOut[1] = 0x00;
                    }
                    if(!boStatus) {
                        baOut[0] = 0x00;
                        baOut[1] = 0x43;
                    }
                    System.arraycopy(balen,0,baOut,2,4);
                    System.arraycopy(baJson,0,baOut,6,iJsonlen);
                    for (int index = 0; index < 2; index++) {
                        Log.d("ttsss", String.valueOf(baOut[index]));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                break;
        }

    }



    public void initListview(){

        if(PermissionManager.checkPermission(view.getContext(), DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO)) {
            TelephonyManager telManager = ((TelephonyManager) view.getContext().getSystemService(Context.TELEPHONY_SERVICE));
            GetTerminalInfo myGetTerminalInfo = new GetTerminalInfo();
            terminalInfomation = myGetTerminalInfo.getGsonInformation(view.getContext());
            Log.d("My terminal info", myInfo.toJson(terminalInfomation));
        }else{
            Log.d("My terminal info", "No permission");
        }
    }


    public void initPermission(){
        if(!PermissionManager.checkPermission(view.getContext(), DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO)) {
//            PermissionManager.requestPermissions(view.getContext(),DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO,DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO_CODE);
            requestPermissions(DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO_2, DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO_CODE: {
                boolean boJudge = true;
                for(int permissionGrant:grantResults){
                    Log.d("grantResults", String.valueOf(permissionGrant));
                    if (permissionGrant != PackageManager.PERMISSION_GRANTED) {
                        boJudge = false;
                    }
                }

                if (boJudge) {
                    Log.d("grantResults", "finished!");
                } else {
                    Log.d("grantResults", "Not allow!");
                }
                break;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onDestroy() {
        if (listener != null) {
            listener.unregister();
        }
        super.onDestroy();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}