package utility.castles.getfile.ui.TabFragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utility.castles.getfile.R;
import utility.castles.getfile.module.permission.PermissionManager;
import utility.castles.getfile.util.FileUtil;

public class FileFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.tv_file_path)
    TextView tvFilePath;
    @BindView(R.id.tv_Content)
    TextView tvContent;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;
    Unbinder unbinder;
    @BindView(R.id.btn_Test)
    Button btnTest;
    @BindView(R.id.btn_mkf)
    Button btnMkf;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.sp_type)
    Spinner spType;
    @BindView(R.id.bnt_del)
    Button bntDel;
    @BindView(R.id.etPath)
    EditText etPath;
    private static final String PRM_CASE_ONE = "[{\"PRM\":{\"Name1\":\"Content1\",\"Name2\":\"\",\"Name3\":\"Content1\"},\"VR\":\"9006\"},{\"PRM\":{\"Name1\":\"Content1\",\"Name2\":\"\",\"Name31\":\"Content1\",\"Name32\":\"a1_\"},\"VR\":\"9005\"}]";
    @BindView(R.id.tv_readContent)
    TextView tvReadContent;
    @BindView(R.id.tv_File)
    TextView tvFile;
    @BindView(R.id.sp_file)
    Spinner spFile;

    private View view;
    private String strMakeFileType;
    private String strCurrentPath;
    private FileUtil mFileUtil = new FileUtil();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //Button btnStart,btnGetSize,btnDelFile;
        view = inflater.inflate(R.layout.file_fragment, null);

        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Test:
                File[] myFileList = new FileUtil().FileFilter(new File(etPath.getText().toString()));
                String[] myStrList = new String[myFileList.length];
                if (myFileList.length != 0) {
                    tvFile.setVisibility(View.VISIBLE);
                    spFile.setVisibility(View.VISIBLE);
                    for(int i=0 ;i<myFileList.length;i++){
                        myStrList[i] = myFileList[i].getAbsolutePath().replaceAll(strCurrentPath,"");
                    }
                    ArrayAdapter<String> lunchList = new ArrayAdapter<>(view.getContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            myStrList);
                    spFile.setAdapter(lunchList);
                }



                break;
            case R.id.btn_mkf:
                final int random = new Random().nextInt(Integer.MAX_VALUE);
                mFileUtil.makeDefaultFile(etPath.getText().toString(), random + strMakeFileType);
                mFileUtil.writeFile(etPath.getText().toString(), "COM.EXAMPLE.CASTLES_USER.TESTAPP1.prm", PRM_CASE_ONE);
                tvReadContent.setText(mFileUtil.readFile(etPath.getText().toString(), "COM.EXAMPLE.CASTLES_USER.TESTAPP1.prm"));
                break;
            case R.id.bnt_del:
                mFileUtil.deleteAllFile(new File(strCurrentPath));
                break;

        }

    }

    public void init() {
        btnTest.setOnClickListener(this);
        btnMkf.setOnClickListener(this);
        bntDel.setOnClickListener(this);
        strCurrentPath = mFileUtil.getDefaultFolder(view.getContext());
        etPath.setText(strCurrentPath);
        ArrayAdapter<CharSequence> lunchList = ArrayAdapter.createFromResource(view.getContext(),
                R.array.file_type,
                android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(lunchList);
        spType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView parent, View v, int position, long id) {
                // parent = 事件發生的母體 spinner_items
                // position = 被選擇的項目index = parent.getSelectedItemPosition()
                // id = row id，通常給資料庫使用
                strMakeFileType = String.valueOf(parent.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView parent) {
            }
        });

        if (!new PermissionManager(view.getContext()).checkPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE})) {

            new PermissionManager(view.getContext()).requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
