package java_practice.PracticeVFS;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class VFS {
    private userInput userInput;
    private Directory root;
    private FileMetadata metadata;

    public VFS(userInput userInput) {
        this.root = null;
        this.userInput = userInput;
    }

    public void run() {
        long sizeByte = userInput.userInit();
        this.root = new Directory("/", null);
        this.metadata = new FileMetadata(sizeByte);
        excuteFileSystem();
    }

    public void excuteFileSystem(){
        while(true){
            List<String> userOrder = userInput.userOrder();
            if (userOrder.get(0).equals("exit")){
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            switch (userOrder.get(0)){
                case "list" -> processList(userOrder);
                case "makedir" -> makedir(userOrder);
                case "create" -> create(userOrder);
                case "read" -> read(userOrder);
                case "import" -> importFile(userOrder);
                case "export" -> exportFile(userOrder);
            }
        }
    }

    public void processList(List<String> order){
        if (order.size()>2){
            System.out.println("입력 방식을 맞춰주세요 ex)list /");
        }
        String path = order.get(1);

        Directory target = findDirectory(path);
        if (target == null){
            System.out.println("경로를 찾을 수 없습니다.");
        }

        for (String dir : target.getDirectories().keySet()){
            System.out.println(dir);
        }

        for (String file: target.getFiles().keySet()){
            System.out.println(file);
        }

        String size = (metadata.remainSize() >= 1024L * 1024 * 1024) ? (metadata.remainSize() / (1024L * 1024 * 1024)) + "G": (metadata.remainSize() / (1024L * 1024)) + "M";
        System.out.println("사용 가능한 크기: " + size);
    }

    public void makedir(List<String> order){
        if(order.size() > 3){
            System.out.println("입력방식을 맞춰주세요 ex) makedir / hello");
            return;
        }

        String parent = order.get(1);
        String name = order.get(2).replaceAll("[^a-zA-Z0-9]", "");

        Directory parentDirectory = findDirectory(parent);
        if (parentDirectory == null){
            System.out.println("경로를 찾을 수 없습니다.");
        }
        parentDirectory.addSubdirectory(name);
    }

    public void create(List<String> order){
        if (order.size() > 4){
            System.out.println("입력방식을 맞춰주세요 ex)create /hello greeting.txt \"hello, vfs\"");
            return;
        }

        String path = order.get(1);
        String fileName = order.get(2);
        String content = order.get(3).replaceAll("\"\"","");
        if (!fileName.contains(".") || fileName.indexOf(".") != fileName.lastIndexOf(".")){
            System.out.println("파일 형식이 아닌 것 같은데요?");
            return;
        }

        Directory target = findDirectory(path);
        if (target == null){
            System.out.println("경로를 찾을 수 없습니다.");
            return;
        }
        if (target.getDirectories().containsKey(fileName)) {
            System.out.println("파일이 이미 존재합니다.");
            return;
        }

        long filesize = content.getBytes().length;
        if (metadata.remainSize() < filesize){
            System.out.println("파일 저장공간 부족");
        }

        FileEntry fileEntry = new FileEntry(fileName, content);
        target.addFiles(fileName, fileEntry);
        metadata.updateSize(filesize);

        System.out.println(target.getName()+ " 디렉토리 아래 "+ fileName+" 파일을 생성했습니다.");
        System.out.println("여유 공간 " + String.format("%,d byte", metadata.remainSize()));
    }

    public void read(List<String> order){
        if (order.size() > 2){
            System.out.println("입력방식을 맞춰주세요 ex)read /hello/greeting.txt");
        }
        String path = order.get(1);
        int lastSlash = path.lastIndexOf('/');

        if (lastSlash == -1){
            System.out.println("경로가 잘못 입력되었습니다.");
            return;
        }

        String filePath = path.substring(0, lastSlash);
        String fileName = path.substring(lastSlash+1);

        Directory target = findDirectory(filePath);
        if (target == null){
            System.out.println("경로를 찾을 수 없습니다.");
            return;
        }

        if (!target.getFiles().containsKey(fileName)) {
            System.out.println("해당 파일이 존재하지 않습니다");
            return;
        }

        FileEntry file = target.getFiles().get(fileName);
        System.out.println("\n" + file.getContent() + "\n");

    }

    public void importFile(List<String> order){
        if (order.size() > 3){
            System.out.println("입력방식을 맞춰주세요 (실제경로에 \\, \"\" 포함가능) ex)import C:/Users/USER/OneDrive/a.txt /users/honux");
            return;
        }
        String realPath = order.get(1).replace("\\", "/").replace("\"", "");
        String vfsPath = order.get(2);

        File realFile = new File(realPath);
        if (!realFile.exists() || !realFile.isFile()) {
            System.out.println("파일을 찾을 수 없습니다: ");
            return;
        }

        Directory target = findDirectory(vfsPath);
        if (target == null){
            System.out.println("vfs 경로를 찾을 수 없습니다.");
            return;
        }

        try{
            byte[] filebytes = Files.readAllBytes(realFile.toPath());
            String fileContent = new String(filebytes);
            String fileName = realFile.getName();

            FileEntry newFile = new FileEntry(fileName, fileContent);
            target.addFiles(fileName, newFile);

            metadata.updateSize(filebytes.length);
            System.out.println("파일이 복사되었습니다.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
    public void exportFile(List<String> order){
        if (order.size() > 3){
            System.out.println("입력방식을 맞춰주세요 ex)export /hello/greeting.txt /users/honux");
            return;
        }
        String vfsPath = order.get(1);
        String realPath = order.get(2).replace("\\", "/").replace("\"", "");

        int lastSlashIndex = vfsPath.lastIndexOf("/");
        String vfsDirPath = vfsPath.substring(0, lastSlashIndex);
        String vfsFileName = vfsPath.substring(lastSlashIndex + 1);

        Directory target = findDirectory(vfsDirPath);
        if (target == null){
            System.out.println("vfs 경로를 찾을 수 없습니다.");
        }

        if (!target.getFiles().containsKey(vfsFileName)) {
            System.out.println("해당 파일이 존재하지 않습니다");
            return;
        }

        FileEntry file = target.getFiles().get(vfsFileName);

        File realDir = new File(realPath);
        if (!realDir.exists() || !realDir.isDirectory()) {
            System.out.println("디렉토리가 존재하지 않습니다.");
            return;
        }

        File realFile = new File(realDir, vfsFileName);
        if(realFile.exists()){
            System.out.println("동일한 파일이 존재합니다.");
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(realFile)){
            fos.write(file.getContent().getBytes());
            System.out.println("가상 파일시스템" + vfsPath + " 를 " + realFile.getAbsolutePath() + "로 내보내기 성공!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Directory findDirectory(String path){
        if (path.equals("/")) {
            return root;
        }

        String[] dirs = path.split("/");
        Directory current = root;

        for (String dir : dirs) {
            if (dir.isEmpty()) {
                continue;
            }
            if (!current.getDirectories().containsKey(dir)){
                return null;
            }
            current = current.getDirectories().get(dir);
        }
        return current;
    }

}