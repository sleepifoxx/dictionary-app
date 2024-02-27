import java.util.Scanner;

public class DictionaryBasic {
    public static void dictionaryMenu(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("Nhan Enter de tiep tuc.");
        scanner.nextLine();
        System.out.println("--------DICTIONARY--------");
        System.out.println("1. Nhap tu ban phim: ");
        System.out.println("2. Tra tu: ");
        System.out.println("3. Sua tu: ");
        System.out.println("4. Xoa tu: ");
        System.out.println("5. Xuat ra file: ");
        System.out.println("6. Tim kiem tu: ");
        System.out.println("7. Hien thi tat ca tu: ");
        System.out.println("8. Thoat");
        System.out.println("--------------------------");
        System.out.println("Chon: ");
        // Kiểm tra xem người dùng nhập vào có phải là số không
        while (!scanner.hasNextInt()) {
            System.out.println("Nhap so thuoc khoang (1-8): ");
            scanner.next();
        }
        int choice = scanner.nextInt();
        // Kiểm tra xem người dùng nhập vào có nằm trong khoảng từ 1-8 không
        while (choice < 1 || choice > 8) {
            System.out.println("Nhap so thuoc khoang (1-8): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Nhap so thuoc khoang (1-8): ");
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