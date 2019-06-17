/*
    JPC: An x86 PC Hardware Emulator for a pure Java Virtual Machine

    Copyright (C) 2012-2013 Ian Preston

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
 
    Details (including contact information) can be found at: 

    jpc.sourceforge.net
    or the developer website
    sourceforge.net/projects/jpc/

    End of licence header
*/

package com.github.smeny.jpc.emulator.execution.opcodes.vm;

import com.github.smeny.jpc.emulator.execution.Executable;
import com.github.smeny.jpc.emulator.execution.decoder.PeekableInputStream;
import com.github.smeny.jpc.emulator.processor.Processor;
import com.github.smeny.jpc.emulator.processor.ProcessorException;

public class out_DX_AL extends Executable {

  public out_DX_AL(int blockStart, int eip, int prefices, PeekableInputStream input) {
    super(blockStart, eip);
  }

  public Branch execute(Processor cpu) {
    if (cpu.checkIOPermissions8(0xFFFF & cpu.r_dx.get16())) {
      cpu.ioports.ioPortWrite8(0xFFFF & cpu.r_dx.get16(), 0xFF & cpu.r_al.get8());
    } else {
      throw ProcessorException.GENERAL_PROTECTION_0;
    }
    return Branch.None;
  }

  public boolean isBranch() {
    return false;
  }

  public String toString() {
    return this.getClass().getName();
  }
}