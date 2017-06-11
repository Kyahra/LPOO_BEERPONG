

import com.beerpong.game.controller.GameController;
import com.beerpong.game.controller.levels.EasyController;
import com.beerpong.game.controller.levels.MediumController;
import com.beerpong.game.model.GameModel;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by up201504570 on 10/06/2017.
 */

public class GameLogicTest extends GameTest{

    public void start(){
        GameController.reset();
        GameModel.reset();
    }




    @Test
    public void testShootBall() {

        start();
        GameController.setLevelController(new EasyController());


        GameController.getInstance().shootBall(1000,1000);
        GameController.getInstance().update(1/300f);

        assertFalse(GameController.getInstance().getBallBody().getLinearVelocity().isZero());


    }

    @Test
    public void testBlockInput(){

        start();
        GameController.setLevelController(new MediumController());


        GameController.getInstance().shootBall(1000,1000);
        GameController.getInstance().update(1/300f);

        assertTrue(GameController.getInstance().isBallMoving());

    }

    @Test
    public void testScoreUpdated(){

        start();
        GameController.setLevelController(new EasyController());

        assertEquals(GameController.getInstance().getScore(),0);

        GameController.getInstance().shootBall(1000,1000);
        GameController.getInstance().update(1/300f);

        assertTrue(GameController.getInstance().getScore() >0);


    }

    @Test
    public void testGameOver(){

        start();
        GameController.setLevelController(new EasyController());

        assertEquals(GameController.getInstance().getScore(),0);

        GameController.getInstance().shootBall(0,0);
        GameController.getInstance().update(1/300f);

        System.out.println(GameController.getInstance().isOver());

        assertTrue(GameController.getInstance().isOver()>0);

    }




}
