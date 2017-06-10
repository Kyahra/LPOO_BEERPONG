


import com.beerpong.game.controller.GameController;
import com.beerpong.game.controller.levels.EasyController;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by up201504570 on 10/06/2017.
 */

public class GameLogicTest extends GameTest{

    @Test
    public void testShootBall() {
       GameController.setLevelController(new EasyController());

        GameController.getInstance().shootBall(1000,100);
        GameController.getInstance().update(1);

        assertEquals(GameController.getInstance().getBallBody().getLinearVelocity().isZero(),false);
        assertEquals(GameController.getInstance().isBallMoving(), true);

    }

    @Test
    public void testSecondShoot(){

    }


}
