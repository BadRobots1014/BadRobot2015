package org.usfirst.frc.team1014.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GoToLevel extends CommandBase {

	public int target;
	public int difference;
	public int currentLevel;
	
	public GoToLevel(int current, int toGo)
	{
		requires((Subsystem) grabber);
		requires((Subsystem) os);
		target = toGo;
		difference = toGo - current;
		currentLevel = current;
	}
	
	@Override
	protected void initialize() {

	}

	@Override
	public String getConsoleIdentity() {
		return "GoToLevel";
	}

	@Override
	protected void end() {
		grabber.lift(0);
	}

	@Override
	protected void execute() {		
		if(difference > 0)
		{
			grabber.lift(.2);
			if(os.echo.get())
				currentLevel ++;
		}
		else if(difference < 0)
		{
			grabber.lift(-.2);
			if(os.echo.get())
				currentLevel --;
		}
	}

	@Override
	protected void interrupted() {
		System.out.println(getConsoleIdentity() + "has been interrupted");
	}

	@Override
	protected boolean isFinished() {
		if(currentLevel == target)
			return true;
		return false;
	}

}
