package org.usfirst.frc.team1014.robot.commands.autonomous;
import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;
public class AutoTurn extends CommandBase {

	public double degree;
	public double difference;	
	
	public double startTime, passedTime;
	
	public AutoTurn(double degree)
	{
		requires((Subsystem) driveTrain);
		requires((Subsystem) mxp);
		this.degree = degree;
	}
	
	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
		this.difference = 0;
		this.passedTime = 0;
		this.startTime = Utility.getFPGATime();
		mxp.resetGyro();		
	}

	@Override
	public String getConsoleIdentity() {
		return "AutoTurn";
	}

	@Override
	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime;
		difference = mxp.getAngle() - degree;// negative for counterclockwise
		driveTrain.mecanumDriveCartesian(0, 0, deadzone(rotation()), 0);
		
	}

	@Override
	protected boolean isFinished() {
		if(deadzone(difference) == 0 || passedTime/1000000 > 2)
		{
			System.out.println("AutoTurnFinished");
			return true;
		}

		return false;
	}

	@Override
	protected void end() {
		driveTrain.tankDrive(0, 0);
		
	}

	@Override
	protected void interrupted() {
		System.out.println("AutoTurn interuppted");
		
	}
	
	public double rotation()
	{
		return -(difference/60);
	}
    public static double deadzone(double d) {
        //whenever the controller moves LESS than the magic number, the 
        //joystick is in the loose position so return zero - as if the 
        //joystick was not moved
        if (Math.abs(d) < .1) {
            return 0;
        }
        
        if (d == 0)
        {
            return 0;
        }
        //When the joystick is used for a purpose (passes the if statements, 
        //hence not just being loose), do math
        return (d / Math.abs(d)) //gets the sign of d, negative or positive
            * ((Math.abs(d) - .1) / (1 - .1)); //scales it
    }

}
