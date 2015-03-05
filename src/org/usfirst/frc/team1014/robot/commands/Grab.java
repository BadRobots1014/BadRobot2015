package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grab extends CommandBase {

	public boolean holdY, holdA;
	public boolean onRetro;
	
	public Grab()
	{
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
		if (OI.priXboxController.isYButtonPressed()) {
			holdY = true;
		}
		if (holdY && !OI.priXboxController.isYButtonPressed()) {
			holdY = false;
			driveTrain.toggleDriveMode();
			raiseToTape();
		}
		if (OI.priXboxController.isAButtonPressed()) {
			holdA = true;
		}
		if (holdA && !OI.priXboxController.isAButtonPressed()) {
			holdA = false;
			driveTrain.toggleDriveMode();
			lowerToTape();
		}
		grabber.lift(-OI.secXboxController.getLeftStickY());
	}

	public void raiseToTape()//negative is down
	{
		//if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			//return;
		double liftSpeed = .25; //startspeed
		
		while(!grabber.isRetro(true) && OI.secXboxController.getLeftStickY() == 0)
		{
			grabber.lift(liftSpeed);
			liftSpeed += 01;
		}

	}
	
	public void lowerToTape()//negative is down
	{
		//if(grabber.levelCount >= grabber.MAX_NUMBER_OF_LEVELS)
			//return;
		double liftSpeed = -.25; //startspeed
		while(!grabber.isRetro(false) && OI.secXboxController.getLeftStickY() == 0)
		{
			grabber.lift(liftSpeed);
			liftSpeed -= 01;
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
