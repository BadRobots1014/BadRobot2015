package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.subsystems.IMU.IMU;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is the class that provides a medium between
 * the us and the Nav6MXP, basically a ridiculously useful
 * gyro that apparently has 9 directional inputs, an 
 * accelerometer, and a magnometer. 
 * @author Steve P.
 *
 */
public class Nav6MXP extends BadSubsystem {

	private static Nav6MXP instance;
	
	IMU imu;
	SerialPort serial_port;
	
    public static Nav6MXP getInstance()
    {
        if (instance == null)
        {
            instance = new Nav6MXP();
        }
        return instance;
    }
	
	private Nav6MXP()
	{
		
	}
	@Override
	protected void initialize() {
		serial_port = new SerialPort(57600,SerialPort.Port.kMXP);

		byte update_rate_hz = 127;
		imu = new IMU(serial_port,update_rate_hz);
        Timer.delay(0.3);
        imu.zeroYaw();
	

	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "Nav6MXP";
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public double getAngle()
	{
		return (double)imu.getYaw();
	}
	public IMU getMXP()
	{
		return imu;
	}

}
