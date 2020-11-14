package net.romvoid95.common.world.testing;

import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.map.HashedMap;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.romvoid95.ExoInfo;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID)
public class ExoplanetsTestWorld {
	
	public static final Version V1 = new Version(1, 0, 0);
	
	private static final Map<World, ExoplanetsTestWorld> INSTANCE_CACHE = new HashedMap<>();
	private final World world;
	private Random generatorRandom = null;
	
    private ExoplanetsTestWorld(World world) {

        this.world = world;
    }
	
    public static ExoplanetsTestWorld getInstance(World world) {
        if (!INSTANCE_CACHE.containsKey(world)) {
            INSTANCE_CACHE.put(world, new ExoplanetsTestWorld(world));
        }
        return INSTANCE_CACHE.get(world);
    }
    
    /**
     * Syncs this objects Random to the chunk generator
     *
     * @param random the chunk generator Random
     */
    public void setRandom(Random random) {
        if (this.generatorRandom == null) {
            this.generatorRandom = random;
        }
    }

    /**
     * Gets the {@link World} object for this wrapper.
     *
     * @return The world object for this wrapper
     * @since 1.0.0
     */
    public World world() {
        return this.world;
    }
	
	public static class Version {
	    public static final Version NULL_VERSION = new Version(0, 0, 0);

	    public final int major;
	    public final int minor;
	    public final int patch;

	    public Version(int major, int minor, int patch) {
	        this.major = major;
	        this.minor = minor;
	        this.patch = patch;
	    }
	    
	    public static String toString(Version version) {
	    	String mj = String.valueOf(version.major) + ".";
	    	String mi = String.valueOf(version.minor) + ".";
	    	String mp = String.valueOf(version.patch);
	    	return mj + mi + mp;
	    	
	    }
	    
	    public static boolean isVersionLessOrEqual(Version comparate1, Version comparate2) {
	        if (comparate1.major > comparate2.major) {
	            return false;
	        } else if (comparate1.major == comparate2.major) {
	            if (comparate1.minor > comparate2.minor) {
	                return false;
	            } else if (comparate1.major == comparate2.major && comparate1.minor == comparate2.minor) {
	                if (comparate1.patch > comparate2.patch) {
	                    return false;
	                }
	            }
	            return true;
	        }
	        return true;
	    }
	}
}
