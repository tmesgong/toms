package org.example.repository;

import org.example.po.GodownEntry;
import org.example.po.GodownEntryItem;
import org.example.po.GodownEntryItemKey;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GodownEntryItemRepository extends Repository<GodownEntryItem, GodownEntryItemKey> {


    void save(GodownEntryItem godownEntryItem);


}
