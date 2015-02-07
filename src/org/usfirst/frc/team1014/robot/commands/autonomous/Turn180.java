package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Turns the robot around for autonomous.
 * 
 * @author Manu S.
 *
 */
public class Turn180 extends CommandGroup {

	public Turn180()
	{
		this.addSequential(new AutoTurn(180));
	}
}
