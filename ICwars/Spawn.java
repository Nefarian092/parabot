package ICwars;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by sriptss on 10/01/15.
 */
public class Spawn implements Strategy {



    Area IN_SPAWN = new Area(new Tile(2368, 3127), new Tile(2368, 3135), new Tile(2376, 3135), new Tile(2376, 3127));

    public boolean activate() {
        if (IN_SPAWN.contains(Players.getMyPlayer().getLocation())) {
            System.out.println("IN_SPAWN activated");
            ICwars.status = "Waiting for game to end";

            return true;
        }
        return false;
    }

    public void execute() {
        System.out.println("IN_SPAWN executed");
        Time.sleep(10000);



    }



}
