import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

class DictionaryManagerBasic {
    // Thêm từ vào từ điển từ commandline
    public static void insertFromCommandline(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so tu: ");
        // Kiểm tra xem người dùng nhập số hay không
        while (!scanner.hasNextInt()) {
            System.out.println("Nhap so nguyen duong: ");
            scanner.next();
        }
        int n = scanner.nextInt();
        // Kiểm tra xem người dùng nhập số âm hay không
        while (n <= 0) {
            System.out.println("Nhap so nguyen duong: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Nhap so nguyen duong: ");
                scanner.next();
            }
            n = scanner.nextInt();
        }
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap tu tieng Anh: ");
            String word_target = scanner.nextLine();
            System.out.println("Nhap nghia tieng Viet: ");
            String word_explain = scanner.nextLine();
            Word word = new Word(word_target, word_explain);
            dictionaryAdd(dictionary, word);
        }
    }

    // Thêm từ vào từ điển từ file dictionaries.txt
    public static void insertFromFile(Dictionary dictionary) {
        try {
            Scanner scanner = new Scanner(new File(getPath() + "\\dictionaries.txt"));
            while (scanner.hasNextLine()) {
                String word_target = scanner.nextLine();
                String word_explain = scanner.nextLine();
                Word word = new Word(word_target, word_explain);
                dictionaryAdd(dictionary, word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Co loi xay ra!");
        }
    }

    // Tra từ điển
    public static void dictionaryLookup(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap tu can tra nghia Tieng Viet: ");
        String word_target = scanner.nextLine();
        if (dictionary.words.get(word_target) == null) {
            System.out.println("Khong tim thay tu nay. Nhap them tu moi? (Y/N): ");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                insertFromCommandline(dictionary);
            } else
                return;
        } else
            System.out.println(dictionary.words.get(word_target));
    }

    // Thêm từ vào từ điển
    public static void dictionaryAdd(Dictionary dictionary, Word word) {
        dictionary.words.put(word.word_target, word.word_explain);
    }

    // Xuất từ điển ra file dictionaries.txt
    public static void exportToFile(Dictionary dictionary) {
        try {
            File file = new File(getPath() + "\\dictionaries.txt");
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            for (Map.Entry<String, String> entry : dictionary.words.entrySet()) {
                output.println(entry.getKey());
                output.println(entry.getValue());
            }
            output.close();
            System.out.println("Xuat ra file thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Co loi xay ra!");
        }
    }

    // Sửa từ trong từ điển
    public static void dictionaryEdit(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap tu can sua: ");
        String word_target = scanner.nextLine();
        System.out.println("Nhap nghia tieng Viet moi: ");
        String word_explain = scanner.nextLine();
        dictionary.words.put(word_target, word_explain);
    }

    // Xóa từ trong từ điển
    public static void dictionaryDelete(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap tu can xoa: ");
        String word_target = scanner.nextLine();
        // Kiểm tra từ cần xóa có trong từ điển không
        if (dictionary.words.get(word_target) == null) {
            System.out.println("Tu nay khong co trong tu dien!");
            return;
        } else
            dictionary.words.remove(word_target);
    }

    // Tìm kiếm từ điển theo từ khóa
    public static void dictionarySearcher(Dictionary dictionary) {
        dictionarySortAlphabet(dictionary);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap chu cai can tim: ");
        String word_target = scanner.nextLine();
        for (Map.Entry<String, String> entry : dictionary.words.entrySet()) {
            if (entry.getKey().startsWith(word_target)) {
                System.out.println(entry.getKey());
            }
        }
    }

    // Hiển thị tất cả các từ trong từ điển
    public static void showAllWords(Dictionary dictionary) {
        dictionarySortAlphabet(dictionary);
        System.out.println("No | English | Vietnamese");
        int index = 1;
        for (Map.Entry<String, String> entry : dictionary.words.entrySet()) {
            System.out.println(index + " | " + entry.getKey() + " | " + entry.getValue());
            index++;
        }
    }

    // Sắp xếp từ điển theo thứ tự bảng chữ cái
    public static void dictionarySortAlphabet(Dictionary dictionary) {
        Map<String, String> sorted = new TreeMap<String, String>(dictionary.words);
        dictionary.words = sorted;
    }

    // Code để lấy đường dẫn tới thư mục chứa file dictionaries.txt
    public static String getPath() {
        String currentDirectory = System.getProperty("user.dir");
        File txtFolder = findTxtFolder(new File(currentDirectory));
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
