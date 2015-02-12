package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This Command Group describes the most optimal way 
 * to score one tote and one recycling bin during 
 * the autonomous period. 
 * We will start facing the driver station with the 
 * tote already in our grabbing lifter. Then, we will 
 * strafe over to the corresponding bin, turn around 
 * completely, pick up the bin, and drive into the
 * Auto Zone. 
 * 
 * @author Manu S.
 *
 */
public class ScoreBinAndTote extends CommandGroup {

	public ScoreBinAndTote()
	{
		this.addSequential(new CheckToteDistance());
		this.addSequential(new PickUpTote());
		this.addSequential(new AutoTurn(-180));
		this.addSequential(new AutoStrafe(.5, -.2));
		this.addSequential(new PickUpBin());
		this.addSequential(new DriveStraightDistance(250, .5));
		this.addSequential(new PutDownTote());
		this.addSequential(new PutDownBin());
	}
}
