package utility.castles.getfile.util;

import CTOS.CtSystem;
import CTOS.CtSystemException;

import static utility.castles.getfile.Define.Define.ID_BIOS;
import static utility.castles.getfile.Define.Define.ID_BOOTSULD;
import static utility.castles.getfile.Define.Define.ID_CADRV_KO;
import static utility.castles.getfile.Define.Define.ID_CAUSB_KO;
import static utility.castles.getfile.Define.Define.ID_CIF_KO;
import static utility.castles.getfile.Define.Define.ID_CLDRV_KO;
import static utility.castles.getfile.Define.Define.ID_CLVWM_MP;
import static utility.castles.getfile.Define.Define.ID_CRADLE_MP;
import static utility.castles.getfile.Define.Define.ID_CRYPTO_HAL;
import static utility.castles.getfile.Define.Define.ID_EMVCL_SO;
import static utility.castles.getfile.Define.Define.ID_EMV_SO;
import static utility.castles.getfile.Define.Define.ID_KMS;
import static utility.castles.getfile.Define.Define.ID_LIBCABARCODE_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAEMVL2_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAETHERNET_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAFONT_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAFS_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAGSM_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAKMS_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCALCD_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAMODEM_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAPMODEM_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAPRT_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCARTC_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAUART_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAULDPM_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCAUSBH_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCLVW_SO;
import static utility.castles.getfile.Define.Define.ID_LIBCTOSAPI_SO;
import static utility.castles.getfile.Define.Define.ID_LIBTLS_SO;
import static utility.castles.getfile.Define.Define.ID_LINUX_KERNEL;
import static utility.castles.getfile.Define.Define.ID_ROOTFS;
import static utility.castles.getfile.Define.Define.ID_SAM_KO;
import static utility.castles.getfile.Define.Define.ID_SECURITY_KO;
import static utility.castles.getfile.Define.Define.ID_SYSUPD_KO;
import static utility.castles.getfile.Define.Define.ID_TMS;
import static utility.castles.getfile.Define.Define.ID_ULDPM;

public class GetModuleUtil {

    public static String getModuleVersion(String strName, int iStype) {


        CtSystem CTOSSystem = new CtSystem();
        String strReturn = "None";
        if (strName == null || strName == "" || strName.equals("")) {
            return strReturn;
        }


        switch (strName) {

            case ID_BOOTSULD:
            case ID_BIOS:
            case ID_CADRV_KO:
            case ID_CRYPTO_HAL:
            case ID_LINUX_KERNEL:
            case ID_SECURITY_KO:
            case ID_SYSUPD_KO:
            case ID_KMS:
            case ID_CAUSB_KO:
            case ID_LIBCAUART_SO:
            case ID_LIBCAUSBH_SO:
            case ID_LIBCAMODEM_SO:
            case ID_LIBCAETHERNET_SO:
            case ID_LIBCAFONT_SO:
            case ID_LIBCALCD_SO:
            case ID_LIBCAPRT_SO:
            case ID_LIBCARTC_SO:
            case ID_LIBCAULDPM_SO:
            case ID_LIBCAPMODEM_SO:
            case ID_LIBCAGSM_SO:
            case ID_LIBCAEMVL2_SO:
            case ID_LIBCAKMS_SO:
            case ID_LIBCAFS_SO:
            case ID_LIBCABARCODE_SO:
            case ID_CRADLE_MP:
            case ID_LIBTLS_SO:
            case ID_LIBCLVW_SO:
            case ID_LIBCTOSAPI_SO:
            case ID_SAM_KO:
            case ID_CLVWM_MP:
            case ID_ROOTFS:
            case ID_CIF_KO:
            case ID_CLDRV_KO:
            case ID_TMS:
            case ID_ULDPM:
            case ID_EMV_SO:
            case ID_EMVCL_SO:
                try {
                    strReturn = CTOSSystem.getModuleVersion(iStype);
                } catch (CtSystemException e) {
                    e.printStackTrace();
                    strReturn = "None";
                }
                break;
            default:
                strReturn = "None";
                break;

        }


        return strReturn;
    }
}
