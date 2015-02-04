package irestore.script;

import irestore.Main;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * Created by Scriptss
 * parabot
 * irestore.script
 * 04/02/15
 */
public class Banking implements Strategy {


    SceneObject[] bankBooth = SceneObjects.getNearest(2213);
    SceneObject banks = bankBooth[0];

    public boolean activate() { //if true continue to execute


        return Inventory.getCount(Main.ENERGY) < 1 || Inventory.getCount(Main.PAPAYA) < 1;
        //or operator because it cant make a pot without one or the other

    }


    public void execute() { //

          Skill.HERBLORE.getLevel();

        if (Inventory.getCount(Main.ENERGY) < 1 || Inventory.getCount(Main.PAPAYA) < 1 && Game.getOpenInterfaceId() != 5292) {

            if (banks != null && bankBooth.length > 0 && banks.distanceTo() < 5 && Game.getOpenInterfaceId() != 5292) { //check bank is actually there
                bankBooth[0].interact(0);

                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Game.getOpenInterfaceId() == 5292; // sleep until bank interface is open
                    }
                }, 7000);
            }
        }


        if (Game.getOpenInterfaceId() == 5292) {
            Bank.depositAll(); //dont think these work but you could use Menu.sendAction
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid(){
                    return Inventory.isEmpty(); //if the inv is empty deposit all has finished
                }
            },2000);
            Bank.withdraw(Main.PAPAYA -1, Main.ENERGY -1, 300); // -1 because the ikov bank item id is -1 to inventory
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid(){
                    return Inventory.getCount(Main.PAPAYA) > 1
                           && Inventory.getCount(Main.ENERGY) > 1; //if we have the pots withdraw has finished
                }
            },2000);
            Bank.close(); //close le bank (could use Menu.sendAction)

        }
    }




}
