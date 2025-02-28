// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public static class DrivetrainConstants {
    public static final MotorType MOTOR_TYPE = MotorType.kBrushless; // Use this with brushless REV Spark Max/Flex Motors
    // public static final MotorType MOTOR_TYPE = MotorType.kBrushed; // Use this with brushed REV Spark Max/Flex Motors

    public static final int FRONT_LEFT_MOTOR_ID  = 1;
    public static final int FRONT_RIGHT_MOTOR_ID = 2;
    public static final int BACK_LEFT_MOTOR_ID   = 3;
    public static final int BACK_RIGHT_MOTOR_ID  = 4;

    // Only use if you have an IMU for field centric driving; These values will need to be tuned
    public static final double TURN_P = 0.1;
    public static final double TURN_I = 0.0;
    public static final double TURN_D = 0.0;

    // Usually its fine to keep this at default, max is 12, min is usually ~6 before motors are too slow
    // Can be used to reduce the max voltage of the drivetrain (also slows the drivetrain down at values below 12.0)
    public static final double NOMINAL_VOLTAGE = 12.0;
  }

  public static class ElevatorConstants {
    public static final int ELEVATOR_MOTOR_ID = 1;
  }

  public static class EndEffectorConstants {
    public static final int CORAL_MOTOR_ID = 2;
  }
}
