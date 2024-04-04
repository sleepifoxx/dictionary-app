import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class game2 {
    public static void game_transplant(Dictionary dictionary) {
        System.out.println("Tôi sẽ cho chữ cái của 1 từ và bạn phải sắp xếp lại chúng để tạo thành từ đó.");
        System.out.println("Bạn sẽ có 3 lần chơi trước khi chúng tôi đưa ra đáp án đúng.");
        System.out.println("Nếu bạn đã hiểu, hãy ấn 'Go' để bắt đầu!");
        System.out.println("Để thoát trò chơi, hãy nhập 'exit'.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        Random rd = new Random();
        List<Map.Entry<String, String>> wordsList = new ArrayList<>(dictionary.words.entrySet());
        while (!wordsList.isEmpty()) {
            Map.Entry<String, String> questionEntry = wordsList.remove(rd.nextInt(wordsList.size()));
            String word_target = questionEntry.getKey();
            char[] word_target_array = word_target.toCharArray();
            for (int i = 0; i < word_target_array.length; i++) {
                int j = rd.nextInt(word_target_array.length);
                char temp = word_target_array[i];
                word_target_array[i] = word_target_array[j];
                word_target_array[j] = temp;
            }
            System.out.println("Hãy sắp xếp lại chữ cái để tạo thành từ tiếng Anh sau: ");
            for (int i = 0; i < word_target_array.length; i++) {
                System.out.print(word_target_array[i] + " ");
            }
            System.out.println();
            for (int i = 0; i < 3; i++) {
                System.out.println("Lần chơi thứ " + (i + 1) + ": ");
                String answer = scanner.nextLine();
                if (answer.equals(word_target)) {
                    System.out.println("Chúc mừng, bạn đã trả lời đúng!");
                    break;
                } else if (i == 2) {
                    System.out.println("Bạn đã hết lượt chơi. Đáp án đúng là: " + word_target);
                } else {
                    System.out.println("Rất tiếc, bạn đã trả lời sai. Hãy thử lại!");
                }
            }
        }
    }
}
