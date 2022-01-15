package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Sensor;

public class CheckSensor extends CommandBase {
private Sensor m_Sensor; 
    public CheckSensor(Sensor sensor) {
        m_Sensor = sensor;
        addRequirements(sensor);
    }
    
    @Override
    public void execute() {
        if (m_Sensor.objectInFront()) {
            System.out.println("I see you!");
        } else {
            System.out.println("I don't see you");
        }
    }
 }
