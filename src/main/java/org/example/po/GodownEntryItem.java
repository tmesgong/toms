package org.example.po;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Entity
public class GodownEntryItem {
    @Valid
    @EmbeddedId
    private GodownEntryItemKey godownEntryItemKey;

    private Integer stockingNum;
    @Pattern(regexp = "[1-9]([0-9])*",message = "申请数量必须是正整数")
    @Transient
    private String stockingNumString;
    public String getStockingNumString() {
        return stockingNumString;
    }

    public void setStockingNumString(String stockingNumString) {
        this.stockingNumString = stockingNumString;
    }


    public GodownEntryItemKey getGodownEntryItemKey() {
        return godownEntryItemKey;
    }

    public void setGodownEntryItemKey(GodownEntryItemKey godownEntryItemKey) {
        this.godownEntryItemKey = godownEntryItemKey;
    }

    public Integer getStockingNum() {
        return stockingNum;
    }

    public void setStockingNum(Integer stockingNum) {
        this.stockingNum = stockingNum;
    }
}
