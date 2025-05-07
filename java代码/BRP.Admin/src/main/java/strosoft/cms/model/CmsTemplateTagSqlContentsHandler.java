package strosoft.cms.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.CmsContent;
import strosoft.cms.data.CmsSqlContentsContext;

public class CmsTemplateTagSqlContentsHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition = null;
	private Node currentNode = null;

	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		currentNode = context.getCurrentNode();
		attributeCondition = getAttributeCondition(currentNode);
		StringBuffer content =  new StringBuffer();
		// 获取contents生成参数
		CmsSqlContentsContext contentsContext = new CmsSqlContentsContext(
				attributeCondition);
		SqlSession sqlSession = null;
		try {
			//获取sqlSession
			sqlSession =getSqlSession(contentsContext);
			String sql = getQueryString(contentsContext, currentNode);
			List<CmsContent> cmsContents = sqlSession.selectList("CmsContent.selectEntityBysql", sql);
			if(cmsContents.size()<1 ){
				//如果栏目数组为空，返回空字符串
				return "";
			}
			List<Integer> showCmsContentIds = getIdsByLimit(contentsContext, cmsContents);//设置总的翻页内容数
			cmsBuildContext.setTotalContentNum(showCmsContentIds.size());
			cmsBuildContext.setSqlSession(sqlSession);
			getHtmlAndPaging(contentsContext, content, showCmsContentIds, context, cmsBuildContext);
			return content.toString();
		} catch (Exception e) {
			return "";
		} finally{
			sqlSession.close();
		}
	}
	/**
	 * 根据connectionString获取sqlSession，没有connectionString属性返回系统sqlSession
	 * @param contentsContext
	 * @return 根据connectionString创建sqlSession失败
	 * @throws IOException
	 */
	private SqlSession getSqlSession(CmsSqlContentsContext contentsContext) throws IOException{
		SqlSession sqlSession = null;
		if(contentsContext.getConnectionString() == null){
			return MyBatisManager.getInstance().openSession();
		}
		String connectionString = contentsContext.getConnectionString();
		String userName = contentsContext.getUsername();
		String password = contentsContext.getPassword();
		String config = getConfig(connectionString, userName, password);
		InputStream in = new ByteArrayInputStream(config.getBytes());
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(in);
		sqlSession = factory.openSession();
		return sqlSession;
	}
	/**
	 * 获取查询sql字符串
	 * @param contentsContext
	 * @param currentNode stl:sqlContentsContext 节点
	 * @return 查询内容的sql
	 */
	private String getQueryString(CmsSqlContentsContext contentsContext,Node currentNode){
		String sql = "";
		NodeList nodes = currentNode.getChildNodes();
		Node queryStringNode = getNodeByNodeName(nodes, "stl:queryString");
		sql = queryStringNode.getTextContent();
		return sql;
	}
	/**
	 * 根据startNum，totalNum属性获取数据的显示范围
	 * @param contentsContext SqlContentsContext
	 * @param cmsContents 要筛选的内容列表
	 * @return 筛选后的内容ID列表
	 */
	private List<Integer> getIdsByLimit(CmsSqlContentsContext contentsContext,List<CmsContent> cmsContents){
		int startNum = 0;
		int totalNum = cmsContents.size();
		List<Integer> result = new ArrayList<Integer>();
		if(contentsContext.getStartNum()!=null){
			try{
				startNum = Integer.parseInt(contentsContext.getStartNum());
				if(startNum<0){
					startNum = 0;
				}
			} catch(Exception e){
				startNum = 0;
				e.printStackTrace();
			}
		}
		if(contentsContext.getTotalNum()!=null){
			try{
				totalNum = Integer.parseInt(contentsContext.getTotalNum());
				if(totalNum<0){
					totalNum = 0;
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		int endNum = cmsContents.size()<(totalNum+startNum)? cmsContents.size():(totalNum+startNum);
		for(int i=startNum;i<endNum;i++){
			Integer id = cmsContents.get(i).getId();
			result.add(id);
		}
		return result;
	}
	
	//判断是否设置pageNum属性，进行分页
	public String getHtmlAndPaging(CmsSqlContentsContext contentsContext, StringBuffer content,List<Integer> cmsContentIdList,CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) throws CloneNotSupportedException{
		int pageNum = getPageNum(contentsContext);
		if(pageNum>0){
			StringBuffer pageHtml = new StringBuffer();
			cmsBuildContext.setPageHtml(new ArrayList<StringBuffer>());
			cmsBuildContext.setPageContentHtml(new ArrayList<String>());
			cmsBuildContext.setPageHtmlReplaceStr("<"+UUID.randomUUID().toString()+">");
			NodeList nlChildren = currentNode.getChildNodes();
			for(int i=0;i<cmsContentIdList.size();i++){
				CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
				cmsHtmlGenerateContext.setCurrentContentId(cmsContentIdList.get(i));
				cmsHtmlGenerateContext.setCurrentShowType("Content");
				cmsHtmlGenerateContext.setNum(i);
				cmsHtmlGenerateContext.setNumOnPage(i%pageNum);
				cmsHtmlGenerateContext.setTotalNum(cmsContentIdList.size());
				cmsBuildContext.setIsPaging("true");
				if(((i+1)%pageNum == 0)){
					//生成子节点html
					pageHtml.append(createChildNodeHtml(nlChildren, cmsHtmlGenerateContext,cmsBuildContext));
					cmsBuildContext.getPageHtml().add(pageHtml);
					pageHtml = new StringBuffer();
				}else{
					//生成子节点html
					pageHtml.append(createChildNodeHtml(nlChildren, cmsHtmlGenerateContext,cmsBuildContext));
				}
			}
			//当生成0条数据时
			if(cmsContentIdList.size()==0){
				cmsBuildContext.getPageHtml().add(new StringBuffer(""));
				cmsBuildContext.setIsPaging("true");
			}
			if((cmsContentIdList.size())%pageNum!=0){
				cmsBuildContext.getPageHtml().add(pageHtml);
			}
			return content.append(cmsBuildContext.getPageHtmlReplaceStr()).toString();
		}else{
			for(int i=0;i<cmsContentIdList.size();i++){
				CmsHtmlGenerateContext cmsHtmlGenerateContext = context.clone();
				cmsHtmlGenerateContext.setCurrentContentId(cmsContentIdList.get(i));
				cmsHtmlGenerateContext.setNum(i);
				cmsHtmlGenerateContext.setNumOnPage(i);
				cmsHtmlGenerateContext.setCurrentShowType("Content");
				cmsHtmlGenerateContext.setTotalNum(cmsContentIdList.size());
				NodeList nlChildren = currentNode.getChildNodes();
				//生成子节点html
				content.append(createChildNodeHtml(nlChildren, cmsHtmlGenerateContext,cmsBuildContext));
			}
			return content.toString();
		}
	}
	//分页参数PageNum
	public int getPageNum(CmsSqlContentsContext contentsContext){
		Integer pageNum = 0;
		if(!(contentsContext.getPageNum()==null)){
			try{
				pageNum = Integer.parseInt(contentsContext.getPageNum());
			}catch(Exception e){
				e.printStackTrace();
				pageNum = 0;
			}
		}
		return pageNum;
	}
	
	/**
	 * 获取NodeList中节点名为name的节点
	 * @param nodes 节点列表
	 * @param name 节点名称
	 * @return 第一节点名称为name的节点
	 */
	public Node getNodeByNodeName(NodeList nodes, String name) {
		Node result = null;
		for (int i = 0; i < nodes.getLength(); i++) {
			// 获取节点
			Node currentNode = nodes.item(i);
			// 获取节点名
			String nodeName = currentNode.getNodeName();
			if (nodeName.equals(name)) {
				result = currentNode;
				break;
			}
		}
		return result;
	}
}
