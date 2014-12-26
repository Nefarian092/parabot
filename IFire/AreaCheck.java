package IFire;

import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;


public class AreaCheck implements Strategy {


    public boolean activate() {
        if (Players.getMyPlayer().getLocation().equals(new Tile(3023,3362))) {
            System.out.println("checking area");

            return true;
        }
        return false;
    }

    public void execute() {
        System.out.println("executed area");


        IFire.stage = 1;

    }


}
