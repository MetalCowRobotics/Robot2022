/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.autonomous.NoAuto;
import frc.lib14.MCRCommand;
import frc.systems.DriveTrain;
import frc.systems.MasterControls;

/**
 * The VM is configured to automatically run this class. If you change the name
 * of this class or the package after creating this project, you must also
 * update the build.gradle file in the project.
 */

public class Robot extends TimedRobot {
  // systems
  DriveTrain driveTrain = DriveTrain.getInstance();
  MasterControls controls = MasterControls.getInstance();
  RobotDashboard dashboard = RobotDashboard.getInstance();

  // class variables
  MCRCommand mission;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture(0);
    dashboard.initialPush();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    driveTrain.initInstruments();
    driveTrain.initSong("song1.chrp");
  }

  @Override
  public void teleopPeriodic() {  
    driveTrain.playSong();
    driveTrain.driveSwerve();
    SmartDashboard.putNumber("yaw", driveTrain.getYaw());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }
}
