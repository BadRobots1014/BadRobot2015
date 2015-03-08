package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreBin extends CommandGroup {
	
	public ScoreBin()
	{
		/*this.addSequential(new PickUpBin());//This works a little
		this.addSequential(new DriveStraightForward(2.5, .5));//this.addSequential(new DriveStraightDistance(250, .5));
		this.addSequential(new PutDownBin());
		this.addSequential(new DriveStraightForward(.5, .5));*/
		
		this.addSequential(new PickUpBin());
		this.addSequential(new AutoTurn(30));
		this.addSequential(new DriveStraightForward(2.0, .5));//drives backwards
		this.addSequential(new AutoSlowTurn(-90));
		//this.addSequential(new PutDownBin());
	}

}
