package org.example.vo;

/**
 * @author zzq
 * @date 2023/4/26
 */
public class FileVo {
    
    private String path;
    
    private boolean isDir;
    private String content;
    
    public boolean getIsDir() {
        return isDir;
    }
    
    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}
