/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ElevatorTalon extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX elevatorFront;
  public WPI_TalonSRX elevatorBack;

  public AHRS gyro;

  public double frontSpeed = 0.0;
  public double backSpeed = 0.0;

  public double angle = 0.0;


  public ElevatorTalon() {

    //this public ElevatorTalon constructs all of the devices used in this subsystem when we call a new Elevator Talon in the Robot.java

    elevatorFront = new WPI_TalonSRX(RobotMap.elevator_Front);
    elevatorBack = new WPI_TalonSRX(RobotMap.elevator_Rear);

    gyro = new AHRS(Port.kMXP);

    //the following add current limits to the talon motor controllers.
    //we were able to deturmine that the motor towards the drive team was tripping the PCM breaker
    //current limit should lower our locked rotor current and increase the time it takes to trip the breaker
    
    elevatorFront.configContinuousCurrentLimit(40, 1); //current limit appears to work meter shows around 50 amps limit and driverstation log shows 40
    elevatorBack.configContinuousCurrentLimit(40, 1);

    elevatorFront.enableCurrentLimit(true);  //i think this is needed to actually enable the current limits functionality
    elevatorBack.enableCurrentLimit(true);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public double GetAngle(){
    //if (gyro.isConnected()){
      angle = gyro.getPitch();
    //}
    return angle;
  }

  public void AllUp(){
    elevatorFront.set(-frontSpeed);
    elevatorBack.set(-backSpeed);
  }

  public void FrontUp(){
    elevatorFront.set(0.5);
  }

  public void BackUp(){
    elevatorBack.set(0.5);
  }

  public void FrontStop(){
    elevatorFront.set(0.0);
  }

  public void BackStop(){
    elevatorBack.set(0.0);
  }

}
