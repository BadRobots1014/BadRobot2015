package org.usfirst.frc.team1014.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Optical extends BadSubsystem{

	public static Optical instance;
	public static DigitalInput ping,echo;
	
	private Optical()
	{
		
	}
	
    public static Optical getInstance()
    {
        if (instance == null)
        {
            instance = new Optical();
        }
        return instance;
    }
	@Override
	protected void initialize() {
		ping = new DigitalInput(1);
		echo = new DigitalInput(0);
	}

	@Override
	public String getConsoleIdentity() {
		return "OpticalSensor";
	}

	@Override
	protected void initDefaultCommand() {
		
		
	}
	
	public void ping()
	{
		System.out.println("Ping: " + ping.get());
	}
	public void echo()
	{
		System.out.println("Echo: " + echo.get());
	}

}
