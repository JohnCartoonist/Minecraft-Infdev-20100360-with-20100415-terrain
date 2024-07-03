package net.minecraft.src;

import java.util.Random;

public final class NoiseGeneratorPerlin extends NoiseGenerator {
	private int[] permutations;
	private double xCoord;
	private double yCoord;
	private double zCoord;

	public NoiseGeneratorPerlin() {
		this(new Random());
	}

	public NoiseGeneratorPerlin(Random var1) {
		this.permutations = new int[512];
		this.xCoord = var1.nextDouble() * 256.0D;
		this.yCoord = var1.nextDouble() * 256.0D;
		this.zCoord = var1.nextDouble() * 256.0D;

		int var2;
		for(var2 = 0; var2 < 256; this.permutations[var2] = var2++) {
		}

		for(var2 = 0; var2 < 256; ++var2) {
			int var3 = var1.nextInt(256 - var2) + var2;
			int var4 = this.permutations[var2];
			this.permutations[var2] = this.permutations[var3];
			this.permutations[var3] = var4;
			this.permutations[var2 + 256] = this.permutations[var2];
		}

	}

	private double generateNoise(double var1, double var3, double var5) {
		double var7 = var1 + this.xCoord;
		double var9 = var3 + this.yCoord;
		double var11 = var5 + this.zCoord;
		int var22 = MathHelper.floor_double(var7) & 255;
		int var2 = MathHelper.floor_double(var9) & 255;
		int var23 = MathHelper.floor_double(var11) & 255;
		var7 -= (double)MathHelper.floor_double(var7);
		var9 -= (double)MathHelper.floor_double(var9);
		var11 -= (double)MathHelper.floor_double(var11);
		double var16 = generateNoise(var7);
		double var18 = generateNoise(var9);
		double var20 = generateNoise(var11);
		int var4 = this.permutations[var22] + var2;
		int var24 = this.permutations[var4] + var23;
		var4 = this.permutations[var4 + 1] + var23;
		var22 = this.permutations[var22 + 1] + var2;
		var2 = this.permutations[var22] + var23;
		var22 = this.permutations[var22 + 1] + var23;
		return lerp(var20, lerp(var18, lerp(var16, grad(this.permutations[var24], var7, var9, var11), grad(this.permutations[var2], var7 - 1.0D, var9, var11)), lerp(var16, grad(this.permutations[var4], var7, var9 - 1.0D, var11), grad(this.permutations[var22], var7 - 1.0D, var9 - 1.0D, var11))), lerp(var18, lerp(var16, grad(this.permutations[var24 + 1], var7, var9, var11 - 1.0D), grad(this.permutations[var2 + 1], var7 - 1.0D, var9, var11 - 1.0D)), lerp(var16, grad(this.permutations[var4 + 1], var7, var9 - 1.0D, var11 - 1.0D), grad(this.permutations[var22 + 1], var7 - 1.0D, var9 - 1.0D, var11 - 1.0D))));
	}

	private static double generateNoise(double var0) {
		return var0 * var0 * var0 * (var0 * (var0 * 6.0D - 15.0D) + 10.0D);
	}

	private static double lerp(double var0, double var2, double var4) {
		return var2 + var0 * (var4 - var2);
	}

	private static double grad(int var0, double var1, double var3, double var5) {
		var0 &= 15;
		double var8 = var0 < 8 ? var1 : var3;
		double var10 = var0 < 4 ? var3 : (var0 != 12 && var0 != 14 ? var5 : var1);
		return ((var0 & 1) == 0 ? var8 : -var8) + ((var0 & 2) == 0 ? var10 : -var10);
	}

	public final double generateNoise(double var1, double var3) {
		return this.generateNoise(var1, var3, 0.0D);
	}

	public final double generateNoiseD(double var1, double var3, double var5) {
		return this.generateNoise(var1, var3, var5);
	}
}
