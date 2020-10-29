package org.example.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;
@Entity
public class Inventory {
    @Id
    private String ibookNo;
    private Integer stockQuantity;

    public String getIbookNo() {
        return ibookNo;
    }

    public void setIbookNo(String ibookNo) {
        this.ibookNo = ibookNo;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
