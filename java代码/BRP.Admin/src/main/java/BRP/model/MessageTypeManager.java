package BRP.model;
import BRP.data.MessageTypeMaster;
public class MessageTypeManager extends MessageTypeMaster 
{
    private static MessageTypeManager _instance;
    private MessageTypeManager()    
    {
        this.setTableName("message_type");
        this.setTypeName("MessageType");
    }
    public  static MessageTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MessageTypeManager();
        }
        return _instance;
    }
}