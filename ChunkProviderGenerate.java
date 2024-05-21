package net.minecraft.src;

import java.util.Random;

public final class ChunkProviderGenerate implements IChunkProvider {
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	private NoiseGeneratorOctaves noiseGen5;
	private NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;

	public ChunkProviderGenerate(World var1, long var2) {
		this.worldObj = var1;
		this.rand = new Random(var2);
		new Random(var2);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 4);
		new NoiseGeneratorOctaves(this.rand, 5);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 5);
	}

	public final Chunk provideChunk(int var1, int var2) {
		this.rand.setSeed((long)var1 * 341873128712L + (long)var2 * 132897987541L);
		byte[] var3 = new byte[-Short.MIN_VALUE];
		Chunk var4 = new Chunk(this.worldObj, var3, var1, var2);

		int var5;
		int var6;
		double var50;
		for(var5 = 0; var5 < 4; ++var5) {
			for(var6 = 0; var6 < 4; ++var6) {
				double[][] var7 = new double[33][4];
				int var8 = (var1 << 2) + var5;
				int var9 = (var2 << 2) + var6;

				for(int var10 = 0; var10 < var7.length; ++var10) {
					var7[var10][0] = this.initializeNoiseField((double)var8, (double)var10, (double)var9);
					var7[var10][1] = this.initializeNoiseField((double)var8, (double)var10, (double)(var9 + 1));
					var7[var10][2] = this.initializeNoiseField((double)(var8 + 1), (double)var10, (double)var9);
					var7[var10][3] = this.initializeNoiseField((double)(var8 + 1), (double)var10, (double)(var9 + 1));
				}

				for(var8 = 0; var8 < 32; ++var8) {
					var50 = var7[var8][0];
					double var11 = var7[var8][1];
					double var13 = var7[var8][2];
					double var15 = var7[var8][3];
					double var17 = var7[var8 + 1][0];
					double var19 = var7[var8 + 1][1];
					double var21 = var7[var8 + 1][2];
					double var23 = var7[var8 + 1][3];

					for(int var25 = 0; var25 < 4; ++var25) {
						double var26 = (double)var25 / 4.0D;
						double var28 = var50 + (var17 - var50) * var26;
						double var30 = var11 + (var19 - var11) * var26;
						double var32 = var13 + (var21 - var13) * var26;
						double var34 = var15 + (var23 - var15) * var26;

						for(int var55 = 0; var55 < 4; ++var55) {
							double var37 = (double)var55 / 4.0D;
							double var39 = var28 + (var32 - var28) * var37;
							double var41 = var30 + (var34 - var30) * var37;
							int var27 = var55 + (var5 << 2) << 11 | 0 + (var6 << 2) << 7 | (var8 << 2) + var25;

							for(int var36 = 0; var36 < 4; ++var36) {
								double var45 = (double)var36 / 4.0D;
								double var47 = var39 + (var41 - var39) * var45;
								int var56 = 0;
								if((var8 << 2) + var25 < 64) {
									var56 = Block.waterStill.blockID;
								}

								if(var47 > 0.0D) {
									var56 = Block.stone.blockID;
								}

								var3[var27] = (byte)var56;
								var27 += 128;
							}
						}
					}
				}
			}
		}

		for(var5 = 0; var5 < 16; ++var5) {
			for(var6 = 0; var6 < 16; ++var6) {
				double var49 = (double)((var1 << 4) + var5);
				var50 = (double)((var2 << 4) + var6);
				boolean var51 = this.noiseGen4.generateNoiseOctaves(var49 * (1.0D / 32.0D), var50 * (1.0D / 32.0D), 0.0D) + this.rand.nextDouble() * 0.2D > 0.0D;
				boolean var14 = this.noiseGen4.generateNoiseOctaves(var50 * (1.0D / 32.0D), 109.0134D, var49 * (1.0D / 32.0D)) + this.rand.nextDouble() * 0.2D > 3.0D;
				int var52 = (int)(this.noiseGen5.noiseGenerator(var49 * (1.0D / 32.0D) * 2.0D, var50 * (1.0D / 32.0D) * 2.0D) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var16 = var5 << 11 | var6 << 7 | 127;
				int var53 = -1;
				int var18 = Block.grass.blockID;
				int var54 = Block.dirt.blockID;

				for(int var20 = 127; var20 >= 0; --var20) {
					if(var3[var16] == 0) {
						var53 = -1;
					} else if(var3[var16] == Block.stone.blockID) {
						if(var53 == -1) {
							if(var52 <= 0) {
								var18 = 0;
								var54 = (byte)Block.stone.blockID;
							} else if(var20 >= 60 && var20 <= 65) {
								var18 = Block.grass.blockID;
								var54 = Block.dirt.blockID;
								if(var14) {
									var18 = 0;
								}

								if(var14) {
									var54 = Block.gravel.blockID;
								}

								if(var51) {
									var18 = Block.sand.blockID;
								}

								if(var51) {
									var54 = Block.sand.blockID;
								}
							}

							if(var20 < 64 && var18 == 0) {
								var18 = Block.waterStill.blockID;
							}

							var53 = var52;
							if(var20 >= 63) {
								var3[var16] = (byte)var18;
							} else {
								var3[var16] = (byte)var54;
							}
						} else if(var53 > 0) {
							--var53;
							var3[var16] = (byte)var54;
						}
					}

					--var16;
				}
			}
		}

		var4.generateHeightMap();
		return var4;
	}

	private double initializeNoiseField(double var1, double var3, double var5) {
		double var7 = var3 * 4.0D - 64.0D;
		if(var7 < 0.0D) {
			var7 *= 3.0D;
		}

		double var9 = this.noiseGen3.generateNoiseOctaves(var1 * 684.412D / 80.0D, var3 * 684.412D / 400.0D, var5 * 684.412D / 80.0D) / 2.0D;
		double var11;
		double var13;
		if(var9 < -1.0D) {
			var11 = this.noiseGen1.generateNoiseOctaves(var1 * 684.412D, var3 * 984.412D, var5 * 684.412D) / 512.0D;
			var13 = var11 - var7;
			if(var13 < -10.0D) {
				var13 = -10.0D;
			}

			if(var13 > 10.0D) {
				var13 = 10.0D;
			}
		} else if(var9 > 1.0D) {
			var11 = this.noiseGen2.generateNoiseOctaves(var1 * 684.412D, var3 * 984.412D, var5 * 684.412D) / 512.0D;
			var13 = var11 - var7;
			if(var13 < -10.0D) {
				var13 = -10.0D;
			}

			if(var13 > 10.0D) {
				var13 = 10.0D;
			}
		} else {
			double var15 = this.noiseGen1.generateNoiseOctaves(var1 * 684.412D, var3 * 984.412D, var5 * 684.412D) / 512.0D - var7;
			double var17 = this.noiseGen2.generateNoiseOctaves(var1 * 684.412D, var3 * 984.412D, var5 * 684.412D) / 512.0D - var7;
			if(var15 < -10.0D) {
				var15 = -10.0D;
			}

			if(var15 > 10.0D) {
				var15 = 10.0D;
			}

			if(var17 < -10.0D) {
				var17 = -10.0D;
			}

			if(var17 > 10.0D) {
				var17 = 10.0D;
			}

			double var19 = (var9 + 1.0D) / 2.0D;
			var11 = var15 + (var17 - var15) * var19;
			var13 = var11;
		}

		return var13;
	}

	public final boolean chunkExists(int var1, int var2) {
		return true;
	}

	public final void populate(IChunkProvider var1, int var2, int var3) {
		this.rand.setSeed((long)var2 * 318279123L + (long)var3 * 919871212L);
		int var8 = var2 << 4;
		var2 = var3 << 4;

		int var4;
		int var5;
		int var6;
		for(var3 = 0; var3 < 20; ++var3) {
			var4 = var8 + this.rand.nextInt(16);
			var5 = this.rand.nextInt(128);
			var6 = var2 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreCoal.blockID)).generate(this.worldObj, this.rand, var4, var5, var6);
		}

		for(var3 = 0; var3 < 10; ++var3) {
			var4 = var8 + this.rand.nextInt(16);
			var5 = this.rand.nextInt(64);
			var6 = var2 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreIron.blockID)).generate(this.worldObj, this.rand, var4, var5, var6);
		}

		if(this.rand.nextInt(2) == 0) {
			var3 = var8 + this.rand.nextInt(16);
			var4 = this.rand.nextInt(32);
			var5 = var2 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreGold.blockID)).generate(this.worldObj, this.rand, var3, var4, var5);
		}

		if(this.rand.nextInt(8) == 0) {
			var3 = var8 + this.rand.nextInt(16);
			var4 = this.rand.nextInt(16);
			var5 = var2 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreDiamond.blockID)).generate(this.worldObj, this.rand, var3, var4, var5);
		}

		var3 = (int)this.mobSpawnerNoise.noiseGenerator((double)var8 * 0.25D, (double)var2 * 0.25D) << 3;
		WorldGenBigTree var9 = new WorldGenBigTree();

		for(var5 = 0; var5 < var3; ++var5) {
			var6 = var8 + this.rand.nextInt(16) + 8;
			int var7 = var2 + this.rand.nextInt(16) + 8;
			var9.setScale(1.0D, 1.0D, 1.0D);
			var9.generate(this.worldObj, this.rand, var6, this.worldObj.getHeightValue(var6, var7), var7);
		}

	}

	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		return true;
	}

	public boolean unload100OldestChunks() {
		return false;
	}

	public boolean canSave() {
		return true;
	}
}
