package BRP.service;

import BRP.data.Member;
import BRP.data.MemberScore;
import BRP.model.MemberManager;
import BRP.model.MemberScoreManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class EditMemberScoreServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        String memberScoreTypeCode = JsonHelper.getString(jData, "memberScoreTypeCode");
        Integer score = JsonHelper.getInt(jData, "score");
        String operator = JsonHelper.getString(jData, "operator");
        String type = JsonHelper.getString(jData, "type");

        if(score < 0){
            this.writeErrorResponse(response,"积分不允许小于0");
            return;
        }

        // 生成会员积分
        MemberScore memberScore = new MemberScore();
        memberScore.setMemberId(memberId);
        memberScore.setMemberScoreTypeCode(memberScoreTypeCode);
        memberScore.setScore(score);
        memberScore.setOperator(operator);
        memberScore.setScoreTime(new Timestamp(System.currentTimeMillis()));
        memberScore.setCreateTime(new Timestamp(System.currentTimeMillis()));
        memberScore.setType(type);
        MemberScoreManager.getInstance().add(memberScore);
        // 修改会员积分
        Member member = MemberManager.getInstance().getEntity(memberId);
        // 避免空指针
        if(member.getScore() == null){
            member.setScore(0);
        }
        if(type.equals("augment")){
            member.setScore(member.getScore() + score);
        }else if(type.equals("decrease")){
            member.setScore(member.getScore() - score);
        }
        MemberManager.getInstance().update(member);
        this.writeSuccessResponse(response);
    }
}
