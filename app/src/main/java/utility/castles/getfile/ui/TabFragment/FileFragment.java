package utility.castles.getfile.ui.TabFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import utility.castles.getfile.R;

public class FileFragment extends Fragment implements View.OnClickListener {
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //Button btnStart,btnGetSize,btnDelFile;
        view = inflater.inflate(R.layout.fragment_1, null);

        //getfile.writeFile(d_FILE_NAME,d_FILE_TEST_STR);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
