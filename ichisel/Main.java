package ichisel;

import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import java.util.ArrayList;
import ichisel.script.*;

import javax.swing.*;

/**
 * Created by Scriptss
 * parabot
 * ichisel
 * 02/02/15
 */


@ScriptManifest(author = "Scriptss", category = Category.CRAFTING, description = "Chisels Gems", name = "Ichisel", servers = { "Ikov" }, version = 1.0)

public class Main extends Script implements MessageListener { //message listener for the levels gained




    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

    public static int GEM_ID;
    int LEVELS_GAINED = 0; // LEVELS_GAINED OR LevelsGained because the variable changes ?

    public boolean onExecute() {
        GEM_ID = Integer.parseInt(JOptionPane.showInputDialog("GEM ID"));//simple gui to make it so you can cut any gem
        strategies.add(new Chisel());
        strategies.add(new Banking());
        provide(strategies);
        return true;
    }


    public void onFinish() {
        System.out.println("Levels Gained - "+LEVELS_GAINED); //simple stuffs is simple

    }


    public void messageReceived(MessageEvent m) //game message listener
    {
        if (m.getMessage().contains("just advanced")) { //leveled up game text
           LEVELS_GAINED += 1; //cbf making a proper paint for it
        }
    }






}