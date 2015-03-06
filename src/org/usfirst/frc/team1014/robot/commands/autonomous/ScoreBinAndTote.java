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
 * @author Manu S.//good manu
 *
 */
public class ScoreBinAndTote extends CommandGroup {

	public ScoreBinAndTote()
	{
		this.addSequential(new CheckToteDistance());
		this.addSequential(new PutDownTote());
		this.addSequential(new PickUpTote());
		this.addSequential(new AutoTurn(-210));//this.addSequential(new AutoTurn(-180));//turns away from tote
		this.addSequential(new DriveStraightForward(.5, -.25));//this.addSequential(new AutoStrafe(.5, -.2));//needs to be checked
		this.addSequential(new PickUpBin());
		this.addSequential(new AutoTurn(30));
		this.addSequential(new DriveStraightForward(2.0, .5));//this.addSequential(new DriveStraightDistance(250, .5));
		this.addSequential(new PutDownTote());
		this.addSequential(new PutDownBin());
	}
}
