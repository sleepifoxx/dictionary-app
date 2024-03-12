import java.util.Scanner;

public class DictionaryBasic {
    public static void dictionaryMenu(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("Nhấn Enter để tiếp tục.");
        scanner.nextLine();
        System.out.println("--------DICTIONARY--------");
        System.out.println("1. Nhập từ bàn phím: ");
        System.out.println("2. Tra từ: ");
        System.out.println("3. Sửa từ: ");
        System.out.println("4. Xoá từ: ");
        System.out.println("5. Xuất ra file: ");
        System.out.println("6. Tìm kiếm từ theo chữ cái: ");
        System.out.println("7. Hiển thị tất cả các từ: ");
        System.out.println("8. Thoát");
        System.out.println("--------------------------");
        System.out.println("Lựa chọn: ");
        // Kiểm tra xem người dùng nhập vào có phải là số không
        while (!scanner.hasNextInt()) {
            System.out.println("Chú ý! Nhập số thuộc khoảng (1-8): ");
            scanner.next();
        }
        int choice = scanner.nextInt();
        // Kiểm tra xem người dùng nhập vào có nằm trong khoảng từ 1-8 không
        while (choice < 1 || choice > 8) {
            System.out.println("Chú ý! Nhập số thuộc khoảng (1-8): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Chú ý! Nhập số thuộc khoảng (1-8): ");
                scanner.next();
            }
            choice = scanner.nextInt();
        }
        scanner.nextLine();
        switch (choice) {
            case 1:
                DictionaryManagerBasic.insertFromCommandline(dictionary);
                break;
            case 2:
                DictionaryManagerBasic.dictionaryLookup(dictionary);
                break;
            case 3:
                DictionaryManagerBasic.dictionaryEdit(dictionary);
                break;
            case 4:
                DictionaryManagerBasic.dictionaryDelete(dictionary);
                break;
            case 5:
                DictionaryManagerBasic.exportToFile(dictionary);
                break;
            case 6:
                DictionaryManagerBasic.dictionarySearcher(dictionary);
                break;
            case 7:
                DictionaryManagerBasic.showAllWords(dictionary);
                break;
            case 8:
                return;
        }
        dictionaryMenu(dictionary);
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        DictionaryManagerBasic.insertFromFile(dictionary);
        dictionaryMenu(dictionary);
    }
}