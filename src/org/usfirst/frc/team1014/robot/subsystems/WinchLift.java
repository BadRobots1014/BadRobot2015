package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.commands.GrabBin;

import edu.wpi.first.wpilibj.Talon;

public class WinchLift extends BadSubsystem {

	public static WinchLift instance;
	
	Talon winchLift;
	
	public static WinchLift getInstance()
	{
		if(instance == null)
		{
			instance = new WinchLift();
		}
		return instance;
	}
	
	public WinchLift() {}
	
	protected void initialize()
	{
		winchLift = new Talon(RobotMap.liftWinch);
		winchLift.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		this.setDefaultCommand(new GrabBin());
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "WinchLift";
	}

	
	
	public void lift(double speed)
	{
		winchLift.set(speed);
	}
}
