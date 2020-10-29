package org.example.po;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    private String tworkNo;


    private String tname;
    private String className;
    private String tpassword;
    @OneToMany
    @JoinColumn(name = "proposer_work_no")
    private List<BuyingRequisition> buyingRequisitions;

    public String getTworkNo() {
        return tworkNo;
    }

    public void setTworkNo(String tworkNo) {
        this.tworkNo = tworkNo;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }

    public List<BuyingRequisition> getBuyingRequisitions() {
        return buyingRequisitions;
    }

    public void setBuyingRequisitions(List<BuyingRequisition> buyingRequisitions) {
        this.buyingRequisitions = buyingRequisitions;
    }
}
