package Igainz.Ioak;

import Igainz.Igainz;
import Igainz.Utilitys.Area;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by callum on 29/01/15.
 */
public class Inseers implements Strategy {


   public Area SEERS_AREA = new Area(new Tile(2717, 3476), new Tile(2717, 3495), new Tile(2731, 3495), new Tile(2731, 3476));

    public boolean activate() { //if true continue to execute

        return !SEERS_AREA.contains(Players.getMyPlayer().getLocation());

    }


    public void execute() {
        Igainz.status = "Out Of Bounds Fix";

            if (Game.getOpenBackDialogId() != 2492) {

                Mouse.getInstance().click(744, 184, true); //spell tab open
                Time.sleep(1000);
                Mouse.getInstance().click(616, 335, true); //select skills
                Time.sleep(1000);
            }

            if (Game.getOpenBackDialogId() == 2492) {
                Time.sleep(500);
                Mouse.getInstance().click(253, 382, true); //select woodcutting
                Time.sleep(900);
                Mouse.getInstance().click(242, 415, true); //select seers
                Time.sleep(6000);
            }
         }






}
