package org.usfirst.frc.team1014.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Grabber extends BadSubsystem {
	
	public static Grabber instance;
	
	SpeedController lift;
	
	
	public static Grabber getInstance()
	{
		
        if (instance == null)
        {
            instance = new Grabber();
        }
        return instance;
	}
	
	public Grabber()
	{
		
	}
	@Override
	protected void initialize() {
		lift = new Talon(4);
		lift.set(0);
		
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "Grabber";
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void lift(double l)
	{
		lift.set(l);
	}

}
