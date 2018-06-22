package info.aoisensi.shuumatsu

import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemBlock
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World

class ItemBlockStatueHead(block: Block?) : ItemBlock(block) {
    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        var posNeck = pos
        if (worldIn.isRemote) {
            return EnumActionResult.SUCCESS
        }

        if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL
        }

        val state = worldIn.getBlockState(posNeck)
        val block = state.block
        val flagReplaceable = block.isReplaceable(worldIn, posNeck)
        if (!flagReplaceable) {
            posNeck = posNeck.up()
        }

        val i = MathHelper.floor((player.rotationYaw * 4.0F / 360.0F).toDouble() + 0.5) and 3
        val facing = EnumFacing.getHorizontal(i)
        val posFace = posNeck.offset(facing)
        val posHead = posNeck.up()
        val posEdge = posFace.up()
        val stack = player.getHeldItem(hand)

        val flag = arrayOf(posNeck, posFace, posHead, posEdge).all {
            player.canPlayerEdit(it, facing, stack) and
                    ((posNeck == it) or worldIn.getBlockState(it).block.isReplaceable(worldIn, it)) and
                    worldIn.isAirBlock(it)
        }
        if (!flag) {
            return EnumActionResult.FAIL
        }

        val blockState = Shuumatsu.BLOCK_STATUE_HEAD.defaultState
                .withProperty(BlockHorizontal.FACING, facing)

        worldIn.setBlockState(posNeck, blockState.withProperty(BlockStatueHead.PART, BlockStatueHead.Companion.EnumPartType.NECK))
        worldIn.setBlockState(posFace, blockState.withProperty(BlockStatueHead.PART, BlockStatueHead.Companion.EnumPartType.FACE))
        worldIn.setBlockState(posHead, blockState.withProperty(BlockStatueHead.PART, BlockStatueHead.Companion.EnumPartType.HEAD))
        worldIn.setBlockState(posEdge, blockState.withProperty(BlockStatueHead.PART, BlockStatueHead.Companion.EnumPartType.EDGE))

        val sound = blockState.block.getSoundType(blockState, worldIn, pos, player)
        worldIn.playSound(null, pos, sound.placeSound, SoundCategory.BLOCKS, (sound.getVolume() + 1.0f) / 2.0f, sound.getPitch() * 0.8f)

        return EnumActionResult.SUCCESS
    }
}