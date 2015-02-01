package Igainz.Isoul;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import Igainz.Igainz;


/**
 * Created by callum on 29/01/15.
 */
public class BuySouls implements Strategy {


    public boolean activate() {

        return Game.getOpenInterfaceId() == 3824;

    }


    public void execute() { //using mouse because actions seem to bug out after a set time
        Igainz.status = "Buying Soul Runes";
        Mouse.getInstance().click(189, 130, false);
        Time.sleep(200);
        Mouse.getInstance().click(169, 217, true);
        Time.sleep(500);



    }





}
