package request;

import java.io.*;

public class IO {
    protected static String read(String fileName) throws IOException {
        try (
            FileReader fr = new FileReader("./db/" + fileName);
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
protected static void write(String fileName, String str) throws IOException {
        File file = new File("./db/" + fileName);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(str);
        ps.close();
    }

    protected static void commit() {
        // writeToFile("./db/info.json.tmp");
        // deleteFile("./db/info.json");
        // moveFile(from: "./db/info.json.tmp", to: "./db/info.json");
    }
}
