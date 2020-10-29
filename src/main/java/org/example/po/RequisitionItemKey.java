package org.example.po;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class RequisitionItemKey implements Serializable {
    @Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "书号必须10或13位的数字")
    private String bno;
    @ManyToOne
    @JoinColumn(name = "rno")
    private BuyingRequisition buyingRequisition;

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public BuyingRequisition getBuyingRequisition() {
        return buyingRequisition;
    }

    public void setBuyingRequisition(BuyingRequisition buyingRequisition) {
        this.buyingRequisition = buyingRequisition;
    }
}
