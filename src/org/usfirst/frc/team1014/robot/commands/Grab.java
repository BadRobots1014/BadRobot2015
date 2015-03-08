package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grab extends CommandBase {

	public boolean holdY, holdA, isDown, isUp;
	
	public Grab()
	{
		requires((Subsystem) grabber);
		if(grabber.isRetro())
			isDown = true;
	}
	
	@Override
	protected void initialize() {
		grabber.lift(0);
		if(grabber.isRetro())
			isDown = true;
		
	}

	@Override
	public String getConsoleIdentity() {
		return "Grab";
	}

	@Override
	protected void execute() 
	{
		if (OI.secXboxController.isYButtonPressed()) {
			holdY = true;
		}
		if (holdY && !OI.secXboxController.isYButtonPressed()) {
			holdY = false;
			raiseToTape();
		}
		if (OI.secXboxController.isAButtonPressed()) {
			holdA = true;
		}
		if (holdA && !OI.secXboxController.isAButtonPressed()) {
			holdA = false;
			lowerToTape();
		}
		
		
		//Checks for the y button pressed to run the grabber to the next level of tape//grabber.lift(-OI.secXboxController.getLeftStickY());
		if(isUp && OI.secXboxController.getLeftStickY() > 0)
			grabber.lift(-OI.secXboxController.getLeftStickY());
		else if(isDown && OI.secXboxController.getLeftStickY() < 0)
			grabber.lift(-OI.secXboxController.getLeftStickY());
		else if((!isDown && !isUp))
		{
			grabber.lift(-OI.secXboxController.getLeftStickY());
			if(grabber.isRetro() && OI.secXboxController.getLeftStickY() < 0)
			{
				grabber.lift(0);
				isUp = true;
				isDown = false;
			}
			if(grabber.isRetro() && OI.secXboxController.getLeftStickY() > 0)
			{
				grabber.lift(0);
				isUp = false;
				isDown = true;
			}
		}
		else
		{
			if(!grabber.isRetro())
			{
				isUp = false;
				isDown = false;
			}
			grabber.lift(0);
		}
			
	}

	public void raiseToTape()//negative is down
	{
		//if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			//return;
		double liftSpeed = .25; //startspeed
		System.out.println("In raiseToTape");
		while(grabber.isRetro() && OI.secXboxController.getLeftStickY() == 0 && !isUp)
		{
			grabber.lift(liftSpeed);
			liftSpeed += .01;
		}
		while(!grabber.isRetro() && OI.secXboxController.getLeftStickY() == 0 && !isUp)
		{
			grabber.lift(liftSpeed);
			liftSpeed += .01;
		}
		if(grabber.isRetro())
			isUp = true;

	}
	
	public void lowerToTape()//negative is down
	{
		//if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			//return;
		double liftSpeed = -.25; //startspeed
		while(grabber.isRetro() && OI.secXboxController.getLeftStickY() == 0 && !isDown)
		{
			grabber.lift(liftSpeed);
			liftSpeed -= .01;
		}
		while(!grabber.isRetro() && OI.secXboxController.getLeftStickY() == 0 && !isDown)
		{
			grabber.lift(liftSpeed);
			liftSpeed -= .01;
		}
		if(grabber.isRetro())
			isDown = true;
	}
	
	public boolean isSafeToLiftUp()
	{
		if(grabber.getLevelCount() >= grabber.MAX_NUMBER_OF_LEVELS)
			return false;
		return true;
	}
	public boolean isSafeToLiftDown()
	{
		if(grabber.getLevelCount() <= 0)
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
