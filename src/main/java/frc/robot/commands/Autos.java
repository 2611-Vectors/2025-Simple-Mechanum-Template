// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.drivetrain.SparkMaxDrivetrain;

public final class Autos {
  /** Example static factory for an autonomous command. */
  /** Change SparkMaxDrivetrain to match drivetrain defined in RobotContainer */
  /** This example drives forward at 0 degrees for 1 second, then turns 45 degrees and strafes right for 1 second */
  public static Command exampleAuto(SparkMaxDrivetrain m_Drivetrain) {
    return Commands.sequence(
      Commands.race(
        m_Drivetrain.driveWithRotation(() -> 0.0, () -> 1.0, () -> 0.0),
        Commands.waitSeconds(1)), 
      Commands.race(
        m_Drivetrain.driveWithRotation(() -> 1.0, () -> 0.0, () -> 45.0),
        Commands.waitSeconds(1)));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
