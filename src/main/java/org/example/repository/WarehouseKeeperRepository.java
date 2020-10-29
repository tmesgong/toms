package org.example.repository;


import org.example.po.WarehouseKeeper;
import org.springframework.data.repository.Repository;

public interface WarehouseKeeperRepository extends Repository<WarehouseKeeper, String> {
    WarehouseKeeper findWarehouseKeeperByKworkNoAndKpassword(String kworkNo, String kpassword);
}
