package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

/**
 * This code controls the pancake flipper, which
 * is supposed to flip over totes, specifically
 * from the landfill. Currently, it is made such that
 * the arm moves faster the longer it is active. This
 * is done so that the arm has enough power to flip 
 * the bins over completely. 
 * @author Stev
 *
 */
public class PancakeFlip extends CommandBase{
	
	public double speed;
	
	@Override	
	protected void initialize() 
	{
		pancake.lift(0.0);
		speed = .25;
	}

	@Override
	public String getConsoleIdentity() 
	{
		// TODO Auto-generated method stub
		return "PancakeFlip";
	}

	@Override
	protected void execute() 
	{
		//This checks the bumpers being pressed and runs the pancake arm accordingly
		if(OI.secXboxController.getRawButton(OI.secXboxController.LB))//lower arm
		{
			pancake.lift(speed);
			speed += .01; // increases the speed
		}

		else if(OI.secXboxController.getRawButton(OI.secXboxController.RB))//raises arm
		{
			pancake.lift(-speed);
			speed -= .01; // also increases the speed 
		}
			
		else
		{
			// stop the arm 
			// reset the speed
			speed = .25;
			pancake.lift(0);
		}
			
		
	}

	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * This is teleop command, so
	 * end is not neccesary
	 */
	protected void end()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		System.out.println(getConsoleIdentity() + ": I have been interrupted");
	}

}
