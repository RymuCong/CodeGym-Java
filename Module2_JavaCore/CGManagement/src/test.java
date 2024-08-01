import model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
            String src = "src/view/students.csv";
            File file = new File(src);
            List<Student> list = new ArrayList<>();

            try (FileReader fileReader = new FileReader(file);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String temp = "";
                 do {
                    String[] dataArray = temp.split(",");
                    System.out.println(Arrays.toString(dataArray));
                } while ((temp = bufferedReader.readLine()) != null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
