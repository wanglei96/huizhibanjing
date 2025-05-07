package BRP.vo;

import java.util.List;
import java.util.Map;

public class PickBookOrderVo {

    private Integer id;
    private String memberNo;
    private String name;
    private String mobilePhone;
    private String address;
    private String deliverLineName; // 配送线路
    private Integer pickTotal; // 总共需拣本数

    private Integer yPicKNum; // 已拣本数

    private Integer nPicKNum; // 未拣本数

    private List<Map<String, String>> childBookOrder;

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Map<String, String>> getChildBookOrder() {
        return childBookOrder;
    }

    public void setChildBookOrder(List<Map<String, String>> childBookOrder) {
        this.childBookOrder = childBookOrder;
    }

    public Integer getPickTotal() {
        return pickTotal;
    }

    public void setPickTotal(Integer pickTotal) {
        this.pickTotal = pickTotal;
    }

    public Integer getyPicKNum() {
        return yPicKNum;
    }

    public void setyPicKNum(Integer yPicKNum) {
        this.yPicKNum = yPicKNum;
    }

    public Integer getnPicKNum() {
        return nPicKNum;
    }

    public void setnPicKNum(Integer nPicKNum) {
        this.nPicKNum = nPicKNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliverLineName() {
        return deliverLineName;
    }

    public void setDeliverLineName(String deliverLineName) {
        this.deliverLineName = deliverLineName;
    }
}
