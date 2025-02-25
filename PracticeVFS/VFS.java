package java_practice.PracticeVFS;

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
        String path = order.get(1);

        Directory target = findDirectory(path);
        if (target == null){
            System.out.println("경로를 찾을 수 없습니다.");
        }

        for (String dir : target.getDirectories().keySet()){
            System.out.println(dir);
        }
        System.out.println("사용 가능한 크기: " + metadata.remainSize() + " 바이트");
    }

    public void makedir(List<String> order){
        String parent = order.get(1);
        String name = order.get(2);

        Directory parentDirectory = findDirectory(parent);
        if (parentDirectory == null){
            System.out.println("경로를 찾을 수 없습니다.");
        }

        parentDirectory.addSubdirectory(name);
    }

    public void create(List<String> order){

    }

    public void read(List<String> order){

    }

    public void importFile(List<String> order){

    }

    public void exportFile(List<String> order){

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