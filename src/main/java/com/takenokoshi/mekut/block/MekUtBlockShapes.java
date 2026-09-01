package com.takenokoshi.mekut.block;

import mekanism.common.util.VoxelShapeUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MekUtBlockShapes {
    public static final VoxelShape[] GREEN_HOUSE = new VoxelShape[4];
    public static final VoxelShape[] METEOR_COLLECTOR = new VoxelShape[4];
    static {
        VoxelShapeUtils.setShape(Block.box(-16.0d, -16.0d, -16.0d, 32.0d, 32.0d, 32.0d).move(0, 1, 0), GREEN_HOUSE);
        VoxelShapeUtils.setShape(MeteorCollectorShapes.shape().move(0, 1, 0), METEOR_COLLECTOR);
    }

    public static class MeteorCollectorShapes {

        public static VoxelShape shape() {
            VoxelShape shape = Shapes.empty();

            // Bottom corner
            shape = Shapes.or(shape, Shapes.box(
                    -1, -1, -1,
                    -0.25, -0.5, -0.25));
            shape = Shapes.or(shape, Shapes.box(
                    1.25, -1, 1.25,
                    2, -0.5, 2));
            shape = Shapes.or(shape, Shapes.box(
                    -1, -1, 1.25,
                    -0.25, -0.5, 2));
            shape = Shapes.or(shape, Shapes.box(
                    1.25, -1, -1,
                    2, -0.5, -0.25));

            // Pillar bases
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, -0.5, -0.875,
                    -0.25, -0.125, -0.25));
            shape = Shapes.or(shape, Shapes.box(
                    1.25, -0.5, -0.875,
                    1.875, -0.125, -0.25));
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, -0.5, 1.25,
                    -0.25, -0.125, 1.875));
            shape = Shapes.or(shape, Shapes.box(
                    1.25, -0.5, 1.25,
                    1.875, -0.125, 1.875));

            // Pillars
            shape = Shapes.or(shape, Shapes.box(
                    -0.75, -0.125, -0.75,
                    -0.375, 1.375, -0.375));
            shape = Shapes.or(shape, Shapes.box(
                    1.375, -0.125, -0.75,
                    1.75, 1.375, -0.375));
            shape = Shapes.or(shape, Shapes.box(
                    -0.75, -0.125, 1.375,
                    -0.375, 1.375, 1.75));
            shape = Shapes.or(shape, Shapes.box(
                    1.375, -0.125, 1.375,
                    1.75, 1.375, 1.75));

            // Front lower pieces
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -1, -0.875,
                    1.25, -0.875, -0.75));
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -0.875, -0.875,
                    -0.125, -0.375, -0.75));
            shape = Shapes.or(shape, Shapes.box(
                    1.125, -0.875, -0.875,
                    1.25, -0.375, -0.75));
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -1, -0.75,
                    1.25, -0.25, -0.625));

            // Ports
            shape = Shapes.or(shape, Shapes.box(
                    -1, -1, 0,
                    -0.75, 0, 1));
            shape = Shapes.or(shape, Shapes.box(
                    1.75, -1, 0,
                    2, 0, 1));
            shape = Shapes.or(shape, Shapes.box(
                    -0.75, -1, -0.25,
                    -0.625, -0.25, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    1.625, -1, -0.25,
                    1.75, -0.25, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    0, -1, 1.75,
                    1, 0, 2));
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -1, 1.625,
                    1.25, -0.25, 1.75));

            // Port details
            shape = Shapes.or(shape, Shapes.box(
                    1.75, -1, 1,
                    1.875, -0.875, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    1.75, -0.375, 1,
                    1.875, -0.25, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, -1, -0.25,
                    -0.75, -0.875, 0));
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, -1, 1,
                    -0.75, -0.875, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, -0.375, -0.25,
                    -0.75, -0.25, 0));
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, -0.375, 1,
                    -0.75, -0.25, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    1.75, -1, -0.25,
                    1.875, -0.875, 0));
            shape = Shapes.or(shape, Shapes.box(
                    1.75, -0.375, -0.25,
                    1.875, -0.25, 0));
            shape = Shapes.or(shape, Shapes.box(
                    1, -0.375, 1.75,
                    1.25, -0.25, 1.875));
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -0.375, 1.75,
                    0, -0.25, 1.875));
            shape = Shapes.or(shape, Shapes.box(
                    1, -1, 1.75,
                    1.25, -0.875, 1.875));
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -1, 1.75,
                    0, -0.875, 1.875));

            // Main body lower
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -1, -0.625,
                    1.25, -0.125, -0.25));
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -1, 1.25,
                    1.25, -0.125, 1.625));

            // Upper pillars
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, 1.375, -0.875,
                    -0.25, 1.875, -0.25));
            shape = Shapes.or(shape, Shapes.box(
                    1.25, 1.375, 1.25,
                    1.875, 1.875, 1.875));
            shape = Shapes.or(shape, Shapes.box(
                    1.25, 1.375, -0.875,
                    1.875, 1.875, -0.25));
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, 1.375, 1.25,
                    -0.25, 1.875, 1.875));

            // Upper frame
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, 1.375, 1.5,
                    1.25, 1.875, 1.625));
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, 1.375, -0.625,
                    1.25, 1.875, -0.5));
            shape = Shapes.or(shape, Shapes.box(
                    1.5, 1.375, -0.25,
                    1.625, 1.875, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    -0.625, 1.375, -0.25,
                    -0.5, 1.875, 1.25));

            // Side / outer frame
            shape = Shapes.or(shape, Shapes.box(
                    -0.625, -1, -0.25,
                    -0.25, -0.125, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    1.25, -1, -0.25,
                    1.625, -0.125, 1.25));
            shape = Shapes.or(shape, Shapes.box(
                    -0.25, -1, -0.25,
                    1.25, 0, 1.25));

            // Small center pieces
            shape = Shapes.or(shape, Shapes.box(
                    0.6875, 0, 0.1875,
                    0.8125, 0.5, 0.3125));
            shape = Shapes.or(shape, Shapes.box(
                    0.1875, 0, 0.6875,
                    0.3125, 1, 0.8125));

            // Glass/frame
            shape = Shapes.or(shape, Shapes.box(
                    -0.375, -0.125, 1.5,
                    1.375, 1.375, 1.625));
            shape = Shapes.or(shape, Shapes.box(
                    -0.625, -0.125, -0.375,
                    -0.5, 1.375, 1.375));
            shape = Shapes.or(shape, Shapes.box(
                    1.5, -0.125, -0.375,
                    1.625, 1.375, 1.375));
            shape = Shapes.or(shape, Shapes.box(
                    -0.375, -0.125, -0.625,
                    1.375, 1.375, -0.5));

            // Top frame
            shape = Shapes.or(shape, Shapes.box(
                    -1, 1.875, -1,
                    -0.125, 2, -0.125));
            shape = Shapes.or(shape, Shapes.box(
                    -1, 1.875, 1.125,
                    -0.125, 2, 2));
            shape = Shapes.or(shape, Shapes.box(
                    1.125, 1.875, -1,
                    2, 2, -0.125));
            shape = Shapes.or(shape, Shapes.box(
                    1.125, 1.875, 1.125,
                    2, 2, 2));
            shape = Shapes.or(shape, Shapes.box(
                    -0.875, 1.875, -0.125,
                    -0.125, 2, 1.125));
            shape = Shapes.or(shape, Shapes.box(
                    1.125, 1.875, -0.125,
                    1.875, 2, 1.125));
            shape = Shapes.or(shape, Shapes.box(
                    -0.125, 1.875, -0.875,
                    1.125, 2, -0.125));
            shape = Shapes.or(shape, Shapes.box(
                    -0.125, 1.875, 1.125,
                    1.125, 2, 1.875));
            shape = Shapes.or(shape, Shapes.box(
                    -0.125, 1.875, -0.125,
                    1.125, 2, 1.125));

            // Tier decoration / pillar details
            shape = Shapes.or(shape, Shapes.box(
                    -0.625, 0.25, -0.875,
                    -0.5, 0.375, -0.75));
            shape = Shapes.or(shape, Shapes.box(
                    1.5, 0.875, -0.875,
                    1.625, 1, -0.75));
            shape = Shapes.or(shape, Shapes.box(
                    1.5, 0.25, -0.875,
                    1.625, 0.375, -0.75));
            shape = Shapes.or(shape, Shapes.box(
                    -0.625, 0.875, -0.875,
                    -0.5, 1, -0.75));
            shape = Shapes.or(shape, Shapes.box(
                    -0.625, 0.375, -0.875,
                    -0.5, 0.875, -0.75));
            shape = Shapes.or(shape, Shapes.box(
                    1.5, 0.375, -0.875,
                    1.625, 0.875, -0.75));

            return shape;
        }
    }
}
