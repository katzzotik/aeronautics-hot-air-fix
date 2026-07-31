package com.katzzotik.aeronauticshotairfix;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;

import java.nio.file.Path;
import java.util.Optional;

@Mod(AeronauticsHotAirFix.MODID)
public class AeronauticsHotAirFix {

    public static final String MODID = "aeronauticshotairfix";

    public AeronauticsHotAirFix(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::addPackFinders);
    }

    private void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() != PackType.CLIENT_RESOURCES) {
            return;
        }

        Path path = ModList.get()
                .getModFileById(MODID)
                .getFile()
                .findResource("resourcepacks/hot_air_fix");

        Pack pack = Pack.readMetaAndCreate(
                new PackLocationInfo(
                        MODID + ":hot_air_fix",
                        Component.literal("Aeronautics Hot Air Fix"),
                        PackSource.BUILT_IN,
                        Optional.empty()
                ),
                new PathPackResources.PathResourcesSupplier(path),
                PackType.CLIENT_RESOURCES,
                new PackSelectionConfig(
                        true,
                        Pack.Position.TOP,
                        false
                )
        );

        if (pack != null) {
            event.addRepositorySource(consumer -> consumer.accept(pack));
        }
    }
}