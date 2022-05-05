package org.firstinspires.ftc.teamcode.pipelines

import org.opencv.core.Mat
import org.openftc.easyopencv.OpenCvPipeline

// copy-paste this file for every new pipeline
class TemplatePipeline: OpenCvPipeline() {

    enum class Stage {
        EXAMPLE,
        EXAMPLE_TWO;

    }

    private var currentStage: Stage = Stage.EXAMPLE
    private val stages = Stage.values()

    override fun onViewportTapped() {

        val currentStageNum: Int = currentStage.ordinal

        var nextStageNum = currentStageNum + 1

        if (nextStageNum >= stages.size) {
            nextStageNum = 0
        }

        currentStage = stages[nextStageNum]
    }

    override fun processFrame(input: Mat?): Mat {
        return when (currentStage) {
            Stage.EXAMPLE -> Mat()
            Stage.EXAMPLE_TWO -> Mat()
        }
    }
}