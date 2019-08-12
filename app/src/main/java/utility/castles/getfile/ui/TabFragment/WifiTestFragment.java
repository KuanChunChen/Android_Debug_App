package utility.castles.getfile.ui.TabFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utility.castles.getfile.Controller.CtmsConfigFun;
import utility.castles.getfile.Controller.WifiController;
import utility.castles.getfile.R;
import utility.castles.getfile.util.ConfigurationUtil;


public class WifiTestFragment extends Fragment {

    private static View view;
    @BindView(R.id.frag4_btn1)
    Button frag4Btn1;
    @BindView(R.id.frag4_text1)
    TextView frag4Text1;
    Unbinder unbinder;


    private TextView tv1;
    private Button btn_Return, btn_GET;
    private WifiController mMifiController;
    private CtmsConfigFun mGetCtmsConfig;


    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_4, null);
        unbinder = ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {
        mMifiController = new WifiController(getContext());
        mGetCtmsConfig = new CtmsConfigFun();
        frag4Btn1.setText("exit");
    }

    @OnClick(R.id.frag4_btn1)
    public void onBtnWifiTestClick() {

//        mMifiController.getWifi();
//        Log.d("CTMS_configA", mGetCtmsConfig.getAllconfig());
//        mGetCtmsConfig.setAllConfig(d_JSON_CTMS_CONFIG_REWRITE_A);
//        Log.d("CTMS_configA", mGetCtmsConfig.getAllconfig());
//        ConfigurationUtil.setTlsConfig();
//        ConfigurationUtil.setCM_Mode(true);
//        Log.d("CTMS_configC", mGetCtmsConfig.getAllconfig());
        new AlertDialog.Builder(view.getContext())
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
