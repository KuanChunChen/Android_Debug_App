package utility.castles.getfile;


import android.content.Context;
import android.graphics.Color;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import static utility.castles.getfile.Define.Define.d_DIR_PATH;
import static utility.castles.getfile.Define.Define.d_ENVIROMENT_PATH;
import static utility.castles.getfile.Define.Define.d_FILE_NAME;
import static utility.castles.getfile.Define.Define.d_FILE_TYPE;


public class MainActivity extends AppCompatActivity {


    UsbManager mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

    public String[] strarrSplit;
    //public ListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btn1);
        Button btnEnd = findViewById(R.id.btn2);
        Button btnGetSize = findViewById(R.id.btn3);
        Button btnDelFile = findViewById(R.id.btn4);

        final GetfileMAin getfile = new GetfileMAin() ;

        //getfile.writeFile(d_FILE_NAME,d_FILE_TEST_STR);

        //tVPath.setText(d_DIR_PATH+File.separator+d_FILE_NAME+".txt");


        /* ** ListView ** */
        //ListView mList = findViewById(R.id.listview1);
        //mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strarrSplit);
        //mList.setAdapter(mAdapter);




        /* **Start button** */
        btnStart.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
                String strReadData ="";
                String strWriteData="";
                TextView tVPath = findViewById(R.id.test1);
                TextView tVStatus = findViewById(R.id.test2);
                tVStatus.setMovementMethod(ScrollingMovementMethod.getInstance());

                strReadData = getfile.readFile(d_FILE_NAME, d_FILE_TYPE, d_ENVIROMENT_PATH);
                //String[] strarrSplit = strReadData.split("\n");
                if(strReadData!="FileNotFoundException"&&strReadData!="IOExecption"){


                    tVPath.setText("StoreDataPosition : "+d_DIR_PATH+File.separator+d_FILE_NAME+d_FILE_TYPE);
                    //tVPath.setText("StoreDataPosition : "+Environment.getExternalStorageDirectory().getAbsolutePath());
                    tVPath.setTextColor(Color.WHITE);
                    tVPath.setBackgroundColor(android.graphics.Color.RED);

                    //writeFile Start
                    strWriteData =getfile.writeFile(d_FILE_NAME,strReadData);
                    if(strWriteData=="Suc!"){


                        strarrSplit = strReadData.split("\n");


                        tVStatus.setText(strarrSplit[0]+strarrSplit[1]);
                        tVStatus.setTextColor(Color.WHITE);
                        tVStatus.setBackgroundColor(android.graphics.Color.BLUE);

                    }else{
                        //失敗下顯示錯誤情況
                        tVStatus.setText(strWriteData);
                    }
                }else{
                    tVStatus.setText(strReadData);
                }

            }

        });



        /* **exit button** */
        btnEnd.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        /* **get Size button** */
        btnGetSize.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String strSize;
                TextView tVSize =findViewById(R.id.test1);
                strSize = getfile.getFileSize(d_FILE_NAME);
                tVSize.setText(Environment.getExternalStorageDirectory().getAbsolutePath()+d_FILE_NAME+d_FILE_TYPE+"=="+"file size:"+strSize);
                tVSize.setTextColor(Color.WHITE);
                tVSize.setBackgroundColor(android.graphics.Color.RED);
            }
        });

        /* **DelFile button** */
        btnDelFile.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Boolean boDel;


                TextView tVSize =findViewById(R.id.test1);
//                boDel = getfile.delFile(d_FILE_NAME);
//                tVSize.setText("Delete file (boolean):"+boDel);
                tVSize.setTextColor(Color.WHITE);
                tVSize.setBackgroundColor(android.graphics.Color.RED);
            }
        });

    }













}

