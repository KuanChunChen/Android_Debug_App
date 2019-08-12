package utility.castles.getfile;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App instance = null;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;

    }

    public static App getInstance() {
        return instance;
    }


    public static Context getAppContext() {
        if (instance != null) {
            return instance.getApplicationContext();
        }
        throw new RuntimeException("APP instance is null");
    }

    public static Context getContext() {
        return mContext;
    }


}