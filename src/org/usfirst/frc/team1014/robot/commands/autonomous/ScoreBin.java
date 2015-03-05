package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreBin extends CommandGroup {
	
	public ScoreBin()
	{
		this.addSequential(new PickUpBin());
		this.addSequential(new DriveStraightForward(3, .5));
		this.addSequential(new PutDownBin());
		this.addSequential(new DriveStraightForward(.5, .5));
	}

}
