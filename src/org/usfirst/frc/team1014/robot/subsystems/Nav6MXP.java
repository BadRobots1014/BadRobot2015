package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.subsystems.IMU.IMU;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

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
	public double getAngle()// return -180 - 180
	{
		return (double)imu.getYaw();
	}
	public double getAngle360() // returns 0 -360
	{
		if(imu.getYaw() < 0)
			return imu.getYaw() + 360;
		else
			return imu.getYaw();
	}
	public IMU getMXP()
	{
		return imu;
	}
	public void resetGyro()
	{
		imu.zeroYaw();
	}

}
