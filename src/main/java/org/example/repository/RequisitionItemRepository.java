package org.example.repository;

import org.example.po.RequisitionItem;
import org.example.po.RequisitionItemKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RequisitionItemRepository extends Repository<RequisitionItem, RequisitionItemKey> {
    void save(RequisitionItem RequisitionItem);
}
