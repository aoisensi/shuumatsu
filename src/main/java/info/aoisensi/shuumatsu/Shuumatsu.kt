package info.aoisensi.shuumatsu

import net.minecraft.world.WorldType
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = Shuumatsu.MOD_ID, name = Shuumatsu.MOD_NAME, version = Shuumatsu.VERSION, modLanguage = "kotlin")
class Shuumatsu {

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        WORLD_TYPE_SHUUMATSU = WorldTypeShuumatsu()
    }

    companion object {

        const val MOD_ID = "shuumatsu"
        const val MOD_NAME = "Shuumatsu"
        const val VERSION = "1.0-SNAPSHOT"

        var WORLD_TYPE_SHUUMATSU: WorldType? = null
    }

}
