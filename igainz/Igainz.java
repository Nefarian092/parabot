package igainz;

import igainz.ioak.BankLogs;
import igainz.ioak.CutLogs;
import igainz.ioak.Inseers;
import igainz.iplank.BankAndPlank;
import igainz.iplank.GoMarket;
import igainz.iplank.OperatorArea;
import igainz.isoul.BuySouls;
import igainz.isoul.CheckArea;
import igainz.isoul.CheckInterface;
import igainz.isoul.GoHome;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import igainz.utilitys.Frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;


@ScriptManifest(author = "Scriptss", category = Category.UTILITY, description = "Makes bank on Ikov", name = "igainz", servers = { "Ikov" }, version = 1.0)

public class Igainz extends Script implements MessageListener, Paintable {

    /*
    to anyone who can be bothered reading this..
    it is the first time ive attempted to even use GUI's and it was loads of fun to learn

    Some future updates Im planning are -
    (o) Screen Capture for when you reach a certain milestone (level or profit gained)
    (o) linking the screen capture to the Imgur API to upload loots upon finishing script (will be optional)
    (o) Ithiev (currently looking for a better method of making money than stalls)
    (o) Some form of dynamic paint using mysql (hopefully paradox will implement his soon)
    (o) using javaFX instead of swing (looks allot cleaner)



    Tried to avoid using menu.sendAction because it keeps freezing my client
    Ithiev will also be added at a later date, still working on a flawless script for that

    If you have any suggestions feel free to pm me on parabot.

    Credits to -
    (o) Fryslan for his screen capture class
    (o) Paradox for his GUI example
    (o) Minimal for his hide paint method
    (o) Empathy for his display GUI fix
    (o) parabot for it's abundance of help and resources

        ~ scriptss

     */


    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

    private Image image = getImage("http://i.imgur.com/Keiu760.png");
    private static long startTime = System.currentTimeMillis();
    public static String status = "Starting up...";
    private boolean showPaint = true;

    public static int Isoul = 0;
    public static int Iplank = 0;
    public static int Ithiev = 0;
    public static int Ioak = 0;
    int LevelsGained = 0;

    public boolean onExecute() {
        status = "Starting up...";
        //GUI
        Frame window = new  Frame();
        window.setVisible(true);
        while (window.isVisible()) {
            sleep(20);
        }
        //isoul
        if (Isoul == 1) {
            strategies.add(new GoHome());
            strategies.add(new CheckArea());
            strategies.add(new CheckInterface());
            strategies.add(new BuySouls());
        }

        //iplank
        if (Iplank == 1) {
            strategies.add(new OperatorArea());
            strategies.add(new GoMarket());
            strategies.add(new BankAndPlank());
        }
        //ioak
        if (Ioak == 1) {
            strategies.add(new Inseers());
            strategies.add(new CutLogs());
            strategies.add(new BankLogs());
        }


        provide(strategies);
        return true;

    }



    public void onFinish() {

    }


    public void messageReceived(MessageEvent m)
    {
        if (m.getMessage().contains("command does not exist"))
        {
            if (showPaint)
            {
                showPaint = false;
            }
            else
            {
                showPaint = true;
            }
    }
        if (m.getMessage().contains("just advanced")) //used for Ioak
        {
           LevelsGained += 1;
        }
    }

    @Override
    public void paint(Graphics g)
    {
        if (showPaint)
        {
            g.drawImage(image, 2, 241, null);
            g.setColor(Color.WHITE);
        }
        else
        {
            g.setColor(Color.LIGHT_GRAY);
        }

        g.setFont(new Font("Helvetica", Font.PLAIN, 14));


        if (Isoul ==1) {
        g.drawString("isoul", 245, 261);
        }
        if (Ioak ==1) {
            g.drawString("ioak", 245, 261);
        }
        if (Iplank ==1) {
            g.drawString("iplank", 245, 261);
        }

        g.drawString("" +runTime(startTime), 241, 281);

        g.drawString("" +LevelsGained, 236, 300);

        g.drawString("" +status, 79, 324);
    }

    public Image getImage(String str)
    {
        try
        {
            return ImageIO.read(new URL(str));
        }
        catch(IOException e)
        {
            return null;
        }
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