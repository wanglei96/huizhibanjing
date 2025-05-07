package BRP.model;

import BRP.data.MemberCardDisableLogMaster;
import org.apache.ibatis.session.SqlSession;

public class MemberCardDisableLogManager extends MemberCardDisableLogMaster {
    private static MemberCardDisableLogManager _instance;

    private MemberCardDisableLogManager() {
        this.setTableName("member_card_disable_log");
        this.setTypeName("MemberCardDisableLog");
    }

    public static MemberCardDisableLogManager getInstance() {
        if (_instance == null) {
            _instance = new MemberCardDisableLogManager();
        }
        return _instance;
    }
}