package org.launchcode.controllers;


import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    CheeseDao cheeseDao;

    // Request path: /menu
    @RequestMapping(value = "")
    public String index(Model model) {
        System.out.println("Index - Menu Cont");
        model.addAttribute("title" , "Menus");
        model.addAttribute("menus" , menuDao.findAll());
        return "menu/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        System.out.println("Add - GET - Menu Cont");

        model.addAttribute("title" , "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model , @ModelAttribute @Valid Menu menu ,
                      Errors errors) {
        System.out.println("Add - POST - Menu Cont");

        if (errors.hasErrors()) {
            model.addAttribute("title" , "Add Menu");
            return "menu/add";
        }

        menuDao.save(menu);
        return "redirect:/menu/view/" + menu.getId();
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model , @PathVariable int menuId) {
        System.out.println("View/MenuID - GET - Menu Cont");

        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("title" , menu.getName());
        model.addAttribute("cheeses" , menu.getCheeses());
        model.addAttribute("menuId" , menu.getId());
        model.addAttribute("menu", menu);

        return "menu/view";

    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model , @PathVariable int menuId) {
        System.out.println("Add Item/menu ID - Get - MENU Controller");
        Menu menu = menuDao.findOne(menuId);

        AddMenuItemForm form = new AddMenuItemForm(cheeseDao.findAll(),
                menu);
        List<Cheese> cheeses = menu.getCheeses();

        model.addAttribute("title" , "Add item to menu: " + menu.getName());
        model.addAttribute("form" , form);
        model.addAttribute("cheeses" , menu.getCheeses());

        return "menu/add-item";
    }

    // NOt Working
    @RequestMapping(value = "/add-item/{menuId}", method = RequestMethod.POST)
    public String addItem(@ModelAttribute @Valid AddMenuItemForm form, Errors errors, Model model) {
        System.out.println("Add-Item - MEnu Controller -POST");
        if (errors.hasErrors()) {

            model.addAttribute("form" , form);

            return "menu/add-item";
        }

        System.out.println("add-item - POST");

        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        Menu theMenu = menuDao.findOne(form.getMenuId());
        theMenu.addItem(theCheese);
        menuDao.save(theMenu);

        return "redirect:/menu/view/" + theMenu.getId();
    }

}



