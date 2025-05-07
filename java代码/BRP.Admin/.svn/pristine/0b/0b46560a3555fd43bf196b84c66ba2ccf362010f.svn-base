package strosoft.cms.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class CmsTemplateTagHandler {
	/*
	 * 
	 */
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		StringBuffer sb = new StringBuffer();
		String elementName = this.getElementName(currentNode);
		sb.append("<" + elementName);
		// 生成属性Html
		NamedNodeMap nnmAttribute = currentNode.getAttributes();
		if (nnmAttribute != null) {

			for (int i = 0; i < nnmAttribute.getLength(); i++) {
				sb.append(" ");
				Node attribute = nnmAttribute.item(i);
				//获取属性名
				String attributeName = attribute.getNodeName();
				//获取属性值
				String attributeValue = attribute.getNodeValue();
				sb.append(attributeName + "='" + attributeValue + "'");
			}
		}
		NodeList nlChildren = currentNode.getChildNodes();
		if ((nlChildren.getLength() == 0) && (currentNode.getTextContent() == null || currentNode.getTextContent().equals(""))) {
			if(elementName.equalsIgnoreCase("br")||elementName.equalsIgnoreCase("input")||elementName.equalsIgnoreCase("hr")){
				sb.append("/>");
			}else{
				sb.append("></" + elementName + ">");
			}
		} else {
			sb.append(">");
			// 生成子节点Html
			sb.append(createChildNodeHtml(nlChildren, context,cmsBuildContext));
			if(nlChildren.getLength() == 0){
				sb.append(currentNode.getTextContent());
			}
			sb.append("</" + elementName + ">");
		}
		String html = sb.toString();
		return html;
	}

	/*
	 * 取得元素名称
	 */
	protected String getElementName(Node currentNode) {
		// TODO Auto-generated method stub
		return currentNode.getNodeName();
	}

	protected void removeTextNode(Node root) {
		if (root.hasChildNodes()) {
			NodeList children = root.getChildNodes();
			int count = children.getLength();
			for (int i = count - 1; i >= 0; i--) {// 需要从后往前删除，防止出现沙漏效应
				Node child = children.item(i);
				if (child.getNodeType() == Node.TEXT_NODE) {
					child.getParentNode().removeChild(child);
				} else {
					removeTextNode(child);
				}
			}
		}
	}
	//获取属性键值对
	public HashMap<String, String> getAttributeCondition(Node currentNode){
		HashMap<String, String> attributeCondition = new HashMap<String, String>();
		NamedNodeMap nnmAttribute = currentNode.getAttributes();
		if (nnmAttribute != null && nnmAttribute.getLength() != 0) {
			//属性不为空
			for (int i = 0; i < nnmAttribute.getLength(); i++) {
				Node attribute = nnmAttribute.item(i);
				String attributeName = attribute.getNodeName().toLowerCase();
				String attributeValue = attribute.getNodeValue();
				attributeCondition.put(attributeName, attributeValue);
			}
		}
		return attributeCondition;
	}
	//继续生成子节点html
	public String createChildNodeHtml(NodeList nlChildren,CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext){
		String html = CmsHtmlGenerator.getInstance().createChildNodeHtml(nlChildren, context, cmsBuildContext);
		return html;
	}
	//根据IdList获取idString
	public String getIdsString(List<Integer> ids){
		StringBuffer idsString = new StringBuffer("");
		for(int i=0;i<ids.size()-1;i++){
			idsString.append(ids.get(i)+",");
		}
		idsString.append(ids.get(ids.size()-1));
		return idsString.toString();
	}
	//设置分页每页地址
	public List<String> setPagePath(String createFilePath,int length,CmsBuildContext cmsBuildContext){
		List<String> pagePathList = new ArrayList<String>();
		String[] createFilePathList = createFilePath.split("\\.");
		pagePathList.add(cmsBuildContext.getCmsBuildTask().getAdminRootUrl()+createFilePath);
		for(int i=1;i<length;i++){
			pagePathList.add(cmsBuildContext.getCmsBuildTask().getAdminRootUrl()+createFilePathList[0]+"_"+(i+1)+"."+createFilePathList[1]);
		}
		return pagePathList;
	}
	//获取没有解析的属性值
	public String getOriginalAttribute(HashMap<String, String> attributeCondition){
		StringBuffer html = new StringBuffer();
		Set<String> set=attributeCondition.keySet();
		for(String key:set){
			html.append(" "+key+"='"+attributeCondition.get(key)+"'");
		}
		return html.toString();
	}
	
	protected String getConfig(String connectionString,String userName,String password){
		String config = "<?xml version='1.0' encoding='UTF-8' ?>"
				+"<!DOCTYPE configuration "
				+ "PUBLIC '-//mybatis.org//DTD Config 3.0//EN' "
				+ "'http://mybatis.org/dtd/mybatis-3-config.dtd'>"
				+"<configuration>"
					+"<properties>"
						+"<property name='driver' value='com.mysql.jdbc.Driver' />"
						+"<property name='url' value='"+connectionString+"' />"
						+"<property name='username' value='"+userName+"' />"
						+"<property name='password' value='"+password+"' />"
				        +"<property name='poolPingEnabled' value='true'/>"
				        +"<property name='poolPingQuery' value='select now() from stro_cms.sys_user limit 1'/>"
				        +"<property name='poolPingConnectionsNotUsedFor' value='3600000'/>"
					+"</properties>"
					+"<settings>"
						+"<setting name='callSettersOnNulls' value='true' />"
				        +"<setting name='logImpl' value='STDOUT_LOGGING' />"
					+"</settings>"
					+"<typeAliases>"
						+"<typeAlias alias='JSONObject' type='net.sf.json.JSONObject' />"
						+"<typeAlias alias='LinkedHashMap' type='java.util.LinkedHashMap' />"
						+"<package name='strosoft.cms.data' />" 
					+"</typeAliases>"
					+"<environments default='development'>"
						+"<environment id='development'>"
							+"<transactionManager type='JDBC' />"
							+"<dataSource type='POOLED'>"
								+"<property name='driver' value='${driver}' />"
								+"<property name='url' value='${url}' />"
								+"<property name='username' value='${username}' />"
								+"<property name='password' value='${password}' />"
								+"<property name='poolPingQuery' value='select now()' />"
								+"<property name='poolPingEnabled' value='true' />"
								+"<property name='poolPingConnectionsNotUsedFor' value='${poolPingConnectionsNotUsedFor}' />"
							+"</dataSource>"
						+"</environment>"
					+"</environments>"
					+"<mappers>"
						+"<mapper resource='strosoft/cms/mapper/CmsChannelMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/ViewCmsChannelMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/CmsChannelChannelGroupMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/CmsContentMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/ViewCmsContentMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/CmsCommentMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/CmsTemplateMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/CmsTemplateTagMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/CmsIncludeMapper.xml' />"
						+"<mapper resource='strosoft/cms/mapper/CmsSiteMapper.xml' />"
						
					+"</mappers>"
				+"</configuration>";
		return config;
	}

}
