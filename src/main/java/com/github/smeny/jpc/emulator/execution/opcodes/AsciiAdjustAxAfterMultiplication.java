/*
    JPC: An x86 PC Hardware Emulator for a pure Java Virtual Machine

    Copyright (C) 2012-2013 Ian Preston
    Copyright (C) 2015 Stephane Meny

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

package com.github.smeny.jpc.emulator.execution.opcodes;

import com.github.smeny.jpc.emulator.execution.Executable;
import com.github.smeny.jpc.emulator.execution.ExecutableParameters;
import com.github.smeny.jpc.emulator.execution.StaticOpcodes;
import com.github.smeny.jpc.emulator.execution.decoder.Modrm;
import com.github.smeny.jpc.emulator.processor.Processor;

public class AsciiAdjustAxAfterMultiplication extends Executable {
    private static final int BYTE_MASK = 0xFF;

    private final int immediateByte;

    public AsciiAdjustAxAfterMultiplication(ExecutableParameters parameters) {
        super(parameters.getBlockStart(), parameters.getEip());
        immediateByte = Modrm.Ib(parameters.getInput());
    }

    public Branch execute(Processor cpu) {
        StaticOpcodes.aam(cpu, BYTE_MASK & immediateByte);
        return Branch.None;
    }

}
