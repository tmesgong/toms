package org.example.service;

import org.example.po.Buyer;
import org.example.po.Teacher;
import org.example.po.TextbookAdmin;
import org.example.po.WarehouseKeeper;
import org.example.repository.BuyerRepository;
import org.example.repository.TeacherRepository;
import org.example.repository.TextbookAdminRepository;
import org.example.repository.WarehouseKeeperRepository;
import org.example.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class LoginService {
    private TeacherRepository teacherRepository;
    private TextbookAdminRepository textbookAdminRepository;
    private BuyerRepository buyerRepository;
    private WarehouseKeeperRepository warehouseKeeperRepository;

    @Autowired
    public LoginService(TeacherRepository teacherRepository, TextbookAdminRepository textbookAdminRepository, BuyerRepository buyerRepository, WarehouseKeeperRepository warehouseKeeperRepository) {
        this.teacherRepository = teacherRepository;
        this.textbookAdminRepository = textbookAdminRepository;
        this.buyerRepository = buyerRepository;
        this.warehouseKeeperRepository = warehouseKeeperRepository;
    }


    @Transactional
    public Object login(User user) {
        Integer userType = user.getUserType();
        Object o;
        String workNo = user.getWorkNo();
        String password = user.getPassword();
        switch (userType) {
            case 0:
                o = teacherRepository.findTeacherByTworkNoAndTpassword(workNo, password);
                break;
            case 1:

                o = textbookAdminRepository.findTextbookAdminByAworkNoAndApassword(workNo, password);
                break;
            case 2:

                o = buyerRepository.findBuyerByBworkNoAndBpassword(workNo, password);
                break;
            case 3:

                o = warehouseKeeperRepository.findWarehouseKeeperByKworkNoAndKpassword(workNo, password);
                break;
            default:
                o = null;
        }


        return o;
    }


}
