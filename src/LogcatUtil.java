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
    private boolean cmdOpen = true;
    private Process p;

    public LogcatUtil(long minutes) {

        renameInterval = minutes * 60 * 1000;

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
            cmdOpen = true;
            p = Runtime.getRuntime().exec("adb logcat -v time");
            Process pTemp = p;
            br = new BufferedReader(new InputStreamReader(pTemp.getInputStream(),"GBK"));
            String line = null;
            while ((line = br.readLine()) != null && cmdOpen) {
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

    public void stopLogcat() {
        cmdOpen = false;
        if (p != null) {
            p.destroy();
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

    public String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmm");//设置日期格式
        return df.format(new Date());
    }
}
