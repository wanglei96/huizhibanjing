package strosoft.tools.sms;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.List;
import org.w3c.dom.*;

import strosoft.sys.data.DatabaseBackup;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;

public class DataDocumentBuilderFactory {
	
	/**
	 * 取得DocumentBuilder对象
	 * @return DocumentBuilder
	 * @throws Exception
	 */
	public static DocumentBuilder getDocumentBuilder() throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		return factory.newDocumentBuilder();	
	}
	/**
	 * 添加数据
	 * @param Document
	 * @param DatabaseBackup
	 */
	public static Document add(Document db,DatabaseBackup s){
        Element theData=null, theElem=null, root=null;
		try {  
            root=db.getDocumentElement();
            theData=db.createElement("SqlFile");
    		String[] fieldNames = getFiledName(s);
    		 for(int i=0;i<fieldNames.length;i++){    
    	               String name = fieldNames[i];//遍历所有属性 
    	               theElem=db.createElement(name);
    	               theElem.setTextContent(getFieldValueByName(name,s).toString()); //获取属性的名字
    	               theData.appendChild(theElem);
    	     } 
            root.appendChild(theData);
            saveXml(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return db;
	}
    public static Node selectSingleNode(String express, Object source) {//查找节点，并返回第一个符合条件节点
        Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }     
        return result;
    }
    public static NodeList selectNodes(String express, Object source) {//查找节点，返回符合条件的节点集。
        NodeList result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } 
        return result;
    }
    
    //事务提交
    private static void saveXml(Document db) {//将Document输出到文件
        TransformerFactory transFactory=TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source=new DOMSource();
            source.setNode(db);
            StreamResult result=new StreamResult();
            result.setOutputStream(new FileOutputStream(db.getBaseURI().substring(6, db.getBaseURI().length())));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }   
    }
	public static void main(String[] args) throws Exception {
		File xmlFile = new File("WebRoot/WEB-INF/DataList.xml");
		Document db = getDocumentBuilder().parse(xmlFile);
		//update(db,new SqlFile("1","2017","111","555"));
		add(db, new DatabaseBackup("3","2018","111","123"));
		//delete(db, new SqlFile("2",null,null));
		List<DatabaseBackup> sqlFiles = getList(db);
		System.out.println(sqlFiles);
	}
	/**
	 * 根据条件删除
	 * @param db
	 * @param where
	 */
	public static void delete(Document db,DatabaseBackup s){
		Element datafile=null;
		String[] fieldNames = getFiledName(s);
		 for(int i=0;i<fieldNames.length;i++){    
	               String name = fieldNames[i];//遍历所有属性 
	               String value = getFieldValueByName(name,s).toString();
	               if(value!=null){
	            	   datafile=(Element) selectSingleNode("/Data/SqlFile["+name+"='"+value+"']", db.getDocumentElement());
	            	   datafile.getParentNode().removeChild(datafile);
	           		   saveXml(db);
	           		   break;
	              }  
	     }
	}
	/**
	 * 根据条件修改
	 * @param db
	 * @param where
	 * @param outnane
	 * @param newnane
	 */
	public static void update(Document db,DatabaseBackup s){
		Element datafile=(Element) selectSingleNode("/Data/SqlFile[id='"+s.getId()+"']", db.getDocumentElement());
		String[] fieldNames = getFiledName(s);
		 for(int i=0;i<fieldNames.length;i++){    
	               String name = fieldNames[i];//遍历所有属性 
	               String value = getFieldValueByName(name,s).toString();
	               if(value!=null){
	            	   datafile.getElementsByTagName(name).item(0).setTextContent(value);
	           		   saveXml(db);
	              }  
	     }	
	}
	/**
	 * 输出列表
	 * @param db
	 * @return List<SqlFile>
	 */
	public static List getList(Document db) {
		List<DatabaseBackup> sqlFiles=new ArrayList<DatabaseBackup>();
		Element e=null;Node t=null;DatabaseBackup sf=null;
		NodeList sqlFile = db.getDocumentElement().getElementsByTagName("SqlFile");
		String[] fieldNames = getFiledName(new DatabaseBackup());
		for (int i = 0; i < sqlFile.getLength(); i++) {
			   Element currentElement = (Element) sqlFile.item(i);
			   sf = new DatabaseBackup();
			   sf.setId(currentElement.getElementsByTagName(fieldNames[0]).item(0).getFirstChild().getNodeValue());
			   sf.setName(currentElement.getElementsByTagName(fieldNames[1]).item(0).getFirstChild().getNodeValue());
			   sf.setTime(currentElement.getElementsByTagName(fieldNames[2]).item(0).getFirstChild().getNodeValue());
			   Node nodeDescription = currentElement.getElementsByTagName(fieldNames[3]).item(0);
			   if(nodeDescription.getFirstChild() != null) {
				   sf.setDescription(nodeDescription.getFirstChild().getNodeValue());
			   }
			   sqlFiles.add(sf);
		  }	
		return sqlFiles;
    }
	/**
	 * 获取属性名数组  
	 */ 
    private static String[] getFiledName(Object o){  
        Field[] fields=o.getClass().getDeclaredFields();  
        String[] fieldNames=new String[fields.length];  
        for(int i=0;i<fields.length;i++){  
            fieldNames[i]=fields[i].getName();  
        }  
        return fieldNames;  
    }  
    /**
     * 根据属性名获取属性值  
     */ 
      private static Object getFieldValueByName(String fieldName, Object o) {  
           try {    
               String firstLetter = fieldName.substring(0, 1).toUpperCase();    
               String getter = "get" + firstLetter + fieldName.substring(1);    
               Method method = o.getClass().getMethod(getter, new Class[] {});    
               Object value = method.invoke(o, new Object[] {});    
               return value;    
           } catch (Exception e) {    
             
               return null;    
           }    
       } 
}
