import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("******        " + util.getCurrentTime());
                    System.out.println("******        I will stop command logcat and execute a new one...");
                    System.out.println("******        .................");
                    util.stopLogcat();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            util.startLogcat();
                        }
                    });
                    t.start();
                }
            }, 0,   10 * 60 * 1000);
        }catch (Exception e) {
            System.out.println("******        Sorry, I has an exception...");
            System.out.println("******        msg: " + e.getClass().getSimpleName());
        }
    }
}
