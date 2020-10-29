package org.example.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Buyer {
    @Id
    private String bworkNo;
    private String bname;
    private String bpassword;
    @OneToMany
    @JoinColumn(name = "warehousing_no")
    private List<GodownEntry> godownEntries;

    public String getBworkNo() {
        return bworkNo;
    }

    public void setBworkNo(String bworkNo) {
        this.bworkNo = bworkNo;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBpassword() {
        return bpassword;
    }

    public void setBpassword(String bpassword) {
        this.bpassword = bpassword;
    }

    public List<GodownEntry> getGodownEntries() {
        return godownEntries;
    }

    public void setGodownEntries(List<GodownEntry> godownEntries) {
        this.godownEntries = godownEntries;
    }
}
