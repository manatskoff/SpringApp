package org.example.springapp.controllers;

import org.example.springapp.entitys.FilesItem;
import org.example.springapp.entitys.Tags;
import org.example.springapp.entitys.User;
import org.example.springapp.repositories.FileItemRepo;
import org.example.springapp.repositories.TagsRepo;
import org.example.springapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import java.io.File;
import java.io.IOException;
import java.util.UUID;




@Controller
public class MainController {


    @Autowired
    private FileItemRepo fileItemRepo;
    @Autowired
    private TagsRepo tagsRepo;
    @Autowired
    private UserRepo userRepo;




    @Value("${upload.path}")
    private String uploadPath;






    @GetMapping("/")
    public String hellopage()
        {
            return "hellopage";
        }




    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       Model model)
        {
            Iterable<FilesItem> fileItemArray = fileItemRepo.findAll();     // Получаем полный список файлов в БД
            Iterable<Tags> tagsArray = tagsRepo.findAll();                  // Получаем список Тэгов

            model.addAttribute("fileItemArray", fileItemArray);
            model.addAttribute("tagsArray", tagsArray);
            model.addAttribute("currentUser", user.getUsername());

            return "main";
        }





    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {


        FilesItem fileItem = new FilesItem(text, tag, user);


        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            fileItem.setFilename(resultFilename);


            fileItemRepo.save(fileItem);


            Iterable<FilesItem> fileItemArray = fileItemRepo.findAll();
            Iterable<Tags> tagsArray = tagsRepo.findAll();
            model.addAttribute("fileItemArray", fileItemArray);
            model.addAttribute("tagsArray", tagsArray);
            model.addAttribute("currentUser", user.getUsername());

            return "main";
        }


        return "redirect:/errorloadfile";
    }





    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model){

        Iterable<FilesItem> fileItemArray;

        if (filter != null && !filter.isEmpty()) {
            fileItemArray = fileItemRepo.findByTag(filter);
        } else {
            fileItemArray = fileItemRepo.findAll();
        }

        model.addAttribute("fileItemArray", fileItemArray);
        return "main";
    }




    // Считываем переменную переданную как fileconfig?fileId=123
    @GetMapping("/fileconfig")
    public String fileconfig(@RequestParam int fileId, Model model)
    {

        Iterable<User> usersArray = userRepo.findAll();

        model.addAttribute("fileId", fileId);
        model.addAttribute("usersArray", usersArray);
        return "fileconfig";
    }




    @PostMapping("/fileconfig")
    public String fileconfigPost(@RequestParam int fileId,
                                 @RequestParam String[] checkboxUsers,
                                 Model model)
    {

        // Сохраняем список пользователей в БД
        FilesItem filesItem = fileItemRepo.findById(fileId);
        filesItem.setListAuthorizedUsers(checkboxUsers);
        fileItemRepo.save(filesItem);

        Iterable<User> usersArray = userRepo.findAll();


        model.addAttribute("message_ok", "Permit users save!!!");
        model.addAttribute("fileId", fileId);
        model.addAttribute("usersArray", usersArray);

        return "fileconfig";
    }




}
