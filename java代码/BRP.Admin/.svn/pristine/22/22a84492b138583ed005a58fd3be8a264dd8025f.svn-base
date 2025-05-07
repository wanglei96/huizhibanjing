package BRP.service;

import BRP.data.Bookcase;
import BRP.model.BookcaseManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.UpdateServiceHandler;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class UpdateBookcaseServiceHandler extends UpdateServiceHandler {

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
        Bookcase newBookcase = (Bookcase) newDataEntity;//获取书柜信息
        Bookcase oldBookcase = BookcaseManager.getInstance().getEntity(newAddServiceContext.getSession(), newBookcase.getId());
        if (!newBookcase.getName().equals(oldBookcase.getName())) {
            String condition = String.format("name='%s' and company_id=%d", newBookcase.getName(), newBookcase.getCompanyId());
            Integer count = BookcaseManager.getInstance().getCount(newAddServiceContext.getSession(), condition);
            if (count > 0) {
                throw new Exception("书柜已经存在，请勿重复创建！");
            }
        }
        return newDataEntity;
    }

}
