package BRP.data;

import java.util.List;

public class MemberBorrowBookInfo {
    private List<BookOrderItemInfo> bookOrderItemInfoList;
    private List<BookInfo> bookList;
    //借阅的数量
    private Integer borrowedQuantity;
    //剩余借阅的数量
    private Integer borrowableQuantity;
    //还书的数量
    private Long returnedQuantity;

    public List<BookOrderItemInfo> getBookOrderItemInfoList() {
        return bookOrderItemInfoList;
    }

    public void setBookOrderItemInfoList(List<BookOrderItemInfo> bookOrderItemInfoList) {
        this.bookOrderItemInfoList = bookOrderItemInfoList;
    }


    public List<BookInfo> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookInfo> bookList) {
        this.bookList = bookList;
    }

    public Long getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(Long returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public Integer getBorrowedQuantity() {
        return borrowedQuantity;
    }

    public void setBorrowedQuantity(Integer borrowedQuantity) {
        this.borrowedQuantity = borrowedQuantity;
    }

    public Integer getBorrowableQuantity() {
        return borrowableQuantity;
    }

    public void setBorrowableQuantity(Integer borrowableQuantity) {
        this.borrowableQuantity = borrowableQuantity;
    }
}
