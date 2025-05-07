package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetTianHolidayServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            //获取法定节假日列表
            List<Holiday> lstHoliday = HolidayManager.getInstance().getList(sqlSession);
            if (lstHoliday.size() > 0) {
                this.writeSuccessResponse(response, lstHoliday);
            } else {
                // 获取今年的一月一号
                LocalDate firstDayOfYear = LocalDate.now().withMonth(1).withDayOfMonth(1);
                // 格式化日期为字符串
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = firstDayOfYear.format(formatter);
                String type = "1";
                JSONObject jResult = TianApiUtils.getTianJieJiaRi(date, type);
                // 获取节假日列表
                JSONArray holidayList = jResult.getJSONObject("result").getJSONArray("list");
                List<Holiday> newHoliday = JsonHelper.toJavaList(Holiday.class, holidayList);
                HolidayManager.getInstance().addList(sqlSession, newHoliday);
                this.writeSuccessResponse(response, newHoliday);
            }
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}
