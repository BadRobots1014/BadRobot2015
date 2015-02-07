package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveSquare extends CommandGroup {
	
	public DriveSquare(double timeDrive, double speedDrive, double degree)
	{
		this.addSequential(new DriveStraightForward(timeDrive, speedDrive));
		this.addSequential(new AutoTurn(degree));
		this.addSequential(new DriveStraightForward(timeDrive, speedDrive));
		this.addSequential(new AutoTurn(degree));
		this.addSequential(new DriveStraightForward(timeDrive, speedDrive));
		this.addSequential(new AutoTurn(degree));
		this.addSequential(new DriveStraightForward(timeDrive, speedDrive));
		this.addSequential(new AutoTurn(degree));
	}

}
