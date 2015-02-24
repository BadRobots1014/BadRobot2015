package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

public class PancakeFlip extends CommandBase{
	
	public double speed;
	
	@Override	
	protected void initialize() 
	{
		pancake.lift(0.0);
		
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
		if(OI.secXboxController.getRawButton(OI.secXboxController.LB))
		{
			pancake.lift(speed);
			speed += .01;
		}

		else if(OI.secXboxController.getRawButton(OI.secXboxController.RB))
		{
			pancake.lift(-speed);
			speed += .01;
		}
			
		else
		{
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
	protected void end()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
