package BRP.service;

import BRP.data.MemberCollectStoryVideo;
import BRP.model.MemberCollectStoryVideoManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class MemberCollectStoryVideoServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
        JSONObject requestData = getRequestData(request);
        Integer memberId = JsonHelper.getInt(requestData, "memberId");
        Integer storyVideoId = JsonHelper.getInt(requestData, "storyVideoId");

        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            MySqlConditionBuilder mb = new MySqlConditionBuilder();
            mb.addEqualCondition("member_id", memberId);
            mb.addEqualCondition("story_video_id", storyVideoId);
            List<MemberCollectStoryVideo> list = MemberCollectStoryVideoManager.getInstance().getList(sqlSession, mb.toString());

            if (list.isEmpty()) {
                //收藏
                MemberCollectStoryVideo newMemberCollectStoryVideo = new MemberCollectStoryVideo();
                newMemberCollectStoryVideo.setMemberId(memberId);
                newMemberCollectStoryVideo.setStoryVideoId(storyVideoId);
                MemberCollectStoryVideoManager.getInstance().add(sqlSession, newMemberCollectStoryVideo);
            } else {
                //取消收藏
                MemberCollectStoryVideoManager.getInstance().delete(sqlSession, list.get(0).getId());
            }
            MyBatisManager.getInstance().commitSession(sqlSession);
            writeSuccessResponse(response);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

}
