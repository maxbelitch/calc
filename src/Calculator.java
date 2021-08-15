import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.go();
    }

    public void go() {
        String line = readLine(); //По примерам задачи операнды и оператор должны быть разделены пробелом.
        Action action = new Action(line);
        System.out.println(action);
    }

    public String readLine() {
        String returnedLine = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            returnedLine = reader.readLine();
        } catch (IOException ignored) {

        }
        return returnedLine;
    }

}
