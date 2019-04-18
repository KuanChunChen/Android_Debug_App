package utility.castles.getfile.Controller;

import CTOS.CtCtms;

public class CtmsConfigFun {

    CtCtms mCtCtms = new CtCtms();
    String strReturn = "";

    public String getAllconfig() {
        return mCtCtms.getAllConfig();
    }


    public void setAllConfig(String strAllConfigJson) {
        mCtCtms.setAllConfig(strAllConfigJson);
        return;
    }

}
