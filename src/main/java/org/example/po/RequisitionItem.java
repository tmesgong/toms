package org.example.po;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class RequisitionItem {

    @EmbeddedId
    @Valid
    private RequisitionItemKey requisitionItemKey;
    @Pattern(regexp = "[1-9]([0-9])*",message = "申请数量必须是正整数")
    @Transient
    private String applicationNumString;//用于验证applicationNum是正整数
    @Column(name = "application_num")
    private Integer applicationNum;
    {
        if (applicationNumString != null){
            applicationNum = Integer.parseInt(applicationNumString);
        }
    }
    public RequisitionItemKey getRequisitionItemKey() {
        return requisitionItemKey;
    }

    public void setRequisitionItemKey(RequisitionItemKey requisitionItemKey) {
        this.requisitionItemKey = requisitionItemKey;
    }

    public Integer getApplicationNum() {
        return applicationNum;
    }

    public void setApplicationNum(Integer applicationNum) {
        this.applicationNum = applicationNum;
    }


    public String getApplicationNumString() {
        return applicationNumString;
    }

    public void setApplicationNumString(String applicationNumString) {
        this.applicationNumString = applicationNumString;
    }

}
