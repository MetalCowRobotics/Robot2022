package frc.robot;

import java.util.logging.Logger;

import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDashboard {
	private static final Logger logger = Logger.getLogger(RobotDashboard.class.getName());
	private static final RobotDashboard ourInstance = new RobotDashboard();
	private SendableChooser<AutoMission> autonomousAction = new SendableChooser<>();
	private SendableChooser<AutoPosition> startingPosition = new SendableChooser<>();

	public enum AutoMission {
		AUTOMODE_NONE, AUTOMODE_SHOOT_N_GO, AUTOMODE_SHOOT_N_GATHER
	}

	public enum AutoPosition {
		AUTOMODE_LEFT_OF_TARGET, AUTOMODE_CENTER, AUTOMODE_RIGHT_OF_TARGET
	}

	private RobotDashboard() {
		logger.setLevel(RobotMap.LogLevels.robotDashboardClass);
	}

	public static RobotDashboard getInstance() {
		return ourInstance;
	}

	public void initialPush() {
		pushAuto();
		pushTurnPID();
		pushShooterTargetVelocity(2700);
		pushTargetingTuning();
		pushTurretOffset();
	}

	public void pushAuto() {
		autonomousAction.setDefaultOption("shoot and go", AutoMission.AUTOMODE_SHOOT_N_GO);
		autonomousAction.addOption("shoot and gather", AutoMission.AUTOMODE_SHOOT_N_GATHER);
		autonomousAction.addOption("no autonomous", AutoMission.AUTOMODE_NONE);
		startingPosition.setDefaultOption("center position", AutoPosition.AUTOMODE_CENTER);
		startingPosition.addOption("left position", AutoPosition.AUTOMODE_LEFT_OF_TARGET);
		startingPosition.addOption("right position", AutoPosition.AUTOMODE_RIGHT_OF_TARGET);
		SmartDashboard.putData("missions", autonomousAction);
		SmartDashboard.putData("starting Position", startingPosition);
	}

	public AutoMission getAutoMission() {
		return autonomousAction.getSelected();
	}

	public AutoPosition getStartingPosition() {
		return startingPosition.getSelected();
	}

	public void pushShooterPIDValues(double P, double I, double D, double Iz) {
		SmartDashboard.putNumber("SkP", P);
		SmartDashboard.putNumber("SkI", I);
		SmartDashboard.putNumber("SkD", D);
		SmartDashboard.putNumber("SIz", Iz);
	}

	public double getShooterP(double defaultVal) {
		return SmartDashboard.getNumber("SkP", defaultVal);
	}

	public double getShooterI(double defaultVal) {
		return SmartDashboard.getNumber("SkI", defaultVal);
	}

	public double getShooterD(double defaultVal) {
		return SmartDashboard.getNumber("SkD", defaultVal);
	}

	public double getShooterIz(double defaultVal) {
		return SmartDashboard.getNumber("SIz", defaultVal);
	}

	// public void pushDrivePIDValues(double P, double I, double D, double Iz) {
	// SmartDashboard.putNumber("DkP", P);
	// SmartDashboard.putNumber("DkI", I);
	// SmartDashboard.putNumber("DkD", D);
	// SmartDashboard.putNumber("DIz", Iz);
	// }

	// public double getDriveP(double defaultVal) {
	// return SmartDashboard.getNumber("DkP", defaultVal);
	// }

	// public double getDriveI(double defaultVal) {
	// return SmartDashboard.getNumber("DkI", defaultVal);
	// }

	// public double getDriveD(double defaultVal) {
	// return SmartDashboard.getNumber("SDkD", defaultVal);
	// }

	// public double getDriveIz(double defaultVal) {
	// return SmartDashboard.getNumber("DIz", defaultVal);
	// }

	public void pushGyro(double angle) {
		SmartDashboard.putNumber("Gyro Reading", angle);
	}

	public void pushLeftEncoder(double leftTics) {
		SmartDashboard.putNumber("driveLeftTics", leftTics);
	}

	public void pushRightEncoder(double rightTics) {
		SmartDashboard.putNumber("driveRight", rightTics);
	}

	public void pushTurnPID() {
		SmartDashboard.putNumber("TkP", RobotMap.TurnDegrees.kP);
		SmartDashboard.putNumber("TkI", RobotMap.TurnDegrees.kI);
		SmartDashboard.putNumber("TkD", RobotMap.TurnDegrees.kD);
		SmartDashboard.putNumber("TIz", RobotMap.TurnDegrees.Iz);
	}

	public double getTurnKP() {
		return SmartDashboard.getNumber("TkP", RobotMap.TurnDegrees.kP);
	}

	public double getTurnKI() {
		return SmartDashboard.getNumber("TkI", RobotMap.TurnDegrees.kI);
	}

	public double getTurnKD() {
		return SmartDashboard.getNumber("TkD", RobotMap.TurnDegrees.kD);
	}

	public double getTurnIz() {
		return SmartDashboard.getNumber("TIz", RobotMap.TurnDegrees.Iz);
	}

	public void pushFieldMode(Boolean Mode) {
		SmartDashboard.putBoolean("Field Mode", Mode);
	}

	public void pushShooterTargetVelocity(double targetVelocity) {
		SmartDashboard.putNumber("Target Velocity", targetVelocity);
	}

	public void pushShooterVelocity(double actualVelocity) {
		SmartDashboard.putNumber("Actual Velocity", actualVelocity);
	}

	public double getShooterTargetVelocity(double defaultValue) {
		return SmartDashboard.getNumber("Target Velocity", defaultValue);
	}

	public void pushTargetColor(String color) {
		SmartDashboard.putString("FMSTargetColor", color);
	}

	public void pushCurrentColor(String underMySensor, String underFieldReader) {
		SmartDashboard.putString("Sensed Color", underMySensor);
		SmartDashboard.putString("Field Sensor Color", underFieldReader);
	}

	public void pushColorSensor(ColorSensorV3 sensor) {
		SmartDashboard.putNumber("red", sensor.getRed());
		SmartDashboard.putNumber("green", sensor.getGreen());
		SmartDashboard.putNumber("blue", sensor.getBlue());
		SmartDashboard.putNumber("proximity", sensor.getProximity());
	}

	public void pushColorMatch(ColorMatchResult match) {
		SmartDashboard.putString("color", match.color.toString());
		SmartDashboard.putNumber("confidence", match.confidence);
	}

	public void pushTargetingTuning() {
		pushAutoTargeting(true);
		SmartDashboard.putNumber("HoodCorrection", 0);
		SmartDashboard.putNumber("YawCorrectionShort", -15);
		SmartDashboard.putNumber("YawCorrectionLong", .175);
		SmartDashboard.putNumber("SpeedCorrection", 0);
	}

	public boolean pushAutoTargeting(boolean autoOn) {
		return SmartDashboard.getBoolean("AutoTarget", autoOn);
	}

	public boolean isAutoTargeting() {
		return SmartDashboard.getBoolean("AutoTarget", true);
	}

	public int hoodCorrection() {
		return (int) SmartDashboard.getNumber("HoodCorrection", 0);
	}

	public double yawCorrectionShort() {
		return SmartDashboard.getNumber("YawCorrectionShort", 0);
	}

	public double yawCorrectionLong() {
		return SmartDashboard.getNumber("YawCorrectionLong", 0);
	}

	public double speedCorrection() {
		return SmartDashboard.getNumber("SpeedCorrection", 0);
	}

	public void pushHoodPositionText(int tics) {
        if (tics > 1822) {
            SmartDashboard.putString("Hood Position", "Long");
        } else if (tics > 1647) {
            SmartDashboard.putString("Hood Position", "10 Foot");
        } else {
            SmartDashboard.putString("Hood Position", "Safe Zone");
        }
	}

	public void pushTurretOffset() {
		SmartDashboard.putNumber("TurretOffset", 0);
	}

	public double getTurretOffset() {
		return SmartDashboard.getNumber("TurretOffset", 0);
	}

	public void pushFMSColor(String gameSpecificMessage) {
		SmartDashboard.putString("FMS_String", gameSpecificMessage);
	}

}
