package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This turns the robot 90 degrees to the right.
 * 
 * @author Manu S.
 *
 */
public class Turn90Clockwise extends CommandGroup {

	public Turn90Clockwise()
	{
		this.addSequential(new AutoTurn(90));
	}
}
