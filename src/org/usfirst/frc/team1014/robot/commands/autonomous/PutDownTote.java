package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This drive group is supposed to put a tote down
 * in autonomous.
 * 
 * @author Manu S.
 *
 */
public class PutDownTote extends CommandGroup {

	public PutDownTote()
	{
		this.addSequential(new AutoGrab(2.5, false));
	}
}
