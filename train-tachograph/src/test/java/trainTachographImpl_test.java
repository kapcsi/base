import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class trainTachographImpl_test {
    TrainController ctr;
    TrainUser user;
    trainTachographImpl tacho;

    @Before
    public void before(){
        tacho = new trainTachographImpl();
        ctr = new TrainControllerImpl();
        user = new TrainUserImpl(ctr);
    }

    @Test
    public void Test(){
        tacho.add(user.getJoystickPosition(), ctr.getReferenceSpeed());
        Assert.assertEquals(false, tacho.isEmpty());
    }
}
