package IFire;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;


 @ScriptManifest(author = "Scriptss", category = Category.FIREMAKING, description = "Burns Logs", name = "IFire", servers = { "Ikov" }, version = 1.0)

 public class IFire extends Script implements MessageListener, Paintable {

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
     private final Image img1 = getImage("http://i.imgur.com/lTqE8mB.png");

     public void paint(Graphics g1) {
         Graphics2D g = (Graphics2D)g1;
         g.setColor(color1);
         g.drawImage(img1, 0, 0, null);
         g.setFont(font2);
         // Stuff
         g.setFont(font1);
         g.drawString("Logs Burnt: " +burnt, 27, 84);
         g.drawString("Run Time: " +runTime(startTime), 186, 84);
     }

     private static long startTime = System.currentTimeMillis();

 private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

        public static int Log;
        public static int stage = 0;
        int burnt = 0;



 public boolean onExecute() {
     JOptionPane.showMessageDialog (null, "Log ID's \nNormal Logs - 1512 \nOak Logs - 1522 \nWillow Logs - 1520\nMaple Logs - 1518\nYew Logs - 1516\nMagic Logs - 1514");
     Log = Integer.parseInt(JOptionPane.showInputDialog("Log ID"));
     strategies.add(new Burn());
     strategies.add(new ToBank());
     strategies.add(new AreaCheck());
     provide(strategies);
    return true;
 }

     public void messageReceived(MessageEvent m)
     {


         if (m.getMessage().contains("fire catches")) {
             burnt += 1;
         }

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
