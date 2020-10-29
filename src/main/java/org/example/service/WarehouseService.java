package org.example.service;

import exception.BookNotEnoughException;
import org.example.po.*;
import org.example.repository.BuyingRequisitionRepository;
import org.example.repository.GodownEntryRepository;
import org.example.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    public WarehouseService(InventoryRepository inventoryRepository, GodownEntryRepository godownEntryRepository, BuyingRequisitionRepository buyingRequisitionRepository) {
        this.inventoryRepository = inventoryRepository;
        this.godownEntryRepository = godownEntryRepository;
        this.buyingRequisitionRepository = buyingRequisitionRepository;
    }

    private InventoryRepository inventoryRepository;
    private GodownEntryRepository godownEntryRepository;
    private BuyingRequisitionRepository buyingRequisitionRepository;


    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    public List<GodownEntry> findAllGodownEntry() {
        return godownEntryRepository.findAll();
    }

    public List<BuyingRequisition> findAllBuyingRequisition() {
        return buyingRequisitionRepository.findAll();
    }


    public GodownEntry findGownEntryByEno(Integer eno) {
        return godownEntryRepository.findGodownEntriesByEno(eno);

    }

    public BuyingRequisition findBuyingRequisitionByRno(Integer rno) {
        return buyingRequisitionRepository.findBuyingRequisitionByRno(rno);

    }


    @Transactional
    public void passWarehousing(Integer eno, WarehouseKeeper warehouseKeeper) {
        godownEntryRepository.updateInventoryStatusAndVerifierByEno(eno, 1, warehouseKeeper);
        GodownEntry entry = godownEntryRepository.findGodownEntriesByEno(eno);
        entry.getGodownEntryItems().forEach((it) -> {
            Integer stockingNum = it.getStockingNum();
            String bno = it.getGodownEntryItemKey().getBno();
            Inventory inventory = new Inventory();
            inventory.setIbookNo(bno);
            Inventory inventoryFind = inventoryRepository.findInventoryByIbookNo(bno);
            if (inventoryFind == null || inventoryFind.getStockQuantity() == null) {


                inventory.setStockQuantity(stockingNum);
            } else {


                inventory.setStockQuantity(inventoryFind.getStockQuantity() + stockingNum);

            }
            inventoryRepository.save(inventory);


        });
    }

    @Transactional
    public void passGet(Integer rno, WarehouseKeeper warehouseKeeper) throws BookNotEnoughException {
        BuyingRequisition requisition = buyingRequisitionRepository.findBuyingRequisitionByRno(rno);
        for (RequisitionItem it : requisition.getRequisitionItems()) {
            String bno = it.getRequisitionItemKey().getBno();
            Inventory inventory = inventoryRepository.findInventoryByIbookNo(bno);
            if (inventory.getStockQuantity() < it.getApplicationNum()){
                throw new BookNotEnoughException("库存不够");
            }
            inventory.setStockQuantity(inventory.getStockQuantity() - it.getApplicationNum());
            inventoryRepository.save(inventory);
        }


        buyingRequisitionRepository.updateGetStatusAndGetBookDateAndGetBookVerifierByRno(rno, 2, new Date(), warehouseKeeper);
    }
}
