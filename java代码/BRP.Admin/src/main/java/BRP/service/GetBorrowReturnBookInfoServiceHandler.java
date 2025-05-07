package BRP.service;

import BRP.data.BorrowReturnBookInfo;
import BRP.data.Member;
import BRP.data.ViewMemberCard;
import BRP.model.MemberManager;
import BRP.model.ViewBookOrderItemManager;
import BRP.model.ViewMemberCardManager;
import BRP.model.ViewMemberManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetBorrowReturnBookInfoServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        //String memberInfo = JsonHelper.getString(jData, "memberInfo");
        Integer memberCartId = JsonHelper.getInt(jData, "memberCartId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BorrowReturnBookInfo borrowReturnBookInfo = ViewMemberCardManager.getInstance().getBorrowReturnBookInfo(companyId, memberCartId);
        if (borrowReturnBookInfo != null) {
            if (borrowReturnBookInfo.getBirthday() != null) {
                borrowReturnBookInfo.setBirthdayText(DateHelper.timestampToString(borrowReturnBookInfo.getBirthday(), "yyyy-MM-dd"));
            }
            if (borrowReturnBookInfo.getExpireDate() != null) {
                borrowReturnBookInfo.setExpireDateText(DateHelper.timestampToString(borrowReturnBookInfo.getExpireDate(), "yyyy-MM-dd"));
            }
        } else {
            throw new Exception("会员信息不存在");
        }
        //判断会员是否可以借书
        borrowReturnBookInfo.setCanBorrowed(true);
        //获取会员卡已借阅数量
        Integer borrowedQuantity = ViewBookOrderItemManager.getInstance().getBorrowedQuantity(borrowReturnBookInfo.getMemberId());
        //获取会员卡总借阅数量
        Integer allQuantity = borrowReturnBookInfo.getMaxBorrowableQuantity().intValue() + borrowReturnBookInfo.getExtendQuantity().intValue();
        String notBorrowedCause = "";
        if (borrowedQuantity >= allQuantity) {
            notBorrowedCause += "这张会员卡最多可借" + allQuantity + "本！\n";
            borrowReturnBookInfo.setCanBorrowed(false);
            //borrowReturnBookInfo.setNotBorrowedCause("这张会员卡最多可借" + allQuantity + "本！");
        }
        if (borrowReturnBookInfo.getExpireDate() != null && borrowReturnBookInfo.getExpireDate().before(DateHelper.getCurrentTimestamp())) {
            notBorrowedCause += "会员卡过期：" + DateHelper.timestampToString(borrowReturnBookInfo.getExpireDate(), "yyyy-MM-dd") + "\n";
            borrowReturnBookInfo.setCanBorrowed(false);
            //borrowReturnBookInfo.setNotBorrowedCause("\n会员卡过期！");
        }
        if (borrowReturnBookInfo.getMemberCardStatusCode() != null && borrowReturnBookInfo.getMemberCardStatusCode().equals("Locked")) {
            notBorrowedCause += "会员卡锁定！\n";
            borrowReturnBookInfo.setCanBorrowed(false);
            //borrowReturnBookInfo.setNotBorrowedCause("\n会员卡锁定！");
        }
        if (borrowReturnBookInfo.getIsStopCard() != null && borrowReturnBookInfo.getIsStopCard()) {
            notBorrowedCause += "会员卡停卡中！\n";
            borrowReturnBookInfo.setCanBorrowed(false);
        }
        borrowReturnBookInfo.setNotBorrowedCause(notBorrowedCause);
        this.writeSuccessResponse(response, borrowReturnBookInfo);
    }
}
