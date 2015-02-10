package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.commands.Grab;
import org.usfirst.frc.team1014.robot.commands.SafeMecanumDriveField;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Grabber extends BadSubsystem {
	
	public static Grabber instance;
	
	SpeedController lift1, lift2, lift3;
	
	
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
		lift1 = new Talon(RobotMap.lift1);//create Grabber
		lift1.set(0);
		lift2 = new Talon(RobotMap.lift2);
		lift2.set(0);
		lift3 = new Talon(RobotMap.lift3);
		lift3.set(0);
		
	}

	@Override
	public String getConsoleIdentity() {
		return "Grabber";
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new Grab()); 
		
	}
	
	public void lift(double l)
	{
		lift1.set(l);
		lift2.set(l);
		lift3.set(l);
	}

}
