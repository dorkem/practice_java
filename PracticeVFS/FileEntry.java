package java_practice.PracticeVFS;

import java.nio.charset.StandardCharsets;

public class FileEntry {
    private String fileName;
    private byte[] content;

    public FileEntry(String fileName, String content) {
        this.fileName = fileName;
        this.content = content.getBytes(StandardCharsets.UTF_8);
    }

    public String getContent() {
        return new String(content, StandardCharsets.UTF_8);
    }
}
