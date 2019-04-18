package utility.castles.getfile.ui.TabFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

    @BindView(R.id.frag3_btn2) Button btn_GET;
    @BindView(R.id.frag3_text1) TextView tv1;
    @BindView(R.id.frag3_btn1) Button btn_Return;
    @BindView(R.id.listView1) ListView listView1;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private CtCtms ct = new CtCtms();


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
        init();
        return view;
    }

    public void init() {

        tv1.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_Return.setOnClickListener(this);
        btn_GET.setOnClickListener(this);
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

                String strMy = new MachineUtil(getContext()).getCradleVersion();
                if (strMy == null) {
                    Toast.makeText(getContext(), "string is null", Toast.LENGTH_LONG).show();
                    Log.e("My cradle ", "string is null");
                } else {
                    Log.e("My cradle ", strMy);
                    Toast.makeText(getContext(), "My cradele =" + strMy, Toast.LENGTH_LONG).show();
                }


                break;
        }
    }

    public void initListview(){

        TelephonyManager telManager = ((TelephonyManager) view.getContext().getSystemService(Context.TELEPHONY_SERVICE));
        GetTerminalInfo myGetTerminalInfo = new GetTerminalInfo();
        terminalInfomation = myGetTerminalInfo.getGsonInformation(view.getContext());

        Log.d("My terminal info", myInfo.toJson(terminalInfomation));

    }

    public void initPermission(){
        if(!PermissionManager.checkPermission(view.getContext(), DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO)) {
            PermissionManager.requestPermissions(view.getContext(),DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO,DebugAppConstants.Permission.PERMISSION_GET_TERMINAL_INFO_CODE);
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