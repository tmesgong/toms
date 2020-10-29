package org.example.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class TextbookAdmin {
    @Id
    private String aworkNo;
    private String aname;
    private String apassword;
    @OneToMany
    @JoinColumn(name = "rverifier_work_no")
    private List<BuyingRequisition> buyingRequisitions;

    public List<BuyingRequisition> getBuyingRequisitions() {
        return buyingRequisitions;
    }

    public void setBuyingRequisitions(List<BuyingRequisition> buyingRequisitions) {
        this.buyingRequisitions = buyingRequisitions;
    }

    public String getAworkNo() {
        return aworkNo;
    }

    public void setAworkNo(String aworkNo) {
        this.aworkNo = aworkNo;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }
}
