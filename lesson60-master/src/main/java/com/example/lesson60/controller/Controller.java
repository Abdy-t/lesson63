package com.example.lesson60.controller;

import com.example.lesson60.service.ComService;
import com.example.lesson60.service.PubService;
import com.example.lesson60.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

@org.springframework.stereotype.Controller
public class Controller {
    private final UserService userService;
    private final PubService pubService;
    private final ComService comService;

    public Controller(UserService userService, PubService pubService, ComService comService) {
        this.userService=userService;
        this.pubService=pubService;
        this.comService=comService;
    }
    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/demo")
    public String dd(Model model) {
        return "demo";
    }

    @GetMapping("/page")
    public String demo(Model model) {
        model.addAttribute("user",userService.getUser());
        model.addAttribute("images", pubService.getPublications());
        model.addAttribute("comments", comService.getComments());
        return "page";
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST, consumes=MULTIPART_FORM_DATA)
    public String postDemo(@RequestParam("idUser") String idUser, @RequestParam("photo") MultipartFile photo, @RequestParam("description") String description)  throws IOException {
        System.out.println("User id : " + idUser);
        System.out.println("Image : " + photo.getOriginalFilename());
        String path = "../images";
        File photoFile = new File(path + "/" + photo.getOriginalFilename());
        FileOutputStream os = new FileOutputStream(photoFile);
        os.write(photo.getBytes());
        os.close();
        pubService.saveNewPhoto(idUser, "../image/" + photo.getOriginalFilename(), description);
        return "success";

    }
    @RequestMapping(value = "/page/comment", method = RequestMethod.POST, consumes=MULTIPART_FORM_DATA)
    public String commentDemo(@RequestParam("idUser") String idUser, @RequestParam("idPhoto") String idPhoto, @RequestParam("comment") String comment) {
        System.out.println("User id : " + idUser);
        System.out.println("idPhoto : " + idPhoto);
        System.out.println("comment : " + comment);
        comService.saveNewComment(idUser, idPhoto, comment);
        return "redirect:/page/";
    }
    @GetMapping("/image/{name}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) {
        String path = "../images";
        try {
            InputStream is = new FileInputStream(new File(path) + "/" + name);
            return ResponseEntity
                    .ok()
                    .contentType(name.toLowerCase().contains(".png")?MediaType.IMAGE_PNG:MediaType.IMAGE_JPEG)
                    .body(StreamUtils.copyToByteArray(is));
        } catch (Exception e) {
            InputStream is = null;
            try {
                is = new FileInputStream(new File(path) + "/" + name);
                return ResponseEntity
                        .ok()
                        .contentType(name.toLowerCase().contains(".png")?MediaType.IMAGE_PNG: MediaType.IMAGE_JPEG)
                        .body(StreamUtils.copyToByteArray(is));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
}
