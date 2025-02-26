package java_practice.PracticeVFS;

public class FileMetadata {
    private long size;
    private long usedSize;
    public FileMetadata(long sizeByte) {
        size = sizeByte;
        usedSize = 0;
    }

    public long remainSize(){
        return size - usedSize;
    }

    public void updateSize(long filesize){
        usedSize += filesize;
    }
}