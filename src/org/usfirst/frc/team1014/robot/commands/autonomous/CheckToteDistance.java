package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CheckToteDistance extends CommandBase {

	public static double PERFECT_DISTANCE_FOR_TOTES_MM = 100;
	public static double PERFECT_DISTANCE_FOR_TOTES_IN = 4.2;//this is not right
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
		driveTrain.tankDrive(.5 ,-.5);
		System.out.println(driveTrain.getUltraMM());
		System.out.println(driveTrain.getUltraIN());
		System.out.println(driveTrain.getRangeValid() + "\n");
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
		if(driveTrain.getUltraMM() <= PERFECT_DISTANCE_FOR_TOTES_MM)
		{
			return true;
		}
		System.out.println(driveTrain.getUltraMM());
		return false;
		
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "CheckToteDistance";
	}
	

}
