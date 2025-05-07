package BRP.service;

import BRP.data.BookSkuAgeGroup;
import BRP.data.BookSkuBookTag;
import BRP.data.Bookcase;
import BRP.model.BookSkuAgeGroupManager;
import BRP.model.BookSkuBookTagManager;
import BRP.model.BookcaseManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AddBookcaseServiceHandler extends AddServiceHandler {

    /**
     * 处理数据实体
     *
     * @param newDataEntity
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        Bookcase newBookcase = (Bookcase) newDataEntity;
        String condition = String.format("name='%s' and company_id=%d", newBookcase.getName(), newBookcase.getCompanyId());
        Integer count = BookcaseManager.getInstance().getCount(newAddServiceContext.getSession(), condition);
        if (count > 0) {
            throw new Exception("书柜已经存在，请勿重复创建！");
        }
        return newDataEntity;
    }

}
