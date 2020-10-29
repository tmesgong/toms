package org.example.bo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LackBook {
    @Id
    private String bno;
    private Integer num;

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
