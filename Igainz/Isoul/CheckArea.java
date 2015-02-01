package Igainz.Isoul;

import Igainz.Igainz;
import Igainz.Utilitys.Area;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

/**
 * Created by callum on 29/01/15.
 */
public class CheckArea implements Strategy {

   Area HOME_AREA = new Area(new Tile(3084, 3487), new Tile(3084, 3504), new Tile(3103, 3504), new Tile(3103, 3487));

    Tile[] Walk_To = {new Tile (3093, 3497, 0), //1
            new Tile (3092, 3502 ,0), //2
            new Tile (3097, 3505, 0),};

    TilePath path = new TilePath(Walk_To);



    public boolean activate() { //if true continue to execute

        return !Players.getMyPlayer().getLocation().equals(new Tile(3097, 3505, 0)) && (HOME_AREA.contains(Players.getMyPlayer().getLocation()));

    }


    public void execute() {
        Igainz.status = "Navigating To Mage";

            if (path != null && !path.hasReached()) {
                path.traverse();
                Time.sleep(2000);
            }
         }
      }
