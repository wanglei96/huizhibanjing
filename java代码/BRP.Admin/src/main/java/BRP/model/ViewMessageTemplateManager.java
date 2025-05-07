package BRP.model;
import BRP.data.ViewMessageTemplateMaster;
public class ViewMessageTemplateManager extends ViewMessageTemplateMaster 
{
    private static ViewMessageTemplateManager _instance;
    public  static ViewMessageTemplateManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewMessageTemplateManager();
        }
        return _instance;
    }
    private   ViewMessageTemplateManager()    
    {
        this.setTypeName("ViewMessageTemplate");
    }
}