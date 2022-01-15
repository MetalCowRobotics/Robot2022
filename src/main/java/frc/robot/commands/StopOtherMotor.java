package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cim;
import frc.robot.subsystems.Cim2;

public class StopOtherMotor extends CommandBase{
    private Cim2 m_cim;

    public StopOtherMotor(Cim2 cim){
        m_cim = cim;
        addRequirements(m_cim);
    }

    @Override 
    public void execute(){
        m_cim.stop();
    }
}
