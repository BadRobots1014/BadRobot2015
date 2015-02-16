package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AutoStrafe extends CommandBase {

	public double driveTime, speed;
	public double startTime, passedTime;
	
	public AutoStrafe(double driveTime, double speed)
	{
		requires((Subsystem) driveTrain);
		this.driveTime = driveTime;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
		driveTrain.resetGyro();// makes start angle zero
		startTime = Utility.getFPGATime();
		passedTime = 0;		
	}

	@Override
	public String getConsoleIdentity() {
		return "AutoStrafe";
	}

	@Override
	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime;
		driveTrain.mecanumDrive(speed, 0, deadzone(rotation()));
	}

	@Override
	protected boolean isFinished() {
		if((passedTime / 1000000) > driveTime)
		{
			System.out.println("AutoStrafe is done");
			return true;
		}
		else
			return false;
	}

	@Override
	protected void end() {
		driveTrain.tankDrive(0, 0);
		
	}

	@Override
	protected void interrupted() {
		
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
		return -(driveTrain.getAngle()/45);
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

}
