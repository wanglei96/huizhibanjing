package BRP.model;

import net.sf.json.JSONObject;
import strosoft.app.util.HttpHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TianApiUtils {
    public static JSONObject getTianJieJiaRi(String date,String type) throws Exception {

        String key = "7be4f16833d1e0f286fb6926e3cfa40d";

        String url = String.format("https://apis.tianapi.com/jiejiari/index?key=%s&date=%s&type=%s"
                , key, date, type);
        String strResult =HttpHelper.get(url);
        JSONObject result = JSONObject.fromObject(strResult);
        return result;
    }
}
