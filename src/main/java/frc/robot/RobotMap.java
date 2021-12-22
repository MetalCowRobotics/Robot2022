package frc.robot;

import java.util.logging.Level;

public class RobotMap {

	public final class DriverController {
		public static final int USB_PORT = 0;
	}

	public final class OperatorController {
		public static final int USB_PORT = 1;
	}

	public static final class Drivetrain {
		public static final int A_DRIVE_MOTOR = 2;
		public static final int A_STEERING_MOTOR = 1;
		public static final int A_ENCODER = 3;
		public static final int B_DRIVE_MOTOR = 5;
		public static final int B_STEERING_MOTOR = 4;
		public static final int B_ENCODER = 6;
		public static final int C_DRIVE_MOTOR = 8;
		public static final int C_STEERING_MOTOR = 7;
		public static final int C_ENCODER = 9;
		public static final int D_DRIVE_MOTOR = 11;
		public static final int D_STEERING_MOTOR = 10;
		public static final int D_ENCODER = 12;
		public static final int WHEEL_DIAMETER = 4;
		public static final int TICS_PER_ROTATION = 4096; 
		public static final double INCHES_PER_ROTATION = Math.PI * RobotMap.Drivetrain.WHEEL_DIAMETER;		
		public static final double RAMP_SPEED = .6;

		public static final int PIGEON_IMU = 13;
	}

	public final class DriveToSensor {
		public static final double TOP_SPEED =.95;
		public static final int SLOW_DOWN_DISTANCE = 18;
		public static final double BOTTOM_SPEED = .5;
		public static final double MAX_ADJUSTMENT = .4;
	}

	public final class DriveWithEncoder {
		public static final double TOP_SPEED = .7;
		public static final double BOTTOM_SPEED = .511111;
		public static final double MAX_ADJUSTMENT = .6;
		public static final int TICS_PER_ROTATION = 4096; 
		public static final double INCHES_PER_ROTATION = Math.PI * RobotMap.Drivetrain.WHEEL_DIAMETER;
		public static final double SLOW_DOWN_DISTANCE = (12 / INCHES_PER_ROTATION) * TICS_PER_ROTATION;
		public static final double REVERSE_TOP_SPEED = -0.6;
		public static final double REVERSE_BOTTOM_SPEED = -0.4;
	}

	public final class TurnDegrees {
		public static final double kP = 0.04; 
		public static final double kI = .008; 
		public static final double kD = .08;
		public static final double Iz = 5;
		public static final double TOP_SPEED = 0;
		public static final double VARIANCE = 1; //.25
		public static final double MAX_ADJUSTMENT = .6;
		public static final double SLOW_VARIANCE = 15; //10
		public static final double SLOW_ADJUSTMENT = .4;
	}
	

	public final class DriveStraightTime {
		public static final double TOP_SPEED = 1;
		public static final double MAX_ADJUSTMENT = .4;
		public static final double BOTTOM_SPEED = .3;
		public static final double SLOW_DOWN_TIME = 2;
	}

	public final class Test {
		public static final int BAG_MOTOR = 2;
	}
	
	public static final class LogLevels {
		public static final Level robotClass = Level.WARNING;
		public static final Level robotDashboardClass = Level.WARNING;
		public static final Level masterControlsClass = Level.WARNING;
		public static final Level driveTrainClass = Level.WARNING;
		public static final Level elevatorClass = Level.WARNING;
		public static final Level intakeClass = Level.WARNING;
		public static final Level climberClass = Level.WARNING;
		public static final Level missionClass = Level.WARNING;
		public static final Level autoDriveClass = Level.WARNING;
		public static final Level componentBuilderClass = Level.WARNING;
	}

	public static final class LightRing {
		public static final int PORT = 0; //RELAY Port
	}

	public static final class LightBar {
		public static final int PORT = 0;  //PWM Port
		public static final int LENGTH = 60;
	}
}
