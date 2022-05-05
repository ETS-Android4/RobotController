package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory

abstract class TemplateOpMode : LinearOpMode() {

    @Config
    companion object Constants {
        @JvmField var MAGIC_NUMBER = 32
        // repeat for other "constants"
    }

    abstract fun run()

    val robot = Hardware(hardwareMap)
    private val dashboard: FtcDashboard = FtcDashboard.getInstance()
    val dashTelemetry = MultipleTelemetry(telemetry, dashboard.telemetry)
    val camera: OpenCvCamera = OpenCvCameraFactory.getInstance().createWebcam(robot.webcamName, robot.cameraMonitorViewId!!)

    override fun runOpMode() {
        dashboard.startCameraStream(camera, 0.0)
        run()
    }

}
