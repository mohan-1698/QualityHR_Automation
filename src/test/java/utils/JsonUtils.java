package utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {

    public static Object[][] getLoginData(String relativePath) {

        try {
            // 🔥 Convert relative path to absolute path (Maven safe)
            String path = System.getProperty("user.dir") + relativePath;

            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(content);

            Object[][] data = new Object[jsonArray.length()][3];

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);

                data[i][0] = obj.getString("username");
                data[i][1] = obj.getString("password");
                data[i][2] = obj.getString("type");
            }

            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}