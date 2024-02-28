import java.io.File;

public class DictionaryPath {
    public static String getPath() {
        String currentDirectory = System.getProperty("user.dir");
        File txtFolder = findTxtFolder(new File(currentDirectory));
        System.out.println(txtFolder.getAbsolutePath());
        return txtFolder.getAbsolutePath();
    }

    private static File findTxtFolder(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (containsTxtFiles(file)) {
                        return file;
                    } else {
                        File txtFolder = findTxtFolder(file);
                        if (txtFolder != null) {
                            return txtFolder;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean containsTxtFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    return true;
                }
            }
        }
        return false;
    }
}
