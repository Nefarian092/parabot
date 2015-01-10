package ICwars;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by scriptss on 10/01/15.
 */
public class Waiting implements Strategy {


    Area IN_WAITING = new Area(new Tile(2411, 9514), new Tile(2411, 9532), new Tile(2429, 9532), new Tile(2429, 9514));

    public boolean activate() {
        if (IN_WAITING.contains(Players.getMyPlayer().getLocation())) {
            System.out.println("IN_WAITING activated");
            ICwars.status = "waiting for game to start";

            return true;
        }
        return false;
    }

    public void execute() {
        System.out.println("IN_WAITING executed");
        Time.sleep(10000);



    }
}
