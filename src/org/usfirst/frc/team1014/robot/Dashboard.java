package org.usfirst.frc.team1014.robot;

import org.usfirst.frc.team1014.robot.commands.autonomous.PullTotesFromLandFill;
import org.usfirst.frc.team1014.robot.commands.autonomous.ScoreBin;
import org.usfirst.frc.team1014.robot.commands.autonomous.ScoreBinAndTote;
import org.usfirst.frc.team1014.robot.commands.autonomous.ScoreToteFromHigh;
import org.usfirst.frc.team1014.robot.commands.autonomous.ScoreToteFromLow;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * This class outlines the dashboard used for the robot.
 * 
 * The SFX SmartDashboard can be used as an external program that can talk live to the robot. It can be used to pick auton programs,
 * live camera feeds, output feedback.
 * 
 * Use in 2015 was key but iffy.  To be used, the robot had to be turned on and the computer connected into the field ethernet, THEN 
 * the driver station had to be opened, you had to wait for the SFX dashboard to open then manually add the camera, change the IP it
 * was looking for then hit the okay button in the bottom right hand corner then pick the boolean you want for auton.
 * 
 * @author Subash
 *
 */

public class Dashboard 
{
	/**
	 * Used to put values into SmartDashboard
	 * 
	 * This will set-up the dashboard with the right booleans
	 * 
	 * @param table
	 */
	public static void setup(NetworkTable table)
	{
		table.putBoolean("ScoreBin", false);
		table.putBoolean("ScoreBinAndTote", false);
		table.putBoolean("ScoreToteFromLow", false);
		table.putBoolean("ScoreToteFromHigh", false);
		table.putBoolean("PullTotesFromLandFill", false);
		/*table.putNumber("frontLeft", DriveTrain.getInstance().getFrontLeft().get());
		table.putNumber("frontRight", DriveTrain.getInstance().getFrontRight().get());
		table.putNumber("backLeft", DriveTrain.getInstance().getBackLeft().get());
		table.putNumber("backRight", DriveTrain.getInstance().getBackRight().get());
		table.putNumber("grabberLevel", Grabber.getInstance().getLevelCount());
		table.putBoolean("initializeKey", false);*/
		
	}
	/**
	 * adds specified command group to the scheduler which is determined from the Smartdashboard inputs
	 * @param table
	 */
	public static void parameterSetup(NetworkTable table)// not used
	{
		while(!table.getBoolean("initializeKey"))
		{
			if(table.getBoolean("DriveStraightForward"))
			{
				
			}
			else if(table.getBoolean("DriveSquare"))
			{
				table.putNumber("timeDrive", 0.0);
			table.putNumber("speedDrive", 0.0);
			}
		}
	}
	
	/**
	 * This method actaully adds the proper auto command to the scheduler for operation
	 * @param table
	 */
	
	public static void init(NetworkTable table)
	{
		if(table.getBoolean("ScoreBin"))
		{
			Scheduler.getInstance().add(new ScoreBin());
		}
		else if(table.getBoolean("ScoreBinAndTote"))
		{
			Scheduler.getInstance().add(new ScoreBinAndTote());
		}
		else if(table.getBoolean("ScoreToteFromLow"))
		{
			Scheduler.getInstance().add(new ScoreToteFromLow());
		}
		else if(table.getBoolean("ScoreToteFromHigh"))
		{
			Scheduler.getInstance().add(new ScoreToteFromHigh());
		}
		else if(table.getBoolean("PullTotesFromLandFill"))
		{
			Scheduler.getInstance().add(new PullTotesFromLandFill());
		}
	}
	
	/**
	 * This method is called wherever you want to put values onto the Dashboard.
	 * 
	 * Warning: If this is called as often as execute is, this can use a lot of bandwidth/processing and break robot comms
	 * 
	 */
	
	public static void update(NetworkTable table)
	{
		/*table.putNumber("frontLeft", DriveTrain.getInstance().getFrontLeft().get());
		table.putNumber("frontRight", DriveTrain.getInstance().getFrontRight().get());
		table.putNumber("backLeft", DriveTrain.getInstance().getBackLeft().get());
		table.putNumber("backRight", DriveTrain.getInstance().getBackRight().get());
		table.putNumber("GrabberLevel", Grabber.getInstance().getLevelCount());*/
	}
	
	
}



