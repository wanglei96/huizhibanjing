package BRP.data;

public class BookInOrderInfo extends ViewBookInOrder{
    private Integer borrowingQuantity;

    private Boolean showUnlock;

    public Integer getBorrowingQuantity() {
        return borrowingQuantity;
    }

    public void setBorrowingQuantity(Integer borrowingQuantity) {
        this.borrowingQuantity = borrowingQuantity;
    }

    public Boolean getShowUnlock() {
        return showUnlock;
    }

    public void setShowUnlock(Boolean showUnlock) {
        this.showUnlock = showUnlock;
    }
}
