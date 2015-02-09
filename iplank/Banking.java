package iplank;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by Scriptss
 * parabot
 * iplank
 * 09/02/15
 */
public class Banking implements Strategy {

    Area IN_SAWMILL = new Area(new Tile(3200, 3434), new Tile(3200, 3443), new Tile(3215, 3443), new Tile(3215, 3434));
    SceneObject[] bankBooth = SceneObjects.getNearest(2213);
    SceneObject banks = bankBooth[0];

    public boolean activate() {


        if (IN_SAWMILL.contains(Players.getMyPlayer().getLocation()) && Inventory.getCount(Main.LOGS) == 0) {
            System.out.println("banking"); //some debugging

            return true;
        }

        return false;
    }


    public void execute() {

        if (banks != null && bankBooth.length > 0 && banks.distanceTo() < 9 && Game.getOpenInterfaceId() != 5292) {
            bankBooth[0].interact(0);

            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Game.getOpenInterfaceId() == 5292; // sleep until bank interface is open
                }
            }, 7000);
        }


        if (Game.getOpenInterfaceId() == 5292 && Inventory.getCount(Main.LOGS) == 0) {
            Main.planksMade += Inventory.getCount(8779);
            Bank.depositAllExcept(995); //bank.depositall() doesn't work on ikov ?
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.isEmpty();
                }
            }, 4000);
        }

        if (Game.getOpenInterfaceId() == 5292 && Inventory.isEmpty()) {
            Menu.sendAction(431, Main.LOGS -1, getBankSlot(Main.LOGS), 5382, 2213, 4);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(Main.LOGS) > 1;
                }
            }, 4000);

        }

        if (Game.getOpenInterfaceId() == 5292 && Inventory.getCount(Main.LOGS) > 1) {
            Bank.close();
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Game.getOpenInterfaceId() != 5292;
                }
            }, 4000);
        }

    }

    private int getBankSlot(int id) {

        int[] bankIds = Loader.getClient().getInterfaceCache()[5382].getItems();

        for (int i = 0; i < bankIds.length; i++) {
            if (bankIds[i] == id) {
                return i;
            }
        }
        return 0;
    }


    }






