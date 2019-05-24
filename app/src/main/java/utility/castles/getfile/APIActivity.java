package utility.castles.getfile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utility.castles.getfile.util.ConfigurationUtil;

public class APIActivity extends AppCompatActivity implements View.OnClickListener  {

    Unbinder unbinder;

    @BindView(R.id.frag5_btn1)
    Button frag5Btn1;
    @BindView(R.id.frag5_btn2)
    Button frag5Btn2;
    @BindView(R.id.frag5_btn3)
    Button frag5Btn3;
    @BindView(R.id.frag5_btn4)
    Button frag5Btn4;
    @BindView(R.id.frag5_btn5)
    Button frag5Btn5;
    @BindView(R.id.frag5_btn6)
    Button frag5Btn6;
    @BindView(R.id.frag5_btn_7)
    Button frag5Btn7;
    @BindView(R.id.frag5_btn_8)
    Button frag5Btn8;
    @BindView(R.id.frag5_btn_leave)
    Button frag5BtnLeave;
    @BindView(R.id.frag5_text1)
    TextView frag5Text1;
    @BindView(R.id.ed_ip)
    EditText edIp;
    @BindView(R.id.ed_port)
    EditText edPort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_5);
        ButterKnife.bind(this);
        initbutton();
    }

    private void initbutton() {
        frag5Btn1.setOnClickListener(this);
        frag5Btn2.setOnClickListener(this);
        frag5Btn3.setOnClickListener(this);
        frag5Btn4.setOnClickListener(this);
        frag5Btn5.setOnClickListener(this);
        frag5Btn6.setOnClickListener(this);
        frag5Btn7.setOnClickListener(this);
        frag5Btn8.setOnClickListener(this);
        frag5BtnLeave.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.frag5_btn_leave:

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
            case R.id.frag5_btn1:

                ConfigurationUtil.setCM_Mode(true);
                frag5Text1.setText("TLS ON");
                frag5Text1.setTextColor(Color.GREEN);
                Log.d("CTMS_configAll", ConfigurationUtil.getAllConfig());

                break;
            case R.id.frag5_btn2:

                if (edIp.getText().toString().isEmpty() || edPort.getText().toString().isEmpty()) {

                    frag5Text1.setText("Set TLS" + "\n" + ConfigurationUtil.setTlsConfig());
                }else{
                    frag5Text1.setText("Set TLS" + "\n" + ConfigurationUtil.setTlsConfig(edIp.getText().toString(),edPort.getText().toString()));
                }

                frag5Text1.setTextColor(Color.BLUE);
                Log.d("CTMS_configAll", ConfigurationUtil.getAllConfig());

                break;
            case R.id.frag5_btn3:

                ConfigurationUtil.setCM_Mode(false);
                frag5Text1.setText("TLS OFF");
                frag5Text1.setTextColor(Color.RED);

                Log.d("CTMS_configAll", ConfigurationUtil.getAllConfig());

                break;
            case R.id.frag5_btn4:

                ConfigurationUtil.setCTMS_Enable(true);
                frag5Text1.setText("TCP ON");
                frag5Text1.setTextColor(Color.GREEN);
                Log.d("CTMS_configAll", ConfigurationUtil.getAllConfig());

                break;
            case R.id.frag5_btn5:

                if (edIp.getText().toString().isEmpty() || edPort.getText().toString().isEmpty()) {

                    frag5Text1.setText("Set TCP" + "\n" + ConfigurationUtil.setTcpConfig());
                }else{
                    frag5Text1.setText("Set TCP" + "\n" + ConfigurationUtil.setTcpConfig(edIp.getText().toString(),edPort.getText().toString()));
                }
                frag5Text1.setTextColor(Color.BLUE);
                Log.d("CTMS_configAll", ConfigurationUtil.getAllConfig());

                break;
            case R.id.frag5_btn6:

                ConfigurationUtil.setCTMS_Enable(false);
                frag5Text1.setText("TCP OFF");
                frag5Text1.setTextColor(Color.RED);
                Log.d("CTMS_configAll", ConfigurationUtil.getAllConfig());

                break;
            case R.id.frag5_btn_7:


                frag5Text1.setText(ConfigurationUtil.getAllConfig());
                frag5Text1.setTextColor(Color.BLUE);
                frag5Text1.setMovementMethod(ScrollingMovementMethod.getInstance());
//                Log.d("CTMS_configAll", ConfigurationUtil.getAllConfig());

                break;
            case R.id.frag5_btn_8:

                edPort.getText().clear();
                edIp.getText().clear();
                frag5Text1.setText("");
                frag5Text1.setMovementMethod(ScrollingMovementMethod.getInstance());
//                Log.d("CTMS_configAll", ConfigurationUtil.getAllConfig());

                break;
        }
    }



}
