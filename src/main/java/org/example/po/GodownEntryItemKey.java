package org.example.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Embeddable
public class GodownEntryItemKey implements Serializable {

    @Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "书号必须10或13位的数字")
    private String bno;

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public GodownEntry getGodownEntry() {
        return godownEntry;
    }

    public void setGodownEntry(GodownEntry godownEntry) {
        this.godownEntry = godownEntry;
    }

    @ManyToOne
    @JoinColumn(name = "eno")
    private GodownEntry godownEntry;
}
