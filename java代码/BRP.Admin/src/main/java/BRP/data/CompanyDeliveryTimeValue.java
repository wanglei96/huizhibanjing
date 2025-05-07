package BRP.data;

import java.util.Objects;

public class CompanyDeliveryTimeValue {
    private Integer companyId;
    private String strDeliveryTime;

    public CompanyDeliveryTimeValue(Integer companyId, String strDeliveryTime) {
        this.companyId = companyId;
        this.strDeliveryTime = strDeliveryTime;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompanyDeliveryTimeValue)) return false;
        CompanyDeliveryTimeValue other = (CompanyDeliveryTimeValue) obj;
        return this.companyId == other.companyId && this.strDeliveryTime.equals(other.strDeliveryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, strDeliveryTime);
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getStrDeliveryTime() {
        return strDeliveryTime;
    }

    public void setStrDeliveryTime(String strDeliveryTime) {
        this.strDeliveryTime = strDeliveryTime;
    }
}
