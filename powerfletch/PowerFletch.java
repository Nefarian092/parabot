package powerfletch;

import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

import java.util.ArrayList;


@ScriptManifest(author = "Scriptss", category = Category.FLETCHING, description = "Power Fletches Willow Longbows", name = "powerfletch", servers = { "Ikov" }, version = 1.0)

public class PowerFletch extends Script {

public final static int BOW = 57; //capitalised because its a constant (the variable doesn't change)
public final static int LOG = 1522; //capitalised because its a constant (the variable doesn't change)

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

    public boolean onExecute() {

        strategies.add(new Banking());
        strategies.add(new Cutting());
        provide(strategies);
        return true;
    }

    public void onFinish() {
        //println with total loots and shit



    }







}
