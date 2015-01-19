package org.usfirst.frc.team1014.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class TankDriveGroup extends CommandGroup {

	public TankDriveGroup()
	{
		this.addSequential(new TankDrive());	
	}
}
