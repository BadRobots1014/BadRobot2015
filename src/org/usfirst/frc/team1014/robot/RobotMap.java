package org.usfirst.frc.team1014.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	
	//Analogs					// These are for the practice bot
	public static int backLeftController = 1; 	// 1
	public static int frontLeftController = 0; 	// 4
	public static int frontRightController = 2; 	// 3
	public static int backRightController = 3; 	// 2
	
	public static int lift1 = 7;
	public static int lift2 = 8;
	public static int lift3 = 9;
	

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
