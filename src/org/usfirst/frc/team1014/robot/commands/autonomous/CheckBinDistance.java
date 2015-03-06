package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CheckBinDistance extends CommandBase {

	//public static double PERFECT_DISTANCE_FOR_TOTES_MM = 110;
	public static double PERFECT_DISTANCE_FOR_TOTES_IN = 2;
	
	public CheckBinDistance()
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
		driveTrain.tankDrive(-.5 ,.5);
		System.out.println(driveTrain.getLidarDistanceInches());
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
		if(driveTrain.getLidarDistanceInches() <= PERFECT_DISTANCE_FOR_TOTES_IN)
		{
			return true;
		}
		System.out.println(driveTrain.getLidarDistanceInches());
		return false;
		
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "CheckToteDistance";
	}
	

}
