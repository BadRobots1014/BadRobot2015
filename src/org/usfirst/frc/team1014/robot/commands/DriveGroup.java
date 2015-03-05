package org.usfirst.frc.team1014.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class DriveGroup extends CommandGroup {

	public DriveGroup()
	{
		this.addParallel(new MecanumDrive());
		this.addParallel(new Grab());
		this.addParallel(new GrabBin());
		this.addParallel(new PancakeFlip());
	}
}
