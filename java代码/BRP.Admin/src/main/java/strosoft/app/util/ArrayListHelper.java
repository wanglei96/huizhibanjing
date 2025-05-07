package strosoft.app.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;

public class ArrayListHelper {
	public static String toJSONArray(ArrayList<LinkedHashMap<String,Object>> HashMapList){
		String string = "["; 
		for (int i = 0; i < HashMapList.size(); i++) {
			string+="{";
			LinkedHashMap map=HashMapList.get(i);
		    for (Iterator it = map.entrySet().iterator(); it.hasNext();) {  
		        Entry e = (Entry) it.next();  
		        string += "\"" + e.getKey() + "\":";  
		        string += "\"" + e.getValue() + "\",";  
		    }  
		    string = string.substring(0, string.lastIndexOf(","));  
		    if(HashMapList.size()==0){
		    	string += "}";  	
		    }else{
		    	if(i==HashMapList.size()-1){
		    		string+="}";
		    	}else{
		    		string+="},";
		    	}
		    	
		    }
		}	
		string+="]";
		return string;  
	}
	
	public static  String toJsonString(ArrayList<LinkedHashMap<String,Object>> HashMapList) throws SQLException{
		String string = toJSONArray(HashMapList);
		//System.out.println(" d "+string);
		return string;
	}
	
	//List<Integer>转String
	public static String intListToString(List<Integer> ids,String separator){
		if(null == ids || ids.size()==0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(ids.get(0).toString());
		for(int i=1;i<ids.size();i++){
			sb.append(separator);
			sb.append(ids.get(i).toString());
		}
		return sb.toString();
	}
	//List<String>转String
	public static String stringListToString(List<String> ids,String separator){
		if(null == ids || ids.size()==0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("'");
		sb.append(ids.get(0));
		sb.append("'");
		for(int i=1;i<ids.size();i++){
			sb.append(separator);
			sb.append("'");
			sb.append(ids.get(i));
			sb.append("'");
		}
		return sb.toString();
	}

}
