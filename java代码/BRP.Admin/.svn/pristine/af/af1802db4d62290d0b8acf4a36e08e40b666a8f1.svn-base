package BRP.model;
import BRP.data.MessageBookMaster;
public class MessageBookManager extends MessageBookMaster 
{
    private static MessageBookManager _instance;
    private MessageBookManager()    
    {
        this.setTableName("message_book");
        this.setTypeName("MessageBook");
    }
    public  static MessageBookManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new MessageBookManager();
        }
        return _instance;
    }
}