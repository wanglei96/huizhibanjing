package BRP.service;

import BRP.data.BookOrder;
import BRP.data.Company;
import BRP.data.Member;
import BRP.data.SalesOrderItem;
import BRP.model.SalesOrderItemManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.UpdateServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

/**
 * 注销会员
 */
public class CancelMemberServiceHandler extends UpdateServiceHandler {
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        Member theMember = (Member) newDataEntity;
        theMember.setMobilePhone(null);
        theMember.setUnionId(null);
        theMember.setWxaOpenId(null);
        theMember.setWxOpenId(null);
        return theMember;
    }

    @Override
    protected void postProcess(ServiceContext updateServiceContext) throws Exception {
        HttpServletRequest request = updateServiceContext.getRequest();
        JSONObject jData = getRequestData(request);
        Member theMember = (Member) updateServiceContext.getDataEntity();
        String sql = "update member_card set disabled = 1 where member_id = " + theMember.getId();
        MyBatisManager.getInstance().executeUpdate(updateServiceContext.getSession(), sql);
    }
}
