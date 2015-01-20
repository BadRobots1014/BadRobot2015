package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDriveField extends CommandBase {

	public static int soCounter;
	
	public MecanumDriveField()
	{
		requires((Subsystem) driveTrain);
		requires((Subsystem) mxp);
		soCounter = 0;
	}
	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
		mxp.getMXP().zeroYaw();
	}

	@Override
	public String getConsoleIdentity() {
		return "MecanumDrive";
	}

	@Override
	protected void execute() {
		driveTrain.mecanumDriveCartesian(OI.xboxController.getLeftStickX(), OI.xboxController.getLeftStickY(), OI.xboxController.getRightStickX(), mxp.getAngle());
		so(mxp.getAngle());
		//driveTrain.mecanumDriveCartesian(OI.xboxController.getLeftStickY(), -OI.xboxController.getLeftStickX(), OI.xboxController.getRightStickX(), mxp.getAngle());
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
