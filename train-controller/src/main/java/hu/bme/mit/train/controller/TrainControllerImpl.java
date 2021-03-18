package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController  {
	private int mode = 0;
	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	public intervalSpeedChange myIntervalSpeedChange;

	public void setReferenceSpeed(int referenceSpeed) {
		this.referenceSpeed = referenceSpeed;
	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) { // no issues found..
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
	}


	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
		if(myIntervalSpeedChange == null){
			myIntervalSpeedChange = new intervalSpeedChange("Tread1");
			myIntervalSpeedChange.start();
		}
	}


	public class intervalSpeedChange implements Runnable{
		private Thread t;
		private String threadName;
		private boolean run;

		intervalSpeedChange( String name) {
			threadName = name;
			System.out.println("Creating " +  threadName );
		}
		@Override
		public void run() {
			run = true;
			try {
				while (run){
					followSpeed();
					System.out.println("Speed set");
					Thread.sleep(1000); // setting speed every second
				}
			} catch (InterruptedException e){
				System.out.println("Thread " +  threadName + " interrupted.");
				Thread.currentThread().interrupt();
			}

		}
		public void start(){
			System.out.println("Starting " +  threadName );
			if (t == null) {
				t = new Thread (this, threadName);
				t.start ();
			}
		}

		public void setRun(boolean b) {
			run =b;
		}
	}

}
