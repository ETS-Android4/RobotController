package org.firstinspires.ftc.teamcode

import org.openftc.easyopencv.OpenCvCamera.AsyncCameraOpenListener
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvPipeline


class OpenCV(private val opMode: TemplateOpMode, private val Pipeline: OpenCvPipeline) {
    init {
        opMode.camera.openCameraDeviceAsync(object : AsyncCameraOpenListener {
            override fun onOpened() {
                opMode.camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT)
                opMode.camera.setPipeline(Pipeline)
            }

            override fun onError(errorCode: Int) {
                opMode.dashTelemetry.addData("OpenCV Error:", errorCode)
            }
        })
    }
}