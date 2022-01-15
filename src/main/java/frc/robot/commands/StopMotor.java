package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cim;

public class StopMotor extends CommandBase{
    private Cim m_cim;

    public StopMotor(Cim cim){
        m_cim = cim;
        addRequirements(m_cim);
    }

    @Override 
    public void execute(){
        m_cim.stop();
    }
}
