package org.usfirst.frc.team1014.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is our first Command Group! This holds the
 * commands to drive the robot in a specific order. 
 * @author Steve Popovich
 *
 */
public class MikeDriveGroup extends CommandGroup {

	/*
	 * So, I'll try to explain. (Steve, you may want
	 * to fix this if it is wrong!)
	 * 
	 * The way we would run our robot regularly is
	 * calling command after command in the Robot
	 * class. However, this means that when one task
	 * is done, the robot automatically moves on to the
	 * next task and it can't do tasks at the same time.
	 * 
	 * A CommandGroup helps us get around that problem.
	 * It lets us put whatever commands we want into a group,
	 * then run them one after the other. In this year's game,
	 * we might make a CommandGroup to gather a tote, pull it
	 * up, stack it, and push the stack out. This is called
	 * sequential.
	 * The CommandGroup also lets us do two things at once.
	 * One use of this might be if we want to drive forward
	 * until the robot sees a wall 25 cm away. This is
	 * called parallel.
	 */
	
	
	public MikeDriveGroup()
	{
		this.addSequential(new MecanumDrive());	
	}
}
