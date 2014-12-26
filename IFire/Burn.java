package IFire;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.wrappers.Item;


public class Burn implements Strategy {

    int Logs = IFire.Log;
    int Tinderbox = 591;

    public boolean activate() {
        if (IFire.stage == 1 &&
           (Inventory.getCount(Logs)>= 1)) {
            System.out.println("burning stage :"+IFire.stage);

            return true;
        }
        return false;
    }

    public void execute() {
        Item[] ToBurn = Inventory.getItems(Logs);
        Item[] Tinder = Inventory.getItems(Tinderbox);
        Time.sleep(300);
        Menu.sendAction(447, Logs , ToBurn[0].getSlot(), 3214, 9398, 3); //select logs
        Time.sleep(400);
        Menu.sendAction(870, Tinderbox , Tinder[0].getSlot(), 3214, 9398, 1); //use on tinderbox


    }
  }

