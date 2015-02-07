package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is supposed to turn the robot 90 degrees counterclockwise
 * 
 * @author Manu S.
 *
 */
public class Turn90CounterClockwise extends CommandGroup {

	public Turn90CounterClockwise()
	{
		this.addSequential(new AutoTurn(-90));
	}
}
