package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TankDrive extends CommandBase{
	
	public TankDrive()
    {
        requires((Subsystem) tankDrive); // This line causes the error
    }

	@Override
	protected void initialize() 
	{
		tankDrive.tankDrive(0, 0);
	}

	public String getConsoleIdentity() 
	{
		return "TankDrive";
	}

	protected void execute() 
	{
		tankDrive.tankDrive(-OI.xboxController.getLeftStickY(), -OI.xboxController.getRightStickY());
		so(tankDrive.getBackUltrasonicDistanceMM());
	}

	@Override
	protected boolean isFinished() 
	{
		return false;
	}

	@Override
	protected void end() 
	{
		driveTrain.tankDrive(0, 0);
	}

	@Override
	protected void interrupted() 
	{

	}
	
//	public static void soc(Object so)
//	{
//		if(soCounter > 50)
//		{
//			System.out.println("MecanumDrive: " + so);
//			soCounter = 0;
//		}
//		soCounter++;
//	}
	
	public static void so(Object so)
	{
		System.out.println("TankDrive: " + so);
	}

}
