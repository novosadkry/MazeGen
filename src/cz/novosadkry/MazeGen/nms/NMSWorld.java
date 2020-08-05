package cz.novosadkry.MazeGen.nms;

import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.IBlockData;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.util.CraftMagicNumbers;

public class NMSWorld {
    public static void setBlockInNativeWorld(World world, int x, int y, int z, Material mat, boolean applyPhysics) {
        net.minecraft.server.v1_15_R1.World nmsWorld = ((CraftWorld)world).getHandle();
        BlockPosition bp = new BlockPosition(x, y, z);
        IBlockData ibd = CraftMagicNumbers.getBlock(mat).getBlockData();
        nmsWorld.setTypeAndData(bp, ibd, applyPhysics ? 3 : 2);
    }
}
