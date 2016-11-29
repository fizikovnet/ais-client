package client.controller;

import com.google.gson.Gson;
import sun.net.www.protocol.http.HttpURLConnection;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private static final String SERVER_URL = "http://localhost:8080/";
    private URL server;

    public List<String> getFiles() {
        List<String> result = new ArrayList<>();
        try {
            server = new URL(SERVER_URL);
            URLConnection connection = server.openConnection();
            try (InputStream is = connection.getInputStream()){
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String str;
                while ((str = in.readLine()) != null) {
                    sb.append(str);
                }
                result = new Gson().fromJson(sb.toString(), List.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public BufferedImage showFile(String fileName) {
        BufferedImage bufferedImage;
        try {
            server = new URL("http://localhost:8080/");
            HttpURLConnection con = (HttpURLConnection) server.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            String urlParameters = "fileTitle=" + fileName;
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            bufferedImage = ImageIO.read(con.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }

        return bufferedImage;
    }
}
