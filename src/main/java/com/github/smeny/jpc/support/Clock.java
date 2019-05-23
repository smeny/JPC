/*
    JPC: An x86 PC Hardware Emulator for a pure Java Virtual Machine
    Original project from the Physics Dept, The University of Oxford

    Copyright (C) 2007-2010 The University of Oxford

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License version 2 as published by
    the Free Software Foundation.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/
package com.github.smeny.jpc.support;

import com.github.smeny.jpc.emulator.HardwareComponent;
import com.github.smeny.jpc.emulator.Timer;
import com.github.smeny.jpc.emulator.TimerResponsive;

/**
 * Interface providing an external time source to the emulator for the provision of timed callbacks.
 *
 * @author Ian Preston
 */
public interface Clock extends HardwareComponent {
  void update(int instructions);

  void updateAndProcess(int instructions);

  void updateNowAndProcess(boolean sleep);

  long getTicks();

  long getEmulatedNanos();

  long getEmulatedMicros();

  long getRealMillis();

  /**
   * @return tick rate per second
   */
  long getTickRate();

  long getIPS();

  /**
   * Constructs a new <code>Timer</code> which will fire <code>callback</code> on the given object when the timer
   * expires.
   *
   * @param object callback object
   * @return <code>Timer</code> instance
   */
  Timer newTimer(TimerResponsive object);

  /**
   * Update the internal state of this clock to account for the change in state of the supplied child
   * <code>Timer</code>.
   *
   * @param object timer whose state has changed
   */
  void update(Timer object);

  /**
   * Pauses this clock instance.  Does nothing if this clock is already paused.
   */
  void pause();

  /**
   * Resumes this clock instance.  Does nothing if this clock is already running.
   */
  void resume();
}
