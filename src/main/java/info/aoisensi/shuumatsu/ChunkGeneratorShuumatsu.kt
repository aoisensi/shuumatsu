package info.aoisensi.shuumatsu

import net.minecraft.entity.EnumCreatureType
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.chunk.ChunkPrimer
import net.minecraft.world.gen.IChunkGenerator

class ChunkGeneratorShuumatsu(private val world: World) : IChunkGenerator {
    override fun generateStructures(chunkIn: Chunk?, x: Int, z: Int): Boolean {
        return false
    }

    override fun getPossibleCreatures(creatureType: EnumCreatureType?, pos: BlockPos?): MutableList<Biome.SpawnListEntry> {
        return mutableListOf()
    }

    override fun populate(x: Int, z: Int) {

    }

    override fun recreateStructures(chunkIn: Chunk?, x: Int, z: Int) {

    }

    override fun getNearestStructurePos(worldIn: World?, structureName: String?, position: BlockPos?, findUnexplored: Boolean): BlockPos? {
        return null
    }

    override fun generateChunk(x: Int, z: Int): Chunk {
        val chunkprimer = ChunkPrimer()

        for (j in 0..15) {
            for (k in 0..15) {
                chunkprimer.setBlockState(j, 0, k, Blocks.BEDROCK!!.defaultState)
                chunkprimer.setBlockState(j, 1, k, Blocks.CONCRETE!!.defaultState)
            }
        }


        val chunk = Chunk(world, chunkprimer, x, z)
        chunk.generateSkylightMap()
        return chunk
    }

    override fun isInsideStructure(worldIn: World?, structureName: String?, pos: BlockPos?): Boolean {
        return false
    }
}