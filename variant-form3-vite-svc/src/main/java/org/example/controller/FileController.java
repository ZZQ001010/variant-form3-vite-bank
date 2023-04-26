package org.example.controller;

import org.example.vo.FileVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;
import static org.example.controller.FilesController.projectDir;

/**
 * @author zzq
 * @date 2023/4/26
 */
@RestController
@RequestMapping("file")
@CrossOrigin(origins = "*")
public class FileController {
    
    @GetMapping("content")
    public String content(String path) throws Exception {
        return Files.readAllLines(Paths.get(projectDir + path )).stream().collect(Collectors.joining("\n"));
    }
    
    @PostMapping
    public void save(@RequestBody FileVo fileVo) throws IOException {
        Files.write(Paths.get(projectDir + fileVo.getPath()),
                fileVo.getContent().getBytes(StandardCharsets.UTF_8),
                new StandardOpenOption[] {WRITE});
    }
    
}
