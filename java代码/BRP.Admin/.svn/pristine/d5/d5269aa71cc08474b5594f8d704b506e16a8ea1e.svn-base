package strosoft.cms.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.CmsStrAContext;
import strosoft.cms.data.ViewCmsChannel;

public class CmsTemplateTagStrAHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition;
	private CmsHtmlGenerateContext con;
	private String hreftext="";	
	private Integer id;
	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext){
	con=context;
	StringBuffer content = new StringBuffer();
	Node currentNode = context.getCurrentNode();
	// 获取节点属性
	attributeCondition =getAttributeCondition(currentNode);
	String text= currentNode.getTextContent();
	 id=context.getCurrentChannelId();
	//处理attribute
	CmsStrAContext stra=new CmsStrAContext(attributeCondition);
	 //获取没有解析的属性值
	 String originalAttribute = getOriginalAttribute(attributeCondition);
	try {
		if(text!=null) hreftext=text;
		String AllAttribute=FunctionAllAttribute(stra, context ,cmsBuildContext);
		String href=" <a "+AllAttribute+" target='_blank'"+originalAttribute+">"+hreftext+"</a>";
		content.append(href);
	} catch (IOException e) {
		return "";
	}
	return content.toString();	
 }

	private String FunctionAllAttribute(CmsStrAContext stra,CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) throws IOException {
		// TODO Auto-generated method stub
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		String result="";
		try{
			if(stra.getHref()!=null){
				result+=" href='"+ "\\" +stra.getHref()+"' ";
			}
			if(stra.getChannelIndex()!=null&&stra.getHref()==null){
				String sql="SELECT * FROM  view_cms_channel "+getSqlCondition(sqlSession, stra, context);
				ViewCmsChannel viewch= sqlSession.selectOne("ViewCmsChannel.selectEntityBysql",sql);
				result=" href='\\"+viewch.getCreateFilePath()+"' ";
				if(hreftext.equals("")){
					hreftext=viewch.getName();
				}
				System.out.println(result);	
			}
			if(stra.getTarget()!=null){
				result+=" target='"+stra.getTarget()+"' ";
			}
			
/*			if(stra.getParent().equals("true")&&stra.getChannelIndex()==null&&stra.getHref()==null){
				//SELECT * FROM view_cms_channel WHERE id=(SELECT parent_id FROM  view_cms_channel WHERE CODE='cm')
				SqlSession session=MyBatisManager.getInstance().openSession();
				String sql="SELECT * FROM view_cms_channel WHERE id=(SELECT parent_id FROM  view_cms_channel WHERE id='"+id+"')";
				ViewCmsChannel viewch= session.selectOne("ViewCmsChannel.selectEntityBysql",sql);
				if(viewch!=null){
				result=" href='\\"+viewch.getCreateFilePath()+"' ";
				System.out.println(result);
				}
			}
			if(!stra.getParent().equals("true")&&stra.getChannelIndex()==null&&stra.getHref()==null){
				
				//SELECT * FROM view_cms_channel WHERE id=(SELECT parent_id FROM  view_cms_channel WHERE CODE='cm')
				SqlSession session=MyBatisManager.getInstance().openSession();
				String sql="SELECT * FROM  view_cms_channel WHERE id='"+id+"'";
				ViewCmsChannel viewch= session.selectOne("ViewCmsChannel.selectEntityBysql",sql);
				if(viewch.getCreateFilePath()!=null){
					result=" href='\\"+viewch.getCreateFilePath()+"' ";
					System.out.println(result);
					}
			}
				*/
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	/*
	 * 根据节点属性名称和值进行返回数据
	 */
	//获取栏目image查询条件：
	public String getSqlCondition(SqlSession sqlSession,CmsStrAContext stra,CmsHtmlGenerateContext context){
		StringBuffer condition = new StringBuffer(" where 1=1");
		//判断有没有设置栏目模板
		boolean falg = false;
		//获取栏目id
		//channelCode
		if(!(stra.getChannelCode()==null)){
			condition.append(" and code ='"+stra.getChannelCode()+"'");
			attributeCondition.remove("channelcode");
			falg = true;
		}
		//channelIndex
		if(!(stra.getChannelIndex()==null)){
			condition.append(" and code ='"+stra.getChannelIndex()+"'");
			attributeCondition.remove("channelindex");
			falg = true;
		}
		
		//channelName
		if(!(stra.getChannelName()==null)){
			condition.append(" and name ='"+stra.getChannelName()+"'");
			attributeCondition.remove("channelname");
			falg = true;
		}
		//topLevel
		if(!(stra.getTopLevel()==null)){
			System.out.println("topLevel:"+stra.getTopLevel());
			Integer level = 0;
			int rootCmsChannelId = CmsChannelManager.getInstance().getIndexId(sqlSession,context.getCmsSiteId());
			try{
				level = Integer.parseInt(stra.getTopLevel());
			} catch(Exception e){
				level = 0;
				e.printStackTrace();
			}
			System.out.println("level:"+level);
			List<Integer> ids =CmsChannelManager.getInstance().getSubLevelByIdAndLevel(sqlSession, rootCmsChannelId, level);
			if(ids.size()<1){
				condition.append(" and 1=2 ");
			}else{
				condition.append(" and id in ("+CmsChannelManager.getInstance().getIdsString(ids)+")");
			}
			attributeCondition.remove("toplevel");
			falg = true;
		}
		//upLevel
		if(!(stra.getUpLevel()==null) && (context.getTemplateType().equalsIgnoreCase("channel") || context.getTemplateType().equalsIgnoreCase("content"))){
			int level = 0;
			Integer cmsChannelId = context.getCurrentChannelId();
			try{
				level = Integer.parseInt(stra.getUpLevel());
			} catch(Exception e){
				level = 0;
				e.printStackTrace();
			}
			cmsChannelId = CmsChannelManager.getInstance().getUpLevelByIdAndLevel(sqlSession,cmsChannelId,level);
			if(null == cmsChannelId){
				condition.append(" and 1=2 ");
			}else{
				condition.append(" and id ="+cmsChannelId);
			}
			attributeCondition.remove("uplevel");
			falg = true;
		}
		//没有设置栏目
		if(!falg){
			condition.append(" and id ='"+context.getCurrentChannelId()+"'");
		}	
		return condition.toString();
	}
	
}
