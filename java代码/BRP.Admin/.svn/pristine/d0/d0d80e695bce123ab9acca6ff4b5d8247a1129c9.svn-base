package strosoft.cms.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;
import strosoft.app.common.MyBatisManager;
import strosoft.cms.data.CmsPageItemContext;
import strosoft.cms.data.CmsPageItemsContext;

public class CmsTemplateTagPageItemHandler extends CmsTemplateTagHandler {
	private HashMap<String, String> attributeCondition = null;
	private List<String> pagePathList= null;
	@Override
	public String process(CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext) {
		Node currentNode = context.getCurrentNode();
		String html = "";
		SqlSession sqlSession = cmsBuildContext.getSqlSession();
		try {
			//判断是否有可翻页标签；stl:channels/stl:content
			if(!isInPagingTag(cmsBuildContext)){
				return "";
			}
			// 获取属性键值对
			this.attributeCondition = getAttributeCondition(currentNode);
			int pageSize = cmsBuildContext.getPageHtml().size();
			//设置CmsPageItemContext
			CmsPageItemContext pageItemContext = new  CmsPageItemContext(attributeCondition);
			this.pagePathList = setPagePath(context.getCurrentUrl(),pageSize,cmsBuildContext);
			String linkValue = getSingleLinkValueByType(pageItemContext, context, cmsBuildContext);
			String textValue = getSingleTextValueByType(pageItemContext, context, cmsBuildContext);
			String targetValue = getTargetValue(pageItemContext);
			if(linkValue == null || linkValue.equalsIgnoreCase("null")){
				html = textValue;
			}else{
				html = "<a href='"+linkValue+"'"+targetValue+" >";
				html+= textValue;
				html+="</a>";
			}
			List<String> linkValueList = getLinkValueListByType(pageItemContext, context, cmsBuildContext);
			List<Integer> textValueList = getTextValueListByType(pageItemContext, context, cmsBuildContext);
			String leftStr = addLeftStr(pageItemContext);
			String rightStr = addRightStr(pageItemContext);
			for(int i=0;i<linkValueList.size();i++){
				if(linkValueList.get(i)!=null){
					//判断导航是否包含左右字符
					if(isIncludeLeftAndRight(pageItemContext)){
						html+= "<a href='"+linkValueList.get(i)+"'"+targetValue+" ><span>";
						html+= leftStr+textValueList.get(i)+rightStr;
						html+="</span></a>";
					}else{
						html+= leftStr+"<a href='"+linkValueList.get(i)+"'"+targetValue+" ><span>";
						html+= textValueList.get(i);
						html+="</span></a>"+rightStr;
					}
				}else{
					html+= leftStr+"<span>"+textValueList.get(i)+"</span>"+rightStr;
				}
			}
		} catch (Exception e) {
			return "";
		}
		return html;
	}
	//判断是否处在可翻页标签内;stl:channels/stl:content
	public boolean isInPagingTag(CmsBuildContext cmsBuildContext){
		if(cmsBuildContext.getIsPaging()!=null && cmsBuildContext.getIsPaging().equalsIgnoreCase("true")){
			return true;
		}
		return false;
	}
	//判断该项是否生成翻页容器
	public boolean isCreatPagingContent(CmsBuildContext cmsBuildContext){
		if(cmsBuildContext.getIsCreatPagingContent()!=null && cmsBuildContext.getIsCreatPagingContent().equalsIgnoreCase("true")){
			return true;
		}
		return false;
	}
	//autoHide;是否在无分页时不显示标签内容
	public boolean isShowPagingContainer(CmsPageItemsContext pageItemsContext){
		if((pageItemsContext.getAutoHide()!=null) && pageItemsContext.getAutoHide().equalsIgnoreCase("false")){
			return true;
		}
		return false;
	}
	//根据type获取要展示的单个链接value值
	public String getSingleLinkValueByType(CmsPageItemContext pageItemContext,CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext){
		String linkValue = "";
		if(!(pageItemContext.getType()==null)){
			String type = pageItemContext.getType();
			if(type.equalsIgnoreCase("CurrentPageIndex")){
				//linkValue = pagePathList.get(context.getCurrentPage()-1);
				linkValue = null;
			}else if(type.equalsIgnoreCase("FirstPage")){
				linkValue = pagePathList.get(0);
			}else if(type.equalsIgnoreCase("LastPage")){
				linkValue = pagePathList.get(cmsBuildContext.getTotalPage()-1);
			}else if(type.equalsIgnoreCase("NextPage")){
				if((context.getCurrentPage()-cmsBuildContext.getTotalPage())>=0){
					linkValue = null;
				}else{
					linkValue = pagePathList.get(context.getCurrentPage());
				}
			}else if(type.equalsIgnoreCase("PreviousPage")){
				if(context.getCurrentPage()-2<0){
					linkValue = null;
				}else{
					linkValue = pagePathList.get(context.getCurrentPage()-2);
				}
			}else if(type.equalsIgnoreCase("TotalNum")){
				linkValue = null;
			}else if(type.equalsIgnoreCase("TotalPageNum")){
				linkValue = null;
			}else if(type.equalsIgnoreCase("PageNavigation")){
				linkValue = null;
			}
		}
		return linkValue;
	}
	//根据type获取要展示的单个textValue值
		public String getSingleTextValueByType(CmsPageItemContext pageItemContext,CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext){
			String textValue = "";
			if(!(pageItemContext.getType()==null)){
				String type = pageItemContext.getType();
				if(type.equalsIgnoreCase("CurrentPageIndex")){
					textValue = (context.getCurrentPage())+"";
				}else if(type.equalsIgnoreCase("FirstPage")){
					textValue = "首页";
				}else if(type.equalsIgnoreCase("LastPage")){
					textValue = "最后一页";
				}else if(type.equalsIgnoreCase("NextPage")){
					textValue = "下一页";
				}else if(type.equalsIgnoreCase("PreviousPage")){
					textValue = "上一页";
				}else if(type.equalsIgnoreCase("TotalNum")){
					textValue = cmsBuildContext.getTotalContentNum()+"";
				}else if(type.equalsIgnoreCase("TotalPageNum")){
					textValue = cmsBuildContext.getTotalPage()+"";
				}
			}
			return textValue;
		}
	//根据type获取要展示的多个链接value值
	public List<String> getLinkValueListByType(CmsPageItemContext pageItemContext,CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext){
		List<String> linkValueList = new ArrayList<String>();
		if(!(pageItemContext.getType()==null)){
			String type = pageItemContext.getType();
			Integer listNum = 7;
			if(type.equalsIgnoreCase("PageNavigation")){
				if(!(pageItemContext.getListNum()==null)){
					try{
						listNum = Integer.parseInt(pageItemContext.getListNum());
					}catch(Exception e){
						e.printStackTrace();
						listNum = 7;
					}
				}
				//显示数据的长度
				int totalPage = cmsBuildContext.getTotalPage();
				int showNum = (listNum>totalPage)?totalPage:listNum;
				int leftNum = 0;
				int rightNum = 0;
				String flag = "right";
				if(showNum>0){
					linkValueList.add(null);
				}
				while(showNum-1>0){
					if(flag.equals("right")){
						if((rightNum+context.getCurrentPage())<totalPage){
							rightNum ++;
							linkValueList.add(pagePathList.get(context.getCurrentPage()-1+rightNum));
							showNum --;
						}
						flag = "left";
					}else{
						if((context.getCurrentPage()-leftNum)>1){
							leftNum ++;
							linkValueList.add(0,pagePathList.get(context.getCurrentPage()-1-leftNum));
							showNum --;
						}
						flag = "right";
					}
				}
			}else if(type.equalsIgnoreCase("PageSelect")){
				
			}
		}
		return linkValueList;
	}
	//根据type获取要展示的多个链接value值
	public List<Integer> getTextValueListByType(CmsPageItemContext pageItemContext,CmsHtmlGenerateContext context,CmsBuildContext cmsBuildContext){
		List<Integer> textValueList = new ArrayList<Integer>();
		if(!(pageItemContext.getType()==null)){
			String type = pageItemContext.getType();
			Integer listNum = 7;
			if(type.equalsIgnoreCase("PageNavigation")){
				if(!(pageItemContext.getListNum()==null)){
					try{
						listNum = Integer.parseInt(pageItemContext.getListNum());
					}catch(Exception e){
						e.printStackTrace();
						listNum = 7;
					}
				}
				//显示数据的长度
				int totalPage = cmsBuildContext.getTotalPage();
				int showNum = (listNum>totalPage)?totalPage:listNum;
				int leftNum = 0;
				int rightNum = 0;
				String flag = "right";
				if(showNum>0){
					textValueList.add(context.getCurrentPage());
				}
				while(showNum-1>0){
					if(flag.equals("right")){
						if((rightNum+context.getCurrentPage())<totalPage){
							rightNum ++;
							textValueList.add(context.getCurrentPage()+rightNum);
							showNum --;
						}
						flag = "left";
					}else{
						if((context.getCurrentPage()-leftNum)>1){
							leftNum ++;
							textValueList.add(0,context.getCurrentPage()-leftNum);
							showNum --;
						}
						flag = "right";
					}
				}
			}else if(type.equalsIgnoreCase("PageSelect")){
				
			}
		}
		return textValueList;
	}
	//target
	public String getTargetValue(CmsPageItemContext pageItemContext){
		String target = "";
		if(!(pageItemContext.getTarget()==null)){
			target = " target='"+pageItemContext.getTarget()+"'";
		}
		return target;
	}
	//左侧添加字符处理
	public String  addLeftStr(CmsPageItemContext pageItemContext){
		String leftStr = "";
		if (!(pageItemContext.getlStr() == null)) {
			leftStr =  pageItemContext.getlStr();
		}
		return leftStr;
	}
	//右侧添加字符处理
	public String  addRightStr(CmsPageItemContext pageItemContext){
		String rightStr = "";
		if (!(pageItemContext.getrStr() == null)) {
			rightStr = pageItemContext.getrStr();
		}
		return rightStr;
	}
	//判断页码导航是否包含左右字符
	public boolean isIncludeLeftAndRight(CmsPageItemContext pageItemContext){
		if (pageItemContext.getHasLR() != null && pageItemContext.getHasLR().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}
	
}
