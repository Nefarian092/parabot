package igainz.utilitys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 29-10-2014
 * Time: 21:52
 */
public class ScreenShot {

    private String dir;

    private int x;
    private int y;
    private int width;
    private int heigth;

    public ScreenShot() {
        x = 0;
        y = 0;
        width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }

    /**
     * @param x      X Location for the ScreenShot.
     * @param y      Y Location for the ScreenShot.
     * @param width  Width of the ScreenShot.
     * @param height Heigth of the ScreenShot.
     */
    public ScreenShot(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = height;
    }

    /**
     * Captures an Screenshot and saves it in the Users Directory.
     */
    public void capture() {
        dir = System.getProperty("user.home");
        createShot();
    }

    /**
     * Captures an Screenshot and saves it in the path's Directory.
     *
     * @param path directory to save the ScreenShot.
     */
    public void capture(String path) {
        dir = path;
        createShot();
    }

    /**
     * Create the ScreenShot;
     */
    private void createShot() {
        try {
            File folder = new File(dir + "\\Screenshots");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(x, y, width, heigth));
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
            Calendar calendar = Calendar.getInstance();
            ImageIO.write(screenShot, "PNG", new File(folder.getPath() + "\\" + format.format(calendar.getTime()) + ".png"));

        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}