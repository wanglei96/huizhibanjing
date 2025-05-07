package BRP.model;

import BRP.data.ViewBookSkuBookTag;
import BRP.data.ViewBookSkuBookTagMaster;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ViewBookSkuBookTagManager extends ViewBookSkuBookTagMaster {
    private static ViewBookSkuBookTagManager _instance;

    public static ViewBookSkuBookTagManager getInstance() {
        if (_instance == null) {
            _instance = new ViewBookSkuBookTagManager();
        }
        return _instance;
    }

    private ViewBookSkuBookTagManager() {
        this.setTypeName("ViewBookSkuBookTag");
    }

    /**
     * 根据skuID获取所有标签
     *
     * @param sqlSession
     * @param bookSkuId
     * @return
     */
    public List<ViewBookSkuBookTag> getListByBookSkuId(SqlSession sqlSession, Integer bookSkuId) {
        String condition = "book_sku_id = " + bookSkuId;
        return this.getList(sqlSession, condition);
    }
}