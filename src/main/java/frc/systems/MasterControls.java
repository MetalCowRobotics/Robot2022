package frc.systems;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.lib14.UtilityMethods;
import frc.lib14.XboxControllerMetalCow;
import frc.robot.RobotDashboard;
import frc.robot.RobotMap;

public class MasterControls {
	private static final Logger logger = Logger.getLogger(MasterControls.class.getName());
	private static final XboxControllerMetalCow driver = new XboxControllerMetalCow(RobotMap.DriverController.USB_PORT);
	private static final XboxControllerMetalCow operator = new XboxControllerMetalCow(RobotMap.OperatorController.USB_PORT);
	private static final RobotDashboard dashboard = RobotDashboard.getInstance();
	private static final MasterControls instance = new MasterControls();

	private boolean fieldMode = true;
	private boolean intakeUp = true;
	private boolean driverInakeButonPressed;

	private MasterControls() {
		// Intentionally Blank for Singleton
		logger.setLevel(RobotMap.LogLevels.masterControlsClass);
	}

	public static MasterControls getInstance() {
		return instance;
	}

	public boolean isSprintToggle() {
		return driver.getXButton();
	}

	public boolean isCrawlToggle() {
		return driver.getAButton();
	}

	//joysticks
	public double getLX() {
		return driver.getLX();
	}
	public double getLY() {
		return driver.getLY();
	}
	public double getRX() {
		return driver.getRX();
	}
	public double getRY() {
		return driver.getRY();
	}

	private boolean isDpadUpperHalf(XboxControllerMetalCow controller) {
		if (UtilityMethods.between(controller.getPOV(), 0, 89)) {
			return true;
		}
		if (UtilityMethods.between(controller.getPOV(), 271, 360)) {
			return true;
		}
		return false;
	}

	private boolean isDpadLowerHalf(XboxControllerMetalCow controller) {
		if (UtilityMethods.between(controller.getPOV(), 91, 269)) {
			return true;
		}
		return false;
	}

	public void changeMode() {
		if (operator.getRawButtonPressed(7)) {
			fieldMode = !fieldMode;
		}
		dashboard.pushFieldMode(fieldMode);
	}

	public boolean getFieldMode() {
		return fieldMode;
	}
}