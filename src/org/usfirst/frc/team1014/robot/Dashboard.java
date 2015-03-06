package org.usfirst.frc.team1014.robot;

import org.usfirst.frc.team1014.robot.commands.CommandBase;
import org.usfirst.frc.team1014.robot.commands.autonomous.DriveSquare;
import org.usfirst.frc.team1014.robot.commands.autonomous.DriveStraightForward;
import org.usfirst.frc.team1014.robot.commands.autonomous.PullTotesFromLandFill;
import org.usfirst.frc.team1014.robot.commands.autonomous.ScoreBin;
import org.usfirst.frc.team1014.robot.commands.autonomous.ScoreBinAndTote;
import org.usfirst.frc.team1014.robot.commands.autonomous.ScoreToteFromHigh;
import org.usfirst.frc.team1014.robot.commands.autonomous.ScoreToteFromLow;
import org.usfirst.frc.team1014.robot.commands.autonomous.Turn180;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Dashboard 
{
	/**
	 * used to put values into SmartDashboard
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
		table.putBoolean("initializeKey", false);
		try {
			table.putValue("AxisCamera", CommandBase.camera.getImage());
		} catch (NIVisionException e) {
			System.out.println("AxisCamera getImage did not work in setup()");
			e.printStackTrace();
		}*/
		
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
	
	public static void update(NetworkTable table)
	{
		/*table.putNumber("frontLeft", DriveTrain.getInstance().getFrontLeft().get());
		table.putNumber("frontRight", DriveTrain.getInstance().getFrontRight().get());
		table.putNumber("backLeft", DriveTrain.getInstance().getBackLeft().get());
		table.putNumber("backRight", DriveTrain.getInstance().getBackRight().get());
		table.putNumber("GrabberLevel", Grabber.getInstance().getLevelCount());
		/*try {
			table.putValue("AxisCamera", CommandBase.camera.getImage());
		} catch (NIVisionException e) {
			System.out.println("AxisCamera getImage did not work in update()");
			e.printStackTrace();
		}*/
		//System.out.println(CommandBase.camera.isFreshImage());*/
	}
	
	
}



