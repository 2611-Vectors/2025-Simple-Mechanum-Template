// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix6.hardware.TalonFX;
import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DrivetrainConstants.*;

public class TalonFxDrivetrain extends SubsystemBase {
  private final TalonFX frontLeft, frontRight, backLeft, backRight;

  private final PIDController turnPID;
  private double rotationOffset = 0.0;

  /** Creates a new Drive. */
  public TalonFxDrivetrain() {
    frontLeft = new TalonFX(FRONT_LEFT_MOTOR_ID);
    frontRight = new TalonFX(FRONT_RIGHT_MOTOR_ID);
    backLeft = new TalonFX(BACK_LEFT_MOTOR_ID);
    backRight = new TalonFX(BACK_RIGHT_MOTOR_ID);

    rotationOffset = getRotation(); // Sets the rotational offset to the starting rotation

    turnPID = new PIDController(TURN_P, TURN_I, TURN_D); // Used for rotational setpoints in auton
    turnPID.enableContinuousInput(-180, 180); // Setpoints range from -180 to 180; You can switch to a range of 0 to 360 if you prefer
  }

  public double getRotation() {
    // Replace 0.0 with the "getRotation" method of IMU if you choose to switch to field centric
    // Just be sure to add a button to reset the IMU rotation to 0 (Incase you start the robot in an odd rotation)
    return 0.0 - rotationOffset; // Please make sure this returns in degrees as other functions rely on that
  }

  public Command driveWithRotation(Supplier<Double> powerX, Supplier<Double> powerY, Supplier<Double> angleDegrees) {
    // Please note: this only works in field centric; If used without an IMU/Gyro, it will rotate until stoppped
    return runOnce(() -> {
      drive(powerX, powerY, () -> turnPID.calculate(getRotation(), angleDegrees.get()));
    });
  }

  public Command drive(Supplier<Double> powerX, Supplier<Double> powerY, Supplier<Double> powerTurn) {
    // Found the math here: https://www.youtube.com/watch?v=gnSW2QpkGXQ
    // He doesn't do a great job at explaining it, but "Math.PI/4"
    // is supposed to be the rotation of the robot in radians
    return runOnce(() -> {
      double turn = powerTurn.get();

      double theta = Math.atan2(powerY.get(), powerX.get());
      double power = Math.hypot(powerX.get(), powerY.get());

      double sin = Math.sin(theta - Math.toRadians(getRotation()));
      double cos = Math.cos(theta - Math.toRadians(getRotation()));
      double max = Math.max(Math.abs(sin), Math.abs(cos));

      double FL = power * cos/max + turn;
      double FR = power * sin/max - turn;
      double BL = power * sin/max + turn;
      double BR = power * cos/max - turn;

      if ((power + Math.abs(turn)) > 1) {
        FL /= power + turn;
        FR /= power + turn;
        BL /= power + turn;
        BR /= power + turn;
      }

      frontLeft.setVoltage(FL * NOMINAL_VOLTAGE);
      frontRight.setVoltage(FR * NOMINAL_VOLTAGE);
      backLeft.setVoltage(BL * NOMINAL_VOLTAGE);
      backRight.setVoltage(BR * NOMINAL_VOLTAGE);
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
