package BRP.model;
import BRP.data.MessagePushTypeMaster;
public class MessagePushTypeManager extends MessagePushTypeMaster 
{
    private static MessagePushTypeManager _instance;
    private MessagePushTypeManager()    
    {
        this.setTableName("message_push_type");
        this.setTypeName("MessagePushType");
    }
    public  static MessagePushTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MessagePushTypeManager();
        }
        return _instance;
    }
}