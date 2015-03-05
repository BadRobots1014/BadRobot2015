package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.commands.Grab;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Grabber extends BadSubsystem {
	
	public static Grabber instance;
	
	SpeedController lift1;// lift2, lift3;
	DigitalInput retroSensor;//true means no retro 
	private int levelCount;
	public int getLevelCount() {
		return levelCount;
	}

	public boolean onRetro;
	public static final int MAX_NUMBER_OF_LEVELS = 7;
	
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
		levelCount = 0;
	}
	@Override
	protected void initialize() {
		lift1 = new Talon(RobotMap.lift1);//create Grabber
		lift1.set(0);
		/*lift2 = new Talon(RobotMap.lift2);
		lift2.set(0);
		lift3 = new Talon(RobotMap.lift3);
		lift3.set(0);*/
		retroSensor = new DigitalInput(RobotMap.retroSensor);
		
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
		/*lift2.set(l);
		lift3.set(l);*/
	}
	
	/**
	 * This method takes care of the level count change and returns if you are 
	 * on retro tape
	 * 
	 * @param goingUp
	 * @return
	 */
	
	public boolean isRetro(boolean goingUp)
	{
		System.out.println("Checking Retro");
		if(onRetro)
			return true;
		else
		{
			if(!retroSensor.get())//you are on the tape
			{
				onRetro = true;
				if(goingUp)
					levelCount++;
				else
					levelCount--;
				return true;
			}
		return false;
		}

	}
	
	public boolean setGrabberLevel(int level)
	{
		if(level > MAX_NUMBER_OF_LEVELS || level < 0)
			return false;
		levelCount = level;
		return true;
	}
	
}
