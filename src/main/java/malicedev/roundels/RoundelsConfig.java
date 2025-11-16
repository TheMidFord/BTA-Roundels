package malicedev.roundels;

import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
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
    private static final int blockIdStart = 2345;
    public static final TomlConfigHandler config;

    static {
        List<Field> blockFields = Arrays.stream(RoundelsBlocks.class.getDeclaredFields()).filter((F) -> Block.class.isAssignableFrom(F.getType())).collect(Collectors.toList());

        Toml defaultConfig = new Toml("Roundels configuration file.");

        int blockId = blockIdStart;
        for (Field blockField : blockFields) {
            defaultConfig.addEntry("BlockIDs." + blockField.getName(), blockId++);
        }

        config = new TomlConfigHandler(MOD_ID, new Toml("Roundels configuration file."),false);

        File configFile = config.getConfigFile();

        if (config.getConfigFile().exists()) {
            config.loadConfig();
            config.setDefaults(config.getRawParsed());
            Toml rawConfig = config.getRawParsed();
            Toml blockToml = (Toml) rawConfig.get(".BlockIDs");
            Toml itemToml = (Toml) rawConfig.get(".ItemIDs");
            int maxBlocks = 0;
            int maxItems = 0;
            if(blockToml != null) {
                maxBlocks = blockToml.getOrderedKeys().size();
            }
            if(itemToml != null) {
                maxItems = itemToml.getOrderedKeys().size();
            }
            int newNextBlockId = blockIdStart + maxBlocks;
            boolean changed = false;

            for (Field F : blockFields) {
                if (!rawConfig.contains("BlockIDs." + F.getName())) {
                    rawConfig.addEntry("BlockIDs." + F.getName(), newNextBlockId++);
                    changed = true;
                }
            }

            if (changed) {
                config.setDefaults(rawConfig);
                config.writeConfig();
                config.loadConfig();
            }
        } else {
            config.setDefaults(defaultConfig);
            try {
                //noinspection ResultOfMethodCallIgnored
                configFile.getParentFile().mkdirs();
                //noinspection ResultOfMethodCallIgnored
                configFile.createNewFile();
                config.writeConfig();
                config.loadConfig();
            } catch (IOException e) {
                throw new RuntimeException("Failed to generate config!", e);
            }
        }
    }

    public static int item(String cfgId) {
        return config.getInt("ItemIDs." + cfgId);
    }

    public static int block(String cfgId) {
        return config.getInt("BlockIDs." + cfgId);
    }
}
