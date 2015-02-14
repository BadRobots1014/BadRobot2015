package org.usfirst.frc.team1014.robot.subsystems;

import java.util.TimerTask;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PIDSource;
 
public class LIDAR implements PIDSource{
	private I2C i2c;
	private byte[] distance;
	private java.util.Timer updater;
	
	private final int LIDAR_ADDR = 0x62;
	private final int LIDAR_CONFIG_REGISTER = 0x00;
	private final int LIDAR_DISTANCE_REGISTER = 0x8f;
	private long timeSinceLastUpdate;//Used to prevent overpolling
	
	public LIDAR(Port port) {
		i2c = new I2C(port, LIDAR_ADDR);
		
		distance = new byte[2];
		
		updater = new java.util.Timer();
		
		timeSinceLastUpdate = System.currentTimeMillis();
	}
	
	public int getDistance() {
		return (int)Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
	}
	
	public long timeSinceUpdate() {
		return (System.currentTimeMillis() - timeSinceLastUpdate);
	}
	
	/*public void start() {
		updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);
	}
	
	public void start(int period) {
		updater.scheduleAtFixedRate(new LIDARUpdater(), 0, period);
	}
	
	public void stop() {
		updater.cancel();
		updater = new java.util.Timer();
	}*/
	
	// Update distance variable
	public boolean update() {
		if(System.currentTimeMillis() - timeSinceLastUpdate >= 1000) {
			i2c.write(LIDAR_CONFIG_REGISTER, 0x04);
			Timer.delay(0.04); 
			i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); 
			Timer.delay(0.005); 
			timeSinceLastUpdate = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	@Override
	public double pidGet() {
		return getDistance();
	}
	
	// Another test, distance is constantly updated
	/*private class LIDARUpdater extends TimerTask {
		public void run() {
			while(true) {
				update();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
}


