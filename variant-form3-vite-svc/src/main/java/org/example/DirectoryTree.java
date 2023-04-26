package  org.example;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.controller.FilesController.projectDir;

public class DirectoryTree {
    
    public static void addChildren(File parent, TreeNode parentNode) {
        List<File> files = Arrays.stream(parent.listFiles()).sorted().collect(Collectors.toList());
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.getName().equals("node_modules") || file.getName().equals(".git")) { // 排除node_modules目录
                continue;
            }
            TreeNode node = new TreeNode(file.getName(), file.isFile(), file.getAbsolutePath().replaceAll(projectDir, ""));
            if (file.isDirectory()) {
                addChildren(file, node);
            }
            parentNode.addChild(node);
        }
    }
    
    
    public static class TreeNode {
        private String label;
        
        private boolean isLeaf;
        
        private String path;
        private List<TreeNode> children;
        
        public TreeNode(String label, boolean isLeaf, String path) {
            this.label = label;
            this.isLeaf = isLeaf;
            this.path = path;
            this.children = new ArrayList<>();
        }
        
        public void addChild(TreeNode child) {
            children.add(child);
        }
        
    }
}
