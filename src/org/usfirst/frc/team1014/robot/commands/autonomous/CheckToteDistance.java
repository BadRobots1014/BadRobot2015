package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CheckToteDistance extends CommandBase {

	public static double PERFECT_DISTANCE_FOR_TOTES = 10;
	
	public CheckToteDistance()
	{
		requires((Subsystem) driveTrain);
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		driveTrain.tankDrive(0, 0);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		driveTrain.tankDrive(.1, .1);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		driveTrain.tankDrive(0, 0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		System.out.println("I've been interrupted!");
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(driveTrain.getLidarLeft() <= PERFECT_DISTANCE_FOR_TOTES && atSameDistance())
		{
			return true;
		}
		return false;
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "CheckToteDistance";
	}
	
	public boolean atSameDistance()
	{
		return driveTrain.getLidarLeft() == driveTrain.getLidarRight();
	}

}
