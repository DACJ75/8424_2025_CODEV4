// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANDriveSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveDistanceCommand extends Command {
  public CANDriveSubsystem m_drive;
  public double m_distance;
  public DoubleSupplier m_speed;
  public DoubleSupplier m_rotationSpeed;

  /** Creates a new DriveDistanceCommand. */
  public DriveDistanceCommand(CANDriveSubsystem drive, double distance, DoubleSupplier speed, DoubleSupplier rotationSpeed) {
    m_drive = drive;
    m_distance = distance;
    m_speed = speed;
    m_rotationSpeed = rotationSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.driveArcade(m_drive, m_speed, m_rotationSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_drive.getEncoderValue() >= m_distance) {
      return true;
    }

    return false;
  }
}
