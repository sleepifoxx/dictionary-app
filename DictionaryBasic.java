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
        int choice = scanner.nextInt();
        while (choice < 1 || choice > 8) {
            System.out.println("Nhap lai (gia tri trong khoang 1-9): ");
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