package strosoft.cms.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.cms.data.*;
import strosoft.sys.data.SysMenu;

public class ViewCmsChannelManager extends ViewCmsChannelMaster {

	private static ViewCmsChannelManager _instance;

	public static ViewCmsChannelManager getInstance() {
		if (_instance == null) {
			_instance = new ViewCmsChannelManager();
		}
		return _instance;
	}

	private ViewCmsChannelManager() {
		this.setViewName("view_cms_channel");
		this.setTypeName("ViewCmsChannel");
	}

	/*
	 * 取得实体
	 */
	public ViewCmsChannel getEntity(int id) throws Exception {
		String condition = "id=" + id;
		List<ViewCmsChannel> lstViewCmsChannel = this.getList(condition);
		if(lstViewCmsChannel.size() == 0){
			return null;
		}
		return lstViewCmsChannel.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<ViewCmsChannel> getList() throws IOException {
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			List<ViewCmsChannel> lstData = sqlSession.selectList("ViewCmsChannel.selectViewCmsChannel");
			MyBatisManager.getInstance().commitSession((sqlSession));
			return lstData;
		} catch (Exception e) {
			MyBatisManager.getInstance().rollbackSession((sqlSession));
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 根据获得栏目
	 */
	@SuppressWarnings("unchecked")
	public List<ViewCmsChannel> getListBySql(String sql) throws IOException {

		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try {
			List<ViewCmsChannel> lstData = sqlSession.selectList("ViewCmsChannel.selectViewCmsChannelBySql",
					sql);
			MyBatisManager.getInstance().commitSession((sqlSession));
			return lstData;
		} catch (Exception e) {
			MyBatisManager.getInstance().rollbackSession((sqlSession));
			e.printStackTrace();
			return null;
		}

	}
	
	/*
	 * 根据id获得viewCmsChannel
	 */
	public ViewCmsChannel getEntityById(int id) {

		SqlSession sqlSession;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			ViewCmsChannel entity = sqlSession.selectOne("ViewCmsChannel.selectViewCmsChannel",id);
			return entity;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 获取所有父栏目直到首页
	 * @param id
	 * @param viewCmsChannels 所有栏目组成的Hashtable
	 * @return
	 */
	public ArrayList<ViewCmsChannel> getParentsList(int id,Hashtable<Integer, ViewCmsChannel> viewCmsChannels){
		ArrayList<ViewCmsChannel> lstParentChannel = new ArrayList<ViewCmsChannel>();
		ViewCmsChannel theViewCmsChannel = viewCmsChannels.get(id);
		if (theViewCmsChannel.getParentId() == null ||theViewCmsChannel.getParentId() == 0) {
			return lstParentChannel;
		}
		ViewCmsChannel theParentChannel =  viewCmsChannels.get(theViewCmsChannel.getParentId());
		if (theParentChannel == null) {
			return lstParentChannel;
		}
		lstParentChannel.add(theParentChannel);
		ArrayList<ViewCmsChannel> lstParentParentChannel  = this.getParentsList(theViewCmsChannel.getParentId(),viewCmsChannels);	
		if (lstParentParentChannel.size() > 0) {
			lstParentChannel.addAll(lstParentParentChannel);
		}
		return lstParentChannel;
		}
	/**
	 * 将数据库中查出来的list转换为key为id的hashtable
	 * @param viewCmsChannels
	 * @return
	 */
	public Hashtable<Integer, ViewCmsChannel> getViewChannelHashtable(List<ViewCmsChannel> viewCmsChannels){
		Hashtable<Integer, ViewCmsChannel> viewCmsChannelHashtable = new Hashtable<Integer, ViewCmsChannel>();
		for(int i=0;i<viewCmsChannels.size();i++){
			ViewCmsChannel viewCmsChannel = viewCmsChannels.get(i);
			viewCmsChannelHashtable.put(viewCmsChannel.getId(), viewCmsChannel);
		}
		return viewCmsChannelHashtable;
	}
	
/*	public List<ViewCmsChannel> getList(SqlSession sqlSession,String condition){
		String sql = "select * from view_cms_channel where "+condition;
		List<ViewCmsChannel> viewCmsChannels = sqlSession.selectList("ViewCmsChannel.selectEntityBysql", sql);
		return viewCmsChannels;
	}*/
	

	/**
	 * 根据编码取得实体
	 * 
	 * @param code
	 * @return
	 * @throws Exception 
	 */
	public ViewCmsChannel getViewCmsChannelByCode(String code) throws Exception {
		String condition = String.format("code='%s'", code);
		List<ViewCmsChannel> lstViewCmsChannel = this.getList(condition);
		if (lstViewCmsChannel.size() > 0) {
			return lstViewCmsChannel.get(0);
		}
		return null;
	}

}
