package org.example.service;
import org.example.po.BuyingRequisition;
import org.example.po.Teacher;
import org.example.repository.BuyingRequisitionRepository;
import org.example.repository.RequisitionItemRepository;
import org.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class TeacherService {


    private BuyingRequisitionRepository buyingRequisitionRepository;
    private RequisitionItemRepository requisitionItemRepository;


    public Set<BuyingRequisition> findAllBuyingRequisitionAboutTeacher(String tworkNO) {
        return buyingRequisitionRepository.findAllBuyingRequisitionByTworkNo(tworkNO);
    }



    @Transactional
    public void saveBuyingRequisition(BuyingRequisition buyingRequisition) {
        buyingRequisitionRepository.save(buyingRequisition);


    }

    public BuyingRequisition findBuyingRequisitionDetails(Integer rno) {
       return buyingRequisitionRepository.findBuyingRequisitionByRno(rno);
    }


    @Autowired
    public TeacherService(BuyingRequisitionRepository buyingRequisitionRepository, RequisitionItemRepository requisitionItemRepository) {
        this.buyingRequisitionRepository = buyingRequisitionRepository;
        this.requisitionItemRepository = requisitionItemRepository;
    }

    public void updateBuyingRequisition(BuyingRequisition buyingRequisition) {
        buyingRequisition.getRequisitionItems().forEach((x)->{
            x.getRequisitionItemKey().setBuyingRequisition(buyingRequisition);
            requisitionItemRepository.save(x);
        });





    }
}
