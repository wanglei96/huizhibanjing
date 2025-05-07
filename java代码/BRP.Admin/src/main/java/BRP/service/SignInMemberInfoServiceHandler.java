package BRP.service;

import BRP.data.Member;
import BRP.model.MemberManager;
import net.sf.json.JSONObject;
import org.springframework.util.CollectionUtils;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 * 预登记会员信息
 */
public class SignInMemberInfoServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        // 会员信息
        Integer companyId = JsonHelper.getInt(jData, "companyId"); // 门店id
        String wxOpenId = JsonHelper.getString(jData, "wxOpenId"); // 微信openid
        String name = JsonHelper.getString(jData, "name"); // 会员姓名
        String birthday = JsonHelper.getString(jData, "birthday"); // 出生日期
        String mobilePhone = JsonHelper.getString(jData, "mobilePhone"); // 联系方式
        Boolean gender = JsonHelper.getBoolean(jData, "gender"); // 性别
        String school = JsonHelper.getString(jData, "school"); // 学校
        Integer gradeId = JsonHelper.getInt(jData, "gradeId"); // 年级
        Integer classId = JsonHelper.getInt(jData, "classId"); // 班级
        String referrer = JsonHelper.getString(jData, "referrer"); // 推荐人

        if(companyId == null){
            this.writeErrorResponse(response,"companyId不允许为空");
            return;
        }
        if(StringHelper.isNullOrEmpty(wxOpenId)){
            this.writeErrorResponse(response,"wxOpenId不允许为空");
            return;
        }
        List<Member> memberList = MemberManager.getInstance().getList("wx_open_id = " + wxOpenId);
        if(CollectionUtils.isEmpty(memberList)){
            this.writeErrorResponse(response,"当前用户不存在");
            return;
        }
        Member member = memberList.get(0);
        member.setCompanyId(companyId);
        member.setName(name);
        member.setBirthday(DateHelper.toTimestamp(birthday));
        member.setMobilePhone(mobilePhone);
        member.setGender(gender);
        member.setSchool(school);
        member.setGradeId(gradeId);
        member.setClassId(classId);
        member.setReferrer(referrer);
        member.setIsActivated(false);
        MemberManager.getInstance().update(member);
        this.writeSuccessResponse(response, "会员登记成功");
    }
}