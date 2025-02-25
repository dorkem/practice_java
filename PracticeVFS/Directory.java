package java_practice.PracticeVFS;

import java.util.HashMap;
import java.util.Map;

public class Directory {
    private String name;
    private Directory parent;
    private Map<String, Directory> directories;
    private Map<String, FileEntry> files;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.directories = new HashMap<>();
        this.files = new HashMap<>();
    }

    public void addSubdirectory(String name) {
        if (directories.containsKey(name)) {
            System.out.println("이미 있음");
            return;
        }

        Directory newDirectory = new Directory(name, this);
        directories.put(name, newDirectory);
        System.out.println(this +" 디렉토리 아래 "+name+"를 생성합니다.");
    }

    public String getName() {
        return name;
    }

    public Map<String, Directory> getDirectories() {
        return directories;
    }

    @Override
    public String toString() {
        return (parent == null) ? "/" : parent.getName();
    }

}
