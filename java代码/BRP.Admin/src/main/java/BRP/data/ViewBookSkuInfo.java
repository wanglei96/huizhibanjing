package BRP.data;

import java.util.List;

public class ViewBookSkuInfo extends ViewBookSku{
    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }


    private Integer bookCount;

    public List<AgeGroup> getAgeGroupList() {
        return ageGroupList;
    }

    public void setAgeGroupList(List<AgeGroup> ageGroupList) {
        this.ageGroupList = ageGroupList;
    }

    private List<AgeGroup> ageGroupList;

    private Boolean isExist;

    private String locationType;

    private List<String> bookTagNameList;

    public List<String> getBookTagNameList() {
        return bookTagNameList;
    }

    public void setBookTagNameList(List<String> bookTagNameList) {
        this.bookTagNameList = bookTagNameList;
    }

    public Boolean getIsExist() {
        return isExist;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public void setIsExist(Boolean isExist) {
        this.isExist = isExist;
    }
}

