package ICwars;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by scriptss on 09/01/15.
 */
public class Lobby implements Strategy {

    Area IN_LOBBY = new Area(new Tile(2437, 3082), new Tile(2437, 3097), new Tile(2446, 3097), new Tile(2446, 3082));

    public boolean activate() {
        if (IN_LOBBY.contains(Players.getMyPlayer().getLocation())) {
            System.out.println("IN_LOBBY activated");
            ICwars.status = "Entering portal";
            Time.sleep(2000);

            return true;
        }
        return false;
    }

    public void execute() {

        SceneObject[] bankBooth = SceneObjects.getNearest(4408);
        System.out.println("IN_LOBBY executed");

        if (bankBooth != null) {
            System.out.println("Portal isn't null");
        if (bankBooth[0].distanceTo() <= 10 && (bankBooth.length > 0)) {
            System.out.println("interacting with portal");
        bankBooth[0].interact(0);
        Time.sleep(5000);
        }
        }


    }






}
