package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveSquare extends CommandGroup
{
	public DriveSquare()
	{
		this.addSequential(new DriveStraightForward(1,.25));
		this.addSequential(new AutoTurn(90));
		this.addSequential(new DriveStraightForward(1,.25));
		this.addSequential(new AutoTurn(90));
		this.addSequential(new DriveStraightForward(1,.25));
		this.addSequential(new AutoTurn(90));
		this.addSequential(new DriveStraightForward(1,.25));
		this.addSequential(new AutoTurn(90));
	}
}
