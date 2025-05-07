package BRP.data;

import strosoft.app.data.DataEntity;

import java.sql.Timestamp;

public class RecommendMemberCardInfo extends DataEntity {
    //推荐人ID
    private Integer referrerId;

    private Integer referrerCardId;

    //推荐人卡号
    private String referrerCardNo;

    //推荐人名称
    private String referrerName;

    //推荐人会员生效日期
    private Timestamp referrerCardEffectiveDate;

    private String referrerCardEffectiveDateText;

    //推荐人会员失效日期
    private Timestamp referrerCardExpireDate;
    private String referrerCardExpireDateText;



    //推荐人会员失效日期
    private Timestamp referrerExpireDate;
    private String referrerExpireDateText;

    public Timestamp getReferrerExpireDate() {
        return referrerExpireDate;
    }

    public void setReferrerExpireDate(Timestamp referrerExpireDate) {
        this.referrerExpireDate = referrerExpireDate;
    }

    public String getReferrerExpireDateText() {
        return referrerExpireDateText;
    }

    public void setReferrerExpireDateText(String referrerExpireDateText) {
        this.referrerExpireDateText = referrerExpireDateText;
    }

    public Timestamp getNewExpireDate() {
        return newExpireDate;
    }

    public void setNewExpireDate(Timestamp newExpireDate) {
        this.newExpireDate = newExpireDate;
    }

    public String getNewExpireDateText() {
        return newExpireDateText;
    }

    public void setNewExpireDateText(String newExpireDateText) {
        this.newExpireDateText = newExpireDateText;
    }

    private Timestamp newExpireDate;
    private String newExpireDateText;


    private Timestamp newMemberCardExpireDate;
    private String newMemberCardExpireDateText;

    //推荐人开卡时间
    private Timestamp referrerCardCreateTime;
    private String referrerCardCreateTimeText;
    //新会员ID
    private Integer newMemberId;

    private Integer newMemberCardId;

    //新会员卡号
    private String newMemberCardNo;

    //新会员名称
    private String newMemberName;

    //活动名称
    private String activityName;

    //赠送天数
    private Integer days;

    private Integer displayOrder;


    public String getReferrerCardNo() {
        return referrerCardNo;
    }

    public void setReferrerCardNo(String referrerCardNo) {
        this.referrerCardNo = referrerCardNo;
    }

    public String getReferrerName() {
        return referrerName;
    }

    public void setReferrerName(String referrerName) {
        this.referrerName = referrerName;
    }

    public Timestamp getReferrerCardEffectiveDate() {
        return referrerCardEffectiveDate;
    }

    public void setReferrerCardEffectiveDate(Timestamp referrerCardEffectiveDate) {
        this.referrerCardEffectiveDate = referrerCardEffectiveDate;
    }

    public Timestamp getReferrerCardExpireDate() {
        return referrerCardExpireDate;
    }

    public void setReferrerCardExpireDate(Timestamp referrerCardExpireDate) {
        this.referrerCardExpireDate = referrerCardExpireDate;
    }

    public String getReferrerCardEffectiveDateText() {
        return referrerCardEffectiveDateText;
    }

    public void setReferrerCardEffectiveDateText(String referrerCardEffectiveDateText) {
        this.referrerCardEffectiveDateText = referrerCardEffectiveDateText;
    }

    public String getReferrerCardExpireDateText() {
        return referrerCardExpireDateText;
    }

    public void setReferrerCardExpireDateText(String referrerCardExpireDateText) {
        this.referrerCardExpireDateText = referrerCardExpireDateText;
    }

    public String getNewMemberCardExpireDateText() {
        return newMemberCardExpireDateText;
    }

    public void setNewMemberCardExpireDateText(String newMemberCardExpireDateText) {
        this.newMemberCardExpireDateText = newMemberCardExpireDateText;
    }

    public String getReferrerCardCreateTimeText() {
        return referrerCardCreateTimeText;
    }

    public void setReferrerCardCreateTimeText(String referrerCardCreateTimeText) {
        this.referrerCardCreateTimeText = referrerCardCreateTimeText;
    }

    public Timestamp getReferrerCardCreateTime() {
        return referrerCardCreateTime;
    }

    public void setReferrerCardCreateTime(Timestamp referrerCardCreateTime) {
        this.referrerCardCreateTime = referrerCardCreateTime;
    }

    public String getNewMemberCardNo() {
        return newMemberCardNo;
    }

    public void setNewMemberCardNo(String newMemberCardNo) {
        this.newMemberCardNo = newMemberCardNo;
    }

    public String getNewMemberName() {
        return newMemberName;
    }

    public void setNewMemberName(String newMemberName) {
        this.newMemberName = newMemberName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(Integer referrerId) {
        this.referrerId = referrerId;
    }

    public Integer getNewMemberId() {
        return newMemberId;
    }

    public void setNewMemberId(Integer newMemberId) {
        this.newMemberId = newMemberId;
    }

    public Integer getReferrerCardId() {
        return referrerCardId;
    }

    public void setReferrerCardId(Integer referrerCardId) {
        this.referrerCardId = referrerCardId;
    }

    public Integer getNewMemberCardId() {
        return newMemberCardId;
    }

    public void setNewMemberCardId(Integer newMemberCardId) {
        this.newMemberCardId = newMemberCardId;
    }

    public Timestamp getNewMemberCardExpireDate() {
        return newMemberCardExpireDate;
    }

    public void setNewMemberCardExpireDate(Timestamp newMemberCardExpireDate) {
        this.newMemberCardExpireDate = newMemberCardExpireDate;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
