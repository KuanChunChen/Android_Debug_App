package utility.castles.getfile;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class WelcomeActivity extends Activity {


    private TextView tvNameVersion;
    private String strWelComeTitle;
    private PackageInfo pkgInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        init();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent().setClass(WelcomeActivity.this, MainFragment.class));
                finish();
            }
        }, 3000);

    }

    private void init() {
        try {
            pkgInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tvNameVersion = findViewById(R.id.tvNameVersion);
        strWelComeTitle = "TransFileTool_" + pkgInfo.versionName;
        tvNameVersion.setText(strWelComeTitle);
    }
}
