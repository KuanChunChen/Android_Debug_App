package utility.castles.getfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import utility.castles.getfile.util.FileUtil;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        ButterKnife.bind(this);

        initbutton();
    }

    private void initbutton() {
        btnTest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_test:
                File dir = this.getFilesDir();
                File outFile = new File("/data/data/utility.castles.getfile2", "test2.txt");
                try {
                    outFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(outFile.exists()){
                    Log.d("test", "File exist!");
                    tvTest.setText("Make successful!");

                }else{
                    Log.d("test", "File not exist!");
                    tvTest.setText("Make failed");
                }

//                if (new FileUtil().makeFile()){
//                    tvTest.setText("Make successful!");
//                }else {
//                    tvTest.setText("Make failed");
//                }
                break;
        }
    }


}
