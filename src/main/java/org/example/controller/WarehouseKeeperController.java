package org.example.controller;

import exception.BookNotEnoughException;
import org.example.po.WarehouseKeeper;
import org.example.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/warehouseKeeper")
public class WarehouseKeeperController {
    private WarehouseService warehouseService;

    @Autowired
    public WarehouseKeeperController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("inventories",warehouseService.findAllInventory());
        return "warehouseKeeper/main";

    }

    @GetMapping("/requisition_show")
    public String requisitionShow(Model  model){
        model.addAttribute("buyingRequisitions",warehouseService.findAllBuyingRequisition());
        return "warehouseKeeper/requisition_show";
    }
    @GetMapping("/requisition_show_details")
    public String requisitionShowDetails(Integer rno,Model model){
        model.addAttribute("buyingRequisition",warehouseService.findBuyingRequisitionByRno(rno));
        return "warehouseKeeper/requisition_show_details";
    }
    @GetMapping("/warehousing_show")
    public String warehousingShow(Model model){
        model.addAttribute("godownEntries",warehouseService.findAllGodownEntry());
        return "warehouseKeeper/warehousing_show";
    }
    @GetMapping("/warehousing_show_details")
    public String warehousingShowDetails(Integer eno,Model model){
        model.addAttribute("godownEntry",warehouseService.findGownEntryByEno(eno));
        return "warehouseKeeper/warehousing_show_details";
    }


    @PostMapping("/passWarehousing")
    public String passWarehousing(Integer eno, HttpSession session){
        WarehouseKeeper warehouseKeeper = (WarehouseKeeper) session.getAttribute("warehouseKeeper");
        warehouseService.passWarehousing(eno,warehouseKeeper);
        return "redirect:/warehouseKeeper/warehousing_show";

    }

    @PostMapping("/passGet")
    public String passGet(Integer rno,HttpSession session, Model model){
//        System.out.println(rno);
        WarehouseKeeper warehouseKeeper = (WarehouseKeeper) session.getAttribute("warehouseKeeper");

        try {
            warehouseService.passGet(rno,warehouseKeeper);
        } catch (BookNotEnoughException e) {
            model.addAttribute("error",e.getMessage());
            return requisitionShowDetails(rno,model);
        }
        return "redirect:/warehouseKeeper/requisition_show";

    }



}
