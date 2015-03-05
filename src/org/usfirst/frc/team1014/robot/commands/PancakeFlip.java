package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

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
			speed += .01;
		}

		else if(OI.secXboxController.getRawButton(OI.secXboxController.RB))//raises arm
		{
			pancake.lift(-speed);
			speed += .01;
		}
			
		else//stop speed
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
