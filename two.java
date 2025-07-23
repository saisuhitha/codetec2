import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class two {

    public static void main(String[] args) {
        try {
            String apiKey = "your_api_key"; // 🔁 Replace with your real API key
            String city = "Hyderabad";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" 
                + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response
                JSONObject obj = new JSONObject(response.toString());

                System.out.println("----- Weather Report -----");
                System.out.println("City: " + obj.getString("name"));
                System.out.println("Temperature: " + obj.getJSONObject("main").getDouble("temp") + "°C");
                System.out.println("Feels Like: " + obj.getJSONObject("main").getDouble("feels_like") + "°C");
                System.out.println("Humidity: " + obj.getJSONObject("main").getInt("humidity") + "%");
                System.out.println("Condition: " + obj.getJSONArray("weather").getJSONObject(0).getString("description"));
                System.out.println("--------------------------");

            } else {
                System.out.println("Failed to fetch data. HTTP Response Code: " + status);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}