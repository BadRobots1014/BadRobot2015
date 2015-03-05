package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDrive extends CommandBase {

	boolean holdRB, holdSelect;
	double targetAngle;
	
	public MecanumDrive()
	{
		requires((Subsystem) driveTrain);
	}
	@Override
	protected void initialize() {
		holdRB = false;
		holdSelect = false;
		targetAngle = -1;
		//driveTrain.setAutoMode(true);
	}

	@Override
	public String getConsoleIdentity() {
		return "MecanumDrive";
	}

	@Override
	protected void execute() {
		
		if(OI.priXboxController.isBButtonPressed())// this line works
		{
			driveTrain.resetGyro();;
		}
		
		/*// RB toggle code
		if (OI.priXboxController.isRBButtonPressed()) {
			holdRB = true;
		}
		if (holdRB && !OI.priXboxController.isRBButtonPressed()) {
			holdRB = false;
			driveTrain.toggleDriveSpeed();
			if(driveTrain.speedHigh)
				System.out.println("High Speed");
			else
				System.out.println("Trigger Adjusted low speed");
				
		}*/
		
		// Select toggle code
		if (OI.priXboxController.isSelectButtonPressed()) {
			holdSelect = true;
		}
		if (holdRB && !OI.priXboxController.isSelectButtonPressed()) {
			holdRB = false;
			driveTrain.toggleDriveMode();
			if(driveTrain.fieldOrientated)
				System.out.println("Now Field Orientated");
			else
				System.out.println("Now Bot Orientated");
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
			
			if(OI.priXboxController.getPOV() == -1) //Not using dpad and not holding A
			{
				if(driveTrain.fieldOrientated)
					driveTrain.mecanumDriveAntiTip(rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickX()), rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickY()), rightTriggerAdjustedSpeed(OI.priXboxController.getRightStickX()));
				else
					driveTrain.mecanumDriveBot(rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickX()), rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickY()), rightTriggerAdjustedSpeed(OI.priXboxController.getRightStickX()));
			}
		}
		
		// Select toggle code
		/*if (OI.priXboxController.isSelectButtonPressed()) {
			holdSelect = true;
			System.out.println("Select being held");
		}
		if (holdSelect && !OI.priXboxController.isSelectButtonPressed()) {
			holdSelect = false;
			driveTrain.toggleDriveMode();
			if(driveTrain.fieldOrientated)
				System.out.println("Now Mecanum");
			else
				System.out.println("Now Tank Drive");
		}
		
		if(driveTrain.fieldOrientated)
			driveTrain.mecanumDriveBot(rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickX()), rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickY()), rightTriggerAdjustedSpeed(OI.priXboxController.getRightStickX()));
		else
			driveTrain.tankDrive(-rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickY()), rightTriggerAdjustedSpeed(OI.priXboxController.getRightStickY()));
				  */
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
	
	public static double rightTriggerAdjustedSpeed(double stick)
	{
		if(stick != 0)
			return ((1-OI.priXboxController.getRightTrigger()) + .1)*stick;
		else
			return 0.0;
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
