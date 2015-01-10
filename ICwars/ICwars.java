package ICwars;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;



@ScriptManifest(author = "Scriptss", category = Category.MINIGAMES, description = "Plays Castle wars", name = "ICwars", servers = { "Ikov" }, version = 1.0)

public class ICwars extends Script implements Paintable {

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(255, 255, 255);
    private final Font font1 = new Font("Trebuchet MS", 0, 14);
    private final Font font2 = new Font("Trebuchet MS", 0, 12);
    private final Image img1 = getImage("http://i.imgur.com/hV6eoXG.png");

    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.drawImage(img1, 2, 2, null);
        g.setFont(font2);
        // Stuff
        g.setFont(font1);
           g.drawString("Run Time: " +runTime(startTime), 34, 91);
        g.drawString("Status: " +status, 21, 149);
    }


    private static long startTime = System.currentTimeMillis();
    static String status = "Starting up...";


    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();


    public boolean onExecute() {
         status = "Starting up";
        strategies.add(new Lobby());
        strategies.add(new Waiting());
        strategies.add(new Spawn());
        provide(strategies);
        return true;
    }






    public void onFinish() {
        //println with total loots and shit



    }

    public String runTime(long i)
    {
        DecimalFormat nf = new DecimalFormat("00");

        long millis = System.currentTimeMillis() - i;
        long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        long seconds = millis / 1000;

        return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
    }







}
