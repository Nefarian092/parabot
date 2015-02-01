package Igainz.Iplank;

import Igainz.Igainz;
import Igainz.Utilitys.Area;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by callum on 29/01/15.
 */
public class GoMarket implements Strategy {


    Area PLANK_AREA = new Area(new Tile(3200, 3435), new Tile(3200, 3444), new Tile(3213, 3444), new Tile(3213, 3435));

    Area MARKET_AREA = new Area(new Tile(3207, 3422), new Tile(3207, 3434), new Tile(3220, 3434), new Tile(3220, 3422));

    public boolean activate() { //if true continue to execute

        return !MARKET_AREA.contains(Players.getMyPlayer().getLocation()) && (!PLANK_AREA.contains(Players.getMyPlayer().getLocation()));

    }


    public void execute() {
        Igainz.status = "Out Of Bounds";
        Keyboard.getInstance().sendKeys("::market");
        Time.sleep(6000);
    }
  }
