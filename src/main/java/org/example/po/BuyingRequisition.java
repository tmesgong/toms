package org.example.po;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class BuyingRequisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rverifier_work_no")
    private TextbookAdmin verifierAdmin;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "get_book_verifier_work_no")
    private WarehouseKeeper verifierWarehouseKeeper;
    @ManyToOne
    @JoinColumn(name = "proposer_work_no")
    private Teacher proposer;

    private Date applicationDate = new Date();
    private Integer getStatus = 0;
    private Integer auditStatus = 0;
    private Date getBookDate;
    @Valid
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rno")
    private List<RequisitionItem> requisitionItems;

    public Integer getRno() {
        return rno;
    }

    public void setRno(Integer rno) {
        this.rno = rno;
    }

    public TextbookAdmin getVerifierAdmin() {
        return verifierAdmin;
    }

    public void setVerifierAdmin(TextbookAdmin verifierAdmin) {
        this.verifierAdmin = verifierAdmin;
    }

    public WarehouseKeeper getVerifierWarehouseKeeper() {
        return verifierWarehouseKeeper;
    }

    public void setVerifierWarehouseKeeper(WarehouseKeeper verifierWarehouseKeeper) {
        this.verifierWarehouseKeeper = verifierWarehouseKeeper;
    }

    public Teacher getProposer() {
        return proposer;
    }

    public void setProposer(Teacher proposer) {
        this.proposer = proposer;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Integer getGetStatus() {
        return getStatus;
    }

    public void setGetStatus(Integer getStatus) {
        this.getStatus = getStatus;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getGetBookDate() {
        return getBookDate;
    }

    public void setGetBookDate(Date getBookDate) {
        this.getBookDate = getBookDate;
    }

    public List<RequisitionItem> getRequisitionItems() {
        return requisitionItems;
    }

    public void setRequisitionItems(List<RequisitionItem> requisitionItems) {
        this.requisitionItems = requisitionItems;
    }


}
