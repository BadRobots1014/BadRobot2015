package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grab extends CommandBase {

	public boolean holdY;
	public boolean onRetro;
	
	public Grab(int startLevel)
	{
		grabber.levelCount = startLevel;
		requires((Subsystem) grabber);
		onRetro = false;
	}
	
	@Override
	protected void initialize() {
		grabber.lift(0);
		
	}

	@Override
	public String getConsoleIdentity() {
		return "Grab";
	}

	@Override
	protected void execute() 
	{
		grabber.lift(-OI.secXboxController.getLeftStickY());
	}

	public void raiseToTape()//negative is up
	{
		//if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			//return;
		double liftSpeed = .25; //startspeed
		
		while(1==1)
		{
			if(OI.secXboxController.getLeftStickY() == 0)
			{
				if(!grabber.isRetro(true))
				{
					grabber.lift(liftSpeed);
					liftSpeed += .001;
				}
				else
				{
					return;
				}
			}
			else
				return;
		}

	}
	
	public void lowerToTape()//negative is not up
	{
		//if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			//return;
		double liftSpeed = -.25; //startspeed
		while(1==1)
		{
			if(OI.secXboxController.getLeftStickY() == 0)
			{
				if(!grabber.isRetro(false))
				{
					grabber.lift(liftSpeed);
					liftSpeed += -.001;
				}
				else
				{
					return;
				}
			}
			else
				return;
		}

	}
	
	public boolean isSafeToLiftUp()
	{
		if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			return false;
		return true;
	}
	public boolean isSafeToLiftDown()
	{
		if(grabber.levelCount <= 0)
			return false;
		return true;
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
