package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDriveStayStraight extends CommandBase {

	public static int soCounter;
	
	public MecanumDriveStayStraight()
	{
		requires((Subsystem) driveTrain);
		requires((Subsystem) mxp);
	}
	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
		mxp.getMXP().zeroYaw();
		driveTrain.setInitalGyro((double)mxp.getMXP().getPitch(), (double)mxp.getMXP().getRoll());
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
		if(OI.xboxController.isRBButtonPressed())
		{
			driveTrain.toggleSpeed();
		}
		if(driveTrain.isSafeToDrive((double)mxp.getMXP().getPitch(), (double)mxp.getMXP().getRoll()))
		{
			if(OI.xboxController.getPOV() == -1 && !OI.xboxController.isAButtonPressed()) // not using dpad
			{
				if(driveTrain.speedHigh)
					driveTrain.mecanumDriveCartesian(OI.xboxController.getLeftStickX(), OI.xboxController.getLeftStickY(), OI.xboxController.getRightStickX(), mxp.getAngle()); // just do mecanum
				else
					driveTrain.mecanumDriveCartesian(OI.xboxController.getLeftStickX() / 2, OI.xboxController.getLeftStickY() / 2, OI.xboxController.getRightStickX(), mxp.getAngle());
			}
			else if(OI.xboxController.getPOV() != -1)// use dpad
			{
				driveTrain.lineUpWithField(OI.xboxController.getPOV(), mxp.getAngle());
			}
			else
			{
				if(driveTrain.speedHigh)
					driveTrain.mecanumDriveCartesian(OI.xboxController.getLeftStickX(), OI.xboxController.getLeftStickY(), deadzone(rotation()), mxp.getAngle());
				else
					driveTrain.mecanumDriveCartesian(OI.xboxController.getLeftStickX() / 2, OI.xboxController.getLeftStickY() / 2, deadzone(rotation()), mxp.getAngle());
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

	/**
	 * This method returns an adjusted value to give the motors to keep the robot straight
	 * For days
	 * 
	 * Assumes you are using it with tankDrive with positive angleDifference
	 * returns 2 for zero degree turn and 0 for straight driving
	 * 
	 * @return
	 */
	
	public static double rotation()
	{
		return -(mxp.getAngle()/45);
	}
	
    public static double deadzone(double d) {
        //whenever the controller moves LESS than the magic number, the 
        //joystick is in the loose position so return zero - as if the 
        //joystick was not moved
        if (Math.abs(d) < .1) {
            return 0;
        }
        
        if (d == 0)
        {
            return 0;
        }
        //When the joystick is used for a purpose (passes the if statements, 
        //hence not just being loose), do math
        return (d / Math.abs(d)) //gets the sign of d, negative or positive
            * ((Math.abs(d) - .1) / (1 - .1)); //scales it
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
