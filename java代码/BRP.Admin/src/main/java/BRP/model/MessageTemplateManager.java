package BRP.model;
import BRP.data.MessageTemplateMaster;
public class MessageTemplateManager extends MessageTemplateMaster 
{
    private static MessageTemplateManager _instance;
    private MessageTemplateManager()    
    {
        this.setTableName("message_template");
        this.setTypeName("MessageTemplate");
    }
    public  static MessageTemplateManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MessageTemplateManager();
        }
        return _instance;
    }
}