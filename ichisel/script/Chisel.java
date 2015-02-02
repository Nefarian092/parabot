package ichisel.script;

import ichisel.Main;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Item;

/**
 * Created by Scriptss
 * parabot
 * ichisel
 * 02/02/15
 */
public class Chisel implements Strategy {

        int CHISEL = 1756; //dem ints

     int GEM = Main.GEM_ID; //seems neater than using Main.GEM_ID everywhere ?

    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(GEM) >= 1) {
            System.out.println("Gem activated"); //some debugging

            return true;
        }

        return false;
    }


    public void execute() { //
        Item[] UNCUT = Inventory.getItems(GEM);


            if (Inventory.getCount(GEM) > 0 && UNCUT != null) { //gem isn't null and it's in our inventory
                Inventory.combine(CHISEL, GEM); //combine dat shit
                Time.sleep(200); //small sleep because of the delay in animation
            }

        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid(){
                return Players.getMyPlayer().getAnimation() != 888; //chisel anim
          }
        },6000);
      }
    }
