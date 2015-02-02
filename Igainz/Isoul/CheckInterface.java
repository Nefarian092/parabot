package igainz.isoul;

import igainz.Igainz;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by callum on 29/01/15.
 */
public class CheckInterface implements Strategy {



    public boolean activate() {

        return Game.getOpenInterfaceId() != 3824 && (Players.getMyPlayer().getLocation().equals(new Tile(3097, 3505, 0)));

    }


    public void execute() {
        Igainz.status = "Opening Shop";

        Npc[] Mage = Npcs.getNearest(1658);

    if (Mage[0] != null && Mage[0].distanceTo() < 5) {
     Mage[0].interact(0);
        Time.sleep(2000, 3200);
      }
    }
  }
