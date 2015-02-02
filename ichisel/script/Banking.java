package ichisel.script;

import ichisel.Main;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.methods.Bank;


/**
 * Created by Scriptss
 * parabot
 * ichisel
 * 02/02/15
 */
public class Banking implements Strategy {

   private static int CHISEL = 1756;

   private static int GEM = Main.GEM_ID; //still seems neater than before

    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(GEM) == 0) { //no gems so...
            System.out.println("banking activated"); //more debugging

            return true;
        }

        return false;
    }


    public void execute() { //
        SceneObject[] bankBooth = SceneObjects.getNearest(2213); //need dem obj's

        if (bankBooth != null && bankBooth.length > 0 && Game.getOpenInterfaceId() != 5292) { //bank booth isn't null and is there
        bankBooth[0].interact(0); //interact with bank booth
        Time.sleep(2000); //just a simple sleep because it's only a short time
        }

        if (Game.getOpenInterfaceId() == 5292) { //bank booth is open
            Bank.depositAllExcept(CHISEL); //deposit whatevers in the inventory
            Time.sleep(600, 900); //why cant I use sleep for simple things like this ?
            Menu.sendAction(431, GEM -1, getBankSlot(GEM), 5382, 2213, 4);//withdraw all
            Time.sleep(500, 700); //another small sleep
            Bank.close(); //close deh bankeh
        }
    }

    private int getBankSlot(int id) { //thanks to someone on parabot for this

        int[] bankIds = Loader.getClient().getInterfaceCache()[5382].getItems();

        for (int i = 0; i < bankIds.length; i++) {
            if (bankIds[i] == id) {
                return i;
            }
        }
        return 0;
    }





}
