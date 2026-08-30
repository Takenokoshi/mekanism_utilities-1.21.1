package com.takenokoshi.mekut.registries;

import com.takenokoshi.mekut.core.MekUtConstants;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalBuilder;
import mekanism.common.registration.impl.ChemicalDeferredRegister;
import mekanism.common.registration.impl.DeferredChemical;

public class MekUtChemicals {
    public static final ChemicalDeferredRegister CHEMICALS = new ChemicalDeferredRegister(MekUtConstants.MODID);

    public static final DeferredChemical<?> REFINED_LAPIS_LAZULI = CHEMICALS
            .registerInfuse("refined_lapis_lazuli", 0x1800A8);
    public static final DeferredChemical<?> XP = CHEMICALS.register("xp", 0x7f53ff00);
    public static final DeferredChemical<?> ENRICHED_XP = CHEMICALS.register("enriched_xp", 0x7f29ff00);
    public static final DeferredChemical<?> ASTRAL_ETHER = CHEMICALS.register("astral_ether", 0xD4A1FF);
    public static final DeferredChemical<?> AMETHYST = CHEMICALS.registerInfuse("amethyst", 0x7A73B8);
    public static final DeferredChemical<?> GLOWSTONE = CHEMICALS.registerInfuse("glowstone", 0xFFBC5E);
    public static final DeferredChemical<?> IRIDIUM = CHEMICALS.register("iridium", 0xC4CCD8);
    public static final DeferredChemical<?> NETHERITE = CHEMICALS.register("netherite", 0x5A4E52);

    public static final DeferredChemical<?> ACETYLENE = CHEMICALS.register("acetylene", 0xD6C9B8);
    public static final DeferredChemical<?> BENZENE = CHEMICALS.register("benzene", 0xB8C7D8);
    public static final DeferredChemical<?> CYCLOHEXANE = CHEMICALS.register("cyclohexane", 0x9FBFC2);
    public static final DeferredChemical<?> KA_OIL = CHEMICALS.register("ka_oil", 0x80602F);
    public static final DeferredChemical<?> ADIPIC_ACID = CHEMICALS.register("adipic_acid", 0xEDE7D3);
    public static final DeferredChemical<?> ADIPAMIDE = CHEMICALS.register("adipamide", 0xD4D0BD);
    public static final DeferredChemical<?> ADIPONITRILE = CHEMICALS.register("adiponitrile", 0x9BB9D1);
    public static final DeferredChemical<?> HEXAMETHYLENE_DIAMINE = CHEMICALS
            .register("hexamethylene_diamine", 0xD8BFA3);
    public static final DeferredChemical<?> POLYAMIDE_FIBER = CHEMICALS.register("polyamide_fiber", 0xF5F0DF);

    public static final DeferredChemical<?> I_II_DICHLOROETHANE = CHEMICALS.register("1.2-dichloroethane", 0xC7D9D2);
    public static final DeferredChemical<?> CHLOROETHYLENE = CHEMICALS.register("chloroethylene", 0xB8C9D8);
    public static final DeferredChemical<?> POLYVINYL_CHLORIDE = CHEMICALS.register("polyvinyl_chloride", 0xD5D2C7);
    public static final DeferredChemical<?> PROPYLENE = CHEMICALS.register("propylene", 0xD4B8A6);
    public static final DeferredChemical<?> POLYPROPYLENE = CHEMICALS.register("polypropylene", 0xE0D2B8);
    public static final DeferredChemical<?> HYDROGEN_CHLORIDE_OXIGEN_MIXED_GAS = CHEMICALS
            .register("hydrogen_chloride-oxigen_mixed_gas", 0xB8B6C8);
    public static final DeferredChemical<?> CARBON_MONOXIDE = CHEMICALS.register("carbon_monoxide", 0x6E7278);
    public static final DeferredChemical<?> METHANOL = CHEMICALS.register("methanol", 0xAFC8D8);

    public static final DeferredChemical<?> CLEAN_AMETHYST_SLURRY = CHEMICALS.register(
            "clean_amethyst_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7FA361FF)));
    public static final DeferredChemical<?> CLEAN_CERTUS_QUARTZ_SLURRY = CHEMICALS.register(
            "clean_certus_quartz_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7FC9F2FF)));
    public static final DeferredChemical<?> CLEAN_COAL_SLURRY = CHEMICALS.register(
            "clean_coal_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7FD2D2D)));
    public static final DeferredChemical<?> CLEAN_DIAMOND_SLURRY = CHEMICALS.register(
            "clean_diamond_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7F5CDBD5)));
    public static final DeferredChemical<?> CLEAN_EMERALD_SLURRY = CHEMICALS.register(
            "clean_emerald_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7F11C95A)));
    public static final DeferredChemical<?> CLEAN_ENTRO_SLURRY = CHEMICALS.register(
            "clean_entro_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7F03B99A)));
    public static final DeferredChemical<?> CLEAN_FLUORITE_SLURRY = CHEMICALS.register(
            "clean_fluorite_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7F78FFBE)));
    public static final DeferredChemical<?> CLEAN_LAPIS_LAZULI_SLURRY = CHEMICALS.register(
            "clean_lapis_lazuli_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7F2661DB)));
    public static final DeferredChemical<?> CLEAN_NETHERITE_SLURRY = CHEMICALS.register(
            "clean_netherite_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7F433D47)));
    public static final DeferredChemical<?> CLEAN_OVERLOAD_SLURRY = CHEMICALS.register(
            "clean_overload_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7FFFA8FD)));
    public static final DeferredChemical<?> CLEAN_QUARTZ_SLURRY = CHEMICALS.register(
            "clean_quartz_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7FF5E6DC)));
    public static final DeferredChemical<?> CLEAN_REDSTONE_SLURRY = CHEMICALS.register(
            "clean_redstone_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7FC81E1E)));
    public static final DeferredChemical<?> CLEAN_SILICON_SLURRY = CHEMICALS.register(
            "clean_silicon_slurry", () -> new Chemical(ChemicalBuilder.cleanSlurry().tint(0x7F858585)));

    public static final DeferredChemical<?> DIRTY_AMETHYST_SLURRY = CHEMICALS.register(
            "dirty_amethyst_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7FA361FF)));
    public static final DeferredChemical<?> DIRTY_CERTUS_QUARTZ_SLURRY = CHEMICALS.register(
            "dirty_certus_quartz_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7FC9F2FF)));
    public static final DeferredChemical<?> DIRTY_COAL_SLURRY = CHEMICALS.register(
            "dirty_coal_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7FD2D2D)));
    public static final DeferredChemical<?> DIRTY_DIAMOND_SLURRY = CHEMICALS.register(
            "dirty_diamond_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7F5CDBD5)));
    public static final DeferredChemical<?> DIRTY_EMERALD_SLURRY = CHEMICALS.register(
            "dirty_emerald_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7F11C95A)));
    public static final DeferredChemical<?> DIRTY_ENTRO_SLURRY = CHEMICALS.register(
            "dirty_entro_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7F03B99A)));
    public static final DeferredChemical<?> DIRTY_FLUORITE_SLURRY = CHEMICALS.register(
            "dirty_fluorite_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7F78FFBE)));
    public static final DeferredChemical<?> DIRTY_LAPIS_LAZULI_SLURRY = CHEMICALS.register(
            "dirty_lapis_lazuli_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7F2661DB)));
    public static final DeferredChemical<?> DIRTY_NETHERITE_SLURRY = CHEMICALS.register(
            "dirty_netherite_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7F433D47)));
    public static final DeferredChemical<?> DIRTY_OVERLOAD_SLURRY = CHEMICALS.register(
            "dirty_overload_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7FFFA8FD)));
    public static final DeferredChemical<?> DIRTY_QUARTZ_SLURRY = CHEMICALS.register(
            "dirty_quartz_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7FF5E6DC)));
    public static final DeferredChemical<?> DIRTY_REDSTONE_SLURRY = CHEMICALS.register(
            "dirty_redstone_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7FC81E1E)));
    public static final DeferredChemical<?> DIRTY_SILICON_SLURRY = CHEMICALS.register(
            "dirty_silicon_slurry", () -> new Chemical(ChemicalBuilder.dirtySlurry().tint(0x7F858585)));
}
