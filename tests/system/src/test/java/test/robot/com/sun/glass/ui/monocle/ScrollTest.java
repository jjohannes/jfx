/*
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package test.robot.com.sun.glass.ui.monocle;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import com.sun.glass.ui.monocle.TestLogShim;
import test.robot.com.sun.glass.ui.monocle.input.devices.TestTouchDevice;

/**
 * Scroll tests generated by one and more touch points.
 *  */
public class ScrollTest extends ScrollTestBase {

    public ScrollTest(TestTouchDevice device) {
        super(device);
    }

    private int getDelta() throws Exception {
        int max = Math.max(getScrollThreshold(), device.getTapRadius());
        return Math.max(max, 30) + 1;
    }

    /**
     * Tap one finger, drag it up few times in order to scroll
     */
    @Test
    public void testScrollUp() throws Exception {
        pressFirstFinger();
        moveOneFinger(0, -getDelta(), 3, true);
        releaseFirstFinger();
    }

    /**
     * Tap one finger, drag it to the right few times in order to scroll
     */
    @Test
    public void testScrollRight() throws Exception {
        pressFirstFinger();
        moveOneFinger(getDelta(), 0, 2, true);
        releaseFirstFinger();
    }

    /**
     * Tap one finger, drag it down few times in order to scroll
     */
    @Test
    public void testScrollDown() throws Exception {
        pressFirstFinger();
        moveOneFinger(0, getDelta(), 5, true);
        releaseFirstFinger();
    }

    /**
     * Tap one finger, drag it to the left few times in order to scroll
     */
    @Test
    public void testScrollLeft() throws Exception {
        pressFirstFinger();
        moveOneFinger(-getDelta() * 2, 0, 4, true);
        releaseFirstFinger();
    }

    /**
     * Tap one finger, drag it up & right few times in order to scroll
     */
    @Test
    public void testScrollUpAndRight() throws Exception {
        pressFirstFinger();
        moveOneFinger(getDelta(), -getDelta(), 3, true);
        releaseFirstFinger();
    }

    /**
     * Tap one finger, drag it down & right few times in order to scroll
     */
    @Test
    public void testScrollDownAndRight() throws Exception {
        pressFirstFinger();
        moveOneFinger(getDelta(), getDelta(), 2, true);
        releaseFirstFinger();
    }

    /**
     * Tap one finger, drag it left & down few times in order to scroll
     */
    @Test
    public void testScrollDownAndLeft() throws Exception {
        pressFirstFinger();
        moveOneFinger(-getDelta(), getDelta(), 5, true);
        releaseFirstFinger();
    }

    /**
     * Tap one finger, drag it left & up few times in order to scroll
     */
    @Test
    public void testScrollUpAndLeft() throws Exception {
        pressFirstFinger();
        moveOneFinger(-getDelta(), -getDelta() * 2, 4, true);
        releaseFirstFinger();
    }

    /**
     * Tap two fingers, scroll up
     */
    @Test
    public void testTwoFingersScrollUp() throws Exception {
        Assume.assumeTrue(device.getPointCount() >= 2);
        pressFirstFinger();
        pressSecondFinger();
        moveTwoFingers(0, -getDelta(), 3, true, false);
        releaseAllFingers();
    }

    /**
     * Tap two fingers, scroll up, scroll down
     */
    @Test
    public void testTwoFingersScrollTwice() throws Exception {
        Assume.assumeTrue(device.getPointCount() >= 2);
        pressFirstFinger();
        pressSecondFinger();
        moveTwoFingers(0, -getDelta(), 1, true, false);
        moveTwoFingers(0, getDelta() * 2, 1, false, false);
        releaseAllFingers();
    }

    /**
     * Tap one finger, scroll down, add second finger, continue scrolling down
     */
    @Test
    public void testTwoFingersScroll1() throws Exception {
        Assume.assumeTrue(device.getPointCount() >= 2);
        pressFirstFinger();
        moveOneFinger(0, getDelta(), 2, true);
        pressSecondFinger();
        moveTwoFingers(0, getDelta(), 3, false, true);
        releaseAllFingers();
    }


    /**
     * Tap two fingers, scroll down, release one finger, continue scrolling down
     */
    @Test
    public void testTwoFingersScroll2()
            throws Exception {
        Assume.assumeTrue(device.getPointCount() >= 2);
        pressFirstFinger();
        pressSecondFinger();
        moveTwoFingers(0, getDelta(), 1, true, false);
        releaseSecondFinger();
        moveOneFinger(0, getDelta(), 2, false);
        releaseFirstFinger();
    }

    /**
     * Tap one finger, scroll down,
     * add second finger, continue scrolling down,
     * remove second finger, continue scrolling down.
     */
    @Test
    public void testTwoFingersScroll3()
            throws Exception {
        Assume.assumeTrue(device.getPointCount() >= 2);
        pressFirstFinger();
        moveOneFinger(0, getDelta(), 2, true);
        pressSecondFinger();
        moveTwoFingers(0, -getDelta() * 2, 2, false, true);
        releaseSecondFinger();
        moveOneFinger(0, getDelta(), 2, false);
        releaseFirstFinger();
    }

    /**
     * Tap two fingers, scroll them down asymmetrically: first finger's location
     * changed by delta, second finger's location changed by (delta * 2)
     */
    @Test
    public void testTwoFingersAsymmetricScroll() throws Exception {
        Assume.assumeTrue(device.getPointCount() >= 2);
        int deltaY1 = getDelta() + 1;
        int deltaY2 = deltaY1 * 2;
        int numOfIterations = 4;
        Assert.assertTrue(paramsValid(0, deltaY1, numOfIterations,
                point1X, point1Y) &&
                paramsValid(0, deltaY2, numOfIterations,
                        point2X, point2Y));
        TestLogShim.reset();
        p1 = device.addPoint(point1X, point1Y);
        p2 = device.addPoint(point2X, point2Y);
        device.sync();
        //verify fingers pressed
        TestLogShim.waitForLogContaining("TouchPoint: PRESSED %d, %d", point1X, point1Y);
        TestLogShim.waitForLogContaining("TouchPoint: PRESSED %d, %d", point2X, point2Y);
        point1Y += deltaY1;
        point2Y += deltaY2;
        int avgDelta = (deltaY1 + deltaY2) / 2;
        //scroll fingers
        TestLogShim.reset();
        device.setPoint(p1, point1X, point1Y);
        device.setPoint(p2, point2X, point2Y);
        device.sync();
        TestLogShim.waitForLogContaining("TouchPoint: MOVED %d, %d", point1X, point1Y);
        TestLogShim.waitForLogContaining("TouchPoint: MOVED %d, %d", point2X, point2Y);
        totalDeltaY = avgDelta;
        TestLogShim.waitForLogContaining("Scroll started, DeltaX: " + 0
                + ", DeltaY: " + 0
                + ", totalDeltaX: " + 0
                + ", totalDeltaY: " + 0
                + ", touch points: " + 2
                + ", inertia value: false");
        TestLogShim.waitForLogContaining("Scroll, DeltaX: " + 0
                + ", DeltaY: " + avgDelta
                + ", totalDeltaX: " + 0
                + ", totalDeltaY: " + totalDeltaY
                + ", touch points: " + 2
                + ", inertia value: false");
        String expectedLog;
        for (int i = 2; i <= numOfIterations; i++) {
            point1Y += deltaY1;
            point2Y += deltaY2;
            TestLogShim.reset();
            device.setPoint(p1, point1X, point1Y);
            device.setPoint(p2, point2X, point2Y);
            device.sync();
            TestLogShim.waitForLogContaining("TouchPoint: MOVED %d, %d", point1X, point1Y);
            TestLogShim.waitForLogContaining("TouchPoint: MOVED %d, %d", point2X, point2Y);
            totalDeltaY = avgDelta * i;
            expectedLog = "Scroll, DeltaX: " + 0 + ", DeltaY: " + avgDelta
                    + ", totalDeltaX: " + 0
                    + ", totalDeltaY: " + totalDeltaY
                    + ", touch points: " + 2 + ", inertia value: false";
            TestLogShim.waitForLogContaining(expectedLog);
        }
        releaseAllFingers();
    }
}
