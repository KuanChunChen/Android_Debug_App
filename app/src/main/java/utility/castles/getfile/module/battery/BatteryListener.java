package utility.castles.getfile.module.battery;



/**
 * Created by Willy Chen on 2019/04/16.
 *
 * @author Willy Chen
 * @Purpose Battery listener to get battery status.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class BatteryListener {

    private Context mContext;

    private BatteryBroadcastReceiver receiver;

    private BatteryStateListener mBatteryStateListener;

    public BatteryListener(Context context) {
        mContext = context;
        receiver = new BatteryBroadcastReceiver();
    }

    public void register(BatteryStateListener listener) {
        mBatteryStateListener = listener;
        if (receiver != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_BATTERY_CHANGED);
            filter.addAction(Intent.ACTION_BATTERY_LOW);
            filter.addAction(Intent.ACTION_BATTERY_OKAY);
            filter.addAction(Intent.ACTION_POWER_CONNECTED);
            filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
            mContext.registerReceiver(receiver, filter);
        }
    }

    public void unregister() {
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String acyion = intent.getAction();
                switch (acyion) {
                    case Intent.ACTION_BATTERY_CHANGED://電量發生改變
                        if (mBatteryStateListener != null) {
                            Log.e("BatteryListener", "BatteryBroadcastReceiver --> onReceive--> ACTION_BATTERY_CHANGED");
                            mBatteryStateListener.onStateChanged(intent);
                        }
                        break;
                    case Intent.ACTION_BATTERY_LOW://電量低
                        if (mBatteryStateListener != null) {
                            Log.e("BatteryListener", "BatteryBroadcastReceiver --> onReceive--> ACTION_BATTERY_LOW");
                            mBatteryStateListener.onStateLow();
                        }
                        break;
                    case Intent.ACTION_BATTERY_OKAY://電量充滿
                        if (mBatteryStateListener != null) {
                            Log.e("BatteryListener", "BatteryBroadcastReceiver --> onReceive--> ACTION_BATTERY_OKAY");
                            mBatteryStateListener.onStateOkay();
                        }
                        break;
                    case Intent.ACTION_POWER_CONNECTED://接通電源
                        if (mBatteryStateListener != null) {
                            Log.e("BatteryListener", "BatteryBroadcastReceiver --> onReceive--> ACTION_POWER_CONNECTED");
                            mBatteryStateListener.onStatePowerConnected();
                        }
                        break;
                    case Intent.ACTION_POWER_DISCONNECTED://拔出電源
                        if (mBatteryStateListener != null) {
                            Log.e("BatteryListener", "BatteryBroadcastReceiver --> onReceive--> ACTION_POWER_DISCONNECTED");
                            mBatteryStateListener.onStatePowerDisconnected();
                        }
                        break;
                }
            }
        }
    }

    public interface BatteryStateListener {

        void onStateChanged(Intent mIntent);

        void onStateLow();

        void onStateOkay();

        void onStatePowerConnected();

        void onStatePowerDisconnected();
    }

}
