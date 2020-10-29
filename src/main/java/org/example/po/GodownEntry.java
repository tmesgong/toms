package org.example.po;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
public class GodownEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eno;

    @ManyToOne
    @JoinColumn(name = "warehousing_no")
    private Buyer warehousingBuyer;
    @ManyToOne
    @JoinColumn(name = "verifier_work_no")
    private WarehouseKeeper verifier;


    private Integer inventoryStatus = 0;
    @Valid
    @OneToMany
    @JoinColumn(name = "eno")
    @NotFound(action= NotFoundAction.IGNORE)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<GodownEntryItem> godownEntryItems;

    public Integer getEno() {
        return eno;
    }

    public void setEno(Integer eno) {
        this.eno = eno;
    }

    public Buyer getWarehousingBuyer() {
        return warehousingBuyer;
    }

    public void setWarehousingBuyer(Buyer warehousingBuyer) {
        this.warehousingBuyer = warehousingBuyer;
    }

    public WarehouseKeeper getVerifier() {
        return verifier;
    }

    public void setVerifier(WarehouseKeeper verifier) {
        this.verifier = verifier;
    }

    public Integer getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(Integer inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public List<GodownEntryItem> getGodownEntryItems() {
        return godownEntryItems;
    }

    public void setGodownEntryItems(List<GodownEntryItem> godownEntryItems) {
        this.godownEntryItems = godownEntryItems;
    }
}
