package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is supposed to pick up a tote.
 * 
 * @author Manu S.
 *
 */
public class PickUpTote extends CommandGroup {

	public PickUpTote()
	{
		this.addSequential(new AutoGrab(2.5, true));
	}
}
