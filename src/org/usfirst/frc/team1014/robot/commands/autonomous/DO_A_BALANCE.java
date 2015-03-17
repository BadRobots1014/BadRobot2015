package org.usfirst.frc.team1014.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This should balance the robot on the rear wheels
 * 
 * NOTE: THIS IS VERY DANGEROUS
 * @author Steve
 *
 */

public class DO_A_BALANCE extends CommandGroup {

	public DO_A_BALANCE()
	{
		this.addSequential(new AccelerateAndStopBackward());
		this.addSequential(new BalanceRobotOnRearWheels());
	}
	
}
