package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.commands.MecanumDrive;
import org.usfirst.frc.team1014.robot.commands.SafeMecanumDriveField;
import org.usfirst.frc.team1014.robot.sensors.LIDAR;
import org.usfirst.frc.team1014.robot.subsystems.IMU.IMU;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;

public class DriveTrain extends BadSubsystem {
	private static DriveTrain instance;
	
	RobotDrive train;
	SpeedController frontLeft, backLeft, frontRight, backRight;
	double startPitch, startRoll;
	LIDAR lidarLeft, lidarRight;
	Ultrasonic ultra;
	public boolean speedHigh;
	
	IMU mxp;
	SerialPort serial_port;
	
    public static DriveTrain getInstance()
    {
        if (instance == null)
        {
            instance = new DriveTrain();
        }
        return instance;
    }
	
    private DriveTrain()
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
    	lidarLeft = new LIDAR(I2C.Port.kMXP, 0x62); //lidarRight = new Lidar();
    	
    	train.setInvertedMotor(RobotDrive.MotorType.kRearRight, true); 
    	train.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
    	speedHigh = false;
    	
    	//mxp stuff
    	serial_port = new SerialPort(57600,SerialPort.Port.kMXP);

		byte update_rate_hz = 127;
		mxp = new IMU(serial_port,update_rate_hz);
		Timer.delay(0.3);
        mxp.zeroYaw();
        
		tankDrive(0, 0);
		resetGyro();
		setInitalGyro(mxp.getPitch(), mxp.getRoll());
		
		ultra = new Ultrasonic(RobotMap.ultraPing, RobotMap.ultraEcho);
	}

	@Override
	public String getConsoleIdentity() 
	{
		return "MikeDriveTrain";
	}

	@Override
	protected void initDefaultCommand() 
	{
		this.setDefaultCommand(new MecanumDrive()); 
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
    
    public void mecanumDrive(double leftX, double leftY, double rightX) 
    {
    	if(!speedHigh)
        	train.mecanumDrive_Cartesian(leftX / 2, leftY / 2, rightX / 2, getAngle());
    	else	
    		train.mecanumDrive_Cartesian(leftX, leftY, rightX, getAngle());
    }
    
    public void mecanumDriveAntiTip(double leftX, double leftY, double rightX) 
    {
    	if(isSafeToDrive(mxp.getPitch(), mxp.getRoll()))
    	{
    		if(!speedHigh)
    			train.mecanumDrive_Cartesian(leftX / 2, leftY / 2, rightX / 2, getAngle());
    		else	
    			train.mecanumDrive_Cartesian(leftX, leftY, rightX, getAngle());
    	}
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
    	frontRight.set(speed);
    	backLeft.set(speed);
    	backRight.set(speed);
    }
    
    public void rotate(double targetAngle, double currentAngle)
    {
    	
    }
    
    /**
     * This method, using the gyro and the dpad, lines up the robot in orientation with the field.  
     * 
     * Please don't look at it
     * too late, I did
     * @param angle
     * @param mxpAngle
     */    
    public void rotateToAngle(double angle)
    {
    	double mxpAngle = getAngle360();
    	if(!isNear(angle, mxpAngle))
    	{
        	double angleDif = 0;
        	boolean turnLeft = false;

        	if(angle == 0 && mxpAngle > 180)
        	{
        		angleDif = mxpAngle - angle;
        		double motorSpeedToPut = convertToMotorSpeed(angleDif);
        		rotateRobotDifference(-motorSpeedToPut);
        	}
        	else
        	{
            	if(angle > mxpAngle) // rotate left
            	{
            		angleDif = angle - mxpAngle;
            		if(angleDif > 180)
            		{
            			angleDif = Math.abs(angleDif - 360);
            		}
            			
            		turnLeft = false; // yes it is redundant but I dont care
            	}
            	else
            	{
            		angleDif = mxpAngle - angle;
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
    
    
    public double clampMotorValues(double value)
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
    	//System.out.println("Isn't safe to drive");
    	
    	if(Math.abs(rollAmount) > 6)
    	{
    		if(startPitch - rollAmount > 0) // rolling left
    		{
    			frontLeft.set(-clampMotorValues(rollAmount/60));
    			frontRight.set(clampMotorValues(rollAmount/60));
    		}
    		else
    		{

    			backLeft.set(-clampMotorValues(rollAmount/60));
    			backRight.set(clampMotorValues(rollAmount/60));
    		}
    		return false;
    	}
    	if(Math.abs(pitchAmount) > 6)
    	{
    		if(startPitch - pitch > 0) // pitching back
    		{
    			backLeft.set(clampMotorValues(pitchAmount/60));
    			frontLeft.set(-clampMotorValues(pitchAmount/60));
    		}
    		else
    		{
    			backRight.set(clampMotorValues(pitchAmount/60));  
    			frontRight.set(-clampMotorValues(pitchAmount/60));  
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
    
    public int getLidarLeft()
    {
    	lidarLeft.update();
    	return lidarLeft.getDistance();
    }
    
    public double getLidarRight()
    {
    	return 0.0;	
    }
    
 // Nav6 methods
 	public double getAngle()// return -180 - 180
 	{
 		return (double)mxp.getYaw();
 	}
 	
 	public double getAngle360() // returns 0 -360
 	{
 		if(mxp.getYaw() < 0)
 			return mxp.getYaw() + 360;
 		else
 			return mxp.getYaw();
 	}
 	
 	public IMU getMXP()
 	{
 		return mxp;
 	}
 	
 	public void resetGyro()
 	{
 		mxp.zeroYaw();
 	}
    
    /**
     *Used for easy output to the roboRIO
     */
	public static void so(Object so)
	{
		System.out.println("MikeDriveTrain: " + so);
	}
	public Ultrasonic getUltra()
	{
		return ultra;
	}
	public double getDistanceIn()
	{
		return ultra.getRangeInches();
	}
	public void setAutoMode(boolean on)
	{
		ultra.setAutomaticMode(on);
	}
	public void ping()
	{
		ultra.ping();
	}
	public double getDistanceMM()
	{
		return ultra.getRangeMM();
	}
	public void setUnits(Unit units)
	{
		ultra.setDistanceUnits(units);
	}
}




