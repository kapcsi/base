package hu.bme.mit.train.sensor;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import hu.bme.mit.train.user.TrainUserImpl;
import hu.bme.mit.train.controller.TrainControllerImpl;

public class TrainSensorTest {
    TrainSensorImpl sensor;
    TrainUserImpl mockUser;
    TrainControllerImpl mockCtr;


    @Before
    public void before(){// createing enviroment
        mockUser =  mock(TrainUserImpl.class);
        mockCtr = mock(TrainControllerImpl.class);
        sensor= new TrainSensorImpl( mockCtr,mockUser);
        when(mockCtr.getReferenceSpeed()).thenReturn(200);
    }

    @Test
    public void TryingToSetSpeedLimitToUnderLimit_SetAlarmStateReturnsTrue(){ //testing Absolute margin lower boundary
        sensor.overrideSpeedLimit(-2);
        verify(mockUser).setAlarmState(true);
    }
    @Test
    public void TryingToSetSpeedLimitToOverLimit_SetAlarmStateReturnsTrue() { //testing Absolute margin upper boundary
        sensor.overrideSpeedLimit(501);
        verify(mockUser).setAlarmState(true);
    }
    @Test
    public void TryingToSetSpeedLimitToLessThan50Percent_SetAlarmStateReturnsTrue(){ // testing relative margin
        sensor.overrideSpeedLimit(90);
        verify(mockUser).setAlarmState(true);
    }
    @Test
    public void TryingToSetSpeedLimitToCorrectValue_SetAlarmStateReturnsFalse(){
        sensor.overrideSpeedLimit(190);
        verify(mockUser, times(0)).setAlarmState(true); // should not have been called
        verify(mockUser, times(0)).setAlarmState(false);// should not have been called
    }
    public void TryingToSetSpeedLimitToCorrectValue_SetAlarmStateReturnsFalse(){
        sensor.overrideSpeedLimit(190);
        verify(mockUser, times(0)).setAlarmState(true); // should not have been called
        verify(mockUser, times(0)).setAlarmState(false);// should not have been called
    }
}
