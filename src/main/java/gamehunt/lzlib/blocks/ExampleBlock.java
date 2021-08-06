package gamehunt.lzlib.blocks;

import gamehunt.lzlib.util.RegistryEntry;
import net.minecraft.block.material.Material;

@RegistryEntry
public class ExampleBlock extends AbstractBlock{
    public ExampleBlock() {
        super("example_block", Material.GROUND);
    }
}
