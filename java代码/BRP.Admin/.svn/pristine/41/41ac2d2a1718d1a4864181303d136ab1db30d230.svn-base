package BRP.model;
import BRP.data.ViewBookDamage;
import BRP.data.ViewBookDamageMaster;

import java.util.List;

public class ViewBookDamageManager extends ViewBookDamageMaster
{
    private static ViewBookDamageManager _instance;
    public  static ViewBookDamageManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewBookDamageManager();
        }
        return _instance;
    }
    private   ViewBookDamageManager()    
    {
        this.setTypeName("ViewBookDamage");
    }

    public List<ViewBookDamage> getListByMemberId(Integer memberId) throws Exception {
        String condition = String.format("member_id = %d",memberId);
        return this.getList(condition);
    }
}