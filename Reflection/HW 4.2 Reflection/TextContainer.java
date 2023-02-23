package reflection_hw_two;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@SaveTo(path = "C:\\Users\\AsusWallet\\Desktop\\Prog Academy\\Java PRO EE_2023 02\\Vsevolod\\Reflection1\\Reflection1\\src\\reflection_hw_two\\hw2_path.txt")
public class TextContainer {
    private String text;

    public TextContainer(String text) {
        this.text = text;
    }

    @Saver
    public boolean saveTextToFile (String path) {
        File file = new File(path);

        try (PrintWriter pw = new PrintWriter(file)) {
            pw.print(text);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
