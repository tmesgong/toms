package org.example.repository;

import org.example.po.Inventory;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface InventoryRepository extends Repository<Inventory,String> {

    List<Inventory> findAll();

    Inventory findInventoryByIbookNo(String ibookNo);


    void save(Inventory inventory);
}
