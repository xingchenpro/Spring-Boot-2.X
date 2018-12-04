package com.hly.springbootfileupload.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/12/4
 */
@Controller
public class FileUploadController {


    @RequestMapping("/")
    public String index() {
        return "/fileUpload";
    }


    @RequestMapping("/upload")
    public String fileUpload(@RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes redirectAttributes) {

        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","请选择文件");
            return "redirect:/";
        }

        try {
            byte[] bytes = file.getBytes();
            File classpath = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!classpath.exists()) classpath = new File("");

            File upload = new File(classpath.getAbsolutePath(), "/file/upload/");
            if (!upload.exists()) upload.mkdirs();

            Path path = Paths.get(upload.getPath(),file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message","上传成功");

            List<File> list = new ArrayList<>();






        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";

    }
}
