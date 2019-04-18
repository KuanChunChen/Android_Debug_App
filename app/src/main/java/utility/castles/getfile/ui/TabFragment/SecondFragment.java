package utility.castles.getfile.ui.TabFragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import utility.castles.getfile.R;
import utility.castles.getfile.TransFileFromUSB;

import static utility.castles.getfile.Define.Define.d_FILE_NOT_EXIST;
import static utility.castles.getfile.Define.Define.d_OK;

public class SecondFragment extends Fragment implements View.OnClickListener {
    private static View view;

    private static String strAPK ="",strOTA ="",strSMF ="",strALL ="";

    public Button btn_GetAPK,btn_GetOTA,btn_GetSMF;
    public TextView tv1,tv2,tv3;
    TransFileFromUSB TFFUSB =new TransFileFromUSB();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_2,null);
        init();
        return view;
    }

    public void init(){

        btn_GetAPK = view.findViewById(R.id.frag2_btn1);
        btn_GetOTA = view.findViewById(R.id.frag2_btn2);
        btn_GetSMF = view.findViewById(R.id.frag2_btn3);
        tv1 =view.findViewById(R.id.frag2_text1);
        tv2 =view.findViewById(R.id.frag2_text2);
        tv3 =view.findViewById(R.id.frag2_text3);

        btn_GetAPK.setOnClickListener(this);
        btn_GetOTA.setOnClickListener(this);
        btn_GetSMF.setOnClickListener(this);

        tv3.setText("＂使用說明＂\n"+
                "在USB中建立對應資料夾\n"+
                "如：/APK 或 /SMF 或 /OTA\n"+
                "在對應資料夾加入對應檔\n"+
                "輸入要轉移的檔案加副檔名\n"+
                "按下對應按鈕即可轉移");

    }

    @Override
    public void onClick(View v){

        String strRespone ="";

        EditText ed_filename =view.findViewById(R.id.frag2_ed1);



        switch(v.getId()){
            case R.id.frag2_btn1:

                strAPK = "";
                strAPK = TFFUSB.getStoragePath(view.getContext(),true);
                //strAPK ="/storage/emulated/0";
                if(strAPK!=""){

                    //判斷書入值是不是空
                    if(!"".equals(ed_filename.getText().toString().trim())) {
                        if(ed_filename.getText().toString().endsWith(".apk")) {
                            Toast.makeText(view.getContext(), "USB_path: "+strAPK, Toast.LENGTH_SHORT).show();
                            strRespone = TFFUSB.getfile(strAPK + "/APK"+File.separator + ed_filename.getText(), File.separator + String.valueOf(ed_filename.getText()));


                            if (strRespone == d_OK) {
                                tv1.setText("Success trensfer file to：" + Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ed_filename.getText());
                                tv2.setText("File exist：" + strAPK +"/APK"+ File.separator + ed_filename.getText());
                            } else if (strRespone == d_FILE_NOT_EXIST) {
                                tv1.setText("");
                                tv2.setText("File not exist： " + strAPK +"/APK"+ File.separator + ed_filename.getText());
                            } else {
                                tv1.setText("");
                                tv2.setText(strRespone);
                            }


                        }else{
                            tv1.setText("");
                            tv2.setText("the end of file name is not '.apk' ");

                        }
                    }else{
                        Toast.makeText(view.getContext(), "please input file name！", Toast.LENGTH_SHORT).show();
                        tv1.setText("");
                        tv2.setText("");

                    }
                }else{
                    Toast.makeText(view.getContext(), "can not found USB connect", Toast.LENGTH_SHORT).show();
                    tv1.setText("");
                    tv2.setText("");
                }

                break;
            case R.id.frag2_btn2:
                strOTA = "";
                strOTA = TFFUSB.getStoragePath(view.getContext(),true);
                //strOTA ="/storage/emulated/0";
                if(strOTA!=""){

                    //判斷書入值是不是空
                    if(!"".equals(ed_filename.getText().toString().trim())) {
                        if(ed_filename.getText().toString().endsWith(".ota")) {
                            Toast.makeText(view.getContext(), "USB_path: "+strOTA, Toast.LENGTH_SHORT).show();
                            strRespone = TFFUSB.getfile(strOTA +"/OTA"+ File.separator + ed_filename.getText(), File.separator + String.valueOf(ed_filename.getText()));


                            if (strRespone == d_OK) {
                                tv1.setText("Success trensfer file to：" + Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ed_filename.getText());
                                tv2.setText("File exist：" + strOTA +"/OTA"+ File.separator + ed_filename.getText());
                            } else if (strRespone == d_FILE_NOT_EXIST) {
                                tv1.setText("");
                                tv2.setText("File not exist： " + strOTA +"/OTA"+ File.separator + ed_filename.getText());
                            } else {
                                tv1.setText("");
                                tv2.setText(strRespone);
                            }


                        }else{
                            tv1.setText("");
                            tv2.setText("the end of file name is not '.ota' ");

                        }
                    }else{
                        Toast.makeText(view.getContext(), "please input file name！", Toast.LENGTH_SHORT).show();
                        tv1.setText("");
                        tv2.setText("");

                    }
                }else{
                    Toast.makeText(view.getContext(), "can not found USB connect", Toast.LENGTH_SHORT).show();
                    tv1.setText("");
                    tv2.setText("");
                }
                break;
            case R.id.frag2_btn3:
                strSMF = "";
                strSMF = TFFUSB.getStoragePath(view.getContext(),true);
                //strSMF ="/storage/emulated/0";
                if(strSMF!=""){

                    //判斷書入值是不是空
                    if(!"".equals(ed_filename.getText().toString().trim())) {
                        if(ed_filename.getText().toString().endsWith(".smf")) {
                            Toast.makeText(view.getContext(), "USB_path: "+strSMF, Toast.LENGTH_SHORT).show();
                            strRespone = TFFUSB.getfile(strSMF +"/SMF"+ File.separator + ed_filename.getText(), File.separator + String.valueOf(ed_filename.getText()));


                            if (strRespone == d_OK) {
                                tv1.setText("Success trensfer file to：" + Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ed_filename.getText());
                                tv2.setText("File exist：" + strSMF +"/SMF"+ File.separator + ed_filename.getText());
                            } else if (strRespone == d_FILE_NOT_EXIST) {
                                tv1.setText("");
                                tv2.setText("File not exist： " + strSMF +"/SMF"+ File.separator + ed_filename.getText());
                            } else {
                                tv1.setText("");
                                tv2.setText(strRespone);
                            }


                        }else{
                            tv1.setText("");
                            tv2.setText("the end of file name is not '.smf' ");

                        }
                    }else{
                        Toast.makeText(view.getContext(), "please input file name！", Toast.LENGTH_SHORT).show();
                        tv1.setText("");
                        tv2.setText("");

                    }
                }else{
                    Toast.makeText(view.getContext(), "can not found USB connect", Toast.LENGTH_SHORT).show();
                    tv1.setText("");
                    tv2.setText("");
                }
                break;

        }

    }
}
