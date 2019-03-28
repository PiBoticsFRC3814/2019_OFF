/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.*;

import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class HatchTalon extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX Hatch;

  public static DigitalInput limitLeft = new DigitalInput(RobotMap.oi_LimitL);
  public static DigitalInput limitRight = new DigitalInput(RobotMap.oi_LimitR);
  public static DigitalInput limitCenter = new DigitalInput(RobotMap.oi_LimitC);


  double leftSpeed = 1.0;  // we may want to map the speed variables for better editing purposes
  double rightSpeed = -1.0;
  public int hatch = 0;

  public boolean centered = false;

  public HatchTalon(){
    Hatch = new WPI_TalonSRX(RobotMap.hatch_Slide);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }
  public void HatchLateralLeft()
  {
    Hatch.set(leftSpeed);
  }
  public void HatchLateralRight()
  {
    Hatch.set(rightSpeed);
  }
  public void HatchLateralStop()
  {
    Hatch.set(0.0);
  }
}
