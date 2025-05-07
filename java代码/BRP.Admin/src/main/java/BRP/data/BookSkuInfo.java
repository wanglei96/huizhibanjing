package BRP.data;

import java.util.List;

public class BookSkuInfo extends ViewBookSku{
    private String bookTagNames;
    private String ageGroupNames;
    private Integer isBookAvailable;

    public String getBookTagNames() {
        return bookTagNames;
    }

    public void setBookTagNames(String bookTagNames) {
        this.bookTagNames = bookTagNames;
    }

    public String getAgeGroupNames() {
        return ageGroupNames;
    }

    public void setAgeGroupNames(String ageGroupNames) {
        this.ageGroupNames = ageGroupNames;
    }

    public Integer getIsBookAvailable() {
        return isBookAvailable;
    }

    public void setIsBookAvailable(Integer isBookAvailable) {
        this.isBookAvailable = isBookAvailable;
    }
}

