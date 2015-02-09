package iplank;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by Scriptss
 * parabot
 * iplank
 * 09/02/15
 */
public class SawmillOperator implements Strategy {


    Area IN_SAWMILL = new Area(new Tile(3200, 3434), new Tile(3200, 3443), new Tile(3215, 3443), new Tile(3215, 3434));

    private static Npc q;
    public boolean activate() {
        for(Npc n : Npcs.getNearest(4250)){
            q = n;
            return n != null
            && n.distanceTo() < 10
            && (IN_SAWMILL.contains(Players.getMyPlayer().getLocation())
            && Inventory.getCount(Main.LOGS) >= 1);
        }

        return false;
    }

    public void execute() { //
        System.out.println("sawmill"); //some debugging
        if (q != null && Game.getOpenInterfaceId() != 39000 && Inventory.getCount(Main.LOGS) >= 1){
            q.interact(2); // Change to 1 if 0 doesn't work.
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Game.getOpenInterfaceId() == 39000;
                }
            }, 7000);
        }

        if (Game.getOpenInterfaceId() == 39000) {
            Menu.sendAction(431, 50, 0, 39008, 1278, 2);//make all
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(Main.LOGS) == 0;
                }
            }, 4000);

            Menu.sendAction(646, 8778, 1, 39006, 1189, 1);//close interface
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Game.getOpenInterfaceId() != 39000;
                }
            }, 3000);

        }
    }
 }
