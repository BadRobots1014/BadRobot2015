package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDriveField extends CommandBase {

	public MecanumDriveField()
	{
		requires((Subsystem) driveTrain);
		requires((Subsystem) mxp);
	}
	
	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
		
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute() {
		
		
    	double x = OI.xboxController.getLeftStickX();
    	double y = OI.xboxController.getLeftStickY();
    	double rot = OI.xboxController.getRightStickX();
    	
    	y = -y;
    	
    	double strafe = x;
    	double fwd = y;
    	double rcw = rot;
    	
    	double pi = 3.1415926;
    	
    	double currAngle = 0;
    	currAngle = mxp.getAngle();
    	
    	double currAngleRadians = currAngle * pi/180;
    	double temp = fwd * Math.cos(currAngleRadians) + strafe * Math.sin(currAngleRadians);
    	strafe = -fwd * Math.sin(currAngleRadians) + strafe * Math.cos(currAngleRadians);
		fwd = temp;
		
		double fl = clampMotorValues(fwd + rcw + strafe);
		double fr = clampMotorValues(fwd - rcw - strafe);
		double bl = clampMotorValues(fwd + rcw - strafe);
		double br = clampMotorValues(fwd - rcw + strafe);
		so(mxp.getAngle());
		/*so(fl);
		so(fr);
		so(bl);
		so(br + "\n");*/
		
		driveTrain.setMotors(fl, bl, fr, br);
		
		
	}
	
    private double clampMotorValues(double scaledStrafe)
    {
        //double scaledLeftStrafe = OI.getUsedLeftX() * 1.25 * OI.getSensitivity();

        if (scaledStrafe > 1)
        {
            scaledStrafe = 1;
        }
        if (scaledStrafe < -1)
        {
            scaledStrafe = -1;
        }
        return scaledStrafe;
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	public static void so(Object so)
	{
		System.out.println("MecanumDriveField: " + so);
	}

}
