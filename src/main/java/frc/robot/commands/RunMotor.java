package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cim;

public class RunMotor extends CommandBase{
    private Cim m_cim;

    public RunMotor(Cim cim){
        m_cim = cim;
        addRequirements(m_cim);
    }

    @Override 
    public void execute(){
        m_cim.run();
    }
    @Override
    public void end(boolean interrupted){
        m_cim.stop();
    }
}