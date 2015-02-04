package irestore;

import irestore.script.Banking;
import irestore.script.MakePots;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Skill;

import java.util.ArrayList;

/**
 * Created by Scriptss
 * parabot
 * irestore
 * 04/02/15
 */
public class Main extends Script implements MessageListener { //message listener for game text/levels gained




    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();


    private static int levelsGained = 0;   //variable because the value will change (does not need to be modified outside of this class)
    public static int potsMade = 0;        //variable because the value will change (needs to be accessed from MakePots)
    public static final int PAPAYA = 5973; //Capitalised because its a constant (final because the variable wont be changed)
    public static final int ENERGY = 3019; //Capitalised because its a constant (final because the variable wont be changed)

    private static int herbloreLevel = 0;



    public boolean onExecute() {
        herbloreLevel = Skill.HERBLORE.getLevel(); //should check the herblore level when script starts ?
        strategies.add(new MakePots()); //Camel case because its a class
        strategies.add(new Banking()); //Camel case because its a class
        provide(strategies);
        return true;
    }






    public void onFinish() {

        System.out.println("Total Levels Gained - "+levelsGained);
        System.out.println("Special Restores Made - "+potsMade);
        System.out.println("Herblore Level - "+herbloreLevel);

    }

    public void messageReceived(MessageEvent m) //game message listener
    {
        if (m.getMessage().contains("just advanced") && Skill.HERBLORE.getLevel() > herbloreLevel ) { //checks if it's higher than level on start ?
            levelsGained += 1;
            herbloreLevel = Skill.HERBLORE.getLevel(); //resets the level on start to the current level because we gained a level ?
        }
    }






}
