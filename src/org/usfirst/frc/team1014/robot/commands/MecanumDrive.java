package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDrive extends CommandBase {

	boolean holdRB, holdSelect, holdX;
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
			driveTrain.resetGyro();
		}
		
		/*if(OI.priXboxController.isXButtonPressed())
		{
			if(targetAngle == -1)
				targetAngle = driveTrain.getAngle360();
				
			if(driveTrain.getUltraMM() > 135)
				driveTrain.mecanumDrive(0.0, .5, (rotation(targetAngle)));
		}*/
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
		if (holdSelect && !OI.priXboxController.isSelectButtonPressed()) {
			holdSelect = false;
			driveTrain.toggleDriveMode();
			if(driveTrain.fieldOrientated)
				System.out.println("Now Field Orientated");
			else
				System.out.println("Now Tank Drive");
		}
		
		if(OI.priXboxController.isAButtonPressed())
		{
			if(targetAngle == -1)
				targetAngle = driveTrain.getAngle360();
			
			driveTrain.mecanumDrive(OI.priXboxController.getLeftStickX(), OI.priXboxController.getLeftStickY(), rotation(targetAngle));
		} 
		else if(!driveTrain.fieldOrientated && (OI.priXboxController.isRBButtonPressed() || OI.priXboxController.isLBButtonPressed()))//in tank mode and you are pressing one of the bumpers
		{
			if(targetAngle == -1)
				targetAngle = driveTrain.getAngle360();
			if(OI.priXboxController.isRBButtonPressed())//I know this is bad but I don't wanna change the targetAngle logic
				driveTrain.mecanumDrive(-rightTriggerAdjustedSpeed(-0.75), 0.0, rotation(targetAngle));
			else//the LB button is pressed
				driveTrain.mecanumDrive(rightTriggerAdjustedSpeed(0.75), 0.0, rotation(targetAngle));
		}	
		else
		{
			targetAngle = -1;
			if(OI.priXboxController.getPOV() == -1) //Not using dpad and not holding A
			{
				if(driveTrain.fieldOrientated)
					driveTrain.mecanumDriveAntiTip(rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickX()), rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickY()), rightTriggerAdjustedSpeed(OI.priXboxController.getRightStickX())*.75);
				else
					driveTrain.tankDrive(-rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickY()), rightTriggerAdjustedSpeed(OI.priXboxController.getRightStickY()));//driveTrain.mecanumDriveBot(rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickX()), rightTriggerAdjustedSpeed(OI.priXboxController.getLeftStickY()), rightTriggerAdjustedSpeed(OI.priXboxController.getRightStickX()));
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
		if(OI.priXboxController.getRightTrigger() > .5)
			return stick/2;//return ((1-OI.priXboxController.getRightTrigger()) + .1)*stick;
		else
			return stick;
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
