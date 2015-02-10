package org.usfirst.frc.team1014.robot.subsystems;

import java.awt.Color;

import org.usfirst.frc.team1014.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Utility;

public class Lights extends BadSubsystem
{
	private DigitalOutput outputRed, outputGreen, outputBlue;
	private static Lights instance;
	private double redValue, greenValue, blueValue;
	private static double PWM_RATE = 19000d;//19000d;
	private boolean inverted, sweep;

	public static Lights getInstance()
	{
		if (instance == null)
			instance = new Lights();
		return instance;
	}

	public Lights()
	{
		
	}

	@Override
	protected void initialize()
	{
		outputRed = new DigitalOutput(RobotMap.LIGHTS_RED);
		outputGreen = new DigitalOutput(RobotMap.LIGHTS_GREEN);
		outputBlue = new DigitalOutput(RobotMap.LIGHTS_BLUE);
		outputRed.setPWMRate(PWM_RATE);
		outputGreen.setPWMRate(PWM_RATE);
		outputBlue.setPWMRate(PWM_RATE);
		inverted = false;
		sweep = false;
		redValue = 0;
		greenValue = 0;
		blueValue = 0;
		outputRed.enablePWM(redValue);
		outputGreen.enablePWM(greenValue);
		outputBlue.enablePWM(blueValue);
		updateLights();
	}

	public void invert()
	{
		inverted = !inverted;
		updateLights();
	}

	public void setColorSweep(boolean sweep)
	{
		this.sweep = sweep;
		updateLights();
	}

	public void free()
	{
		outputRed.free();
		outputGreen.free();
		outputBlue.free();
	}

	public void updateLights()
	{
		if (sweep)
		{
			setRedValue((1d + Math.sin(2d * (double) Utility.getFPGATime() / 1000000d)) / 2d);
			setGreenValue((1d + Math.cos(2d * (double) Utility.getFPGATime() / 1000000d)) / 2d);
			setBlueValue((1d + -Math.sin(2d * (double) Utility.getFPGATime() / 1000000d)) / 2d);
			
			//System.out.printf("%01.2f %01.2f %01.2f\n", redValue, greenValue, blueValue);
		}
		if (redValue < 0)
			redValue = 0;
		if (redValue > 1)
			redValue = 1;
		if (greenValue < 0)
			greenValue = 0;
		if (greenValue > 1)
			greenValue = 1;
		if (blueValue < 0)
			blueValue = 0;
		if (blueValue > 1)
			blueValue = 1;

		if (!inverted)
		{
			outputRed.updateDutyCycle(redValue);
			outputGreen.updateDutyCycle(greenValue);
			outputBlue.updateDutyCycle(blueValue);
		} else
		{
			outputRed.updateDutyCycle(1 - redValue);
			outputGreen.updateDutyCycle(1 - greenValue);
			outputBlue.updateDutyCycle(1 - blueValue);
		}
	}

	public void setColor(Color color)
	{
		setColor(((double) color.getRed()) / 255d,
				((double) color.getGreen()) / 255d,
				((double) color.getBlue()) / 255d);
	}

	public void setColor(double red, double green, double blue)
	{
		setRedValue(red);
		setGreenValue(green);
		setBlueValue(blue);
		updateLights();
	}

	@Override
	public String getConsoleIdentity()
	{
		return "Lights";
	}

	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub

	}

	public boolean isInverted()
	{
		return inverted;
	}

	/**
	 * @return the redValue
	 */
	public double getRedValue()
	{
		return redValue;
	}

	/**
	 * @param redValue
	 *            the redValue to set
	 */
	public void setRedValue(double redValue)
	{
		this.redValue = redValue;
		//updateLights();
	}

	/**
	 * @return the greenValue
	 */
	public double getGreenValue()
	{
		return greenValue;
	}

	/**
	 * @param greenValue
	 *            the greenValue to set
	 */
	public void setGreenValue(double greenValue)
	{
		this.greenValue = greenValue;
		//updateLights();
	}

	/**
	 * @return the blueValue
	 */
	public double getBlueValue()
	{
		return blueValue;
	}

	/**
	 * @param blueValue
	 *            the blueValue to set
	 */
	public void setBlueValue(double blueValue)
	{
		this.blueValue = blueValue;
		//updateLights();
	}

	/**
	 * @return the pWM_RATE
	 */
	public double getPWM_RATE()
	{
		return PWM_RATE;
	}

	/**
	 * @param pWM_RATE
	 *            the pWM_RATE to set
	 */
	public void setPWM_RATE(double pWM_RATE)
	{
		PWM_RATE = pWM_RATE;
		updatePWM();
	}

	private void updatePWM()
	{
		outputRed.setPWMRate(PWM_RATE);
		outputGreen.setPWMRate(PWM_RATE);
		outputBlue.setPWMRate(PWM_RATE);
	}

	@Override
	public void log()
	{
		// TODO Auto-generated method stub
		
	}

}