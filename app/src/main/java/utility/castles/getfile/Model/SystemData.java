package utility.castles.getfile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SystemData {
    @SerializedName("Info")
    List<Info> listInfo = null;

    public SystemData(List<Info> mlistInfo) {
        this.listInfo = mlistInfo;
    }

    public static class Info{
        @SerializedName("Name")
        public String strName;
        @SerializedName("P_Name")
        public String strP_Name;
        @SerializedName("Type")
        public Byte bType;
        @SerializedName("Install_Time")
        public String strInstall_Time;
        @SerializedName("Update_Time")
        public String strUpdate_Time;
        @SerializedName("Version")
        public String strVersion;
        @SerializedName("VersionCode")
        public int iVersionCode;

        public Info(String strName, String strP_Name, Byte bType, String strInstall_Time, String strUpdate_Time, String strVersion, int iVersionCode) {
            this.strName = strName;
            this.strP_Name = strP_Name;
            this.bType = bType;
            this.strInstall_Time = strInstall_Time;
            this.strUpdate_Time = strUpdate_Time;
            this.strVersion = strVersion;
            this.iVersionCode = iVersionCode;
        }
    }

    @SerializedName("LOCAL_TIMEZONE")
    public String strTZ;
    @SerializedName("LOCAL_AREA")
    public String strArea;

    public void setSystemData(List<Info> listInfo) {

        this.listInfo = listInfo;

    }

    public void setTimeZone(String strTZ) {
        this.strTZ = strTZ;
    }
    public void setTimeArea(String strArea) {
        this.strArea = strArea;
    }
}
