package org.example.controller;

import org.example.po.BuyingRequisition;
import org.example.po.RequisitionItem;
import org.example.po.Teacher;
import org.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;
    @GetMapping("/main")
    public String main(HttpSession session, Model model){
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        Set<BuyingRequisition> buyingRequisitions = teacherService.findAllBuyingRequisitionAboutTeacher(teacher.getTworkNo());
        model.addAttribute("buyingRequisitions",buyingRequisitions);

        return "teacher/main";
    }

    @GetMapping("/requisition_add")
    public String preAdd(){

        return "teacher/requisition_add";
    }

    @PostMapping("/add")
    public String add(@Validated BuyingRequisition buyingRequisition, BindingResult bindingResult, HttpSession session, Model model){

        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = new ArrayList<>();
            for (FieldError fieldError : fieldErrors) {
                errors.add(fieldError.getDefaultMessage());
            }

            model.addAttribute("errors",errors);
            return "teacher/requisition_add";
        }
        List<RequisitionItem> requisitionItems = buyingRequisition.getRequisitionItems();
        if (isBnoDuplication(model,requisitionItems)){
            return "teacher/requisition_add";
        }


        Teacher teacher = (Teacher) session.getAttribute("teacher");
        buyingRequisition.setProposer(teacher);
        for (RequisitionItem requisitionItem : requisitionItems) {
            requisitionItem.setApplicationNum(Integer.parseInt(requisitionItem.getApplicationNumString()));


           requisitionItem.getRequisitionItemKey().setBuyingRequisition(buyingRequisition);
        }

        teacherService.saveBuyingRequisition(buyingRequisition);
        return "redirect:/teacher/main";


    }

    @GetMapping("/requisition_info")
    public String showBuyingRequisitionDetails(Integer rno,Model model){
        BuyingRequisition buyingRequisition = teacherService.findBuyingRequisitionDetails(rno);
        model.addAttribute("buyingRequisition",buyingRequisition);
        return "teacher/requisition_info";

    }

    @PostMapping("/update")
    public String updateRequisition(@Validated BuyingRequisition buyingRequisition, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = new ArrayList<>();
            for (FieldError fieldError : fieldErrors) {
                errors.add(fieldError.getDefaultMessage());
            }
            model.addAttribute("errors",errors);

            return showBuyingRequisitionDetails(buyingRequisition.getRno(),model);
        }


        List<RequisitionItem> requisitionItems = buyingRequisition.getRequisitionItems();
        if (isBnoDuplication(model, requisitionItems))
            return showBuyingRequisitionDetails(buyingRequisition.getRno(), model);
        requisitionItems.forEach((x)-> x.setApplicationNum(Integer.parseInt(x.getApplicationNumString())));
        teacherService.updateBuyingRequisition(buyingRequisition);
        return "redirect:/teacher/main";

    }

    private boolean isBnoDuplication(Model model, List<RequisitionItem> requisitionItems) {
        Set<String> bnoSet = requisitionItems.stream().map((it) -> (it.getRequisitionItemKey().getBno())).collect(Collectors.toSet());
        if (requisitionItems.size() != bnoSet.size()){
            model.addAttribute("errors", Collections.singletonList("书号重复"));
            return true;
        }
        return false;
    }

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}

