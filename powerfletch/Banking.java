package powerfletch;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

import java.util.ArrayList;

/**
 * Created by callum on 24/01/15.
 */
public class Banking implements Strategy {


    public boolean activate() { //if true continue to execute


        if (Inventory.getCount(PowerFletch.BOW) >= 27) {
            System.out.println("Banking activated");

            return true;
        }

        return false;
    }


    public void execute() { //
        SceneObject[] bankBooth = SceneObjects.getNearest(2213);

        if (Inventory.getCount(PowerFletch.BOW) >= 27
        && Game.getOpenInterfaceId() != 5292
        && bankBooth[0] != null
        && bankBooth.length >0){
           bankBooth[0].interact(0);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid(){
                    return Game.getOpenInterfaceId() == 5292; //
                }
            },7000);

        }

        if (Game.getOpenInterfaceId() == 5292) {
            Bank.depositAllExcept(947);
            Menu.sendAction(431, PowerFletch.LOG -1, 0, 5382, 2213, 4);
            Bank.close();

        }
    }










    public static void depositAllExcept(int... itemIDs) {
        final ArrayList<Integer> dontDeposit = new ArrayList<Integer>();
        if (Inventory.getCount(false) <= 2) {
            return;
        } else {
            for (int i : itemIDs) {
                dontDeposit.add(i);
            }
        }
        for (Item inventoryItem : Inventory.getItems()) {
            if (!dontDeposit.contains(inventoryItem.getId())) {
                Menu.sendAction(431, inventoryItem.getId() - 1, inventoryItem.getSlot(), 5064, 2213, 3);
                Time.sleep(80);
            }
        }
    }

}
