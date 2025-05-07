package BRP.service;

import BRP.data.MemberLikeStoryVideo;
import BRP.model.MemberLikeStoryVideoManager;
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

public class MemberLikeStoryVideoServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
        JSONObject requestData = getRequestData(request);
        Integer memberId = JsonHelper.getInt(requestData, "memberId");
        Integer storyVideoId = JsonHelper.getInt(requestData, "storyVideoId");
        MySqlConditionBuilder mb = new MySqlConditionBuilder();
        mb.addEqualCondition("member_id", memberId);
        mb.addEqualCondition("story_video_id", storyVideoId);

        SqlSession sqlSession = MyBatisManager.getInstance().openSession();

        try {
            List<MemberLikeStoryVideo> list = MemberLikeStoryVideoManager.getInstance().getList(sqlSession, mb.toString());

            if (list.isEmpty()) {
                //添加喜欢
                MemberLikeStoryVideo newMemberLikeStoryVideo = new MemberLikeStoryVideo();
                newMemberLikeStoryVideo.setMemberId(memberId);
                newMemberLikeStoryVideo.setStoryVideoId(storyVideoId);
                MemberLikeStoryVideoManager.getInstance().add(sqlSession, newMemberLikeStoryVideo);
            } else {
                //取消喜欢
                MemberLikeStoryVideoManager.getInstance().delete(sqlSession, list.get(0).getId());
            }
            writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }

}
