package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AutoGrab extends CommandBase {

	public long startTime;
	public long endTime;
	public long runTime;
	public long currentTime;
	
	public boolean hasStarted;
	public boolean goingUp;
	
	public AutoGrab(double runTimeLift, boolean up)
	{
		requires((Subsystem) grabber);
		runTime = (long) runTimeLift;
		goingUp = up;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		hasStarted = false;
		currentTime = Utility.getFPGATime();
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "AutoGrab";
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

		if(!hasStarted)
		{
			startTime = Utility.getFPGATime();
			endTime = startTime + runTime;
			hasStarted = true;
		}
		currentTime = Utility.getFPGATime();
		if(goingUp)
			grabber.lift(1.0);
		else
			grabber.lift(-1.0);
	}

	@Override
	protected boolean isFinished() {
		if(currentTime > endTime)
			return true;
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		grabber.lift(0.0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
