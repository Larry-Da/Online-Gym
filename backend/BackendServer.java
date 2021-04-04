import java.io.*;

import com.alibaba.fastjson.*;

//yyyy-MM-dd
public class BackendServer {
    public String execute(String clientRequestStr) {
        return "";
    }
    public static void main(String[] args) {

        //JSONObject object = JSON.parseObject("{\"request\":\"userinfo\"}");
        //System.out.println(object.getString("request"));
        try {
            System.out.println(read());
        } catch (IOException ex) {
            System.out.println("IO Error");
            ex.printStackTrace();
        }
    }

    private static String read() throws IOException {
        try (
            FileReader fr = new FileReader("./db/info.json");
            BufferedReader br = new BufferedReader(fr)
        ) {
            StringBuilder sb = new StringBuilder();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                sb.append(temp + "\n");
            }

            return sb.toString();
        }
    }

    private static void commit() {
        // writeToFile("./db/info.json.tmp");
        // deleteFile("./db/info.json");
        // moveFile(from: "./db/info.json.tmp", to: "./db/info.json");
    }
    private static void init() {
        // if both info.json.tmp and info.json occur
        //   delete info.json
        //   return
        // if only info.json.tmp
        //   mv info.json.tmp to info.json
        //   return

    }
}