package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SafeMecanumDriveField extends CommandBase {

	public static int soCounter;
	
	public SafeMecanumDriveField()
	{
		requires((Subsystem) driveTrain);
	}
	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
		driveTrain.getMXP().zeroYaw();
		driveTrain.setInitalGyro((double)driveTrain.getMXP().getPitch(), (double)driveTrain.getMXP().getRoll());
	}

	@Override
	public String getConsoleIdentity() {
		return "MecanumDrive";
	}

	@Override
	protected void execute() {
		
		if(OI.priXboxController.isBButtonPressed())// this line works
		{
			driveTrain.resetGyro();
		}
		if(driveTrain.isSafeToDrive((double)driveTrain.getMXP().getPitch(), (double)driveTrain.getMXP().getRoll()))
		{
			if(OI.priXboxController.getPOV() == -1) // not using dpad
			{
				driveTrain.mecanumDrive(OI.priXboxController.getLeftStickX(), OI.priXboxController.getLeftStickY(), OI.priXboxController.getRightStickX()); // just do mecanum
			}
			else // use dpad
			{
				driveTrain.rotateToAngle((double) OI.priXboxController.getPOV());
			}

		}		  
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
		so("I Have been interrupted and am deferring");
		
	}
	public static void soc(Object so)
	{
		if(soCounter > 50)
		{
			System.out.println("MecanumDrive: " + so);
			soCounter = 0;
		}
		soCounter++;
	}
	public static void so(Object so)
	{
		System.out.println("MecanumDrive: " + so);
	}

}
