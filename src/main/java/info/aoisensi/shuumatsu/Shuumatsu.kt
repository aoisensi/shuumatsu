package info.aoisensi.shuumatsu

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.world.WorldType
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries


@Mod(modid = Shuumatsu.MOD_ID, name = Shuumatsu.MOD_NAME, version = Shuumatsu.VERSION, modLanguage = "kotlin")
class Shuumatsu {

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        WORLD_TYPE_SHUUMATSU = WorldTypeShuumatsu()

        val blockStatueBody = BlockStatueBody()
        BLOCK_STATUE_HEAD = BlockStatueHead()

        ForgeRegistries.BLOCKS.apply {
            register(blockStatueBody)
            register(BLOCK_STATUE_HEAD)
        }

        ForgeRegistries.ITEMS.apply {
            register(ItemBlock(blockStatueBody).setRegistryName("statue_body"))
            ITEM_BLOCK_STATUE_HEAD = ItemBlockStatueHead(BLOCK_STATUE_HEAD).setRegistryName("statue_head")
            register(ITEM_BLOCK_STATUE_HEAD)
        }

        if (event.side.isClient) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockStatueBody), 0,
                    ModelResourceLocation("$MOD_ID:statue_body", "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BLOCK_STATUE_HEAD), 0,
                    ModelResourceLocation("$MOD_ID:statue_head", "inventory"))
        }

    }

    companion object {

        const val MOD_ID = "shuumatsu"
        const val MOD_NAME = "Shuumatsu"
        const val VERSION = "0.0-dev"

        lateinit var WORLD_TYPE_SHUUMATSU: WorldType

        lateinit var BLOCK_STATUE_HEAD: BlockStatueHead

        lateinit var ITEM_BLOCK_STATUE_HEAD: Item
    }

}
