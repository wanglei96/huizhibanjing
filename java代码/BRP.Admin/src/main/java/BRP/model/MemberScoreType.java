package BRP.model;

public class MemberScoreType {
    /**
     * @name 每周借阅一次
     * @description 每周借阅1次以上，获得10积分
     */
    public static final String WeeklyBorrowing = "WeeklyBorrowing";
    /**
     * @name 参加活动
     * @description 参加书馆组织的活动，获得20积分
     */
    public static final String EnterIntoActivities = "EnterIntoActivities";
    /**
     * @name 介绍好友加入会员
     * @description 介绍好友加入会员，获得50积分
     */
    public static final String Invite = "Invite";
    /**
     * @name 成为会员
     * @description 成为会员，获得100积分
     */
    public static final String NewMember = "NewMember";
    /**
     * @name 测评成绩合格
     * @description 测评成绩合格，获得30积分
     */
    public static final String QualifiedEvaluation = "QualifiedEvaluation";
    /**
     * @name 获得称号
     * @description 获得阅读大魔王、阅读小蜜蜂、阅读达人奖、阅读雷锋奖，阅读小标兵的称号获得30积分
     */
    public static final String Read = "Read";
    /**
     * @name 每日阅读且分享
     * @description 每日阅读且分享到朋友圈，获得5积分
     */
    public static final String Share = "Share";
}
