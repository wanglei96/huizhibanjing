package strosoft.cg.common;

public class CodeFormatter {
	 private String space = "    ";
     //层次数
     private int level = 0;
     /// <summary>
     /// 是否开始字符串
     /// </summary>
     private Boolean stringStart = false;
     /// <summary>
     /// 格式化代码
     /// </summary>
     /// <param name="code"></param>
     /// <returns></returns>
     public String Format(String code)
     {	
         char lastChar = ' ';
         StringBuilder sbFormattedCode = new StringBuilder();
         for (int i = 0; i < code.length(); i++)
         {
             char c = code.charAt(i);
             switch (c)
             {
                 case '{':
                     {
                         //如果在字符串中，则跳过
                         if (stringStart)
                         {
                             continue;
                         }
                         this.AppendSpace(sbFormattedCode);
                         sbFormattedCode.append("\r\n");
                         this.AppendSpace(sbFormattedCode);
                         sbFormattedCode.append(c);
                         sbFormattedCode.append("\r\n");
                         level++;
                         break;
                     }
                 case '}':
                     {
                         //如果在字符串中，则跳过
                         if (stringStart)
                         {
                             sbFormattedCode.append(c);
                             continue;
                         }
                         level--;
                         if (lastChar != '}' && lastChar != '{' && lastChar != ';')
                         {
                             sbFormattedCode.append("\r\n");
                         }
                         this.AppendSpace(sbFormattedCode);
                         sbFormattedCode.append(c);
                         if (i != code.length() - 1)
                         {
                             sbFormattedCode.append("\r\n");
                         }
                         break;
                     }
                 case '"':
                     {
                         //标记字符串开始和结束
                         if (lastChar != '\\')
                         {
                             stringStart = !stringStart;
                         }
                         sbFormattedCode.append(c);
                         break;
                     }
                 case ';':
                     {
                         if (stringStart)
                         {
                             sbFormattedCode.append(c);
                             continue;
                         }
                         if(i+1<code.length() && ((code.charAt(i+1) == '/' && code.charAt(i+2) == '/')
                        		 ||(code.charAt(i+1) == 'i' && code.charAt(i+2) == '<')
                        		 	||(code.charAt(i+1) == 'i' && code.charAt(i+2) == '+'))){
                        	 sbFormattedCode.append(c);
                        	 continue;
                         }else{
                             sbFormattedCode.append(c);
                             sbFormattedCode.append("\r\n");
                         }
                         break;
                     }
                 default:
                     {
                         if (lastChar == '{' || lastChar == '}' || lastChar == ';')
                         {
                             this.AppendSpace(sbFormattedCode);
                         }
                         sbFormattedCode.append(c);
                         break;
                     }
             }
             lastChar = c;
         }
         return sbFormattedCode.toString();
     }
     /// <summary>
     /// 添加空格
     /// </summary>
     private void AppendSpace(StringBuilder sbFormattedCode)
     {
         for (int i = 0; i < level; i++)
         {
             sbFormattedCode.append(space);
         }
     }
 
}
