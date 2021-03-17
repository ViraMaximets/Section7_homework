//Get request from user and give him responce from Wikipedia

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

public class httpRequest {
    public static <GoogleResults> void main(String[] args) throws IOException {
        String source = "https://ru.wikipedia.org/w/api.php?" +
                "action=query&list=search&utf8=&format=json&srsearch=\"";

        //зчитування запиту
        Scanner in = new Scanner(System.in);
        System.out.println("Enter request: ");
        String req = in.nextLine();

        //Percent-encoding
        String request = source + (URLEncoder.encode(req, StandardCharsets.UTF_8)) + "\"";

        URL url = new URL(request);
        String content = getResponse(url);
        JsonArray responces = getResults(content);
        displayRes(responces,req);
    }

    public static String getResponse(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();
            return content.toString();
        }
        connection.disconnect();
        return null;
    }

    public static JsonArray getResults(String json) {
        JsonParser parser = new JsonParser();
        JsonElement f3 = null;
        JsonElement jsonTree = parser.parse(json);

        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement f1 = jsonObject.get("batchcomplete");
            JsonElement f11 = jsonObject.get("continue");
            JsonElement f2 = jsonObject.get("query");

            if (f2.isJsonObject()) {
                JsonObject f2Obj = f2.getAsJsonObject();
                f3 = f2Obj.get("search");
            }
        }
        assert f3 != null;
        return f3.getAsJsonArray();
    }

    public static void displayRes(JsonArray arr, String keyWord) {
        for (JsonElement pa : arr) {
            JsonObject paymentObj = pa.getAsJsonObject();
            String ns = paymentObj.get("ns").getAsString();
            String title = paymentObj.get("title").getAsString();
            int pageid = paymentObj.get("pageid").getAsInt();
            int size = paymentObj.get("size").getAsInt();
            int wordcount = paymentObj.get("wordcount").getAsInt();

            String snippet = paymentObj.get("snippet").getAsString();
            String s0 = snippet.replace("<span class=\"searchmatch\">"+keyWord+"</span>", "");
            String s1 = s0.replace("<span class=\"searchmatch\">"+keyWord.toLowerCase()+"</span>", "");
            String s2 = s0.replace("<span class=\"searchmatch\">"+keyWord.toUpperCase()+"</span>", "");

            System.out.print("Title: " + title + "\n");
            System.out.println("Details: " + s2 + "\n");
        }
    }

    public static Object readable(JsonElement responces) {
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(responces, new TypeToken<Map<String, Object>>() {
        }.getType());
        map.forEach((x, y) -> System.out.println(x + " : " + y));
        return map.get("query");
    }
}
