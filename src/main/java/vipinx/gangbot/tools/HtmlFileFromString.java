package vipinx.gangbot.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlFileFromString {

    /**
     * Creates an html file as "src/test.html" from a string
     * @param html the html file in string form
     */
    public HtmlFileFromString(String html) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("test.html")));
            writer.write(html);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
