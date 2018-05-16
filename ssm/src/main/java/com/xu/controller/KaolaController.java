package com.xu.controller;

import com.github.pagehelper.PageInfo;
import com.xu.entity.Kaola;
import com.xu.entity.KaolaType;
import com.xu.exception.NotFoundException;
import com.xu.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/kaola")
public class KaolaController {

    @Autowired
    private KaolaService kaolaService;

    /**
     * 获取URL地址中参数的id值，跳转页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String findbyid(@PathVariable Integer id,Model model){
        Kaola kaola = kaolaService.findbyid(id);
        if(kaola == null) {
            throw new NotFoundException();
        }
        model.addAttribute("kaola",kaola);
        return "kaola/hello";
    }

    /**
     * 显示商品列表 接收URL传的值，并建立map集合，灵活对多个条件进行筛选
     * @param p
     * @param model
     * @return
     */
    @GetMapping
    public String list(@RequestParam(defaultValue = "1",required = false) Integer p,Model model,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String place,
                       @RequestParam(required = false) Double minPrice,
                       @RequestParam(required = false) Double maxPrice,
                       @RequestParam(required = false) Integer typeId){

        Map<String,Object> querymap = new HashMap<>();
        querymap.put("productName",productName);
        querymap.put("place",place);
        querymap.put("minPrice",minPrice);
        querymap.put("maxPrice",maxPrice);
        querymap.put("typeId",typeId);

        PageInfo<Kaola> kaolaList = kaolaService.findKaolaByPageNowithquerymap(p,querymap);
        model.addAttribute("kaolaList",kaolaList);
        List<KaolaType> typeList = kaolaService.findkaolaType();
        model.addAttribute("typeList",typeList);
        return "kaola/list";
    }

    /**
     * 当请求/kaola/new时跳转到新建商品的页面，并同时将商品类型集合传过去
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String newkaola(Model model){
        List<KaolaType> typeList = kaolaService.findkaolaType();

        model.addAttribute("typeList",typeList);
        return "kaola/new";
    }

    /**
     * 在新增页面，表单不写action属性，默认提交到当前URL，进行表单提交，进行商品保存到数据库，并发送到前端一个信息，并进行重定向当商品列表页
     * @param kaola
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/new")
    public String savekaola(Kaola kaola, RedirectAttributes redirectAttributes){

        kaolaService.savekaola(kaola);

        redirectAttributes.addFlashAttribute("message","insert success !");
        return "redirect:/kaola";
    }

    /**
     * 删除商品，获取从url中参数id的值，然后传给前端一个信息
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    public String delkaola(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        kaolaService.delkaola(id);
        redirectAttributes.addFlashAttribute("message","删除成功！");
        return "redirect:/kaola";
    }

    /**
     * 获得参数中的id值进行判断是否存在，存在就进行回显旧数据
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String editkaola(@PathVariable Integer id,Model model){
        Kaola oldkaola = kaolaService.findbyid(id);
        if(oldkaola == null){
            throw new NotFoundException();
        }
        List<KaolaType> typeList = kaolaService.findkaolaType();
        model.addAttribute("kaola",oldkaola);
        model.addAttribute("typeList",typeList);

        return "kaola/edit";
    }

    /**
     * 提交编辑表单时action不写，默认是提交到当前路径进行保存，然后返回一个信息
     * @param kaola
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/{id:\\d+}/edit")
    public String editkaola(Kaola kaola,RedirectAttributes redirectAttributes){
        kaolaService.editkaola(kaola);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/kaola";
    }
}
