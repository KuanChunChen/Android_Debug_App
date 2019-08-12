package utility.castles.getfile.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import utility.castles.getfile.App;

public class LocateUtil {



    public static String getCurrentArea() {
        String strCurrentCountry = Locale.ENGLISH.getDefault().getCountry();
        Locale loc = new Locale("en",strCurrentCountry);

        Log.d("Current Country-1 :", TimeZone.getDefault().getID());
        String locale = App.getInstance().getResources().getConfiguration().locale.getCountry();
        Log.d("Current Country0 :", loc.getCountry());
        Log.d("Current Country1 :", strCurrentCountry);
        Log.d("Current Country2 :", loc.getDisplayCountry());
        Log.d("Current Country3 :", locale);
        TelephonyManager tm = (TelephonyManager)App.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        String countryCodeValue = tm.getNetworkCountryIso();
        Log.d("Current Country4 :", countryCodeValue);


        return strCurrentCountry;

    }

    public static String getCountryName(){
        String country_name = null;
        LocationManager lm = (LocationManager)App.getInstance().getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(App.getInstance());
        for(String provider: lm.getAllProviders()) {
            @SuppressWarnings("ResourceType") Location location = lm.getLastKnownLocation(provider);
            if(location!=null) {
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addresses != null && addresses.size() > 0) {
                        country_name = addresses.get(0).getCountryName();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(App.getInstance(), country_name, Toast.LENGTH_LONG).show();
        return country_name;
    }

    public static String getCurrentLanuage() {
        String languageName = Locale.getDefault().getLanguage();
        return languageName;
    }


}
