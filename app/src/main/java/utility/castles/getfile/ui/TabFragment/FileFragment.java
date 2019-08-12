package utility.castles.getfile.ui.TabFragment;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Random;

import CTOS.CtCtms;
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
    @BindView(R.id.btn_read)
    Button btnRead;

    @BindView(R.id.et_value)
    EditText etValue;
    @BindView(R.id.btn_change)
    Button btnChange;
    @BindView(R.id.Lo_Modify)
    LinearLayout LoModify;
    @BindView(R.id.all_up)
    LinearLayout allUp;
    @BindView(R.id.contencrollView)
    ScrollView contencrollView;
    @BindView(R.id.spArray)
    Spinner spArray;

    private View view;
    private String strMakeFileType;
    private String strCurrentPath;
    private String strCurrentFile;
    private String strCurrentReadData;
    private String strCurrentJsonArrayString;
    private String strCurrentJsonObj;
    private int iCurrentArrayIndex;
    private FileUtil mFileUtil = new FileUtil();
    private ArrayAdapter<String> empty= null;


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
                if (mFileUtil.isDirectoryExist(etPath.getText().toString())) {
                    File[] myFileList = mFileUtil.FileFilter(new File(etPath.getText().toString()));

                    if (myFileList.length != 0) {
                        String[] myStrList = new String[myFileList.length];
                        tvFile.setVisibility(View.VISIBLE);
                        spFile.setVisibility(View.VISIBLE);
                        for (int i = 0; i < myFileList.length; i++) {
                            myStrList[i] = myFileList[i].getAbsolutePath().replaceAll(etPath.getText().toString(), "");
                        }
                        ArrayAdapter<String> fileList = new ArrayAdapter<>(view.getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                myStrList);
                        spFile.setAdapter(fileList);
                        spFile.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView parent, View v, int position, long id) {
                                // parent = 事件發生的母體 spinner_items
                                // position = 被選擇的項目index = parent.getSelectedItemPosition()
                                // id = row id，通常給資料庫使用
                                strCurrentFile = String.valueOf(parent.getSelectedItem());

                            }

                            @Override
                            public void onNothingSelected(AdapterView parent) {
                            }
                        });
                        spArray.setAdapter(empty);
                        etValue.setText("");
                        tvContent.setText("Get file from : \r\n" + etPath.getText().toString() + " Success !");
                        tvContent.setTextColor(Color.GREEN);
                        btnRead.setVisibility(View.VISIBLE);
                        bntDel.setVisibility(View.VISIBLE);
                        tvReadContent.setText("");
                        Log.d("MyTest", "Get file from : " + etPath.getText().toString() + "Success !");
                    } else {
                        tvContent.setText("There are no file in : \r\n" + etPath.getText().toString());
                        tvContent.setTextColor(Color.RED);
                        tvFile.setVisibility(View.GONE);
                        spFile.setVisibility(View.GONE);
                        btnRead.setVisibility(View.GONE);
                        bntDel.setVisibility(View.GONE);
                        LoModify.setVisibility(View.GONE);
                        tvReadContent.setText("");
                        Log.d("MyTest", "There are no file in :" + etPath.getText().toString());
                    }
                } else {
                    tvContent.setText("Directory Not Exist ! ");
                    tvContent.setTextColor(Color.RED);
                    tvFile.setVisibility(View.GONE);
                    spFile.setVisibility(View.GONE);
                    btnRead.setVisibility(View.GONE);
                    bntDel.setVisibility(View.GONE);
                    LoModify.setVisibility(View.GONE);
                    tvReadContent.setText("");
                    Log.d("MyTest", etPath.getText().toString() + " Not Exist");

                }

                break;
            case R.id.btn_mkf:
                Log.d("test", new CtCtms().getAllConfig());
                final int random = new Random().nextInt(Integer.MAX_VALUE);
                if (mFileUtil.isDirectoryExist(etPath.getText().toString())) {
                    if (strMakeFileType.equals("COM.EXAMPLE.CASTLES_USER.TESTAPP1.prm")) {
                        mFileUtil.writeFile(etPath.getText().toString(), strMakeFileType, PRM_CASE_ONE);

                    } else {
                        mFileUtil.makeDefaultFile(etPath.getText().toString(), random + strMakeFileType);

                    }
                    tvContent.setText("Make file success ! ");
                    tvContent.setTextColor(Color.GREEN);
                    tvReadContent.setText(new CtCtms().getAllConfig());
                } else {
                    tvContent.setText("Directory not exist , can not create file.");
                    tvContent.setTextColor(Color.RED);
                    tvReadContent.setText("");
                }
                break;
            case R.id.bnt_del:
                if (mFileUtil.isDirectoryExist(etPath.getText().toString())) {

                    if (mFileUtil.deleteAllFile(new File(etPath.getText().toString()))) {
                        tvContent.setText("Delete all file in : \r\n" + etPath.getText().toString() + " success");
                        tvContent.setTextColor(Color.GREEN);
                        tvReadContent.setText("");
                        spArray.setAdapter(empty);
                        etValue.setText("");
                    } else {
                        tvContent.setText("Delete all file from : \r\n" + etPath.getText().toString() + " failed");
                        tvContent.setTextColor(Color.RED);
                        LoModify.setVisibility(View.GONE);
                        tvReadContent.setText("");
                    }
                } else {
                    tvContent.setText("Directory not exist , can not delete file.");
                    tvContent.setTextColor(Color.RED);
                    LoModify.setVisibility(View.GONE);
                    tvReadContent.setText("");
                }
                break;
            case R.id.btn_read:
                if (mFileUtil.isDirectoryExist(etPath.getText().toString())) {
                    strCurrentReadData = mFileUtil.readFile(etPath.getText().toString(), strCurrentFile.replaceAll("/", ""));
                    try {
                        if (strCurrentReadData != null && strCurrentReadData.length() != 0) {

                            JSONArray jsonAt = new JSONArray(strCurrentReadData);
                            String[] myStrList = new String[jsonAt.length()];
                            for (int i = 0; i < jsonAt.length(); i++) {
                                Log.d("test", jsonAt.getString(i));
                                myStrList[i] = jsonAt.getString(i);
                            }
                            ArrayAdapter<String> jsonArrayList = new ArrayAdapter<>(view.getContext(),
                                    android.R.layout.simple_spinner_dropdown_item,
                                    myStrList);
                            spArray.setAdapter(jsonArrayList);
                            spArray.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(AdapterView parent, View v, int position, long id) {
                                    // parent = 事件發生的母體 spinner_items
                                    // position = 被選擇的項目index = parent.getSelectedItemPosition()
                                    // id = row id，通常給資料庫使用
                                    etValue.setText(String.valueOf(parent.getSelectedItem()));
                                    strCurrentJsonArrayString = String.valueOf(parent.getSelectedItem());
                                    iCurrentArrayIndex = position;
                                }

                                @Override
                                public void onNothingSelected(AdapterView parent) {
                                }
                            });
                        }else{
                            tvContent.setText("This file is empty!");
                            tvContent.setTextColor(Color.RED);
                            spArray.setAdapter(empty);
                            etValue.setText("");
                            LoModify.setVisibility(View.GONE);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        tvContent.setText("The read format error");
                        tvContent.setTextColor(Color.RED);
                        spArray.setAdapter(empty);
                        etValue.setText("");
                        LoModify.setVisibility(View.GONE);
                    }

                    LoModify.setVisibility(View.VISIBLE);
                    tvContent.setText("Read " + strCurrentFile.replaceAll("/", "") + " success ! ");
                    tvContent.setTextColor(Color.GREEN);
                    tvReadContent.setText(strCurrentReadData);
                } else {
                    tvContent.setText("Directory not exist , can not read file.");
                    tvContent.setTextColor(Color.RED);
                    LoModify.setVisibility(View.GONE);
                    tvReadContent.setText("");
                }
                break;
            case R.id.btn_change:
                if (mFileUtil.isDirectoryExist(etPath.getText().toString())) {
                    try {
                        JSONArray jsonAt = new JSONArray(strCurrentReadData);
                        jsonAt.put(iCurrentArrayIndex, new JSONObject(etValue.getText().toString()));
                        mFileUtil.writeFile(etPath.getText().toString(), strCurrentFile.replaceAll("/", ""), jsonAt.toString());
                        Log.d("asadsada", jsonAt.toString());
                        tvContent.setText("Modify file success !");
                        tvContent.setTextColor(Color.GREEN);
                        tvReadContent.setText(mFileUtil.readFile(etPath.getText().toString(), strCurrentFile.replaceAll("/", "")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        tvContent.setText("Input format error");
                        tvContent.setTextColor(Color.RED);
                        spArray.setAdapter(empty);
                        etValue.setText("");

                    }


                } else {
                    tvContent.setText("Directory not exist , can not modify file.");
                    tvContent.setTextColor(Color.RED);
                    LoModify.setVisibility(View.GONE);
                    tvReadContent.setText("");
                }
                break;

        }

    }

    public void init() {
        btnTest.setOnClickListener(this);
        btnMkf.setOnClickListener(this);
        bntDel.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnChange.setOnClickListener(this);
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
