package com.user.filemanager;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
public class FileController {
    @GetMapping("/")
    public String showFiles(@RequestParam(defaultValue = "/") String path, Model model) {
        FileInfo fileInfo = new FileInfo(new File(path));

        model.addAttribute("currentPath", fileInfo.getAbsolutePath());
        model.addAttribute("files", fileInfo.listFiles());

        return "index";
    }

    @GetMapping("/view")
    public ResponseEntity<FileSystemResource> downloadFile(@RequestParam String path) {
        File file = new File(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .body(new FileSystemResource(file));
    }
}