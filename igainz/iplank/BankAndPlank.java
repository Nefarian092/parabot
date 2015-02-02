package igainz.iplank;

import igainz.Igainz;
import igainz.utilitys.Area;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by callum on 29/01/15.
 */
public class BankAndPlank implements Strategy {

    Area PLANK_AREA = new Area(new Tile(3200, 3435), new Tile(3200, 3444), new Tile(3213, 3444), new Tile(3213, 3435));

    int LOG = 1522;

    public boolean activate() { //if true continue to execute

        return PLANK_AREA.contains(Players.getMyPlayer().getLocation());

    }


    public void execute() {

        SceneObject[] BankBooth = SceneObjects.getNearest(2213);
        Npc[] Sawmill = Npcs.getNearest(4250);



        if (Inventory.getCount(LOG) == 0 && Game.getOpenInterfaceId() != 5292 && BankBooth[0] != null) {
            Igainz.status = "Banking Items";

            BankBooth[0].interact(0);
            Time.sleep(2000, 4000);
        }

        if (Game.getOpenInterfaceId() == 5292) {
            Time.sleep(600, 800);
            Bank.depositAllExcept(995); //because deposit all doesnt work and coins may be in inv
            Menu.sendAction(431, LOG -1, 0, 5382, 2213, 4);//withdraw all oak logs
            Time.sleep(500, 700);
            Bank.close();
            Time.sleep(700, 900);
        }

        if (Inventory.getCount(LOG) > 1 && Game.getOpenInterfaceId() != 39000 && Sawmill[0] != null) {
            Igainz.status = "Making Planks";
            Sawmill[0].interact(2);
            Time.sleep(2000, 2500);
        }

        if (Game.getOpenInterfaceId() == 39000) {
            Time.sleep(700, 900);
            Menu.sendAction(431, 50, 0, 39008, 1278, 2);//make all
            Time.sleep(700, 900);
            Menu.sendAction(646, 8778, 1, 39006, 1189, 1);//close interface

        }
    }



}
