import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("*******************************************************");
        System.out.println("******        WELCOME");
        System.out.println("******        I WILL GET LOGCAT SOON");
        System.out.println("******        DON'T CLOSE THIS CONSOLE");
        System.out.println("*******************************************************");
        System.out.println("******        MADE BY JAYSON.YE");
        System.out.println("*******************************************************");
        System.out.print("******        Input how often do you want to split the logs (minutes): ");
        Scanner scanner = new Scanner(System.in);
        try {
            long minutes = scanner.nextLong();
            if (minutes < 1) {
                System.out.println("******        Invalid value !!!!!");
                System.out.println("******        You need to enter a number greater than 1 !!!!");
                return;
            }else if (minutes >= 200) {
                System.out.println("******        Too large value !!!!!");
                System.out.println("******        You need to enter a number less than 200 !!!!");
                return;
            }
            System.out.println("******        OK, I will splite per " + minutes + " minutes;");
            System.out.println("******        And I will start grab log...");
            LogcatUtil util = new LogcatUtil(minutes);
            util.startLogcat();
        }catch (Exception e) {
            System.out.println("******        Sorry, I has an exception...");
            System.out.println("******        msg: " + e.getClass().getSimpleName());
        }
    }
}
