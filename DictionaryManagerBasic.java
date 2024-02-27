import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

class DictionaryManagerBasic {
    public static void insertFromCommandline(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so tu: ");
        int n = scanner.nextInt();
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

    public static void insertFromFile(Dictionary dictionary) {
        try {
            Scanner scanner = new Scanner(new File("dictionaries.txt"));
            while (scanner.hasNextLine()) {
                String word_target = scanner.nextLine();
                String word_explain = scanner.nextLine();
                Word word = new Word(word_target, word_explain);
                dictionaryAdd(dictionary, word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    public static void dictionaryAdd(Dictionary dictionary, Word word) {
        dictionary.words.put(word.word_target, word.word_explain);
    }

    public static void exportToFile(Dictionary dictionary) {
        try {
            File file = new File("dictionaries.txt");
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            for (Map.Entry<String, String> entry : dictionary.words.entrySet()) {
                output.println(entry.getKey());
                output.println(entry.getValue());
            }
            output.close();
            System.out.println("Xuat ra file thanh cong!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryEdit(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap tu can sua: ");
        String word_target = scanner.nextLine();
        System.out.println("Nhap nghia tieng Viet moi: ");
        String word_explain = scanner.nextLine();
        dictionary.words.put(word_target, word_explain);
    }

    public static void dictionaryDelete(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap tu can xoa: ");
        String word_target = scanner.nextLine();
        dictionary.words.remove(word_target);
    }

    public static void dictionarySearcher(Dictionary dictionary) {
        dictionarySortAlphabet(dictionary);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap tu can tim: ");
        String word_target = scanner.nextLine();
        for (Map.Entry<String, String> entry : dictionary.words.entrySet()) {
            if (entry.getKey().startsWith(word_target)) {
                System.out.println(entry.getKey());
            }
        }
    }

    public static void showAllWords(Dictionary dictionary) {
        dictionarySortAlphabet(dictionary);
        System.out.println("No | English | Vietnamese");
        int index = 1;
        for (Map.Entry<String, String> entry : dictionary.words.entrySet()) {
            System.out.println(index + " | " + entry.getKey() + " | " + entry.getValue());
            index++;
        }
    }

    public static void dictionarySortAlphabet(Dictionary dictionary) {
        Map<String, String> sorted = new TreeMap<String, String>(dictionary.words);
        dictionary.words = sorted;
    }
}
