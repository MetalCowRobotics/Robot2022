package frc.systems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.PigeonIMU;

import frc.lib14.Xbox360Controller;
import frc.robot.RobotDashboard;
import frc.robot.RobotMap;
import frc.robot.RobotMap.Drivetrain;

public class DriveTrain {

	///////////////////
	//:::WHEEL MAP::://
	//x x x x x x x x//
	// B           A //
	//x \         / x//
	//     \   /     //
	//x      X      x//
	//     /   \     //
	//x /         \ x//
	// C           D //
	//x x x x x x x x//
	//Low # = Steering
	//Medium # = Drive
	//High # = Encoder

	// private static final Logger logger = Logger.getLogger(DriveTrain.class.getName());
	public static final PigeonIMU pigeon = new PigeonIMU(RobotMap.Drivetrain.PIGEON_IMU);
	// public static final IMUFixed GYRO = new IMUFixed();
	private static TalonFX A_Drive = new TalonFX(RobotMap.Drivetrain.A_DRIVE_MOTOR);
	private static TalonFX B_Drive = new TalonFX(Drivetrain.B_DRIVE_MOTOR); 
	private static TalonFX C_Drive = new TalonFX(RobotMap.Drivetrain.C_DRIVE_MOTOR);
	private static TalonFX D_Drive = new TalonFX(Drivetrain.D_DRIVE_MOTOR);
	private static TalonFX A_Steer = new TalonFX(RobotMap.Drivetrain.A_STEERING_MOTOR);
	private static TalonFX B_Steer = new TalonFX(Drivetrain.B_STEERING_MOTOR); 
	private static TalonFX C_Steer = new TalonFX(RobotMap.Drivetrain.C_STEERING_MOTOR);
	private static TalonFX D_Steer = new TalonFX(Drivetrain.D_STEERING_MOTOR);
	private static CANCoder A_Encoder = new CANCoder(RobotMap.Drivetrain.A_ENCODER);
	private static CANCoder B_Encoder = new CANCoder(RobotMap.Drivetrain.B_ENCODER);
	private static CANCoder C_Encoder = new CANCoder(RobotMap.Drivetrain.C_ENCODER);
	private static CANCoder D_Encoder = new CANCoder(RobotMap.Drivetrain.D_ENCODER);

	private static final RobotDashboard dashboard = RobotDashboard.getInstance();
	private static final MasterControls controller = MasterControls.getInstance();

	private final TalonFXControlMode DRIVING_CONTROL_MODE = TalonFXControlMode.PercentOutput;

	private static  Orchestra orchestra;
	private static ArrayList<TalonFX> instruments = new ArrayList<TalonFX>();


	public static final double SPRINT_SPEED = 1.0;
	public static final double NORMAL_SPEED = 0.7;
	public static final double CRAWL_SPEED = 0.5;
	public static final double TURN_SPEED = 0.1;

	private static final double LENGTH = 30;
	private static final double WIDTH = 30;
	private static final double DIAMETER = Math.sqrt(Math.pow(LENGTH,2) + Math.pow(WIDTH,2));

	private double[] ypr = new double[3];
	private double x1, y1, x2;
	private double aSpeed, aAngle, bSpeed, bAngle, cSpeed, cAngle, dSpeed, dAngle;


	
	private static final DriveTrain instance = new DriveTrain();
		
	

	// Singleton
	protected DriveTrain() {
		// set to coast
		A_Drive.configFactoryDefault();
		A_Drive.setNeutralMode(NeutralMode.Coast);
		B_Drive.configFactoryDefault();
		B_Drive.setNeutralMode(NeutralMode.Coast);
		C_Drive.configFactoryDefault();
		C_Drive.setNeutralMode(NeutralMode.Coast);
		D_Drive.configFactoryDefault();
		D_Drive.setNeutralMode(NeutralMode.Coast);

		// set to ramp
		A_Drive.configOpenloopRamp(.25);
		B_Drive.configOpenloopRamp(.25);
		C_Drive.configOpenloopRamp(.25);
		D_Drive.configOpenloopRamp(.25);

		// rightFrontMotor.configOpenloopRamp(Drivetrain.RAMP_SPEED);
		// leftFrontMotor.configOpenloopRamp(Drivetrain.RAMP_SPEED);
		// rightFrontMotor.setNeutralMode(NeutralMode.Brake);
		// leftFrontMotor.setNeutralMode(NeutralMode.Brake);
		// logger.setLevel(RobotMap.LogLevels.driveTrainClass);
	}

	public static DriveTrain getInstance() {
		return instance;
	}

	public void initSong(String chirp) {
		orchestra.loadMusic(chirp);
	}

	public void initInstruments() {
		instruments.add(A_Drive);
		instruments.add(B_Drive);
		instruments.add(C_Drive);
		instruments.add(D_Drive);
		orchestra = new Orchestra(instruments);
	}
	
	public void playSong() {
		orchestra.play();
	}

	public void driveSwerve() {
		playSong();
		updatePigeon();

		x1 = controller.getLX();
		y1 = -controller.getLY();
		x2 = controller.getRX();

		double a = x1 - x2 * (LENGTH /DIAMETER);
        double b = x1 + x2 * (LENGTH / DIAMETER);
        double c = y1 - x2 * (WIDTH / DIAMETER);
		double d = y1 + x2 * (WIDTH / DIAMETER);
		
		double aSpeed = Math.hypot(b, d);
		double bSpeed = Math.hypot(b, c);
		double cSpeed = Math.hypot(a, c);
        double dSpeed = Math.hypot(a, d);
		
        double aAngle = Math.atan2(b, d) * 180 / Math.PI;
		double bAngle = Math.atan2(b, c) * 180 / Math.PI ;
		double cAngle = Math.atan2(a, c) * 180 / Math.PI;
        double dAngle = Math.atan2(a, d) * 180 / Math.PI;


		//Control Modules
		double tmp = aAngle;
		aAngle = Math.floorMod( (long) (tmp - A_Encoder.getAbsolutePosition() + 540), 360) - 180;
		tmp = bAngle;
		bAngle = Math.floorMod( (long) (tmp - B_Encoder.getAbsolutePosition() + 540), 360) - 180;
		tmp = cAngle;
		cAngle = Math.floorMod( (long) (tmp - C_Encoder.getAbsolutePosition() + 540), 360) - 180;
		tmp = dAngle;
		dAngle = Math.floorMod( (long) (tmp - D_Encoder.getAbsolutePosition() + 540), 360) - 180;

		if (aAngle > 3) {
			A_Steer.set(DRIVING_CONTROL_MODE, TURN_SPEED);
		} else if (aAngle < -3) {
			A_Steer.set(DRIVING_CONTROL_MODE, -TURN_SPEED);
		} else {
			A_Steer.set(DRIVING_CONTROL_MODE, 0);
		}

		if (bAngle > 3) {
			B_Steer.set(DRIVING_CONTROL_MODE, TURN_SPEED);
		} else if (bAngle < -3) {
			B_Steer.set(DRIVING_CONTROL_MODE, -TURN_SPEED);
		} else {
			B_Steer.set(DRIVING_CONTROL_MODE, 0);
		}

		if (cAngle > 3) {
			C_Steer.set(DRIVING_CONTROL_MODE, TURN_SPEED);
		} else if (cAngle < -3) {
			C_Steer.set(DRIVING_CONTROL_MODE, -TURN_SPEED);
		} else {
			C_Steer.set(DRIVING_CONTROL_MODE, 0);
		}

		if (dAngle > 3) {
			D_Steer.set(DRIVING_CONTROL_MODE, TURN_SPEED);
		} else if (dAngle < -3) {
			D_Steer.set(DRIVING_CONTROL_MODE, -TURN_SPEED);
		} else {
			D_Steer.set(DRIVING_CONTROL_MODE, 0);
		}


		//Drive Motors
		A_Drive.set(DRIVING_CONTROL_MODE, aSpeed);
		B_Drive.set(DRIVING_CONTROL_MODE, bSpeed);
		C_Drive.set(DRIVING_CONTROL_MODE, cSpeed);
		D_Drive.set(DRIVING_CONTROL_MODE, dSpeed);

	}


	public void stop() {
	}

	private void updatePigeon() {
		pigeon.getYawPitchRoll(ypr);
	}

	public double getYaw() {
		return ypr[0];
	}

	/**
	 * Determine the top speed threshold: CRAWL - Lowest speed threshold Normal -
	 * Normal driving conditions SPRINT - Highest speed threshold
	 * 
	 * @link org.usfirst.frc.team4213.robot.RobotMap
	 */
	private double getThrottle() {
		if (controller.isCrawlToggle()) {
			return CRAWL_SPEED;
		} else if (controller.isSprintToggle()) {
			return SPRINT_SPEED; 
		} else {
			return NORMAL_SPEED;
		}
	}
}
