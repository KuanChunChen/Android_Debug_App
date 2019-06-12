package utility.castles.getfile;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import utility.castles.getfile.ui.TabFragment.APIFragment;
import utility.castles.getfile.ui.TabFragment.FileFragment;
import utility.castles.getfile.ui.TabFragment.FirstFragment;
import utility.castles.getfile.ui.TabFragment.SecondFragment;
import utility.castles.getfile.ui.TabFragment.ThirdFragment;
import utility.castles.getfile.ui.TabFragment.WifiTestFragment;


public class MainFragment extends AppCompatActivity {


    private final FragmentManager fm = getFragmentManager();
    private Boolean AisActive=false;
    private Fragment currentFragment=null;
    private FileFragment FirstFragment = new FileFragment();
    private SecondFragment SecondFragment = new SecondFragment();
    private ThirdFragment ThirdFragment = new ThirdFragment();
    private WifiTestFragment WifiTestFragment = new WifiTestFragment();
    private APIFragment APIFragment = new APIFragment();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainfragment);
        BottomNavigationView bnve = findViewById(R.id.bnve);

        getSupportFragmentManager().beginTransaction().add(R.id.lay_container, APIFragment).hide(APIFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.lay_container, WifiTestFragment).hide(WifiTestFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.lay_container, ThirdFragment).hide(ThirdFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.lay_container, SecondFragment).hide(SecondFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.lay_container, FirstFragment).commit();
        AisActive = true;
        currentFragment = FirstFragment;
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_getfile:
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(FirstFragment).commit();
                        currentFragment = FirstFragment;
                        break;
                    case R.id.menu_debug:
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(SecondFragment).commit();
                        currentFragment = SecondFragment;
                        break;
                    case R.id.menu_setting:
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(ThirdFragment).commit();
                        currentFragment = ThirdFragment;
                        break;
                    case R.id.menu_wifi:
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(WifiTestFragment).commit();
                        currentFragment = WifiTestFragment;
                        break;
                    case R.id.Api_test:
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(APIFragment).commit();
                        currentFragment = APIFragment;
                        break;

                }




                return loadfragment(currentFragment);
            }
        });
    }


    private boolean loadfragment(Fragment fragment){

        if(fragment!=null) {
            //FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
            //transaction.replace(R.id.lay_container, fragment,fragment.getTag());
            //transaction.commit();
            return true;
        }
        return false;
    }








}