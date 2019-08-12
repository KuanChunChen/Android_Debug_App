package utility.castles.getfile.ui.TabFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utility.castles.getfile.Model.SystemData;
import utility.castles.getfile.R;
import utility.castles.getfile.util.GetAppListUtil;
import utility.castles.getfile.util.LocateUtil;
import utility.castles.getfile.util.TimeUtil;

import static utility.castles.getfile.util.LocateUtil.getCountryName;

public class TimeZoneFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.fragTZ_btn1)
    Button fragTZBtn1;
    @BindView(R.id.fragTZ_btn2)
    Button fragTZBtn2;
    @BindView(R.id.fragTZ_btn3)
    Button fragTZBtn3;
    @BindView(R.id.fragTZ_liner)
    LinearLayout fragTZLiner;
    @BindView(R.id.fragTZ_tv1)
    TextView fragTZTv1;
    @BindView(R.id.fragTZ_tv2)
    TextView fragTZTv2;
    @BindView(R.id.fragTZ_tv3)
    TextView fragTZTv3;
    @BindView(R.id.fragTZ_liner2)
    LinearLayout fragTZLiner2;
    @BindView(R.id.fragTZ)
    LinearLayout fragTZ;
    private View view;
    Unbinder unbinder;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //Button btnStart,btnGetSize,btnDelFile;
        view = inflater.inflate(R.layout.fragment_timezone, null);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }
    public void init() {
        fragTZBtn1.setOnClickListener(this);
        fragTZBtn2.setOnClickListener(this);
        fragTZBtn3.setOnClickListener(this);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragTZ_btn1:
                String strCurrentTZ = TimeUtil.getGmtTimeZone();
                String strCurrentLan = LocateUtil.getCurrentLanuage();
                fragTZTv1.setText("CurrentTZ : " + strCurrentTZ + "\r\n +" +
                        "CurrentLan : " + strCurrentLan);
                break;
            case R.id.fragTZ_btn2:
//                TimeUtil.changeZoneTime("");
                LocateUtil.getCurrentArea();

//                getCountryName();
//                GetAppListUtil.getAppList();
                break;
            case R.id.fragTZ_btn3:
                List<SystemData.Info> mlistInfo = new ArrayList<>();
                mlistInfo.add(new SystemData.Info("SMF","", (byte) 0xFC,Long.toString(0),Long.toString(0),"SMFsss",0));
                SystemData mSystemData = new SystemData(mlistInfo);
                mSystemData.setTimeZone("1230213");
                mSystemData.setTimeArea("asdfasf1230213");
                Log.d("test", "  " + new Gson().toJson(mSystemData));
                break;

                default:
                break;

        }
    }
}
