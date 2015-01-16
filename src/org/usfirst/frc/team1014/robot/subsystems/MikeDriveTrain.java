package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class MikeDriveTrain extends BadSubsystem {
	private static MikeDriveTrain instance;
	
	RobotDrive train;
	SpeedController frontLeft, backLeft, frontRight, backRight;
	Gyro gyro;
	
    public static MikeDriveTrain getInstance()
    {
        if (instance == null)
        {
            instance = new MikeDriveTrain();
        }
        return instance;
    }
	
    private MikeDriveTrain()
    {
    	
    }
    
    @Override
	protected void initialize()
    {
        frontLeft = new Talon(RobotMap.frontLeftController);
        backLeft = new Talon(RobotMap.backLeftController);
        frontRight = new Talon(RobotMap.frontRightController);
        backRight = new Talon(RobotMap.backRightController); 
    	
        gyro = new Gyro(RobotMap.gyro);
        gyro.initGyro();
        gyro.reset();
        
    	train = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
	}

	@Override
	public String getConsoleIdentity() 
	{
		return "MikeDriveTrain";
	}

	@Override
	protected void initDefaultCommand() 
	{
		//this.setDefaultCommand(new DpadDrive()); //??
	}
	
    public void tankDrive(double leftY, double rightY) //analogs
    {
        train.tankDrive(leftY, rightY);
    }
    public void dpadDrive(double leftX, double leftY, double rightX)
    {
    	if((Math.abs(leftX)+Math.abs(leftY)) > 
		(Math.abs(rightX)*2))// if left stick is being used more than the right, this works 
	{
		
		if(Math.abs(leftX) < Math.abs(leftY)) // if more Y than X
		{
			frontLeft.set(-(leftY));// move forward/back
			frontRight.set((leftY));
			backLeft.set(-(leftY));
			backRight.set((leftY));
		}
		else
		{
			frontLeft.set(-(leftX));  // strafe works
			frontRight.set(-(leftX));
			backLeft.set(leftX);
			backRight.set(leftX);
		}
	}
	else
	{
		frontLeft.set(rightX); // rotate robot 
		frontRight.set(rightX);
		backLeft.set(rightX);
		backRight.set(rightX);
	}
    	
    	
    }
    public void mecanumDrive(double leftX, double leftY, double rightX, double gyro) 
    {
        train.mecanumDrive_Cartesian(leftX, leftY, rightX, gyro);
    }
    public Gyro getGyro()
    {
    	return gyro;
    }
}
