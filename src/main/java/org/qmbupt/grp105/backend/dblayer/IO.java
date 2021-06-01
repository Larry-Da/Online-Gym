package org.qmbupt.grp105.backend.dblayer;

import java.io.*;

/**
 * IO operations
 * @author Lingsong Feng
 * @version 5.3
 */
public class IO {

    /**
     * read file from disk
     * @param fileName fileName
     * @return the content of that file
     * @throws IOException IOException
     */
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

    /**
     * write to disk
     * @param fileName fileName
     * @param str content of file
     * @throws IOException IOException
     */
    protected static void write(String fileName, String str) throws IOException {
        File file = new File("./db/" + fileName);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(str);
        ps.close();
    }
}
