package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBookCompensationServiceHandler extends AddServiceHandler {


    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        HttpServletRequest request = newAddServiceContext.getRequest();
        JSONObject jData = this.getRequestData(request);
        String memberCardNo = JsonHelper.getString(jData, "memberCardNo");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookCompensation newBookCompensation = (BookCompensation) newDataEntity;
        MemberCard memberCard = MemberCardManager.getInstance().getEntityByMemberCardNo(
                newAddServiceContext.session, companyId, memberCardNo);
        if (memberCard == null) {
            throw new Exception("会员卡不存在");
        }
        newBookCompensation.setMemberId(memberCard.getMemberId());
        //获取标记记录
        BookDamage bookDamage = BookDamageManager.getInstance().getEntityByBookIdAndMemberId(
                newAddServiceContext.session, newBookCompensation.getBookId(), newBookCompensation.getMemberId());
        if (bookDamage != null) {
            newBookCompensation.setBookDamageId(bookDamage.getId());
        }
        //修改book下架
        Book theBook = BookManager.getInstance().getEntity(newAddServiceContext.session, newBookCompensation.getBookId());
        theBook.setBookStatusCode(BookStatusCodes.Off);
        BookManager.getInstance().update(newAddServiceContext.session, theBook);
        //添加pay_order账单
        PayOrder newPayOrder = new PayOrder();
        newPayOrder.setCompanyId(companyId);
        newPayOrder.setAmount(newBookCompensation.getAmount());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        newPayOrder.setMemberId(memberCard.getMemberId());
        Date now = new Date();
        String payOrderNo = sdf.format(now);
        newPayOrder.setPayOrderNo(payOrderNo);
        newPayOrder.setBusinessDate(DateHelper.getCurrentTimestamp());
        newPayOrder.setPaymentItemCode("BookDamageCompensate");
        newPayOrder.setPayTime(DateHelper.getCurrentTimestamp());
        newPayOrder.setOperatorId(newBookCompensation.getCompanyUserId());
        newPayOrder.setBookId(newBookCompensation.getBookId());
        newPayOrder.setPaymentMethodCode(newBookCompensation.getPaymentMethodCode());
        newPayOrder.setIsFinished(true);
        newPayOrder.setIsPaid(true);
        PayOrderManager.getInstance().add(newAddServiceContext.session, newPayOrder);
        return newBookCompensation;
    }
}
