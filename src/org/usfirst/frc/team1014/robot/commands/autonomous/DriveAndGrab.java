package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndGrab extends CommandGroup {
	
	public DriveAndGrab(double driveTime, double grabTime)
	{
		this.addSequential(new DriveStraightForward(driveTime));
		this.addSequential(new AutoGrab(grabTime, false));
		this.addSequential(new AutoGrab(grabTime, true));
	}

}
