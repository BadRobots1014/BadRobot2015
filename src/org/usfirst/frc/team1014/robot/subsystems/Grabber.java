package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.commands.Grab;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Grabber extends BadSubsystem {
	
	public static Grabber instance;
	
	SpeedController lift1;// lift2, lift3;
	DigitalInput retroSensor;//true means no retro 
	public int levelCount;
	public boolean onRetro;
	public static final int MAX_NUMBER_OF_LEVELS = 7;
	
	public static Grabber getInstance()
	{
		
        if (instance == null)
        {
            instance = new Grabber(0);
        }
        return instance;
	}
	
	public Grabber(int startLevel)
	{
		levelCount = startLevel;
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
		this.setDefaultCommand(new Grab(0)); 
		
	}
	
	public void lift(double l)
	{
		lift1.set(l);
		/*lift2.set(l);
		lift3.set(l);*/
	}

	public boolean isRetro(boolean goingUp)
	{
		if(!onRetro)
		{
			if(!retroSensor.get())
			{
				if(goingUp)
					levelCount += 1;
				else
					levelCount -= 1;
				onRetro = true;
				return true;
			}
		}
		else//you're on the tape
		{
			if(retroSensor.get())
			{
				onRetro = false;
				return false;
			}		
		}
		return false;
	}
	
}
