package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;

public class OpticalSensor extends BadSubsystem
{
	
	private DigitalInput input;
	private static OpticalSensor instance;
	
	public static OpticalSensor getInstance() {
		if(instance == null) instance = new  OpticalSensor();
		return instance;
	}
	
	public OpticalSensor()
	{
		initialize();
	}
	@Override
	protected void initialize()
	{
		input = new DigitalInput(RobotMap.opticalSensor1);
	}
	
	public boolean getInput() {
		return input.get();
	}
	
	@Override
	public String getConsoleIdentity()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log()
	{
		// TODO Auto-generated method stub
		
	}

}
