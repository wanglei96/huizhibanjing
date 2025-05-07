package BRP.service;

import BRP.data.ViewCompanyProductStocks;
import BRP.model.ViewCompanyProductStocksManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberGetBookBoxServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        String code = JsonHelper.getString(jData, "code");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            ViewCompanyProductStocks viewCompanyProductStocks =
                    ViewCompanyProductStocksManager.getInstance().getEntityByCodeAndCompanyId(sqlSession, code, companyId);
            if (viewCompanyProductStocks == null) {
                //throw new Exception("书箱库存不足");
                this.writeErrorResponse(response, "书箱库存不足");
                return;
            }

            if (viewCompanyProductStocks.getQuantity() == null || viewCompanyProductStocks.getQuantity() == 0) {
                //throw new Exception("书箱库存不足");
                this.writeErrorResponse(response, "书箱库存不足");
                return;
            }
            String sql = "update company_product_stocks set quantity = quantity - 1 where id = " + viewCompanyProductStocks.getId();
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            //获取会员
            /*String memberSql = "update member set member_bookbox_status_code = 'AlReady' where id = " + memberId;
            MyBatisManager.getInstance().executeUpdate(sqlSession, memberSql);*/
            MyBatisManager.getInstance().commitSession(sqlSession);
            this.writeSuccessResponse(response);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new Exception(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
