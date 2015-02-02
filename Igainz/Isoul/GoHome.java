package igainz.isoul;

import igainz.Igainz;
import igainz.utilitys.Area;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;


/**
 * Created by callum on 29/01/15.
 */
public class GoHome implements Strategy {

    Area HOME_AREA = new Area(new Tile(3084, 3487), new Tile(3084, 3504), new Tile(3103, 3504), new Tile(3103, 3487));

    public boolean activate() { //if true continue to execute

        return !Players.getMyPlayer().getLocation().equals(new Tile(3097, 3505, 0)) && (!HOME_AREA.contains(Players.getMyPlayer().getLocation()));

    }


    public void execute() {
        Igainz.status = "Resetting Location";

        Keyboard.getInstance().sendKeys("::home");
        Time.sleep(6000, 7000);
    }

}
