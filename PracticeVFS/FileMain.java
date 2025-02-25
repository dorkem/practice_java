package java_practice.PracticeVFS;

public class FileMain {
    public static void main(String[] args) {
        userInput userInput = new userInput();
        VFS vfs = new VFS(userInput);
        vfs.run();
    }
}
