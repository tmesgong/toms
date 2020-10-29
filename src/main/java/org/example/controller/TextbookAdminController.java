package org.example.controller;

import org.example.po.BuyingRequisition;
import org.example.po.TextbookAdmin;
import org.example.service.TeacherService;
import org.example.service.TextbookAdminService;
import org.example.vo.BuyingRequisitionIds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/textbookAdmin")
public class TextbookAdminController {


    private TextbookAdminService textbookAdminService;
    private TeacherService teacherService;

    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("buyingRequisitions",textbookAdminService.findAllBuyingRequisition());
        return "textbookAdmin/main";

    }


    public TextbookAdminController(TextbookAdminService textbookAdminService, TeacherService teacherService) {
        this.textbookAdminService = textbookAdminService;
        this.teacherService = teacherService;
    }

    @GetMapping("requisition_info")
    public String showBuyingRequisitionDetails(Integer rno,Model model){
        BuyingRequisition buyingRequisition = teacherService.findBuyingRequisitionDetails(rno);
        model.addAttribute("buyingRequisition",buyingRequisition);
        return "textbookAdmin/requisition_info";

    }

    @PostMapping("/pass")
    public String pass(Integer rno, HttpSession session){
        TextbookAdmin textbookAdmin = (TextbookAdmin) session.getAttribute("textbookAdmin");
        textbookAdminService.pass(rno,textbookAdmin);
        return "redirect:/textbookAdmin/main";

    }

    @PostMapping("/getStart")
    public String getStart(BuyingRequisitionIds buyingRequisitionIds){
        List<Integer> rnos = buyingRequisitionIds.getRnos();
        textbookAdminService.getStart(rnos);



        return "redirect:/textbookAdmin/main";

    }








}
