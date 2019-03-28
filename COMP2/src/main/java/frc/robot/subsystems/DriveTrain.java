/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;
import frc.robot.commands.DriveJoy;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX lf;
  WPI_TalonSRX lr;
  WPI_TalonSRX rf;
  WPI_TalonSRX rr;

  SpeedControllerGroup left;
  SpeedControllerGroup right;

  DifferentialDrive piboticsDrive;

  public DriveTrain() {
    lf = new WPI_TalonSRX(RobotMap.drive_lf);
    lr = new WPI_TalonSRX(RobotMap.drive_lr);
    rf = new WPI_TalonSRX(RobotMap.drive_rf);
    rr = new WPI_TalonSRX(RobotMap.drive_rr);

    left = new SpeedControllerGroup(lf,lr);
    right = new SpeedControllerGroup(rf,rr);
    
    piboticsDrive = new DifferentialDrive(left, right);
	}

	public void arcadeDrive(double y, double z) {
    piboticsDrive.arcadeDrive(y, z);
    //RobotMap.test_map = 1.0;
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveJoy());
  }
}
