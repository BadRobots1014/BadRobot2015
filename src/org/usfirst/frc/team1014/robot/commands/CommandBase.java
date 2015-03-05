package org.usfirst.frc.team1014.robot.commands;

import edu.wpi.first.wpilibj.command.Command;





import edu.wpi.first.wpilibj.networktables.NetworkTable;

//import MikeDriveTrain;
import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Grabber;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.subsystems.PancakeArm;
import org.usfirst.frc.team1014.robot.subsystems.WinchLift;

public abstract class CommandBase extends Command {
    public static OI oi;
    
    public static DriveTrain driveTrain;
    public static Grabber grabber;
    public static WinchLift winchLift;
    public static PancakeArm pancake;
    
    public static void init(NetworkTable table) {
        //Final Subsystems
    	
    	driveTrain = DriveTrain.getInstance();
    	grabber = Grabber.getInstance();
    	winchLift = WinchLift.getInstance();
    	pancake = PancakeArm.getInstance();
    	
    	grabber.levelCount = (int) table.getNumber("grabberLevel", 0);
    	
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        oi.init();

        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData(exampleSubsystem);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
    
    protected abstract void initialize();
        
    
    public abstract String getConsoleIdentity();

}
