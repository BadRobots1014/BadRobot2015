package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This picks the bin up.
 * 
 * @author Manu S.
 *
 */
public class PickUpBin extends CommandGroup {

	public PickUpBin()
	{
		this.addSequential(new AutoBinGrab(1.75, false));//lifts bin for 1.75 seconds
	}
}
