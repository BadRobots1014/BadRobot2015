package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GrabBin extends CommandBase {
	
	public GrabBin()
	{
		requires((Subsystem) winchLift);
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		winchLift.lift(0);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		// THIS IS WRONG, THIS WILL NOT WORK
		// SOMEONE FIX THIS EVENTUALLY
		winchLift.lift(OI.xboxController.getRightStickY());
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		winchLift.lift(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		System.out.println("I am deferring");
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "GrabBin";
	}

}
