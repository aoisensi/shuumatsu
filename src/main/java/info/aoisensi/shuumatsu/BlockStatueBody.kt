package info.aoisensi.shuumatsu

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockStatueBody : Block(Material.ROCK) {
    init {
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
        unlocalizedName = "Statue Body"
        setRegistryName("statue_body")
        setLightLevel(0.0F)
        setHardness(3.0F)
        setResistance(30.0F)
        defaultState = blockState.baseState
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, flagIn: ITooltipFlag?) { }

    /**
     * @deprecated call via {@link IBlockState#getBoundingBox(IBlockAccess,BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
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

    companion object {
        private val AABB = AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 1.0, 0.875)
    }
}