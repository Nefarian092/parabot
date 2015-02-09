package iplank;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

/**
 * Created by Scriptss
 * parabot
 * iplank
 * 09/02/15
 */
public class CheckArea implements Strategy {

    Area IN_SAWMILL = new Area(new Tile(3200, 3434), new Tile(3200, 3443), new Tile(3215, 3443), new Tile(3215, 3434));
    Area IN_MARKET = new Area(new Tile(3200, 3420), new Tile(3200, 3443), new Tile(3221, 3443), new Tile(3221, 3420));

    Tile[] Walk_To = {
            new Tile (3212, 3428, 0),
            new Tile (3211, 3432 ,0),
            new Tile (3210, 3437 ,0),};


    TilePath path = new TilePath(Walk_To);

    public boolean activate() {


        if (!IN_SAWMILL.contains(Players.getMyPlayer().getLocation())) {
            System.out.println("moving back to market"); //some debugging


            return true;
        }

        return false;
    }


    public void execute() {

        if (!IN_SAWMILL.contains(Players.getMyPlayer().getLocation())
        && (!Players.getMyPlayer().getLocation().equals(new Tile(3212, 3424)))
        && (!IN_MARKET.contains(Players.getMyPlayer().getLocation()))) {
            Keyboard.getInstance().sendKeys("::market");
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid(){
                    return (Players.getMyPlayer().getLocation().equals(new Tile(3212, 3424)));
                }
            },7000);
        }

        if (Players.getMyPlayer().getLocation().equals(new Tile(3212, 3424)) && path != null && !path.hasReached()) {
            path.traverse();
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid(){
                    return (IN_SAWMILL.contains(Players.getMyPlayer().getLocation()));
                }
            },9000);

    }

}
}
