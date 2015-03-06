package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreToteFromHigh extends CommandGroup
{
	public ScoreToteFromHigh()
	{
		this.addSequential(new CheckToteDistance());
		this.addSequential(new PutDownTote());
		this.addSequential(new PickUpTote());
		this.addSequential(new DriveStraightForward(2.0, -.5));
		this.addSequential(new Turn180());//good manu
		this.addSequential(new PutDownTote());
	}
}
