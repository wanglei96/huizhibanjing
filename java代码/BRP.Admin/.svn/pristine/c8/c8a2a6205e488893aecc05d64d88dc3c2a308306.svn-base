package BRP.model;

import BRP.data.ViewBookInOrder;
import BRP.data.ViewBookInOrderMaster;
import org.apache.ibatis.session.SqlSession;

public class ViewBookInOrderManager extends ViewBookInOrderMaster {
    private static ViewBookInOrderManager _instance;

    public static ViewBookInOrderManager getInstance() {
        if (_instance == null) {
            _instance = new ViewBookInOrderManager();
        }
        return _instance;
    }

    private ViewBookInOrderManager() {
        this.setTypeName("ViewBookInOrder");
    }

    public ViewBookInOrder getEntityById(SqlSession session, Integer newBookInOrderId) {
        return session.selectOne("selectViewBookInOrderById", newBookInOrderId);
    }
}