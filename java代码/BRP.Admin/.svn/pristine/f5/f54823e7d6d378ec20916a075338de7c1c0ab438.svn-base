package BRP.model;

import BRP.data.BookDamage;
import BRP.data.BookDamageMaster;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookDamageManager extends BookDamageMaster {
    private static BookDamageManager _instance;

    private BookDamageManager() {
        this.setTableName("book_damage");
        this.setTypeName("BookDamage");
    }

    public static BookDamageManager getInstance() {
        if (_instance == null) {
            _instance = new BookDamageManager();
        }
        return _instance;
    }

    public BookDamage getEntityByBookIdAndMemberId(SqlSession session, Integer bookId, Integer memberId) {
        String condition = "book_id = " + bookId + " and member_id = " + memberId;
        List<BookDamage> lstBookDamage = this.getList(session, condition, "create_time desc");
        if (lstBookDamage.size() > 0) {
            return lstBookDamage.get(0);
        }
        return null;
    }
}