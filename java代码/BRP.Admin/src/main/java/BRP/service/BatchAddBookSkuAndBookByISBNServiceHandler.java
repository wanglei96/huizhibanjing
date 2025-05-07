package BRP.service;

import BRP.data.ViewBookSkuInfo;
import BRP.model.BookSkuManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BatchAddBookSkuAndBookByISBNServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        JSONArray jdataList = JsonHelper.getJSONArray(jData, "selectedSkuList");
        List<ViewBookSkuInfo> viewBookSkuInfoList = new ArrayList<ViewBookSkuInfo>();
        for (int i = 0; i < jdataList.size(); i++) {
            JSONObject jdata = jdataList.getJSONObject(i);
            String ISBN = JsonHelper.getString(jdata, "ISBN");
            ViewBookSkuInfo viewBookSkuInfo = JsonHelper.toJavaObject(ViewBookSkuInfo.class, jdata);
            viewBookSkuInfo.setISBN(ISBN);
            viewBookSkuInfo.setPlatformBookSkuId(viewBookSkuInfo.getId());
            viewBookSkuInfoList.add(viewBookSkuInfo);
        }
        Boolean isOn = JsonHelper.getBoolean(jData, "isOn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Lock lock = new ReentrantLock();
        lock.lock(); // 如果获取不到锁会阻塞当前线程，直到获取到锁为止
        try {
            BookSkuManager.getInstance().batchAddBookSkuAndBookByISBN(viewBookSkuInfoList, companyId,isOn);
        } finally {
            lock.unlock();
        }
        this.writeSuccessResponse(response);
    }
}
