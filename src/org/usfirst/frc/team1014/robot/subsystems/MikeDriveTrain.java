package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * This class is refers to the drive train object
 * of Mike, our 2012 robot that is being used for 
 * testing our code.
 * @author Steve Popovich
 *
 */
public class MikeDriveTrain extends BadSubsystem {
	
	// this is the actual object of Mike's drive train
	private static MikeDriveTrain instance;
	
	// these are the objects that we need in order to have
	// the drive train. In other words, these are the "soft"
	// versions of the physical objects on our robot
	RobotDrive train;
	SpeedController frontLeft, backLeft, frontRight, backRight;
	Gyro gyro;
	
	/**
	 * Gets the current instance of this class.
	 * If it doesn't exist, make one.
	 * @return instance - the current instance of 
	 * MikeDriveTrain
	 */
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
    	//initialize(); // I think this goes here
    }
    
    @Override
	protected void initialize()
    {
    	//System.out.println("Here");
        frontLeft = new Talon(RobotMap.frontLeftController);
        backLeft = new Talon(RobotMap.backLeftController);
        frontRight = new Talon(RobotMap.frontRightController);
        backRight = new Talon(RobotMap.backRightController); 
    	
        gyro = new Gyro(RobotMap.gyro);
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
	
	/**
	 * This is the basic way to drive.
	 * @param leftY - the speed at which to drive the left side
	 * @param rightY - the speed at which to drive the right side
	 */
    public void tankDrive(double leftY, double rightY) //analogs
    {
        train.tankDrive(leftY, rightY);
    }
    
    /**
     * This is the driving using the dpad. It begins
     * to use the strafing ability, as well as the rotating the robot
     * on its internal pivot point.
     * @param leftX - the speed at which to strafe
     * @param leftY - the speed at which to move forward
     * @param rightX - the speed at which to rotate the robot
     */
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
    
    /**
     * This is mechanum drive. This allows us to strafe and move
     * in almost any direction. 
     * @param leftX
     * @param leftY
     * @param rightX
     * @param gyro
     */
    public void mecanumDrive(double leftX, double leftY, double rightX, double gyro) 
    {
        train.mecanumDrive_Cartesian(leftX, leftY, rightX, gyro);
    }
    
    public Gyro getGyro()
    {
    	return gyro;
    }

	@Override
	public void log()
	{
		// TODO Auto-generated method stub
		
	}
}
