package info.aoisensi.shuumatsu

import net.minecraft.world.World
import net.minecraft.world.WorldType
import net.minecraft.world.gen.IChunkGenerator

class WorldTypeShuumatsu: WorldType("shuumatsu") {

    override fun getChunkGenerator(world: World, generatorOptions: String?): IChunkGenerator {
        return ChunkGeneratorShuumatsu(world)
    }
}