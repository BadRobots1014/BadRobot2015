package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AutoBinGrab extends CommandBase {

	public double time;
	public boolean whichWay;
	
	public double startTime, passedTime;
	
	public AutoBinGrab(double grabTime, boolean direction)
	{
		requires((Subsystem) winchLift);
		time = grabTime;
		whichWay = direction;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		startTime = Utility.getFPGATime();
		passedTime = 0;
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "AutoGrab";
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		winchLift.lift(0);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		passedTime = Utility.getFPGATime() - startTime;
		double speed = .2;
		if(!whichWay)
			speed *= -1;
		winchLift.lift(speed);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		System.out.println(getConsoleIdentity() + "has been interrupted!");
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if((passedTime / 1000000) > time)
		{
			System.out.println(getConsoleIdentity() + "I'm done");
			return true;
		}
		
		return false;
	}

}
