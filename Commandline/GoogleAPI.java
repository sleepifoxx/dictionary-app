import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleAPI {
    public static String translate(String langFrom, String langTo, String text) {
        try {
            String urlStr = "https://script.google.com/macros/s/AKfycbyy9PjjDO1fKR2kV5wI0i8I-mZDLSK0zOHKPUQDwk0rk-g5QCHUueAgiNIm-fhIFJMa_A/exec"
                    +
                    "?q=" + URLEncoder.encode(text, "UTF-8") +
                    "&target=" + langTo +
                    "&source=" + langFrom;
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            return ("Vui lòng kiểm tra kết nối Internet!");
        }
    }
}