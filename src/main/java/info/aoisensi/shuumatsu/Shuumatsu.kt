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
        val blockStatueHead = BlockStatueHead()

        ForgeRegistries.BLOCKS.apply {
            register(blockStatueBody)
            register(blockStatueHead)
        }

        ForgeRegistries.ITEMS.apply {
            register(ItemBlock(blockStatueBody).setRegistryName("statue_body"))
            register(ItemBlock(blockStatueHead).setRegistryName("statue_head"))
        }

        if (event.side.isClient) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockStatueBody), 0,
                    ModelResourceLocation("$MOD_ID:statue_body", "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockStatueHead), 0,
                    ModelResourceLocation("$MOD_ID:statue_head", "inventory"))
        }

    }

    companion object {

        const val MOD_ID = "shuumatsu"
        const val MOD_NAME = "Shuumatsu"
        const val VERSION = "0.0-dev"

        lateinit var WORLD_TYPE_SHUUMATSU: WorldType
    }

}
