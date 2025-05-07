package BRP.model;

import BRP.data.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.Tracer;
import strosoft.wx.common.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MessageManager extends MessageMaster {
    private static MessageManager _instance;

    private MessageManager() {
        this.setTableName("message");
        this.setTypeName("Message");
    }

    public static MessageManager getInstance() {
        if (_instance == null) {
            _instance = new MessageManager();
        }
        return _instance;
    }

    /**
     * 发送模板消息
     */
    public String sendWxTemplateMessage(ViewBookOrder theViewBookOrder)
            throws Exception {
        //获取theViewBookOrder下所有借书的书
        String condition = String.format("book_order_id='%s' and book_order_type_code='%s'",
                theViewBookOrder.getId(), BookOrderTypeCodes.Borrow);
        List<ViewBookOrderItem> lstViewBookOrderItem = ViewBookOrderItemManager.getInstance().getList(condition);
        String bookNames = "";
        if (lstViewBookOrderItem.size() > 0) {
            bookNames = lstViewBookOrderItem.stream()
                    .map(ViewBookOrderItem::getBookName)
                    .map(bookName -> "《" + bookName + "》")
                    .collect(Collectors.joining(", "));
        }
        String deliveredTime = DateHelper.getDateTimeString();
        if (theViewBookOrder.getDeliveredTime() != null) {
            deliveredTime = DateHelper.timestampToString(theViewBookOrder.getDeliveredTime(), "yyyy-MM-dd HH:mm:ss");
        }


        WxClient theWXClient = new WxClient(WxConfig.getWxAppId(), WxConfig.getWxAppSecret());
        WxAccessToken theWXAccessToken = theWXClient.GetAccessToken();

        WxTemplateMessage newWXTemplateMessage = new WxTemplateMessage();
        newWXTemplateMessage.setToUser(theViewBookOrder.getWxOpenId());
        newWXTemplateMessage.setTemplateID("kfodeO1VSX7aw3I54PjgwHeiKkhSy6PgrMnukPPjKzY");
        //newWXTemplateMessage.setUrl("http://www.strosoft.com?templateID=" + newWXTemplateMessage.TemplateID);
        //跳小程序所需数据，不需跳小程序可不用传该数据
        WxTemplateMessageMiniProgram newWxTemplateMessageMiniProgram = new WxTemplateMessageMiniProgram();
        newWxTemplateMessageMiniProgram.setAppID(WxConfig.getWxaAppId());
        newWxTemplateMessageMiniProgram.setPagePath("pages/books/index");
        newWXTemplateMessage.setMiniProgram(newWxTemplateMessageMiniProgram);


        HashMap<String, JSONObject> requestData = new HashMap<String, JSONObject>();

        //订单编号
        JSONObject jFirst1 = new JSONObject();
        jFirst1.put("value", theViewBookOrder.getBookOrderNo());
        requestData.put("character_string7", jFirst1);
        Tracer.writeLine("订单编号: " + theViewBookOrder.getBookOrderNo());
        //送达时间
        JSONObject jFirst2 = new JSONObject();
        jFirst2.put("value", deliveredTime);
        requestData.put("time5", jFirst2);
        Tracer.writeLine("图书: " + bookNames);
        if (bookNames.length() > 14) {
            bookNames = bookNames.substring(0, 14) + "...";
        }
        Tracer.writeLine("图书缩略: " + bookNames);
        //商品名称
        JSONObject jFirst3 = new JSONObject();
        jFirst3.put("value", bookNames);
        requestData.put("thing14", jFirst3);

        newWXTemplateMessage.setData(requestData);
        String messageID = theWXClient.SendTempateMessage(theWXAccessToken.getAccessToken(), newWXTemplateMessage);
        return messageID;
    }

    /**
     * 停卡办理成功通知
     *
     * @param stopDate
     * @param stopEndDate
     * @param wxOpenId
     */
    public void sendWxStopCardMessage(String stopDate, String stopEndDate, String wxOpenId) throws Exception {

        WxClient theWXClient = new WxClient(WxConfig.getWxAppId(), WxConfig.getWxAppSecret());
        WxAccessToken theWXAccessToken = theWXClient.GetAccessToken();

        WxTemplateMessage newWXTemplateMessage = new WxTemplateMessage();
        newWXTemplateMessage.setToUser(wxOpenId);
        newWXTemplateMessage.setTemplateID("FItE6Hbqlhv_imSMJM53HPeyZ_hTuTjtefne7m9PZu8");
        //newWXTemplateMessage.setUrl("http://www.strosoft.com?templateID=" + newWXTemplateMessage.TemplateID);
        //跳小程序所需数据，不需跳小程序可不用传该数据
        WxTemplateMessageMiniProgram newWxTemplateMessageMiniProgram = new WxTemplateMessageMiniProgram();
        newWxTemplateMessageMiniProgram.setAppID(WxConfig.getWxaAppId());
        newWxTemplateMessageMiniProgram.setPagePath("pages/home/index");
        newWXTemplateMessage.setMiniProgram(newWxTemplateMessageMiniProgram);


        HashMap<String, JSONObject> requestData = new HashMap<String, JSONObject>();

        //开始时间
        JSONObject jFirst1 = new JSONObject();
        jFirst1.put("value", stopDate);
        requestData.put("time1", jFirst1);

        //结束时间
        JSONObject jFirst2 = new JSONObject();
        jFirst2.put("value", stopEndDate);
        requestData.put("time2", jFirst2);
        //办理时间
        JSONObject jFirst3 = new JSONObject();
        jFirst3.put("value", DateHelper.getTimeString());
        requestData.put("time3", jFirst3);

        newWXTemplateMessage.setData(requestData);
        String messageID = theWXClient.SendTempateMessage(theWXAccessToken.getAccessToken(), newWXTemplateMessage);

    }

    /**
     * 发送借阅超期提醒
     *
     * @param messageTypeCode
     * @throws Exception
     */
    public void sendBookBorrowedReturnMessage(String messageTypeCode) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            Integer day = 15;
            if (messageTypeCode.equals(MessageTypeCodes.ThirtyNotReturned)) {
                day = 30;
            }
            //获取借阅数据
            ArrayList<LinkedHashMap<String, Object>> alData = this.getMessageData(session, messageTypeCode, day);
            if (alData != null && alData.size() > 0) {
                List<BookOrderItemMessageInfo> lstBookOrderItemMessageInfo =
                        JsonHelper.toJavaList(BookOrderItemMessageInfo.class, alData);
                Map<Integer, List<BookOrderItemMessageInfo>> bookOrderItemMap = lstBookOrderItemMessageInfo.stream()
                        .collect(Collectors.groupingBy(BookOrderItemMessageInfo::getMemberId));
                List<MessageBook> newMessageBookList = new ArrayList<>();
                for (Map.Entry<Integer, List<BookOrderItemMessageInfo>> entry : bookOrderItemMap.entrySet()) {
                    Integer memberId = entry.getKey();
                    List<BookOrderItemMessageInfo> lstBookOrderItem = entry.getValue();
                    if (lstBookOrderItem.size() > 0) {

                        String bookNames = lstBookOrderItem.stream()
                                .map(BookOrderItemMessageInfo::getBookName)
                                .map(bookName -> "《" + bookName + "》")
                                .collect(Collectors.joining(", "));

                        //添加消息
                        Message newMessage = new Message();
                        newMessage.setCompanyId(lstBookOrderItem.get(0).getCompanyId());
                        newMessage.setPushTime(DateHelper.getCurrentTimestamp());
                        newMessage.setMemberId(memberId);
                        newMessage.setMessageTypeCode(messageTypeCode);
                        newMessage.setContent("您借阅的图书" + bookNames + "已借阅" + day + "天，请及时归还。");
                        Integer newMessageId = MessageManager.getInstance().add(session, newMessage);

                        for (BookOrderItemMessageInfo bookOrderItemMessageInfo : lstBookOrderItem) {
                            MessageBook newMessageBook = new MessageBook();
                            newMessageBook.setBookId(bookOrderItemMessageInfo.getBookId());
                            newMessageBook.setMessageId(newMessageId);
                            newMessageBookList.add(newMessageBook);
                        }

                        if (lstBookOrderItem.get(0).getWxaOpenId() != null) {
                            WxClient theWXClient = new WxClient(WxConfig.getWxaAppId(), WxConfig.getWxaAppSecret());
                            WxAccessToken theWXAccessToken = theWXClient.GetAccessToken();
                            WxaSubscribeMessage newWxSubscribeMessage = new WxaSubscribeMessage();
                            newWxSubscribeMessage.setToUser(lstBookOrderItem.get(0).getWxaOpenId());
                            newWxSubscribeMessage.setTemplateId("T6yoA-2ZrVAtXNQ89le-rkW3QrNcDDRqr98R8l4qLdY");
                            newWxSubscribeMessage.setPage("/pages/books/index");

                            HashMap<String, JSONObject> hmRequestData = new HashMap<>();

                            JSONObject jFirst1 = new JSONObject();
                            if (bookNames.length() > 14) {
                                bookNames = bookNames.substring(0, 14) + "...";
                            }
                            jFirst1.put("value", bookNames);
                            hmRequestData.put("thing2", jFirst1);

                            JSONObject jFirst2 = new JSONObject();
                            String time6 = DateHelper.timestampToString(lstBookOrderItem.get(0).getCreateTime(), "yyyy年M月d日 HH:mm");
                            jFirst2.put("value", time6);
                            hmRequestData.put("time6", jFirst2);

                            JSONObject jFirst3 = new JSONObject();

                            Date createTime = lstBookOrderItem.get(0).getCreateTime();

                            // 创建 Calendar 对象，并设置为 createTime 的日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(createTime);

                            // 将日期增加30天
                            calendar.add(Calendar.DAY_OF_MONTH, 30);

                            // 将 Calendar 对象转换回 Date 对象
                            Date newDate = calendar.getTime();

                            // 格式化新日期
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日 HH:mm");
                            String formattedDate = sdf.format(newDate);

                            //Date addDays = DateHelper.addDays(lstBookOrderItem.get(0).getCreateTime(), 30);
                            jFirst3.put("value", formattedDate);
                            hmRequestData.put("time7", jFirst3);

                            JSONObject jFirst4 = new JSONObject();
                            jFirst4.put("value", String.format("图书已经借阅%s天，请及时归还", day));
                            hmRequestData.put("thing5", jFirst4);

                            newWxSubscribeMessage.setData(hmRequestData);

                            JSONObject jResult = theWXClient.SendWxaTempateMessage(theWXAccessToken.getAccessToken(), newWxSubscribeMessage);
                            /*String errorCode = JsonHelper.getString(jResult, "errcode");
                            if (errorCode != null && !errorCode.equals("") && !errorCode.equals("0")) {
                                break;
                            }*/
                        }
                    }
                }
                MessageBookManager.getInstance().addList(session, newMessageBookList);
            }
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 获取借阅超期提醒数据
     *
     * @param session
     * @param messageTypeCode
     * @param day
     * @return
     * @throws Exception
     */
    private ArrayList<LinkedHashMap<String, Object>> getMessageData(SqlSession session, String messageTypeCode, Integer day) throws Exception {

        //获取所有借阅的图书
        String sql = String.format("SELECT book_order_item.*,\n" +
                "\t\t\t book_order.member_id AS member_id,\n" +
                "\t\t\t book_order.company_id AS company_id,\n" +
                "\t\t\t book_sku.book_name AS book_name,\n" +
                "\t\t\t member.wxa_open_id AS wxa_open_id\n" +
                "FROM book_order_item\n" +
                "LEFT JOIN book_order ON book_order_item.book_order_id = book_order.id\n" +
                "LEFT JOIN member ON book_order.member_id = member.id\n" +
                "LEFT JOIN book ON book_order_item.book_id = book.id\n" +
                "LEFT JOIN book_sku ON book.book_sku_id = book_sku.id\n" +
                "LEFT JOIN (\n" +
                "    SELECT mb.member_id, mb.book_id\n" +
                "    FROM view_message_book mb\n" +
                "    WHERE mb.message_type_code = '%s'\n" +
                ") AS vm\n" +
                "ON vm.member_id = member.id AND vm.book_id = book.id\n" +
                "WHERE book_order_item.book_order_type_code = 'Borrow' \n" +
                "AND book_order_item.is_returned IS NOT TRUE\n" +
                "AND DATE_SUB(CURDATE(), INTERVAL %s DAY) = DATE(book_order_item.create_time)\n" +
                "AND vm.member_id IS NULL", messageTypeCode, day);
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(session, sql);
        return alData;
    }

    /**
     * 发送图书可借阅通知
     *
     * @param lstViewMemberBookSubscription
     */
    public void sendCanBorrowedMessage(List<ViewMemberBookSubscription> lstViewMemberBookSubscription) throws Exception {
        WxClient theWXClient = new WxClient(WxConfig.getWxaAppId(), WxConfig.getWxaAppSecret());
        WxAccessToken theWXAccessToken = theWXClient.GetAccessToken();
        for (ViewMemberBookSubscription item : lstViewMemberBookSubscription) {
            if (item.getWxaOpenId() != null) {
                WxaSubscribeMessage newWxSubscribeMessage = new WxaSubscribeMessage();
                newWxSubscribeMessage.setToUser(item.getWxaOpenId());
                newWxSubscribeMessage.setTemplateId("rtoTf-yZAaIH04Lo2nfbjYuXoLyqYwq_66YBuF_DW3k");
                newWxSubscribeMessage.setPage("/pages/home/booksDetail?bookSkuID="+item.getBookSkuId());

                HashMap<String, JSONObject> hmRequestData = new HashMap<>();

                JSONObject jFirst1 = new JSONObject();
                jFirst1.put("value", "《" + item.getBookName() + "》");
                hmRequestData.put("thing1", jFirst1);

                JSONObject jFirst2 = new JSONObject();
                jFirst2.put("value", "可借");
                hmRequestData.put("phrase2", jFirst2);

                JSONObject jFirst3 = new JSONObject();
                jFirst3.put("value", "赶快借阅吧！");
                hmRequestData.put("thing3", jFirst3);

                newWxSubscribeMessage.setData(hmRequestData);

                theWXClient.SendWxaTempateMessage(theWXAccessToken.getAccessToken(), newWxSubscribeMessage);
            }
        }
    }

    /**
     * 会员到期提醒
     */
    public void sendMemberExpirationMessage() throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            //获取会员到期提醒数据
            ArrayList<LinkedHashMap<String, Object>> alData = getMemberExpirationData(session);
            if (alData.size() > 0) {
                WxClient theWXClient = new WxClient(WxConfig.getWxaAppId(), WxConfig.getWxaAppSecret());
                WxAccessToken theWXAccessToken = theWXClient.GetAccessToken();
                List<ViewMember> lstViewMember = JsonHelper.toJavaList(ViewMember.class, alData);
                List<Message> newMessageList = new ArrayList<>();
                for (ViewMember item : lstViewMember) {

                    // 获取当前时间的 Timestamp
                    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

                    // 计算天数差异
                    long millisecondsDiff = item.getMemberCardExpireDate().getTime() - currentTimestamp.getTime();
                    long daysDiff = millisecondsDiff / (1000 * 60 * 60 * 24);
                    //添加消息记录
                    Message newMessage = new Message();
                    newMessage.setMemberId(item.getId());
                    newMessage.setCompanyId(item.getCompanyId());
                    newMessage.setMessageTypeCode(MessageTypeCodes.MemberExpiration);
                    newMessage.setPushTime(DateHelper.getCurrentTimestamp());
                    newMessage.setContent("会员将于" + daysDiff + "天后到期，请及时续费！");
                    newMessageList.add(newMessage);
                    if (item.getWxaOpenId() != null) {
                        WxaSubscribeMessage newWxSubscribeMessage = new WxaSubscribeMessage();
                        newWxSubscribeMessage.setToUser(item.getWxaOpenId());
                        newWxSubscribeMessage.setTemplateId("rUfZ2uKPijZ1uDSk67lzhVsmWJCWaPviAQFgEPm7LkM");
                        newWxSubscribeMessage.setPage("/pages/user/index");

                        HashMap<String, JSONObject> hmRequestData = new HashMap<>();

                        JSONObject jFirst1 = new JSONObject();
                        jFirst1.put("value", item.getName());
                        hmRequestData.put("thing1", jFirst1);

                        JSONObject jFirst2 = new JSONObject();
                        jFirst2.put("value", DateHelper.timestampToString(item.getMemberCardExpireDate(), "yyyy年M月d日 HH:mm"));
                        hmRequestData.put("time2", jFirst2);


                        JSONObject jFirst3 = new JSONObject();
                        jFirst3.put("value", "会员将于" + daysDiff + "天后到期，请及时续费");
                        hmRequestData.put("thing3", jFirst3);

                        newWxSubscribeMessage.setData(hmRequestData);

                        JSONObject jResult = theWXClient.SendWxaTempateMessage(theWXAccessToken.getAccessToken(), newWxSubscribeMessage);
                        /*String errorCode = JsonHelper.getString(jResult, "errcode");
                        if (errorCode != null && !errorCode.equals("") && errorCode.equals("0")) {

                        }*/
                    }
                }
                MessageManager.getInstance().addList(session, newMessageList);
            }
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 获取会员到期数据
     *
     * @param session
     * @return
     */
    private ArrayList<LinkedHashMap<String, Object>> getMemberExpirationData(SqlSession session) throws Exception {
        String sql = "SELECT *\n" +
                "FROM view_member\n" +
                "LEFT JOIN (\n" +
                "    SELECT message.member_id\n" +
                "    FROM message\n" +
                "    WHERE message.message_type_code = 'MemberExpiration' and DATEDIFF(message.create_time, CURDATE()) < 30\n" +
                ") AS message\n" +
                "ON message.member_id = view_member.id\n" +
                "WHERE DATEDIFF(view_member.member_card_expire_date, CURDATE()) >= 0 AND DATEDIFF(view_member.member_card_expire_date, CURDATE()) <= 30\n" +
                "AND message.member_id IS NULL";
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(session, sql);
        return alData;
    }

    /**
     * 发送送达通知
     *
     * @param theViewBookOrder
     */
    public String sendDeliveryMessage(ViewBookOrder theViewBookOrder, List<ViewBookOrderItem> lstViewBookOrderItem, String bookNames) throws Exception {

        /*String bookNames = "";
        if (lstViewBookOrderItem.size() > 0) {
            bookNames = lstViewBookOrderItem.stream()
                    .map(ViewBookOrderItem::getBookName)
                    .map(bookName -> "《" + bookName + "》")
                    .collect(Collectors.joining(", "));
        }*/
        WxClient theWXClient = new WxClient(WxConfig.getWxaAppId(), WxConfig.getWxaAppSecret());
        WxAccessToken theWXAccessToken = theWXClient.GetAccessToken();
        WxaSubscribeMessage newWxSubscribeMessage = new WxaSubscribeMessage();
        newWxSubscribeMessage.setToUser(theViewBookOrder.getWxaOpenId());
        newWxSubscribeMessage.setTemplateId("i2jBP4bt8e7vkWbJJNIlbcl7hxUecYVAQovmDpNYcGo");
        newWxSubscribeMessage.setPage("/pages/books/index");

        HashMap<String, JSONObject> hmRequestData = new HashMap<>();

        JSONObject jFirst1 = new JSONObject();
        if (bookNames.length() > 14) {
            bookNames = bookNames.substring(0, 14) + "...";
        }
        jFirst1.put("value", bookNames);
        hmRequestData.put("thing1", jFirst1);

        // 假设你有一个Date对象
        String time1 = DateHelper.timestampToString(theViewBookOrder.getCreateTime(), "yyyy年M月d日 HH:mm");

        JSONObject jFirst2 = new JSONObject();
        jFirst2.put("value", time1);
        hmRequestData.put("time2", jFirst2);

        JSONObject jFirst3 = new JSONObject();
        jFirst3.put("value", "快去书箱领取新书吧！");
        hmRequestData.put("thing3", jFirst3);

        newWxSubscribeMessage.setData(hmRequestData);

        JSONObject jResult = theWXClient.SendWxaTempateMessage(theWXAccessToken.getAccessToken(), newWxSubscribeMessage);
        /*String errorCode = JsonHelper.getString(jResult, "errcode");
        if (errorCode != null && !errorCode.equals("") && errorCode.equals("0")) {

        }*/
        return jResult.toString();
    }

    /**
     * 解除停卡发送消息
     *
     * @param theMemberCardDisableLog
     * @param theMember
     */
    public void sendWxaStopCardMessage(MemberCardDisableLog theMemberCardDisableLog, Member theMember, MemberCard theMemberCard) throws Exception {
        WxClient theWXClient = new WxClient(WxConfig.getWxaAppId(), WxConfig.getWxaAppSecret());
        WxAccessToken theWXAccessToken = theWXClient.GetAccessToken();
        WxaSubscribeMessage newWxSubscribeMessage = new WxaSubscribeMessage();
        newWxSubscribeMessage.setToUser(theMember.getWxaOpenId());
        newWxSubscribeMessage.setTemplateId("43yYs8iYed4EE4nHKuG7RRvrmOBP-Y0VcnqNYmrmioo");
        newWxSubscribeMessage.setPage("/pages/user/index");

        HashMap<String, JSONObject> hmRequestData = new HashMap<>();

        JSONObject jFirst1 = new JSONObject();
        jFirst1.put("value", theMember.getName());
        hmRequestData.put("thing1", jFirst1);

        JSONObject jFirst2 = new JSONObject();
        jFirst2.put("value", theMemberCard.getCardNo());
        hmRequestData.put("character_string2", jFirst2);

        String stopDate = DateHelper.timestampToString(theMemberCardDisableLog.getStopDate(), "yyyy年M月d日 HH:mm");
        JSONObject jFirst3 = new JSONObject();
        jFirst3.put("value", stopDate);
        hmRequestData.put("time3", jFirst3);

        String stopEndDate = DateHelper.timestampToString(theMemberCardDisableLog.getStopEndDate(), "yyyy年M月d日 HH:mm");
        JSONObject jFirst4 = new JSONObject();
        jFirst4.put("value", stopEndDate);
        hmRequestData.put("time4", jFirst4);

        JSONObject jFirst5 = new JSONObject();
        jFirst5.put("value", "您的会员卡暂停已结束，可以正常使用了！");
        hmRequestData.put("thing5", jFirst5);

        newWxSubscribeMessage.setData(hmRequestData);

        JSONObject jResult = theWXClient.SendWxaTempateMessage(theWXAccessToken.getAccessToken(), newWxSubscribeMessage);
        /*String errorCode = JsonHelper.getString(jResult, "errcode");
        if (errorCode != null && !errorCode.equals("") && errorCode.equals("0")) {

        }*/
    }
}