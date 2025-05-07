package BRP.model;

import BRP.data.ViewBookOrder;
import BRP.data.ViewBookOrderMaster;
import org.apache.ibatis.session.SqlSession;

public class ViewBookOrderManager extends ViewBookOrderMaster {
    private static ViewBookOrderManager _instance;

    public static ViewBookOrderManager getInstance() {
        if (_instance == null) {
            _instance = new ViewBookOrderManager();
        }
        return _instance;
    }

    private ViewBookOrderManager() {
        this.setTypeName("ViewBookOrder");
    }

    public ViewBookOrder getEntityById(SqlSession session, Integer id) {
        String condition = "id = " + id;
        return this.getList(session, condition).get(0);
    }
    public ViewBookOrder getEntityById(Integer id) throws Exception {
        String condition = "id = " + id;
        return this.getList(condition).get(0);
    }
}