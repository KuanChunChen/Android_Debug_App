package utility.castles.getfile.test;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import utility.castles.getfile.App;
import utility.castles.getfile.Model.ModuleInfo;

import utility.castles.getfile.util.FileUtil;
import utility.castles.getfile.util.GetModuleUtil;

import static utility.castles.getfile.Define.Define.PATH_SME_AME;

public class typeTest {

    public static void typeTest (){

        FileUtil.writeFile(FileUtil.getDefaultFolder(App.getContext()), "ExtraModuleList",
                "{\"SME\": [{\"Name\": \"ID_BOOTSULD\", \"SType\": 0}, {\"Name\": \"ID_LINUX_KERNEL \", \"SType\": 3}], \"AME\":[{\"Name\": \"ID_BIOS \", \"SType\": 31}]}");

        if (FileUtil.isDirectoryExist(PATH_SME_AME)) {
            ModuleInfo mMoudleInfoData = new Gson().fromJson(FileUtil.readFile(FileUtil.getDefaultFolder(App.getContext()) ,"ExtraModuleList"), ModuleInfo.class);
            Log.d("MyReadData : ", FileUtil.readFile(FileUtil.getDefaultFolder(App.getContext()), "ExtraModuleList"));
            if (mMoudleInfoData.getSME() != null) {
                List<ModuleInfo.SME> mSmeList = mMoudleInfoData.getSME();
                for (int index = 0; index < mSmeList.size(); index++) {
                    ModuleInfo.SME mSmeData = new Gson().fromJson(String.valueOf(mSmeList.get(index)), ModuleInfo.SME.class);

                    String strName = mSmeData.getName();
                    int iStype = mSmeData.getSType();
                    String strMoudleVer = GetModuleUtil.getModuleVersion(strName, iStype);
                    Log.d("MyData :", "Name : " + strName + "\r\n"+
                            "iStype : " + iStype + "\r\n"+
                            "strMoudleVer : " + strMoudleVer + "\r\n");

                }
            }

            if (mMoudleInfoData.getAME() != null) {
                List<ModuleInfo.AME> mAmeList = mMoudleInfoData.getAME();
                for (int index = 0; index < mAmeList.size(); index++) {
                    ModuleInfo.AME mAmeData = new Gson().fromJson(String.valueOf(mAmeList.get(index)), ModuleInfo.AME.class);

                    String strName = mAmeData.getName();
                    int iStype = mAmeData.getSType();
                    String strMoudleVer = GetModuleUtil.getModuleVersion(strName, iStype);
                    Log.d("MyData :", "Name : " + strName + "\r\n"+
                            "iStype : " + iStype + "\r\n"+
                            "strMoudleVer : " + strMoudleVer + "\r\n");
                }
            }
        }

    }
}
