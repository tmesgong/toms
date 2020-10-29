package org.example.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class WarehouseKeeper {
    @Id
    private String kworkNo;
    private String kname;
    private String kpassword;
    @OneToMany
    @JoinColumn(name = "verifier_work_no")
    private List<GodownEntry> godownEntries;
    @OneToMany
    @JoinColumn(name = "get_book_verifier_work_no")
    private List<BuyingRequisition> buyingRequisitions;


    public List<GodownEntry> getGodownEntries() {
        return godownEntries;
    }

    public void setGodownEntries(List<GodownEntry> godownEntries) {
        this.godownEntries = godownEntries;
    }

    public List<BuyingRequisition> getBuyingRequisitions() {
        return buyingRequisitions;
    }

    public void setBuyingRequisitions(List<BuyingRequisition> buyingRequisitions) {
        this.buyingRequisitions = buyingRequisitions;
    }

    public String getKworkNo() {
        return kworkNo;
    }

    public void setKworkNo(String kworkNo) {
        this.kworkNo = kworkNo;
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public String getKpassword() {
        return kpassword;
    }

    public void setKpassword(String kpassword) {
        this.kpassword = kpassword;
    }
}
