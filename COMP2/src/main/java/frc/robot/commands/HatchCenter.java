/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchTalon;


public class HatchCenter extends Command {
  public HatchCenter() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_HatchTalon);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_HatchTalon.centered = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(!HatchTalon.limitRight.get()){
      Robot.m_HatchTalon.hatch = -1000; // force robot to know the side it is on
    }
    if(!HatchTalon.limitLeft.get()){
      Robot.m_HatchTalon.hatch = 1000; // force robot to know the side it is on
    }
    if(Robot.m_HatchTalon.hatch > 0) // tells you what direction to go
    {
      Robot.m_HatchTalon.HatchLateralRight();
      if(!HatchTalon.limitCenter.get()) // goes until hits center
      {
        Robot.m_HatchTalon.hatch = 0;
      } 
    }
    if(Robot.m_HatchTalon.hatch < 0)// tell you what direction to go
    {    
      Robot.m_HatchTalon.HatchLateralLeft();
      if(!HatchTalon.limitCenter.get())//goes untill center
      {
        Robot.m_HatchTalon.hatch = 0;
      }
    }
    if (Robot.m_HatchTalon.hatch == 0)
    {
      Robot.m_HatchTalon.HatchLateralStop();
      Robot.m_HatchTalon.centered = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.m_HatchTalon.centered)
    {
      Robot.m_HatchTalon.HatchLateralStop();
      return true;
    }
    else
    {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
