/*
 * Noteblocks++
 * Copyright (C) 2022 WitherTEch
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.withertech.mixin;

import com.withertech.NoteBlockOctave;
import com.withertech.NoteBlockSounds;
import com.withertech.duck.INoteBlockInstrument;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@Mixin(NoteBlockInstrument.class)
public abstract class NoteBlockInstrumentMixin implements INoteBlockInstrument
{
	@Shadow
	@Final
	@Mutable
	private static NoteBlockInstrument[] $VALUES;
	private static final NoteBlockInstrument HARP_L = notepp$addVariant("HARP_L", "harp_l", NoteBlockSounds.HARP_L);
	private static final NoteBlockInstrument HARP_H = notepp$addVariant("HARP_H", "harp_h", NoteBlockSounds.HARP_H);
	private static final NoteBlockInstrument BASEDRUM_L = notepp$addVariant("BASEDRUM_L", "basedrum_l", NoteBlockSounds.BASEDRUM_L);
	private static final NoteBlockInstrument BASEDRUM_H = notepp$addVariant("BASEDRUM_H", "basedrum_h", NoteBlockSounds.BASEDRUM_H);
	private static final NoteBlockInstrument SNARE_L = notepp$addVariant("SNARE_L", "snare_l", NoteBlockSounds.SNARE_L);
	private static final NoteBlockInstrument SNARE_H = notepp$addVariant("SNARE_H", "snare_h", NoteBlockSounds.SNARE_H);
	private static final NoteBlockInstrument HAT_L = notepp$addVariant("HAT_L", "hat_l", NoteBlockSounds.HAT_L);
	private static final NoteBlockInstrument HAT_H = notepp$addVariant("HAT_H", "hat_h", NoteBlockSounds.HAT_H);
	private static final NoteBlockInstrument BASS_L = notepp$addVariant("BASS_L", "bass_l", NoteBlockSounds.BASS_L);
	private static final NoteBlockInstrument BASS_H = notepp$addVariant("BASS_H", "bass_h", NoteBlockSounds.BASS_H);
	private static final NoteBlockInstrument FLUTE_L = notepp$addVariant("FLUTE_L", "flute_l", NoteBlockSounds.FLUTE_L);
	private static final NoteBlockInstrument FLUTE_H = notepp$addVariant("FLUTE_H", "flute_h", NoteBlockSounds.FLUTE_H);
	private static final NoteBlockInstrument BELL_L = notepp$addVariant("BELL_L", "bell_l", NoteBlockSounds.BELL_L);
	private static final NoteBlockInstrument BELL_H = notepp$addVariant("BELL_H", "bell_h", NoteBlockSounds.BELL_H);
	private static final NoteBlockInstrument GUITAR_L = notepp$addVariant("GUITAR_L", "guitar_l", NoteBlockSounds.GUITAR_L);
	private static final NoteBlockInstrument GUITAR_H = notepp$addVariant("GUITAR_H", "guitar_h", NoteBlockSounds.GUITAR_H);
	private static final NoteBlockInstrument CHIME_L = notepp$addVariant("CHIME_L", "chime_l", NoteBlockSounds.CHIME_L);
	private static final NoteBlockInstrument CHIME_H = notepp$addVariant("CHIME_H", "chime_h", NoteBlockSounds.CHIME_H);
	private static final NoteBlockInstrument XYLOPHONE_L = notepp$addVariant("XYLOPHONE_L", "xylophone_l", NoteBlockSounds.XYLOPHONE_L);
	private static final NoteBlockInstrument XYLOPHONE_H = notepp$addVariant("XYLOPHONE_H", "xylophone_h", NoteBlockSounds.XYLOPHONE_H);
	private static final NoteBlockInstrument IRON_XYLOPHONE_L = notepp$addVariant("IRON_XYLOPHONE_L", "iron_xylophone_l", NoteBlockSounds.IRON_XYLOPHONE_L);
	private static final NoteBlockInstrument IRON_XYLOPHONE_H = notepp$addVariant("IRON_XYLOPHONE_H", "iron_xylophone_h", NoteBlockSounds.IRON_XYLOPHONE_H);
	private static final NoteBlockInstrument COW_BELL_L = notepp$addVariant("COW_BELL_L", "cow_bell_l", NoteBlockSounds.COW_BELL_L);
	private static final NoteBlockInstrument COW_BELL_H = notepp$addVariant("COW_BELL_H", "cow_bell_h", NoteBlockSounds.COW_BELL_H);
	private static final NoteBlockInstrument DIDGERIDOO_L = notepp$addVariant("DIDGERIDOO_L", "didgeridoo_l", NoteBlockSounds.DIDGERIDOO_L);
	private static final NoteBlockInstrument DIDGERIDOO_H = notepp$addVariant("DIDGERIDOO_H", "didgeridoo_h", NoteBlockSounds.DIDGERIDOO_H);
	private static final NoteBlockInstrument BIT_L = notepp$addVariant("BIT_L", "bit_l", NoteBlockSounds.BIT_L);
	private static final NoteBlockInstrument BIT_H = notepp$addVariant("BIT_H", "bit_h", NoteBlockSounds.BIT_H);
	private static final NoteBlockInstrument BANJO_L = notepp$addVariant("BANJO_L", "banjo_l", NoteBlockSounds.BANJO_L);
	private static final NoteBlockInstrument BANJO_H = notepp$addVariant("BANJO_H", "banjo_h", NoteBlockSounds.BANJO_H);
	private static final NoteBlockInstrument PLING_L = notepp$addVariant("PLING_L", "pling_l", NoteBlockSounds.PLING_L);
	private static final NoteBlockInstrument PLING_H = notepp$addVariant("PLING_H", "pling_h", NoteBlockSounds.PLING_H);
	@Shadow
	@Final
	private String name;

	@Contract(pure = true)
	@Shadow
	public static @Nullable NoteBlockInstrument valueOf(String name) throws IllegalArgumentException
	{
		return null;
	}

	@Invoker("<init>")
	public static NoteBlockInstrument notepp$invokeInit(String internalName, int internalId, String name, SoundEvent sound)
	{
		throw new AssertionError();
	}

	private static NoteBlockInstrument notepp$addVariant(String internalName, String name, SoundEvent sound)
	{
		ArrayList<NoteBlockInstrument> variants = new ArrayList<>(Arrays.asList(NoteBlockInstrumentMixin.$VALUES));
		NoteBlockInstrument instrument = notepp$invokeInit(internalName, variants.get(variants.size() - 1).ordinal() + 1, name, sound);
		variants.add(instrument);
		NoteBlockInstrumentMixin.$VALUES = variants.toArray(new NoteBlockInstrument[0]);
		return instrument;
	}

	@Override
	public NoteBlockInstrument getOctaveInstrument(NoteBlockOctave octave)
	{
		return switch (octave)
				{
					case LOW -> valueOf(this.name.toUpperCase(Locale.ROOT) + "_L");
					case MID -> valueOf(this.name.toUpperCase(Locale.ROOT));
					case HIGH -> valueOf(this.name.toUpperCase(Locale.ROOT) + "_H");
				};
	}
}
