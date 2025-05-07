package BRP.service;

import BRP.model.BookManager;
import BRP.model.BookSkuManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddBookByBookSkuIdServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer count = JsonHelper.getInt(jData, "count");
        Integer companyId = jData.getInt("companyId");
        Integer bookSkuId = jData.getInt("bookSkuId");
        Lock lock = new ReentrantLock();
        lock.lock(); // 如果获取不到锁会阻塞当前线程，直到获取到锁为止
        try {
            BookManager.getInstance().addBookByBookSkuId(companyId, bookSkuId, count);
        } finally {
            lock.unlock();
        }
        this.writeSuccessResponse(response);
    }
}
