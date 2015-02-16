package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grab extends CommandBase {

	public boolean holdY;
	
	public Grab(int startLevel)
	{
		grabber.levelCount = startLevel;
		requires((Subsystem) grabber);
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
	protected void execute() {
		// RB toggle code
		if (OI.secXboxController.getRawButton(OI.secXboxController.Y_BUTTON)) {
			holdY = true;
		}
		if (holdY && OI.secXboxController.getRawButton(OI.secXboxController.Y_BUTTON)) {
			holdY = false;
			raiseToTape();
		}

		if (OI.secXboxController.getRawButton(OI.secXboxController.A_BUTTON)) {
			holdY = true;
		}
		if (holdY && OI.secXboxController.getRawButton(OI.secXboxController.A_BUTTON)) {
			holdY = false;
			lowerToTape();
		}
		grabber.lift(-OI.secXboxController.getLeftStickY());
			
		
		
		
	}

	public void raiseToTape()//negative is up
	{
		if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			return;
		long startTime = Utility.getFPGATime();
		long passedTime = 0;
		double liftSpeed = .25; //startspeed
		
		while(1==1)
		{
			passedTime = Utility.getFPGATime() - startTime;
			if(OI.secXboxController.getLeftStickY() == 0)
			{
				if(passedTime/1000000 < 0.75)
				{
					grabber.lift(liftSpeed);
				}
				else if(!grabber.isRetro())
				{
					grabber.lift(liftSpeed);
					liftSpeed += .001;
				}
				else
				{
					grabber.levelCount += 1;
					return;
				}
			}
			else
				return;
		}

	}
	
	public void lowerToTape()//negative is not up
	{
		if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			return;
		double liftSpeed = -.25; //startspeed
		long startTime = Utility.getFPGATime();
		long passedTime = 0;
		while(1==1)
		{
			if(OI.secXboxController.getLeftStickY() == 0)
			{
				if(passedTime/1000000 < 0.75)
				{
					grabber.lift(liftSpeed);
				}
				if(!grabber.isRetro())
				{
					grabber.lift(liftSpeed);
					liftSpeed += -.001;
				}
				else
				{
					grabber.levelCount -= 1;
					return;
				}
			}
			else
				return;
		}

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
