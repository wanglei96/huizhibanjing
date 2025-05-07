package BRP.service;

import BRP.data.Book;
import BRP.data.MemberBookmark;
import BRP.model.BarCodeUtils;
import BRP.model.BookManager;
import BRP.model.MemberBookmarkManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

public class GetMemberBookmarkByMemberIdAndBookSkuIdServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        int memberId = JsonHelper.getInt(jData, "memberId");
        int bookSkuId = JsonHelper.getInt(jData, "bookSkuId");
        MemberBookmark theMemberBookmark = MemberBookmarkManager.getInstance().getEntityByMemberIdAndBookSkuId(memberId, bookSkuId);
        this.writeSuccessResponse(response, theMemberBookmark);
    }
}
