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

    public String getName() {
        return name;
    }
}
