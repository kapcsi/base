package hu.bme.mit.train.sensor;


import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;


public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
		this.speedLimit = 5;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		checkAlarmState(speedLimit);
		if(!user.getAlarmState() )
			controller.setSpeedLimit(speedLimit);
	}
	public void checkAlarmState(int speedLimit){
		if(speedLimit < 0 || speedLimit>500 || (controller.getReferenceSpeed()/2) >speedLimit)
			user.setAlarmState(true);
	}

}
