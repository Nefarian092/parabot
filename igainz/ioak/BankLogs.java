package igainz.ioak;


import igainz.Igainz;
import igainz.utilitys.Area;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

/**
 * Created by callum on 29/01/15.
 */
public class BankLogs implements Strategy {

    Area IN_BANK = new Area(new Tile(2721, 3490), new Tile(2721, 3494), new Tile(2730, 3493), new Tile(2730, 3490));

    Tile[] Walk_To = {
            new Tile (2726, 3485, 0),
            new Tile (2725, 3492 ,0),};

    TilePath path = new TilePath(Walk_To);

    public boolean activate() { //if true continue to execute


        return Inventory.isFull();

    }


    public void execute() { //
        Igainz.status = "Banking Logs";
        SceneObject[] BankBooth = SceneObjects.getNearest(2213);

        if(Inventory.isFull() && (!IN_BANK.contains(Players.getMyPlayer().getLocation()))) {

            if (!path.hasReached() && path != null) {
            path.traverse();
            Time.sleep(2000, 2300);
        }
      }


        if (Inventory.isFull()
        && (IN_BANK.contains(Players.getMyPlayer().getLocation()))
        && Game.getOpenInterfaceId() != 5292 && BankBooth[0] != null) {

                BankBooth[0].interact(0);
                Time.sleep(2000, 2500);
            }






        if (Game.getOpenInterfaceId() == 5292) {
            Bank.depositAllExcept(1360, 1361, 1359); //deposit all except axe
            Time.sleep(1000, 1500);
            Mouse.getInstance().click(487, 27, true); //close bank
        }


    }



}
