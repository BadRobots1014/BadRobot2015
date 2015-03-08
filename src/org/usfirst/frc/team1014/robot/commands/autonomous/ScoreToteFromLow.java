package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This class picks up the tote from already being below it. It then moves into the auto zone and drops the tote
 * @author Steve
 *
 */

public class ScoreToteFromLow extends CommandGroup 
{
	public ScoreToteFromLow()
	{
		this.addSequential(new CheckToteDistance());
		this.addSequential(new PickUpTote());
		this.addSequential(new DriveStraightForward(2.5, -.42));
		//this.addSequential(new Turn180());//good manu
		this.addSequential(new PutDownTote());
	}
}
