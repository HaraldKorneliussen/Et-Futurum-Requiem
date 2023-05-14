package ganymedes01.etfuturum.api;

import java.util.HashMap;
import java.util.Map;

import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.configuration.configs.ConfigBlocksItems;
import ganymedes01.etfuturum.api.mappings.BlockAndMetadataMapping;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class StrippedLogRegistry {

	private static final Map<BlockAndMetadataMapping, BlockAndMetadataMapping> strippedLogs = new HashMap<BlockAndMetadataMapping, BlockAndMetadataMapping>();
	
	/**
	 * Adds a specified log and its metadata to be converted to another specified log and its metadata.
	 * This is wrapped by 4, so you don't have to add each log multiple times for every different rotation.
	 * For example, log:0 is mapped to convert to stripped_log:0, but the other rotations, 4 and 8 also work.
	 * 
	 * @param from
	 * @param fromMeta Wrapped by 4
	 * @param to
	 * @param toMeta Wrapped by 4
	 */
	public static void addLog(Block from, int fromMeta, Block to, int toMeta) {
		addLog(new BlockAndMetadataMapping(from, fromMeta % 4), new BlockAndMetadataMapping(to, toMeta % 4));
	}
	
	private static void addLog(BlockAndMetadataMapping from, BlockAndMetadataMapping to) {
		strippedLogs.put(from, to);
	}
	
	/**
	 * @param block
	 * @param meta Wrapped by 4
	 * @return True if this log and its metadata has a stripped variant.
	 */
	public static boolean hasLog(Block block, int meta) {
		return hasLog(new BlockAndMetadataMapping(block, meta % 4));
	}
	
	private static boolean hasLog(BlockAndMetadataMapping map) {
		return strippedLogs.containsKey(map);
	}

	/**
	 * @param block
	 * @param meta Wrapped by 4
	 * @return A mapping containing the stripped alternative of the input block. This is
	 * an instance of the BlockAndMetadataMapping class, containing a variable with
	 * the block instance and the meta data it should be replaced with.
	 */
	public static BlockAndMetadataMapping getLog(Block block, int meta) {
		return getLog(new BlockAndMetadataMapping(block, meta % 4));
	}
	
	private static BlockAndMetadataMapping getLog(BlockAndMetadataMapping map) {
		return strippedLogs.get(map);
	}

	/**
	 * @return
	 * The entire stripped log mapping, where a metadata/block pair is the key.
	 * The key's return value is of the class BlockAndMetadataMapping, which just store
	 * a Block instance, and a metadata value.
	 * 
	 * Do not use this to add or get items from the map this way,
	 * in case the key changes.
	 */
	public static Map<BlockAndMetadataMapping, BlockAndMetadataMapping> getLogMap() {
		return strippedLogs;
	}

	public static void init() {
		if(ModBlocks.LOG_STRIPPED.isEnabled()) {
			strippedLogs.put(new BlockAndMetadataMapping(Blocks.log, 0), new BlockAndMetadataMapping(ModBlocks.LOG_STRIPPED.get(), 0));
			strippedLogs.put(new BlockAndMetadataMapping(Blocks.log, 1), new BlockAndMetadataMapping(ModBlocks.LOG_STRIPPED.get(), 1));
			strippedLogs.put(new BlockAndMetadataMapping(Blocks.log, 2), new BlockAndMetadataMapping(ModBlocks.LOG_STRIPPED.get(), 2));
			strippedLogs.put(new BlockAndMetadataMapping(Blocks.log, 3), new BlockAndMetadataMapping(ModBlocks.LOG_STRIPPED.get(), 3));
		}
		if(ModBlocks.LOG2_STRIPPED.isEnabled()) {
			strippedLogs.put(new BlockAndMetadataMapping(Blocks.log2, 0), new BlockAndMetadataMapping(ModBlocks.LOG2_STRIPPED.get(), 0));
			strippedLogs.put(new BlockAndMetadataMapping(Blocks.log2, 1), new BlockAndMetadataMapping(ModBlocks.LOG2_STRIPPED.get(), 1));
		}

		if(ModBlocks.BARK.isEnabled() && ModBlocks.WOOD_STRIPPED.isEnabled()) {
			strippedLogs.put(new BlockAndMetadataMapping(ModBlocks.BARK.get(), 0), new BlockAndMetadataMapping(ModBlocks.WOOD_STRIPPED.get(), 0));
			strippedLogs.put(new BlockAndMetadataMapping(ModBlocks.BARK.get(), 1), new BlockAndMetadataMapping(ModBlocks.WOOD_STRIPPED.get(), 1));
			strippedLogs.put(new BlockAndMetadataMapping(ModBlocks.BARK.get(), 2), new BlockAndMetadataMapping(ModBlocks.WOOD_STRIPPED.get(), 2));
			strippedLogs.put(new BlockAndMetadataMapping(ModBlocks.BARK.get(), 3), new BlockAndMetadataMapping(ModBlocks.WOOD_STRIPPED.get(), 3));
		}

		if(ModBlocks.BARK2.isEnabled() && ModBlocks.WOOD2_STRIPPED.isEnabled()) {
			strippedLogs.put(new BlockAndMetadataMapping(ModBlocks.BARK2.get(), 0), new BlockAndMetadataMapping(ModBlocks.WOOD2_STRIPPED.get(), 0));
			strippedLogs.put(new BlockAndMetadataMapping(ModBlocks.BARK2.get(), 1), new BlockAndMetadataMapping(ModBlocks.WOOD2_STRIPPED.get(), 1));
		}
	}


}
