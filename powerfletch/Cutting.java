package powerfletch;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;

/**
 * Created by callum on 24/01/15.
 */
public class Cutting implements Strategy {


     private final int KNIFE = 947; //capitalised because its a constant (the variable doesn't change)

    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(PowerFletch.LOG) >= 1) {
            System.out.println("cutting activated");

            return true;
        }

        return false;
    }


    public void execute() { //

        if (Inventory.getCount(PowerFletch.LOG) >= 1 && Game.getOpenBackDialogId() != 8880 && Players.getMyPlayer().getAnimation() != 1248) {
            Inventory.combine(PowerFletch.LOG, KNIFE);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid(){
                    return Game.getOpenBackDialogId() == 8880; //
                }
            },4000);
        }

        if (Game.getOpenBackDialogId() == 8880 && Players.getMyPlayer().getAnimation() != 1248) {

            Menu.sendAction(315, 452, 371, 8894, 1392, 1); //make all

            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid(){
                    return Inventory.getCount(PowerFletch.LOG) == 0; //
                }
            },65000);

        }


}
}
