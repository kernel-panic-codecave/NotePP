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
import com.withertech.NoteBlockProperties;
import com.withertech.duck.INoteBlockInstrument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoteBlock.class)
public abstract class NoteBlockMixin extends Block
{
	private static final EnumProperty<NoteBlockOctave> OCTAVE;

	static
	{
		OCTAVE = NoteBlockProperties.OCTAVE;
	}

	public NoteBlockMixin(Properties properties)
	{
		super(properties);
	}

	@Shadow
	protected abstract void playNote(Level arg, BlockPos arg2);

	@Inject(at = @At(value = "HEAD"), method = "getStateForPlacement", cancellable = true)
	public void notepp$getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext, @NotNull CallbackInfoReturnable<BlockState> cir)
	{
		cir.setReturnValue(this.defaultBlockState().setValue(NoteBlock.INSTRUMENT, ((INoteBlockInstrument) (Object) NoteBlockInstrument.byState(blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().below()))).getOctaveInstrument(NoteBlockOctave.MID)));
	}

	@ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/NoteBlock;registerDefaultState(Lnet/minecraft/world/level/block/state/BlockState;)V"), method = "<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V")
	public BlockState notepp$registerDefaultState(@NotNull BlockState state)
	{
		return state.setValue(OCTAVE, NoteBlockOctave.MID);
	}

	@Inject(at = @At(value = "HEAD"), method = "updateShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", cancellable = true)
	public void notepp$updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2, @NotNull CallbackInfoReturnable<BlockState> cir)
	{
		cir.setReturnValue(direction == Direction.DOWN ? blockState.setValue(NoteBlock.INSTRUMENT, ((INoteBlockInstrument)(Object)NoteBlockInstrument.byState(blockState2)).getOctaveInstrument(blockState.getValue(OCTAVE))) : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2));
	}

	@Inject(at = @At("HEAD"), method = "use(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;", cancellable = true)
	public void notepp$use(BlockState blockState, @NotNull Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir)
	{
		if (level.isClientSide)
		{
			cir.setReturnValue(InteractionResult.SUCCESS);
		} else if (player.isCrouching())
		{
			blockState = blockState.cycle(OCTAVE);
			blockState = blockState.setValue(NoteBlock.INSTRUMENT, ((INoteBlockInstrument) (Object) NoteBlockInstrument.byState(level.getBlockState(blockPos.below()))).getOctaveInstrument(blockState.getValue(OCTAVE)));
			level.setBlock(blockPos, blockState, 3);
			this.playNote(level, blockPos);
			player.awardStat(Stats.TUNE_NOTEBLOCK);
			cir.setReturnValue(InteractionResult.CONSUME);
		} else if (blockState.getValue(NoteBlock.NOTE) == 24)
		{
			blockState = blockState.cycle(OCTAVE);
			blockState = blockState.setValue(NoteBlock.INSTRUMENT, ((INoteBlockInstrument) (Object) NoteBlockInstrument.byState(level.getBlockState(blockPos.below()))).getOctaveInstrument(blockState.getValue(OCTAVE)));
			blockState = blockState.cycle(NoteBlock.NOTE);
			level.setBlock(blockPos, blockState, 3);
			this.playNote(level, blockPos);
			player.awardStat(Stats.TUNE_NOTEBLOCK);
			cir.setReturnValue(InteractionResult.CONSUME);
		}
	}

	@Inject(at = @At("HEAD"), method = "createBlockStateDefinition(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V")
	protected void notepp$createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci)
	{
		builder.add(OCTAVE);
	}
}
