package BRP.model;

import BRP.data.ViewBook;
import BRP.data.ViewBookMaster;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ViewBookManager extends ViewBookMaster {
    private static ViewBookManager _instance;

    public static ViewBookManager getInstance() {
        if (_instance == null) {
            _instance = new ViewBookManager();
        }
        return _instance;
    }

    private ViewBookManager() {
        this.setTypeName("ViewBook");
    }

    public ViewBook getEntityBySn(String returnBookSn, Integer companyId) throws Exception {
        String condition = "sn='" + returnBookSn + "' and company_id=" + companyId;
        List<ViewBook> lstViewBook = this.getList(condition);
        if (lstViewBook.size() > 0) {
            return lstViewBook.get(0);
        }
        return null;
    }

    public ViewBook getEntityBySn(SqlSession sqlSession, String returnBookSn, Integer companyId) throws Exception {
        String condition = "sn='" + returnBookSn + "' and company_id=" + companyId;
        List<ViewBook> lstViewBook = this.getList(sqlSession, condition);
        if (lstViewBook.size() > 0) {
            return lstViewBook.get(0);
        }
        return null;
    }

    public ViewBook getEntity(Integer id) throws Exception {
        List<ViewBook> lstViewBook = this.getList("id=" + id);
        if (lstViewBook.size() > 0) {
            return lstViewBook.get(0);
        }
        return null;
    }

    /**
     * 获取会员已结绘本列表
     *
     * @param memberId
     * @param companyId
     * @return
     */
    public List<ViewBook> getListByMemberId(Integer companyId, Integer memberId) throws Exception {
        return this.getList("member_id=" + memberId + " and company_id=" + companyId);
    }
}