import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LogcatUtil {
    private String fileName = "";
    private String path = "";
    private long renameInterval = 1 * 60 * 1000;

    public LogcatUtil() {
        path = getPath();
        fileName = getFileName();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fileName = getFileName();
            }
        }, renameInterval, renameInterval);
    }

    public void startLogcat() {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec("adb logcat -v time");
            br = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
            String line = null;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                FileUtil.writeContent(path, fileName, line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getPath() {
        String p = FileUtil.getCurrentDir() + File.separator;
        return p;
    }

    private String getFileName() {
        String n = getCurrentTime() + ".log";
        return n;
    }

    private String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmm");//设置日期格式
        return df.format(new Date());
    }
}
