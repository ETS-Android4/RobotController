package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.*

@TeleOp(name = "Driver Control", group = "Linear Opmode")
class DriverControl : TemplateOpMode() {
    override fun run() {

        var r: Double
        var robotAngle: Double
        var rightX: Double
        var scaleFactor = 1.0
        var lastPress = System.currentTimeMillis()

        var bl: Double
        var fl: Double
        var br: Double
        var fr: Double

        waitForStart()

        while (opModeIsActive()) {
            var xLStick = -gamepad1.left_stick_x.toDouble()
            var yLStick = -gamepad1.left_stick_y.toDouble()
            xLStick =
                if(xLStick < 0)
                    -sqrt(-xLStick)
                else
                    sqrt(xLStick)
            yLStick =
                if(yLStick < 0)
                    -sqrt(-yLStick)
                else
                    sqrt(yLStick)
            r = hypot(-xLStick, yLStick)
            robotAngle = atan2(yLStick, -xLStick) - Math.PI / 4
            rightX = 0.5 * gamepad1.right_stick_x.toDouble()
            bl = r * sin(robotAngle) + rightX
            fl = r * cos(robotAngle) + rightX
            br = r * cos(robotAngle) - rightX
            fr = r * sin(robotAngle) - rightX

            val arr = mapOf(
                Pair(robot.motorBL!!, bl * scaleFactor),
                Pair(robot.motorFL!!, fl * scaleFactor),
                Pair(robot.motorBR!!, br * scaleFactor),
                Pair(robot.motorFR!!, fr * scaleFactor)
            )

            if (gamepad1.dpad_up && scaleFactor <= 1 && System.currentTimeMillis() - lastPress > 500) {
                scaleFactor += 0.1
                lastPress = System.currentTimeMillis()
            } else if (gamepad1.dpad_down && scaleFactor >= 0 && System.currentTimeMillis() - lastPress > 500) {
                scaleFactor -= 0.1
                lastPress = System.currentTimeMillis()
            }

            dashTelemetry.addData("scaleFactor", scaleFactor)
            dashTelemetry.update()

            for(i in arr) {
                i.key.power = i.value
            }

            robot.motorDucks?.power = -gamepad2.right_stick_x.toDouble()
            robot.motorArm?.power = gamepad2.left_stick_y.toDouble()

            robot.servoArm?.position = if (gamepad2.right_trigger > 0) 1.0 else 0.0
        }
    }
}
