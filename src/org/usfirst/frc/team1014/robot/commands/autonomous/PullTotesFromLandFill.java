package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PullTotesFromLandFill extends CommandGroup {

	public PullTotesFromLandFill() 
	{
		this.addSequential(new DriveStraightForward(1.0, .5));//drives backwards
		this.addSequential(new PutDownTote());
	}

}
