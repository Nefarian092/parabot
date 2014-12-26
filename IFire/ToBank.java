package IFire;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;


public class ToBank implements Strategy {




    Area InBank = new Area(new Tile(3009, 3354, 0), new Tile(3009, 3358, 0), new Tile(3018, 3358, 0), new Tile(3018, 3354, 0));

    int Logs = IFire.Log;

    Tile[] Walk_To = {
            new Tile (3006, 3363, 0),
            new Tile (3012, 3362, 0),
            new Tile (3013, 3356 ,0),};

    TilePath path = new TilePath(Walk_To);

    Tile[] TO_SPOT = {
            new Tile (3013, 3361, 0),
            new Tile (3019, 3363, 0),
            new Tile (3023, 3362 ,0),};

    TilePath Walk = new TilePath(TO_SPOT);

    public boolean activate() {
        if (Inventory.getCount(Logs) <= 1) {
            System.out.println("1 or less logs");
            IFire.stage = 0;

            return true;
        }
        return false;
    }

    public void execute() {
        SceneObject[] bankBooth = SceneObjects.getNearest(11758);

        if (path != null && !path.hasReached() && (!InBank.contains(Players.getMyPlayer().getLocation())) && (Inventory.getCount(Logs) == 0)) {
            path.traverse();
            Time.sleep(2000);
        }

    if (InBank.contains(Players.getMyPlayer().getLocation()) && (bankBooth != null) && (Inventory.getCount(Logs) == 0)) {
        bankBooth[0].interact(0);
        Time.sleep(2000);

    }

        if (Game.getOpenInterfaceId() == 5292 && (Inventory.getCount(Logs) == 0)) { //If bank is open
            Time.sleep(700);
            Menu.sendAction(431, Logs +1, 0, 5382, 32334, 4);//withdraw all logs
            Time.sleep(500);
            Bank.close();
            System.out.println("banking  :"+IFire.stage);
            System.out.println("walking executed :");

        }

        if (Inventory.isFull() &&(Walk != null) && (!Walk.hasReached())) {
            Walk.traverse();
        }



    }
}
