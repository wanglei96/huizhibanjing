package BRP.data;

public class ReturnBookOrderInfo {
    private ViewBook viewBook;

    public ReturnBookOrderItemInfo getReturnBookOrderItemInfo() {
        return returnBookOrderItemInfo;
    }

    public void setReturnBookOrderItemInfo(ReturnBookOrderItemInfo returnBookOrderItemInfo) {
        this.returnBookOrderItemInfo = returnBookOrderItemInfo;
    }

    private ReturnBookOrderItemInfo returnBookOrderItemInfo;

    public ViewBook getViewBook() {
        return viewBook;
    }

    public void setViewBook(ViewBook viewBook) {
        this.viewBook = viewBook;
    }
}
