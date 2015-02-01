package Igainz.Ioak;

import Igainz.Igainz;
import Igainz.Utilitys.Area;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by callum on 29/01/15.
 */
public class CutLogs implements Strategy {


    Area SEERS_AREA = new Area(new Tile(2717, 3476), new Tile(2717, 3495), new Tile(2731, 3495), new Tile(2731, 3476));

    int TREE = 1281;

    public boolean activate() { //if true continue to execute

        return SEERS_AREA.contains(Players.getMyPlayer().getLocation()) && !Inventory.isFull();

    }


    public void execute() {
        Igainz.status = "Cutting Logs";
        SceneObject[] getTree = SceneObjects.getNearest(TREE);
        SceneObject trees = getTree[0];
        System.out.println("walking to woodcutting spot");

        if (!Inventory.isFull() &&(Players.getMyPlayer().getLocation().equals(new Tile(2719,3480))) && !isNest()) {

            if (getTree!=null && trees.getLocation().distanceTo() < 4) {
                getTree[0].interact(0);
                Time.sleep(4000);
            }
        }

        if  (isNest()) {
            GroundItem[] toPickup = GroundItems.getNearest(5074, 5073, 5072, 5071, 5070, 5075, 5076, 5077);
            if (toPickup.length > 0 && toPickup[0] != null) {
                Igainz.status = "Picking Up Nest";
                toPickup[0].interact(2);
                Time.sleep(1500);
            }

        }

        if (!Inventory.isFull() && (!Players.getMyPlayer().getLocation().equals(new Tile(2719,3480)))) {

            new Tile(2719, 3480).walkTo();
            Time.sleep(3000);
        }



    }

    public boolean isNest() {
        GroundItem[] NestID = GroundItems.getNearest(5074, 5073, 5072, 5071, 5070, 5075, 5076, 5077);
        if (NestID.length > 0
                && NestID[0] != null
                && NestID[0].distanceTo() < 8) {
            return true;
        }
        return false;
    }


}
