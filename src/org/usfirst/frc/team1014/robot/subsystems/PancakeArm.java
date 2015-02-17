package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;

public class PancakeArm extends BadSubsystem  {

	public static PancakeArm instance;
	
	Talon lift;
	
    public static PancakeArm getInstance()
    {
        if (instance == null)
        {
            instance = new PancakeArm();
        }
        return instance;
    }
	
	private PancakeArm()
	{
		
	}
	
	@Override
	protected void initialize() {
		lift = new Talon(RobotMap.pancakeArm);
		
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "PancakeArm";
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void lift(double speed)
	{
		lift.set(speed);
	}
	
}
