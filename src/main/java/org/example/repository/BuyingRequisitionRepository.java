package org.example.repository;

import org.example.po.BuyingRequisition;
import org.example.po.TextbookAdmin;
import org.example.po.WarehouseKeeper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BuyingRequisitionRepository extends Repository<BuyingRequisition,Integer> {
    BuyingRequisition findBuyingRequisitionByRno(Integer rno);

    @Query("from BuyingRequisition where proposer.tworkNo=?1")
    Set<BuyingRequisition> findAllBuyingRequisitionByTworkNo(String tworkNo);

    void save(BuyingRequisition buyingRequisition);

    List<BuyingRequisition> findAll();


    @Query("update BuyingRequisition set auditStatus = ?2,verifierAdmin = ?3 where rno = ?1")
    @Modifying
    void updateAuditStatusAndVerifierByRno(Integer rno, Integer audit, TextbookAdmin textbookAdmin);





    @Query("update BuyingRequisition set getStatus = ?2,getBookDate = ?3,verifierWarehouseKeeper = ?4 where rno = ?1")
    @Modifying
    void updateGetStatusAndGetBookDateAndGetBookVerifierByRno(Integer rno, int status, Date date, WarehouseKeeper getBookVerifier);


    @Query("update BuyingRequisition set getStatus = ?2 where rno = ?1 and auditStatus = ?3 and getStatus = ?4")
    @Modifying
    void updateGetStatus(Integer rno, Integer status,Integer whereAuditStatus,Integer whereGetStatus);
}
