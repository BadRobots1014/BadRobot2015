package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Subsystem;

public class AccelerateAndStopBackward extends CommandBase {

	double speed = .25;
	
	public AccelerateAndStopBackward() {
		requires((Subsystem) driveTrain);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "AccelerateAndStopBackward";
	}

	@Override
	protected void execute() 
	{
		driveTrain.tankDrive(speed, -speed);///this configuration runs the robot backwards at speed
		speed += 0.01;
	}

	@Override
	protected boolean isFinished() {
		if(speed >= 1)
			return true;
		return false;
	}

	@Override
	protected void end() {
		driveTrain.tankDrive(-0.01, 0.01);//THIS WILL TIP OVER THE ROBOT
											//cant be (0,0 or the robot will coast)
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
