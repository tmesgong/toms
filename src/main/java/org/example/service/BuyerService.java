package org.example.service;

import org.example.bo.LackBook;
import org.example.po.GodownEntry;
import org.example.po.GodownEntryItem;
import org.example.repository.GodownEntryItemRepository;
import org.example.repository.GodownEntryRepository;
import org.example.repository.LackBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyerService {
    private LackBookRepository lackBookRepository;

    private GodownEntryRepository godownEntryRepository;

    private GodownEntryItemRepository godownEntryItemRepository;

    @Autowired
    public BuyerService(LackBookRepository lackBookRepository, GodownEntryRepository godownEntryRepository, GodownEntryItemRepository godownEntryItemRepository) {
        this.lackBookRepository = lackBookRepository;
        this.godownEntryRepository = godownEntryRepository;
        this.godownEntryItemRepository = godownEntryItemRepository;
    }




    @Transactional
    public List<LackBook> findAllLackBook(){
        return lackBookRepository.findAllBookLack();
    }

    @Transactional
    public void saveGodownEntry(GodownEntry godownEntry) {



        godownEntryRepository.save(godownEntry);


    }


    public List<GodownEntry> findAllGodownEntryAboutThisBuyer(String bworkNo) {
        return godownEntryRepository.findAllBybworkNo(bworkNo);

    }

    public GodownEntry showEntryDetail(Integer eno) {

        return godownEntryRepository.findGodownEntriesByEno(eno);
    }

    @Transactional
    public void updateGodownEntry(List<GodownEntryItem> godownEntryItems) {
        godownEntryItems.forEach((it)->{
            godownEntryItemRepository.save(it);
        });


    }
}
