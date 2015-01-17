package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DpadDrive extends CommandBase {
	
	public DpadDrive()
	{
		requires((Subsystem) driveTrain); // This line causes the error
	}
	
	@Override
	protected void initialize() 
	{
		driveTrain.tankDrive(0,0);
	}

	public String getConsoleIdentity() 
	{
		return "DpadDrive";
	}

	@Override
	protected void execute() 
	{
		driveTrain.dpadDrive(OI.xboxController.getLeftStickX(), OI.xboxController.getLeftStickY(), OI.xboxController.getRightStickX());
	}

	@Override
	protected boolean isFinished() 
	{
		return false;
	}

	@Override
	protected void end() {
		driveTrain.tankDrive(0,0);	
	}

	@Override
	protected void interrupted() {
		
	}
}
