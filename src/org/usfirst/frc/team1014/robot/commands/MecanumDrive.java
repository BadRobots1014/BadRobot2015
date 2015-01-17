package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDrive extends CommandBase {

	public static int soCounter;
	
	public MecanumDrive()
	{
		requires((Subsystem) driveTrain);
		soCounter = 0;
	}
	@Override
	protected void initialize() {
		driveTrain.getGyro().reset();
		driveTrain.tankDrive(0, 0);
	}

	@Override
	public String getConsoleIdentity() {
		return "MecanumDrive";
	}

	@Override
	protected void execute() {
		driveTrain.mecanumDrive(-OI.xboxController.getLeftStickX(), -OI.xboxController.getRightStickX(), -OI.xboxController.getLeftStickY(), driveTrain.getGyro().getAngle());
		so(driveTrain.getGyro().getAngle());
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
	public static void so(Object so)
	{
		if(soCounter > 50)
		{
			System.out.println("MecanumDrive: " + so);
			soCounter = 0;
		}
		soCounter++;
	}

}
