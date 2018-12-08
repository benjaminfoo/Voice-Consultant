package backend.plugins;

import java.io.File;
import java.io.IOException;

public class PluginTest
{

    public static void main(String[] args) throws IOException, InterruptedException {
        PlugInWatcher plugInWatcher = new PlugInWatcher();
        plugInWatcher.watch("D:\\Temp\\");
        File watchedFile = new File("D:\\Temp\\");

        File newFileInDir = new File(watchedFile, "test.txt");
        newFileInDir.createNewFile();
    }

}