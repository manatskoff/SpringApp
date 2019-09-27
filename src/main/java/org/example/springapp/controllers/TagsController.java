package org.example.springapp.controllers;


import org.example.springapp.entitys.Tags;
import org.example.springapp.repositories.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TagsController {

    @Autowired
    private TagsRepo tagsRepo;



    @GetMapping("/addtags")
    private String tags(Model model) {

        Iterable<Tags> tagsArray = tagsRepo.findAll();             // Получаем список Тэгов
        model.addAttribute("tagsArray", tagsArray);

        return "addtags";
    }



    @PostMapping("/addtags")
    private String addTags(@RequestParam String name, Model model) {

        Tags tag = new Tags(name);
        tagsRepo.save(tag);

        Iterable<Tags> tagsArray = tagsRepo.findAll();             // Получаем список Тэгов
        model.addAttribute("tagsArray", tagsArray);

        return "addtags";
    }



}
