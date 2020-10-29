package org.example.service;

import org.example.po.BuyingRequisition;
import org.example.po.TextbookAdmin;
import org.example.repository.BuyingRequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TextbookAdminService {
    private BuyingRequisitionRepository buyingRequisitionRepository;

    @Autowired
    public TextbookAdminService(BuyingRequisitionRepository buyingRequisitionRepository) {
        this.buyingRequisitionRepository = buyingRequisitionRepository;
    }


    public List<BuyingRequisition> findAllBuyingRequisition(){
        return buyingRequisitionRepository.findAll();
    }


    @Transactional
    public void pass(Integer rno, TextbookAdmin textbookAdmin) {
        //将请购表改为已审核
        buyingRequisitionRepository.updateAuditStatusAndVerifierByRno(rno,1,textbookAdmin);



    }

    @Transactional
    public void getStart(List<Integer> rnos){
        rnos.forEach((it)->{
            buyingRequisitionRepository.updateGetStatus(it,1,1,0);
        });

    }
}
