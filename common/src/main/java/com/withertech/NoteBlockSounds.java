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

package com.withertech;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class NoteBlockSounds
{
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(NotePP.MODID, Registry.SOUND_EVENT_REGISTRY);
	public static final Map<ResourceLocation, SoundEvent> SOUND_EVENTS = new LinkedHashMap<>();

	public static final SoundEvent HARP_L = add("block.note_block.harp_-1");
	public static final SoundEvent HARP_H = add("block.note_block.harp_1");

	public static final SoundEvent BASEDRUM_L = add("block.note_block.basedrum_-1");
	public static final SoundEvent BASEDRUM_H = add("block.note_block.basedrum_1");

	public static final SoundEvent SNARE_L = add("block.note_block.snare_-1");
	public static final SoundEvent SNARE_H = add("block.note_block.snare_1");

	public static final SoundEvent HAT_L = add("block.note_block.hat_-1");
	public static final SoundEvent HAT_H = add("block.note_block.hat_1");

	public static final SoundEvent BASS_L = add("block.note_block.bass_-1");
	public static final SoundEvent BASS_H = add("block.note_block.bass_1");

	public static final SoundEvent FLUTE_L = add("block.note_block.flute_-1");
	public static final SoundEvent FLUTE_H = add("block.note_block.flute_1");

	public static final SoundEvent BELL_L = add("block.note_block.bell_-1");
	public static final SoundEvent BELL_H = add("block.note_block.bell_1");

	public static final SoundEvent GUITAR_L = add("block.note_block.guitar_-1");
	public static final SoundEvent GUITAR_H = add("block.note_block.guitar_1");

	public static final SoundEvent CHIME_L = add("block.note_block.chime_-1");
	public static final SoundEvent CHIME_H = add("block.note_block.chime_1");

	public static final SoundEvent XYLOPHONE_L = add("block.note_block.xylophone_-1");
	public static final SoundEvent XYLOPHONE_H = add("block.note_block.xylophone_1");

	public static final SoundEvent IRON_XYLOPHONE_L = add("block.note_block.iron_xylophone_-1");
	public static final SoundEvent IRON_XYLOPHONE_H = add("block.note_block.iron_xylophone_1");

	public static final SoundEvent COW_BELL_L = add("block.note_block.cow_bell_-1");
	public static final SoundEvent COW_BELL_H = add("block.note_block.cow_bell_1");

	public static final SoundEvent DIDGERIDOO_L = add("block.note_block.didgeridoo_-1");
	public static final SoundEvent DIDGERIDOO_H = add("block.note_block.didgeridoo_1");

	public static final SoundEvent BIT_L = add("block.note_block.bit_-1");
	public static final SoundEvent BIT_H = add("block.note_block.bit_1");

	public static final SoundEvent BANJO_L = add("block.note_block.banjo_-1");
	public static final SoundEvent BANJO_H = add("block.note_block.banjo_1");

	public static final SoundEvent PLING_L = add("block.note_block.pling_-1");
	public static final SoundEvent PLING_H = add("block.note_block.pling_1");

	private static SoundEvent add(String key)
	{
		ResourceLocation id = NotePP.id(key);
		SoundEvent sve = new SoundEvent(id);
		SOUND_EVENTS.put(id, sve);
		return sve;
	}

	public static void register()
	{
		SOUND_EVENTS.forEach((id, sve) ->
		{
			SOUNDS.register(id.getPath(), () -> sve);
		});
		SOUNDS.register();
	}
}
