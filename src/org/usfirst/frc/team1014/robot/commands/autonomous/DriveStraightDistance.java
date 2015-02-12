package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveStraightDistance extends CommandBase {

	public double stopDistance, speed;
	public double startTime, passedTime;
	
	public DriveStraightDistance(double distanceToStopAt, double speed)
	{
		requires((Subsystem) driveTrain);
		requires((Subsystem) mxp);
		stopDistance = distanceToStopAt;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
		mxp.resetGyro();// makes start angle zero
		startTime = Utility.getFPGATime();
		passedTime = 0;		
	}

	@Override
	public String getConsoleIdentity() {
		return "DriveStraightDistance";
	}

	@Override
	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime;
		driveTrain.mecanumDriveCartesian(0, -speed, deadzone(rotation()), 0.0);
	}

	@Override
	protected boolean isFinished() {
		if(getSmallestLidarDistance() < stopDistance)
		{
			System.out.println(getConsoleIdentity() + " is done");
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

    public double getSmallestLidarDistance()
    {
    	if(driveTrain.getLidarLeft() < driveTrain.getLidarRight())
    		return driveTrain.getLidarLeft();
    	else
    		return driveTrain.getLidarRight();
    }
}
