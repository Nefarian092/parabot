package irestore.script;

import irestore.Main;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Item;

/**
 * Created by Scriptss
 * parabot
 * irestore.script
 * 04/02/15
 */
public class MakePots implements Strategy {

    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(Main.ENERGY) >= 1 && Inventory.getCount(Main.PAPAYA) >= 1) {

            return true;
        }

        return false;
    }


    public void execute() { //
        Item[] itemOne = Inventory.getItems(Main.ENERGY); //variable
        Item[] itemTwo = Inventory.getItems(Main.PAPAYA);//variable


        if(itemOne != null //variable
        && itemTwo != null //variable
        && Inventory.getCount(Main.ENERGY) >= 1 //check for Energy pots
        && Inventory.getCount(Main.PAPAYA) >= 1) {//check for papaya pots
            Inventory.combine(Main.ENERGY, Main.PAPAYA);//combine the two together (could use Menu.sendAction)
            Main.potsMade += 1; //amount of pots made
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid(){
                    return Players.getMyPlayer().getAnimation() != 363; //once the animation has finished stop sleeping
                }
            },4000);
        }
    }
}
