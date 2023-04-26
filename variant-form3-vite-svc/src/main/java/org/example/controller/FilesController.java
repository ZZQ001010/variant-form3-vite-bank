package org.example.controller;

import com.google.gson.Gson;
import org.example.DirectoryTree;
import org.example.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zzq
 * @date 2023/4/25
 */
@RestController
@RequestMapping("files")
@CrossOrigin(origins = "*")
public class FilesController {
    
    private static final Logger log = LoggerFactory.getLogger(FilesController.class);
    
    public static final String projectDir = "/Volumes/Radish/github/web/variant-form3-vite";
    
    @GetMapping(produces = "application/json")
    public String files() {
        File root = new File(projectDir);
        DirectoryTree.TreeNode rootNode = new DirectoryTree.TreeNode(root.getName(), false, "/" );
        DirectoryTree.addChildren(root, rootNode);
        List<DirectoryTree.TreeNode> rootNodes = new ArrayList<>();
        rootNodes.add(rootNode);
        return new Gson().toJson(rootNodes);
    }
    
    @PostMapping()
    public void createFile(@RequestBody FileVo fileVo) throws Exception {
        boolean isDir = fileVo.getIsDir();
        if (isDir) {
            Files.createDirectories(Paths.get(projectDir + fileVo.getPath()));
            log.info("创建文件夹[{}]", fileVo.getPath());
        } else {
            Files.createFile(Paths.get(projectDir + fileVo.getPath()));
            log.info("创建文件[{}]", fileVo.getPath());
        }
    }
    
    
    @DeleteMapping
    public void deleteFile(String path)  throws Exception{
        boolean b = Files.deleteIfExists(Paths.get(projectDir + path));
        log.info("删除文件[{}]结果[{}]", path, b);
    }
    
    @PutMapping
    public void renameFile(@RequestBody Map<String, String> map) throws Exception {
        String oldName = map.get("oldName");
        String newName = map.get("newName");
        File file = new File(projectDir + oldName);
        File newFile = new File(projectDir + "/" + newName);
        if(file.renameTo(newFile)) {
            log.info("[{}] -> [{}] 成功", file, newFile);
        } else {
            log.error("[{}] -> [{}] 失败", file, newFile);
        }
    }
    
}
