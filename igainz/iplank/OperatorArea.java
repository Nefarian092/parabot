package igainz.iplank;

import igainz.Igainz;
import igainz.utilitys.Area;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

/**
 * Created by callum on 29/01/15.
 */
public class OperatorArea implements Strategy {


    Area PLANK_AREA = new Area(new Tile(3200, 3435), new Tile(3200, 3444), new Tile(3213, 3444), new Tile(3213, 3435));

    Area MARKET_AREA = new Area(new Tile(3207, 3422), new Tile(3207, 3434), new Tile(3220, 3434), new Tile(3220, 3422));

    Tile[] Walk_To = {new Tile (3212, 3428, 0), //1
            new Tile (3210, 3432 ,0), //2
            new Tile (3210, 3438, 0),};

    TilePath path = new TilePath(Walk_To);



    public boolean activate() { //if true continue to execute

        return MARKET_AREA.contains(Players.getMyPlayer().getLocation()) && (!PLANK_AREA.contains(Players.getMyPlayer().getLocation()));

    }


    public void execute() {
        Igainz.status = "Walking To Banking Area";
        if (path != null && !path.hasReached()) {
            path.traverse();
            Time.sleep(4000, 6000);
        }
    }






}
