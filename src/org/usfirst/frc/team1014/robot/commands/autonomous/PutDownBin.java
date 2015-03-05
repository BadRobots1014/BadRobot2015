package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is supposed to put a bin down.
 * 
 * @author Manu S.
 *
 */
public class PutDownBin extends CommandGroup {

	public PutDownBin()
	{
		this.addSequential(new AutoBinGrab(1.75, true));
	}
}
