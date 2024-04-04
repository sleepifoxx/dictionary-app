import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class game1 {
    public static void game_multiple_choice(Dictionary dictionary) {
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
}
