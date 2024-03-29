package com.fu.blog.web.admin;

import com.fu.blog.po.Type;
import com.fu.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author fudada
 * @date 2019/6/20 - 15:10
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;



    @GetMapping("/types")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }




    /**
     * 添加
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post( Type type, BindingResult result, RedirectAttributes attributes) {
        Type type2 = typeService.getTypeByName(type.getName());

        if (type2 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type type1 = typeService.saveType(type);
        if (type1 == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}")
    public String editPost( Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type type2 = typeService.updateType(id, type);
        if (type2 == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/types";
    }

}
