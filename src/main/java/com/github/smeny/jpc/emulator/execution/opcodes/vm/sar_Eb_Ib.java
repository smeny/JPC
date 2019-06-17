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
import com.github.smeny.jpc.emulator.execution.UCodes;
import com.github.smeny.jpc.emulator.execution.decoder.Modrm;
import com.github.smeny.jpc.emulator.execution.decoder.PeekableInputStream;
import com.github.smeny.jpc.emulator.processor.Processor;

import static com.github.smeny.jpc.emulator.processor.Processor.Reg;

public class sar_Eb_Ib extends Executable {
  final int op1Index;
  final int immb;

  public sar_Eb_Ib(int blockStart, int eip, int prefices, PeekableInputStream input) {
    super(blockStart, eip);
    int modrm = input.readU8();
    op1Index = Modrm.Eb(modrm);
    immb = Modrm.Ib(input);
  }

  public Branch execute(Processor cpu) {
    Reg op1 = cpu.regs[op1Index];
    if (immb != 0) {
      cpu.flagOp1 = op1.get8();
      cpu.flagOp2 = immb;
      int res = (byte) (cpu.flagOp1 >> cpu.flagOp2);
      op1.set8((byte) res);
      cpu.setOSZAPC_Logic8(res);
      cpu.flagStatus |= CF;
      cpu.flagIns = UCodes.SAR8;
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