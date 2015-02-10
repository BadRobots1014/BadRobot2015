package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.commands.SafeMecanumDriveField;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;

public class MikeDriveTrain extends BadSubsystem {
	private static MikeDriveTrain instance;
	
	RobotDrive train;
	SpeedController frontLeft, backLeft, frontRight, backRight;
	double startPitch, startRoll;
	public boolean speedHigh;
	
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

    	train = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
    	
    	train.setInvertedMotor(RobotDrive.MotorType.kRearRight, true); 
    	train.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
    	speedHigh = false;
	}

	@Override
	public String getConsoleIdentity() 
	{
		return "MikeDriveTrain";
	}

	@Override
	protected void initDefaultCommand() 
	{
		this.setDefaultCommand(new SafeMecanumDriveField()); 
	}
	/**
	 * Tank drives the robot
	 * 
	 * @param leftY
	 * @param rightY
	 */
	
	public void toggleSpeed()
	{
		speedHigh = !speedHigh;
	}
	
    public void tankDrive(double leftY, double rightY) //analogs
    {
        train.tankDrive(leftY, rightY);
    }
    
    /**
     * This drive the robot with in orienation with the field with mecanum wheels where the axels of the rollers form an X across the robot
     * 
     * @param leftX
     * @param leftY
     * @param rightX
     * @param gyro
     */
    
    public void mecanumDriveCartesian(double leftX, double leftY, double rightX, double gyro) 
    {
    	train.mecanumDrive_Cartesian(leftX, leftY, rightX, gyro);
    }
    /**
     * Sets each motor speeds at a certain value
     * @param fl
     * @param bl
     * @param fr
     * @param br
     */
    public void setMotors(double fl, double bl, double fr, double br)
    {
    	frontLeft.set(fl);
    	backLeft.set(bl);
    	frontRight.set(fr);
    	backRight.set(br);
    }
    
    
    /**
     * rotates the robot at a given speed
     * 
     * if speed > 0, rotates counter clockwise right
     * @param speed
     */
    public void rotateRobotDifference(double speed) // works
    {
    	frontLeft.set(speed);
    	frontRight.set(-speed);
    	backLeft.set(-speed);
    	backRight.set(speed);
    }
    
    /**
     * This method, using the gyro and the dpad, lines up the robot in orientation with the field.  
     * 
     * Please don't look at it
     * @param dpadAngle
     * @param mxpAngle
     */
    
    public void lineUpWithField(int dpadAngle, double mxpAngle)
    {
    	if(mxpAngle < 0) // makes mxpAngle comparable to gyro, works
    	{
        	mxpAngle = mxpAngle + 360;
    	}
    	if(!isNear(dpadAngle, mxpAngle))
    	{
        	double angleDif = 0;
        	boolean turnLeft = false;

        	if(dpadAngle == 0 && mxpAngle > 180)
        	{
        		angleDif = mxpAngle - dpadAngle;
        		double motorSpeedToPut = convertToMotorSpeed(angleDif);
        		rotateRobotDifference(-motorSpeedToPut);
        	}
        	else
        	{
            	if(dpadAngle > mxpAngle) // rotate left
            	{
            		angleDif = dpadAngle - mxpAngle;
            		if(angleDif > 180)
            		{
            			angleDif = Math.abs(angleDif - 360);
            		}
            			
            		turnLeft = false; // yes it is redundant but I dont care
            	}
            	else
            	{
            		angleDif = mxpAngle - dpadAngle;
            		if(angleDif > 180)
            		{
            			angleDif = Math.abs(angleDif - 360);
            		}
            		
            		turnLeft = true; 
            	}
            	
            	
            	
        		double motorSpeedToPut = convertToMotorSpeed(angleDif);
        		
        		if(turnLeft)
        			rotateRobotDifference(motorSpeedToPut);
        		else
        			rotateRobotDifference(-motorSpeedToPut);
        	}
        	

        	
        	
    	}

    }
    
    /**
     * This method takes an angle difference and converts to a motor speed.  Basically to fake PID
     * 
     * Meant to really only be used with the mxp and dpad, but can be converted
     * 
     * If angleDif > 0, turn left or counter clockwise
     * 
     * @return yourmom
     */
    
    public double convertToMotorSpeed(double angleDifference) // this never works if given a negative value
    {    	
    	
    	if(angleDifference/90 < .01) // 360 would give a perfect linear speed but I want it faster with a little overshoot
    	{
    		return 0;
    	}
    	else
    	{
    		return clampMotorValues(Math.abs(angleDifference/90));
    	}
    }
    
    /**
     * this is for the dpadRotation.  The Gyro will never return a value exactly equal to the POV from the Controller so this just gets it close
     * You can make it closer by lowering the magic number .1 in the if statement
     * 
     * 
     * @param num1
     * @param num2
     * @return is the number near
     */
    public boolean isNear(double num1, double num2)
    {
    	if(Math.abs(num2-num1) < .01)
    	{
    		return true;
    	}
    	return false;
    }
    
    /**
     * This method clamps down values to give to the motors
     * 
     * @param value
     * @return
     */
    
    
    private double clampMotorValues(double value)
    {

        if (value > 1)
        {
            value = 1;
        }
        if (value < -1)
        {
            value = -1;
        }
        return value;
    }
    
    public boolean isSafeToDrive(double pitch, double roll) // back tip gives negative pitch, tip left gives postive roll
    {
    	double rollAmount = startRoll - roll;
    	double pitchAmount = startPitch - pitch;
    	
    	if(Math.abs(rollAmount) > 12)
    	{
    		if(startPitch - rollAmount > 0) // rolling left
    		{
    			frontLeft.set(-clampMotorValues(rollAmount/60));
    			backLeft.set(-clampMotorValues(rollAmount/60));
    		}
    		else
    		{

    			frontRight.set(clampMotorValues(rollAmount/60));
    			backRight.set(clampMotorValues(rollAmount/60));
    		}
    		return false;
    	}
    	if(Math.abs(pitchAmount) > 15)
    	{
    		if(startPitch - pitch > 0) // pitching back
    		{
    			backLeft.set(-clampMotorValues(pitchAmount/60));
    			backRight.set(-clampMotorValues(pitchAmount/60));
    		}
    		else
    		{
    			frontLeft.set(clampMotorValues(pitchAmount/60));  
    			frontRight.set(clampMotorValues(pitchAmount/60));  
    		}
    		return false;
    	}
    	return true;
    }
    public void setInitalGyro(double pitch, double roll)
    {
    	startPitch = pitch;
    	startRoll = roll;
    }
    
    /**
     *Used for easy output to the roboRIO
     */
	public static void so(Object so)
	{
		System.out.println("MikeDriveTrain: " + so);
	}
}




