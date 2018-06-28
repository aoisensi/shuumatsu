package info.aoisensi.shuumatsu

import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockStatueHead : BlockHorizontal(Material.ROCK) {

    init {
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
        unlocalizedName = "Statue Head"
        setRegistryName("statue_Head")
        setLightLevel(0.0F)
        setHardness(3.0F)
        setResistance(30.0F)
        defaultState = blockState.baseState
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, flagIn: ITooltipFlag?) {
    }

    override fun getBoundingBox(state: IBlockState?, source: IBlockAccess?, pos: BlockPos?): AxisAlignedBB {
        return AABB
    }

    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

    override fun addCollisionBoxToList(state: IBlockState, worldIn: World, pos: BlockPos, entityBox: AxisAlignedBB, collidingBoxes: MutableList<AxisAlignedBB>, entityIn: Entity?, isActualState: Boolean) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        val facing = EnumFacing.getHorizontal(meta)
        return when (meta and 12) {
            0 -> this.defaultState.withProperty(FACING, facing).withProperty(PART, EnumPartType.NECK)
            4 -> this.defaultState.withProperty(FACING, facing).withProperty(PART, EnumPartType.FACE)
            8 -> this.defaultState.withProperty(FACING, facing).withProperty(PART, EnumPartType.HEAD)
            12 -> this.defaultState.withProperty(FACING, facing).withProperty(PART, EnumPartType.EDGE)
            else -> {
                throw UnknownError()
            }
        }
    }

    override fun getMetaFromState(state: IBlockState): Int {
        val i = state.getValue(FACING).horizontalIndex
        return i + when(state.getValue(PART)) {
            EnumPartType.NECK -> 0
            EnumPartType.FACE -> 4
            EnumPartType.HEAD -> 8
            EnumPartType.EDGE -> 12
        }
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, BlockHorizontal.FACING, PART)
    }

    override fun dropBlockAsItemWithChance(worldIn: World, pos: BlockPos, state: IBlockState, chance: Float, fortune: Int) {
        if (state.getValue(PART) != EnumPartType.NECK) {
            return
        }
        Block.spawnAsEntity(worldIn, pos, ItemStack(Shuumatsu.ITEM_BLOCK_STATUE_HEAD))
    }

    companion object {
        private val AABB = AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 1.0, 0.875)

        val PART = PropertyEnum.create("part", BlockStatueHead.Companion.EnumPartType::class.java)!!

        enum class EnumPartType(private val part: String) : IStringSerializable {
            NECK("neck"),
            FACE("face"),
            HEAD("head"),
            EDGE("edge");

            override fun getName(): String {
                return part
            }
        }
    }
}