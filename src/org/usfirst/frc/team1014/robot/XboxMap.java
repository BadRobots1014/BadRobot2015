package org.usfirst.frc.team1014.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Isaac
 */
public class XboxMap extends Joystick
{
  public static double DEADZONE_MAGIC_NUMBER = .15;

  private static final int LEFT_STICK_X = 0;
  private static final int LEFT_STICK_Y = 1;
  private static final int RIGHT_STICK_X = 4;
  private static final int RIGHT_STICK_Y = 5;
  private static final int A_BUTTON = 0;
  private static final int B_BUTTON = 1;
  private static final int X_BUTTON = 2;
  private static final int Y_BUTTON = 3;
  private static final int LB = 4;
  private static final int RB = 5;
  private static final int SELECT = 6;
  private static final int START = 7;
  private static final int LEFT_JOY_CLICK = 8;
  private static final int RIGHT_JOY_CLICK = 9;
  private static final int LEFT_TRIGGER = 2;
  private static final int RIGHT_TRIGGER = 3;

  public XboxMap(int port)
  {
    super(port);
  }

  /**
   * Creates a deadzone for joysticks, the controllers sticks are a little loose
   * and so there is a margin of error for where they should be considered
   * "neutral/not pushed"
   *
   * @param d
   *          Double between -1 and 1 to be deadzoned
   * @return The deadzoned value
   */
  public static double deadzone(double d) {
    // whenever the controller moves LESS than the magic number, the
    // joystick is in the loose position so return zero - as if the
    // joystick was not moved
    if (Math.abs(d) < DEADZONE_MAGIC_NUMBER) {
      return 0;
    }

    if (d == 0)
    {
      return 0;
    }
    // When the joystick is used for a purpose (passes the if statements,
    // hence not just being loose), do math
    return (d / Math.abs(d)) // gets the sign of d, negative or positive
        * ((Math.abs(d) - DEADZONE_MAGIC_NUMBER) / (1 - DEADZONE_MAGIC_NUMBER)); // scales
                                                                                 // it
  }

  public double getLeftStickX() {
    return deadzone(this.getRawAxis(LEFT_STICK_X));
  }

  public double getLeftStickY() {
    return deadzone(this.getRawAxis(LEFT_STICK_Y));
  }

  public double getRightStickX() {
    return deadzone(this.getRawAxis(RIGHT_STICK_X));
  }

  public double getRightStickY() {
    return deadzone(this.getRawAxis(RIGHT_STICK_Y));
  }

  public boolean isXButtonPressed() {
    return this.getRawButton(X_BUTTON);
  }

  public boolean isYButtonPressed() {
    return this.getRawButton(Y_BUTTON);
  }

  public boolean isAButtonPressed() {
    return this.getRawButton(A_BUTTON);
  }

  public boolean isBButtonPressed() {
    return this.getRawButton(B_BUTTON);
  }

  public boolean isRBButtonPressed() {
    return this.getRawButton(RB);
  }

  public boolean isLBButtonPressed() {
    return this.getRawButton(LB);
  }

  public boolean isLeftJoyClick() {
    return this.getRawButton(RIGHT_JOY_CLICK);
  }

  public boolean isRightJoyClick() {
    return this.getRawButton(LEFT_JOY_CLICK);
  }

  public boolean isSelectButtonPressed() {
    return this.getRawButton(SELECT);
  }

  public boolean isStartButtonPressed() {
    return this.getRawButton(START);
  }

  public double getRightTrigger() {
    return 0.0;
  }

  public double getLeftTrigger() {
    return 0.0;
  }

  public void checkControllerPorts()
  {

  }
}