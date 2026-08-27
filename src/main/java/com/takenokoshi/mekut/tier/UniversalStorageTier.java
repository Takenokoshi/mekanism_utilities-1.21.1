package com.takenokoshi.mekut.tier;

public final class UniversalStorageTier {
    public final int invSlots;
    public final int invSlotCapacity;
    public final int fluidTankCapacity;
    public final long chemicalTankCapacity;
    public final long energyCapacity;
    public final String name;

    public UniversalStorageTier(int invSlots, int invSlotCapacity, int fluidTankCapacity, long chemicalTankCapacity,
            long energyCapacity, String name) {
        this.invSlots = invSlots;
        this.invSlotCapacity = invSlotCapacity;
        this.fluidTankCapacity = fluidTankCapacity;
        this.chemicalTankCapacity = chemicalTankCapacity;
        this.energyCapacity = energyCapacity;
        this.name = name;
    }

    public static final UniversalStorageTier NONE = new UniversalStorageTier(
            54,
            128,
            10_000,
            10_000,
            640_000,
            "none");

    public static final UniversalStorageTier DIGITAL = new UniversalStorageTier(
            108,
            256,
            20_000,
            40_000,
            2_560_000,
            "digital");

    public static final UniversalStorageTier STANDARD = new UniversalStorageTier(
            162,
            512,
            40_000,
            160_000,
            10_240_000,
            "standard");

    public static final UniversalStorageTier AUGMENT = new UniversalStorageTier(
            216,
            1_024,
            80_000,
            640_000,
            40_960_000,
            "augment");
}
