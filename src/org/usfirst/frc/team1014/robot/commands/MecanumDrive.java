package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDrive extends CommandBase {

	boolean holdRB;
	double targetAngle;
	
	public MecanumDrive()
	{
		requires((Subsystem) driveTrain);
	}
	@Override
	protected void initialize() {
		holdRB = false;
		targetAngle = -1;
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
		
		// RB toggle code
		if (OI.priXboxController.isRBButtonPressed()) {
			holdRB = true;
		}
		if (holdRB && !OI.priXboxController.isRBButtonPressed()) {
			holdRB = false;
			driveTrain.toggleSpeed();
		}
		
		if(OI.priXboxController.isAButtonPressed())
		{
			if(targetAngle == -1)
				targetAngle = driveTrain.getAngle360();
			
			driveTrain.mecanumDrive(OI.priXboxController.getLeftStickX(), OI.priXboxController.getLeftStickY(), rotation(targetAngle));
		} 
		else
		{
			targetAngle = -1;
			driveTrain.mecanumDriveAntiTip(OI.priXboxController.getLeftStickX(), OI.priXboxController.getLeftStickY(), OI.priXboxController.getRightStickX());
//			if(OI.priXboxController.getPOV() == -1 && !OI.priXboxController.isAButtonPressed()) //Not using dpad and not holding A
//			{
//				if(driveTrain.speedHigh)//normal drive
//					driveTrain.mecanumDriveCartesian(OI.priXboxController.getLeftStickX(), OI.priXboxController.getLeftStickY(), OI.priXboxController.getRightStickX(), mxp.getAngle()); // just do mecanum	
//				else
//					driveTrain.mecanumDriveCartesian(OI.priXboxController.getLeftStickX() / 2, OI.priXboxController.getLeftStickY() / 2, OI.priXboxController.getRightStickX() / 2, mxp.getAngle());
//			}
//			else if(OI.priXboxController.getPOV() != -1 && !OI.priXboxController.isAButtonPressed())//using dpad
//			{
//				driveTrain.lineUpWithField(OI.priXboxController.getPOV(), mxp.getAngle());
//			}
//			else//a is held down and you are not using dpad = driving with a button down
//			{
//				if(driveTrain.speedHigh)
//					driveTrain.mecanumDriveCartesian(OI.priXboxController.getLeftStickX(), OI.priXboxController.getLeftStickY(), deadzone(rotation()), mxp.getAngle());
//				else
//					driveTrain.mecanumDriveCartesian(OI.priXboxController.getLeftStickX() / 2, OI.priXboxController.getLeftStickY() / 2, deadzone(rotation()), mxp.getAngle());
//			}

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

	/**
	 * This method returns an adjusted value to give the motors to keep the robot straight
	 * For days
	 * 
	 * Assumes you are using it with tankDrive with positive angleDifference
	 * returns 2 for zero degree turn and 0 for straight driving
	 * 
	 * @return
	 */
	
	public static double rotation(double target)
	{
		double rotateSpeed = driveTrain.clampMotorValues((target - driveTrain.getAngle360())/45);
		if(Math.abs(rotateSpeed) < .05) // .05 = 2.25 degrees off
			rotateSpeed = 0;
		
		return rotateSpeed;
	}
	
	@Override
	protected void interrupted() {
		so("I Have been interrupted and am deferring");
		
	}

	public static void so(Object so)
	{
		System.out.println("MecanumDrive: " + so);
	}

}
