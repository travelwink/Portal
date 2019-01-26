package travelwink.manage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import travelwink.manage.bean.Message;
import travelwink.manage.common.Constant;
import travelwink.manage.domain.entity.Department;
import travelwink.manage.domain.entity.Menu;
import travelwink.manage.domain.entity.User;
import travelwink.manage.service.DepartmentService;
import travelwink.manage.service.MenuService;
import travelwink.manage.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MenuService menuService;

//    @ModelAttribute("departments")
//    public List<Department> populateDepartments() {
//        log.info("--------------> # 部门列表 #");
//        return this.departmentService.findAll();
//    }

//    @ModelAttribute("users")
//    public List<User> populateUsers() {
//        log.info("--------------> # 用户列表 #");
//        return this.userService.findAll();
//    }

    @RequestMapping
    public String initPage (User user, Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        Menu breadcrumb = menuService.findByUrl("/user");
        model.addAttribute("breadcrumb", breadcrumb);
        List<Department> departments = departmentService.findAllForSelect();
        model.addAttribute("departments", departments);
        return "manage/user";
    }

    @RequestMapping(value = "/add", params = {"add"})
    public String add (User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().toString());
            return "manage/user";
        } else {
            user.setPassword("111111");
            user.setCreateDate(new Date());
            user.setStatus(Constant.VALID);
            int result = userService.add(user);
            return "redirect:/user";
        }
    }

    @RequestMapping(value = "/delete", params = {"delete"})
    public String delete(User user, RedirectAttributes attributes) {
        int resultCount = userService.delete(user.getId());
        Message message;
        if (1 == resultCount) {
            message = new Message(1,Constant.MESSAGE_DELETE_SUCCESS);
        } else {
            message = new Message(0,Constant.MESSAGE_DELETE_FAIL);
        }
        attributes.addFlashAttribute("message",message);
        return "redirect:/user";
    }

    @GetMapping(value="/getUserList")
    public List<User> getUserList(@RequestBody User user){
        return null;
    }

}
