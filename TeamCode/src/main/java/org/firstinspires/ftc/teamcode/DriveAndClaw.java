package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "DriveAndClaw", group = "TeleOp")

public class DriveAndClaw extends OpMode {
    DcMotor left;
    DcMotor right;
    DcMotor arm;
    CRServo claw;
    Gamepad previousGamepad;
    Gamepad currentGamepad;


    float armPower=0;

    @Override
    public void init() {
        left=hardwareMap.dcMotor.get("left");
        right=hardwareMap.dcMotor.get("right");
        claw=hardwareMap.crservo.get("claw");
        arm=hardwareMap.dcMotor.get("arm");
        right.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.REVERSE);
        Gamepad previousGamepad = gamepad1;
        Gamepad currentGamepad = gamepad1;


    }

    @Override
    public void loop() {
        //record last button pressed
        previousGamepad=currentGamepad;
        currentGamepad=gamepad1;
        //record new button pressed
        //if you do ___, activate the left motor
        left.setPower(gamepad1.left_stick_y);
        right.setPower(gamepad1.right_stick_y);
        //if you do ___, activi
        //L2 claw open, R2 claw close
        if(gamepad1.left_bumper) {
            claw.setPower(1);
        } else if (gamepad1.right_bumper){
            claw.setPower(-1);
        } else{
            claw.setPower(0);
        }
        //up arrow is arm raise, down arrow is arm lower

        //if previously, the dpad_up button was FALSE and currently, the dpad_up button is TRUE, make the motor spin more in the positive direction


        if(!previousGamepad.dpad_up && currentGamepad.dpad_up){
            //turn arm motor in the positive direction
            armPower +=0.05;
            arm.setPower(armPower);

        } else if (!previousGamepad.dpad_down && currentGamepad.dpad_down){
            //turn arm motor in the negative direction
            armPower -=0.05;
            arm.setPower(armPower);

        }


    }
}