package BRP.model;
import BRP.data.ViewDeliverPlaceMaster;
public class ViewDeliverPlaceManager extends ViewDeliverPlaceMaster 
{
    private static ViewDeliverPlaceManager _instance;
    public  static ViewDeliverPlaceManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewDeliverPlaceManager();
        }
        return _instance;
    }
    private   ViewDeliverPlaceManager()    
    {
        this.setTypeName("ViewDeliverPlace");
    }
}