package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveSquareTest extends CommandGroup {

	public DriveSquareTest()
	{
		this.addSequential(new DriveStraightForward(500000));
		//this.addSequential(new AutoTurn(90, true));
		this.addSequential(new DriveStraightForward(500000));
		//this.addSequential(new AutoTurn(90, true));
		this.addSequential(new DriveStraightForward(500000));
		//this.addSequential(new AutoTurn(90, true));
		this.addSequential(new DriveStraightForward(500000));
		//this.addSequential(new AutoTurn(90, true));
	}
	
}