package org.example.controller;

import org.example.po.Buyer;
import org.example.po.GodownEntry;
import org.example.po.GodownEntryItem;
import org.example.service.BuyerService;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/buyer")
public class BuyerController {
    private BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("lackBooks",buyerService.findAllLackBook());
        return "buyer/main";
    }


    @GetMapping("/warehousing_add")
    public String warehousingAdd(){
        return "buyer/warehousing_add";
    }

    @GetMapping("/warehousing_show")
    public String warehousingShow(HttpSession session, Model model){
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        List<GodownEntry> godownEntries = buyerService.findAllGodownEntryAboutThisBuyer(buyer.getBworkNo());
        model.addAttribute("godownEntries",godownEntries);
        return "buyer/warehousing_show";
    }


    @PostMapping("/add")
    public String add(@Validated GodownEntry godownEntry, BindingResult bindingResult, Model model, HttpSession session){
        if (hasErrors(bindingResult, model)) return warehousingAdd();
        List<GodownEntryItem> godownEntryItems = godownEntry.getGodownEntryItems();
        if (isBnoDuplication(model, godownEntryItems)) return warehousingAdd();
        Buyer buyer = (Buyer)session.getAttribute("buyer");
        godownEntry.setWarehousingBuyer(buyer);
        godownEntryItems.forEach((x)->{
            x.setStockingNum(Integer.parseInt(x.getStockingNumString()));
            x.getGodownEntryItemKey().setGodownEntry(godownEntry);
        });
        buyerService.saveGodownEntry(godownEntry);
        return "redirect:/buyer/warehousing_show";
    }

    private boolean isBnoDuplication(Model model, List<GodownEntryItem> godownEntryItems) {
        Set<String> bnoSet = godownEntryItems.stream().map((it) -> (it.getGodownEntryItemKey().getBno())).collect(Collectors.toSet());
        if (bnoSet.size() != godownEntryItems.size()){
            model.addAttribute("errors", Collections.singletonList("书号重复"));
            return true;

        }
        return false;
    }

    @GetMapping("/showEntryDetail")
    public String showEntryDetail(Integer eno, Model model){
        GodownEntry godownEntry = buyerService.showEntryDetail(eno);

        model.addAttribute("godownEntry",godownEntry);

        return "buyer/warehousing_show_details";

    }

    @PostMapping("/update")
    public String update(@Validated GodownEntry godownEntry, BindingResult bindingResult, Model model){
        if (hasErrors(bindingResult, model)) return warehousingAdd();
        List<GodownEntryItem> godownEntryItems = godownEntry.getGodownEntryItems();
        if (isBnoDuplication(model, godownEntryItems)) return warehousingAdd();
        godownEntryItems.forEach((it)->{
            it.setStockingNum(Integer.parseInt(it.getStockingNumString()));
            it.getGodownEntryItemKey().setGodownEntry(godownEntry);
        });
        buyerService.updateGodownEntry(godownEntryItems);


        return "redirect:/buyer/warehousing_show";

    }

    private boolean hasErrors(BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errors = new ArrayList<>();
            for (FieldError fieldError : fieldErrors) {
                errors.add(fieldError.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            return true;
        }
        return false;
    }


}
