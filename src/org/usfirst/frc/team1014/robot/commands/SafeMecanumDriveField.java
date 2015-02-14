package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SafeMecanumDriveField extends CommandBase {

	public static int soCounter;
	public long time;
	
	public SafeMecanumDriveField()
	{
		requires((Subsystem) driveTrain);
		requires((Subsystem) mxp);
	}
	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
		mxp.getMXP().zeroYaw();
		driveTrain.setInitalGyro((double)mxp.getMXP().getPitch(), (double)mxp.getMXP().getRoll());
		time = System.currentTimeMillis();
	}

	@Override
	public String getConsoleIdentity() {
		return "MecanumDrive";
	}

	@Override
	protected void execute() {
		
		if(OI.xboxController.isBButtonPressed())// this line works
		{
			mxp.resetGyro();
		}
		if(driveTrain.isSafeToDrive((double)mxp.getMXP().getPitch(), (double)mxp.getMXP().getRoll()))
		{
			if(OI.xboxController.getPOV() == -1) // not using dpad
			{
				driveTrain.mecanumDriveCartesian(OI.xboxController.getLeftStickX(), OI.xboxController.getLeftStickY(), OI.xboxController.getRightStickX(), mxp.getAngle()); // just do mecanum
			}
			else // use dpad
			{
				driveTrain.lineUpWithField(OI.xboxController.getPOV(), mxp.getAngle());
			}

		}
		if(OI.xboxController.isAButtonPressed()) {
			if(System.currentTimeMillis() - time >= 1000) {
				so(driveTrain.getLidarDistance());
				time = System.currentTimeMillis();
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
