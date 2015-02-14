package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.subsystems.Optical;

import edu.wpi.first.wpilibj.command.Subsystem;

public class OpticalSensing extends CommandBase {

	public OpticalSensing()
	{
		requires((Subsystem) os);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "OpticalSensing";
	}

	@Override
	protected void execute() {
		os.ping();
		os.echo();
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
