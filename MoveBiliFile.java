import java.io.IOException;
import java.nio.file.*;

/**
 * 合并手机b站下载文件
 */
public class MoveBiliFile {
    /**
     * 返回pathname目录下文件总数
     * @param pathname
     * @return
     * @throws IOException
     */
    public int CountCatalog(String pathname) throws IOException {
        int count=0;
        DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get(pathname));
        for(Path path:paths ){
            count++;
        }
        return count;
    }

    public int MoveFile(String pathname) {
        int count=0;
        String toprepath="E:/Music/res";

        for(int i=1;i<421;i++){
            String name=pathname+"/"+i+"/lua.mp4.bili2api.16/0.blv";
            Path frompath = Paths.get(name);

            String toname=toprepath+"/"+i+".mp4";
            Path topath = Paths.get(toname);
            try {
                Files.move(frompath,topath,StandardCopyOption.ATOMIC_MOVE);
                count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    public void diff(String pathname)  {
        int count=0;
        DirectoryStream<Path> paths = null;
        try {
            paths = Files.newDirectoryStream(Paths.get(pathname));
            for(Path path:paths ){
                System.out.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * gai
     * @param tail
     * @return
     */
    public int changetail(String tail){
        int count=0;
        String toprepath="E:/Music/res";

        for(int i=1;i<421;i++){
            String name=toprepath+"/"+i+".txt";
            Path frompath = Paths.get(name);

            String toname=toprepath+"/"+i+tail;
            Path topath = Paths.get(toname);
            try {
                Files.move(frompath,topath,StandardCopyOption.ATOMIC_MOVE);
                count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * 修改后缀
     * @param path 文件所在文件夹路径
     * @param filename 文件名
     * @param oldTail 后缀
     * @param newTail 修改后的后缀
     */
    public void changetail(String path,String filename,String oldTail,String newTail){
        String name=path+"/"+filename+"."+oldTail;
        Path frompath = Paths.get(name);

        String toname=path+"/"+filename+"."+newTail;
        Path topath = Paths.get(toname);
        try {
            Files.move(frompath,topath,StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        MoveBiliFile file=new MoveBiliFile();
        int i = file.MoveFile("E:/Music/795213137");
        System.out.println("成功移动数量为： "+i);
    }
}
