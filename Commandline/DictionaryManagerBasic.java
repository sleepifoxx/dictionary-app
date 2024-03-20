import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Random;

class DictionaryManagerBasic {
    // Thêm từ vào từ điển từ commandline
    public static void insertFromCommandline(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số từ cần thêm: ");
        // Kiểm tra xem người dùng nhập số hay không
        while (!scanner.hasNextInt()) {
            System.out.println("Chú ý! Nhập số nguyên dương: ");
            scanner.next();
        }
        int n = scanner.nextInt();
        // Kiểm tra xem người dùng nhập số âm hay không
        while (n <= 0) {
            System.out.println("Chú ý! Nhập số nguyên dương: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Chú ý! Nhập số nguyên dương: ");
                scanner.next();
            }
            n = scanner.nextInt();
        }
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập từ tiếng Anh: ");
            String word_target = scanner.nextLine().toLowerCase(); // Chuyển sang chữ thường
            System.out.println("Nhập nghĩa tiếng Việt: ");
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
                String word_target = scanner.nextLine().toLowerCase();
                String word_explain = scanner.nextLine();
                Word word = new Word(word_target, word_explain);
                dictionaryAdd(dictionary, word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Có lỗi xảy ra khi thêm dữ liệu từ file!");
        }
    }

    // Tra từ điển
    public static void dictionaryLookup(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập từ cần tra nghĩa: ");
        String word_target = scanner.nextLine().toLowerCase(); // Chuyển từ cần tìm tiếng anh thành in thường
        if (dictionary.words.get(word_target) == null) {
            String word_explain = GoogleAPI.translate("en", "vi", word_target);
            System.out.println("Nghĩa tiếng Việt: ");
            System.out.println(word_explain);
            dictionary.words.put(word_target, word_explain);
        } else {
            System.out.println("Nghĩa tiếng Việt: ");
            System.out.println(dictionary.words.get(word_target));
        }
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
            System.out.println("Xuất ra file thành công!");
        } catch (FileNotFoundException e) {
            System.out.println("Có lỗi xảy ra khi xuất file!");
        }
    }

    // Sửa từ trong từ điển
    public static void dictionaryEdit(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập từ cần sửa: ");
        String word_target = scanner.nextLine().toLowerCase(); // Chuyển từ tiếng anh thành in thường
        System.out.println("Nhập nghĩa tiếng Việt mới: ");
        String word_explain = scanner.nextLine();
        dictionary.words.put(word_target, word_explain);
    }

    // Xóa từ trong từ điển
    public static void dictionaryDelete(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập từ cần xoá: ");
        String word_target = scanner.nextLine().toLowerCase();
        // Kiểm tra từ cần xóa có trong từ điển không
        if (dictionary.words.get(word_target) == null) {
            System.out.println("Từ này không có trong từ điển để xoá!");
            return;
        } else
            dictionary.words.remove(word_target);
    }

    // Tìm kiếm từ điển theo từ khóa
    public static void dictionarySearcher(Dictionary dictionary) {
        dictionarySortAlphabet(dictionary);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập chữ cái của các từ cần tìm: ");
        String word_target = scanner.nextLine().toLowerCase();
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

    // minigame
    public static void minigame(Dictionary dictionary) {
        System.out.println("Chào mừng bạn đến với trò chơi đoán từ!");
        System.out
                .println("Bạn sẽ được cho một từ tiếng Việt và bạn cần phải đoán chính xác nghĩa tiếng Anh của từ đó.");
        System.out.println("Nếu bạn đã hiểu , hãy ấn 'Go' để bắt đầu!");
        System.out.println("Để thoát trò chơi, hãy nhập 'exit'.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        Random rd = new Random();
        List<Map.Entry<String, String>> wordsList = new ArrayList<>(dictionary.words.entrySet());
        while (!wordsList.isEmpty()) {
            List<Map.Entry<String, String>> a = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Map.Entry<String, String> randomEntry = wordsList.remove(rd.nextInt(wordsList.size()));
                a.add(randomEntry);
            }
            Map.Entry<String, String> questionEntry = a.get(rd.nextInt(a.size()));
            System.out.println("Hãy đoán nghĩa tiếng Anh của từ tiếng Việt sau: " + questionEntry.getKey());
            for (int i = 0; i < a.size(); i++) {
                System.out.println((char) (i + 'A') + ". " + a.get(i).getValue());
            }
            char answer = scanner.next().charAt(0);
            answer = Character.toUpperCase(answer);
            int answerIndex = answer - 'A';
            if (a.get(answerIndex).equals(questionEntry)) {
                System.out.println("Chúc mừng, bạn đã trả lời đúng!");
            } else {
                System.out.println("Rất tiếc, bạn đã trả lời sai. Đáp án đúng là: " + questionEntry.getValue());
            }
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
