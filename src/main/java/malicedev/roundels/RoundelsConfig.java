package malicedev.roundels;

import net.minecraft.core.block.Block;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static malicedev.roundels.Roundels.MOD_ID;

public class RoundelsConfig {
    private static int blockIdStart;
	public static int currentId;
    public static final TomlConfigHandler config;

    static {
        Toml defaultConfig = new Toml("Roundels configuration file.");
		defaultConfig.addEntry("startingId", 2345);

        config = new TomlConfigHandler(MOD_ID, new Toml("Roundels configuration file."),false);

        File configFile = config.getConfigFile();

        if (config.getConfigFile().exists()) {
            config.loadConfig();
            config.setDefaults(config.getRawParsed());
            Toml rawConfig = config.getRawParsed();
            boolean changed = false;

			if(!rawConfig.contains("startingId")){
				rawConfig.addEntry("startingId", 2345);
				changed = true;
			}

            if (changed) {
                config.setDefaults(rawConfig);
                config.writeConfig();
                config.loadConfig();
            }

			blockIdStart = config.getInt("startingId");
			currentId = blockIdStart;
        } else {
            config.setDefaults(defaultConfig);
            try {
                //noinspection ResultOfMethodCallIgnored
                configFile.getParentFile().mkdirs();
                //noinspection ResultOfMethodCallIgnored
                configFile.createNewFile();
                config.writeConfig();
                config.loadConfig();

				blockIdStart = config.getInt("startingId");
				currentId = blockIdStart;
            } catch (IOException e) {
				blockIdStart = 2345;
				currentId = blockIdStart;
                throw new RuntimeException("Failed to generate config!", e);
            }
        }
    }

	public static int getStartingId() {
		return blockIdStart;
	}
}
