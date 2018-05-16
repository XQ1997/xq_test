package com.kaisheng.controller;

import com.github.pagehelper.PageInfo;
import com.kaisheng.entity.Product;
import com.kaisheng.result.ResponseBean;
import com.kaisheng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home(@RequestParam(required = false,defaultValue = "1") Integer pageNo, Model model){
        PageInfo<Product> pageInfo = productService.findAll(pageNo);

        model.addAttribute("pageInfo",pageInfo);
        return "home";
    }

    @GetMapping("/new")
    public String newproduct(){
        return "new";
    }

    @PostMapping("/new")
    public String saveproduct(Product product){
        productService.save(product);
        return "redirect:/product/home";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id,Model model){
        Product product = productService.findByid(id);
        model.addAttribute("product",product);
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,Model model){
        Product product = productService.findByid(id);
        model.addAttribute("product",product);
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String saveedit(Product product){
        productService.update(product);
        return "redirect:/product/home";
    }

    @GetMapping("/{id}/del")
    public String del(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            productService.del(id);
            redirectAttributes.addFlashAttribute("message","删除成功");
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/product/home";
    }

}
