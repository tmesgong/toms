package org.example.repository;

import org.example.po.GodownEntry;
import org.example.po.WarehouseKeeper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GodownEntryRepository extends Repository<GodownEntry,Integer> {

    void save(GodownEntry godownEntry);
    List<GodownEntry> findAll();


    @Query("from GodownEntry where warehousingBuyer.bworkNo=?1")
    List<GodownEntry> findAllBybworkNo(String bworkNo);


    GodownEntry findGodownEntriesByEno(Integer eno);

    @Query("update GodownEntry set inventoryStatus = ?2,verifier = ?3 where eno = ?1")
    @Modifying
    void updateInventoryStatusAndVerifierByEno(Integer eno, Integer status, WarehouseKeeper warehouseKeeper);


//    @Query(nativeQuery = true, value = "update godown_entry set warehousing_no=?1,inventory_status=?2;select @@identity")
//    @Modifying
//    Integer insertWarehousingNoAndInventoryStatus(String bworkNo, Integer status);
}
