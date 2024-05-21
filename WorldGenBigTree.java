package net.minecraft.src;

import java.util.Random;

public final class WorldGenBigTree extends WorldGenerator {
	private static byte[] otherCoordPairs = new byte[]{(byte)2, (byte)0, (byte)0, (byte)1, (byte)2, (byte)1};
	private Random rand = new Random();
	private World worldObj;
	private int[] basePos = new int[]{0, 0, 0};
	private int heightLimit = 0;
	private int height;
	private double heightAttenuation = 0.618D;
	private double branchSlope = 0.381D;
	private double scaleWidth = 1.0D;
	private double leafDensity = 1.0D;
	private int trunkSize = 1;
	private int heightLimitLimit = 12;
	private int leafDistanceLimit = 4;
	private int[][] leafNodes;

	private void placeBlockLine(int[] var1, int[] var2, int var3) {
		int[] var15 = new int[]{0, 0, 0};
		byte var4 = 0;

		byte var5;
		for(var5 = 0; var4 < 3; ++var4) {
			var15[var4] = var2[var4] - var1[var4];
			if(Math.abs(var15[var4]) > Math.abs(var15[var5])) {
				var5 = var4;
			}
		}

		if(var15[var5] != 0) {
			byte var14 = otherCoordPairs[var5];
			var4 = otherCoordPairs[var5 + 3];
			byte var6;
			if(var15[var5] > 0) {
				var6 = 1;
			} else {
				var6 = -1;
			}

			double var10 = (double)var15[var14] / (double)var15[var5];
			double var12 = (double)var15[var4] / (double)var15[var5];
			int[] var7 = new int[]{0, 0, 0};
			int var8 = 0;

			for(var3 = var15[var5] + var6; var8 != var3; var8 += var6) {
				var7[var5] = MathHelper.floor_double((double)(var1[var5] + var8) + 0.5D);
				var7[var14] = MathHelper.floor_double((double)var1[var14] + (double)var8 * var10 + 0.5D);
				var7[var4] = MathHelper.floor_double((double)var1[var4] + (double)var8 * var12 + 0.5D);
				this.worldObj.setBlock(var7[0], var7[1], var7[2], 17);
			}

		}
	}

	private int checkBlockLine(int[] var1, int[] var2) {
		int[] var3 = new int[]{0, 0, 0};
		byte var4 = 0;

		byte var5;
		for(var5 = 0; var4 < 3; ++var4) {
			var3[var4] = var2[var4] - var1[var4];
			if(Math.abs(var3[var4]) > Math.abs(var3[var5])) {
				var5 = var4;
			}
		}

		if(var3[var5] == 0) {
			return -1;
		} else {
			byte var14 = otherCoordPairs[var5];
			var4 = otherCoordPairs[var5 + 3];
			byte var6;
			if(var3[var5] > 0) {
				var6 = 1;
			} else {
				var6 = -1;
			}

			double var9 = (double)var3[var14] / (double)var3[var5];
			double var11 = (double)var3[var4] / (double)var3[var5];
			int[] var7 = new int[]{0, 0, 0};
			int var8 = 0;

			int var15;
			for(var15 = var3[var5] + var6; var8 != var15; var8 += var6) {
				var7[var5] = var1[var5] + var8;
				var7[var14] = (int)((double)var1[var14] + (double)var8 * var9);
				var7[var4] = (int)((double)var1[var4] + (double)var8 * var11);
				int var13 = this.worldObj.getBlockId(var7[0], var7[1], var7[2]);
				if(var13 != 0 && var13 != 18) {
					break;
				}
			}

			return var8 == var15 ? -1 : Math.abs(var8);
		}
	}

	public final void setScale(double var1, double var3, double var5) {
		this.heightLimitLimit = 12;
		this.leafDistanceLimit = 5;
		this.scaleWidth = 1.0D;
		this.leafDensity = 1.0D;
	}

	public final boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		this.worldObj = var1;
		long var6 = var2.nextLong();
		this.rand.setSeed(var6);
		this.basePos[0] = var3;
		this.basePos[1] = var4;
		this.basePos[2] = var5;
		if(this.heightLimit == 0) {
			this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
		}

		int[] var39 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
		int[] var41 = new int[]{this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
		var4 = this.worldObj.getBlockId(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
		boolean var10000;
		if(var4 != 2 && var4 != 3) {
			var10000 = false;
		} else {
			var5 = this.checkBlockLine(var39, var41);
			if(var5 == -1) {
				var10000 = true;
			} else if(var5 < 6) {
				var10000 = false;
			} else {
				this.heightLimit = var5;
				var10000 = true;
			}
		}

		if(!var10000) {
			return false;
		} else {
			WorldGenBigTree var38 = this;
			this.height = (int)((double)this.heightLimit * this.heightAttenuation);
			if(this.height >= this.heightLimit) {
				this.height = this.heightLimit - 1;
			}

			int var40 = (int)(1.382D + Math.pow(this.leafDensity * (double)this.heightLimit / 13.0D, 2.0D));
			if(var40 <= 0) {
				var40 = 1;
			}

			int[][] var42 = new int[var40 * this.heightLimit][4];
			var4 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
			var5 = 1;
			int var43 = this.basePos[1] + this.height;
			int var7 = var4 - this.basePos[1];
			var42[0][0] = this.basePos[0];
			var42[0][1] = var4;
			var42[0][2] = this.basePos[2];
			var42[0][3] = var43;
			--var4;

			int var8;
			int var11;
			while(var7 >= 0) {
				var8 = 0;
				float var61;
				if((double)var7 < (double)((float)var38.heightLimit) * 0.3D) {
					var61 = -1.618F;
				} else {
					float var13 = (float)var38.heightLimit / 2.0F;
					float var14 = (float)var38.heightLimit / 2.0F - (float)var7;
					float var36;
					if(var14 == 0.0F) {
						var36 = var13;
					} else if(Math.abs(var14) >= var13) {
						var36 = 0.0F;
					} else {
						var36 = (float)Math.sqrt(Math.pow((double)Math.abs(var13), 2.0D) - Math.pow((double)Math.abs(var14), 2.0D));
					}

					var36 *= 0.5F;
					var61 = var36;
				}

				float var9 = var61;
				if(var9 < 0.0F) {
					--var4;
					--var7;
				} else {
					for(; var8 < var40; ++var8) {
						double var19 = var38.scaleWidth * (double)var9 * ((double)var38.rand.nextFloat() + 0.328D);
						double var21 = (double)var38.rand.nextFloat() * 2.0D * 3.14159D;
						int var10 = (int)(var19 * Math.sin(var21) + (double)var38.basePos[0] + 0.5D);
						var11 = (int)(var19 * Math.cos(var21) + (double)var38.basePos[2] + 0.5D);
						int[] var12 = new int[]{var10, var4, var11};
						int[] var52 = new int[]{var10, var4 + var38.leafDistanceLimit, var11};
						if(var38.checkBlockLine(var12, var52) == -1) {
							var52 = new int[]{var38.basePos[0], var38.basePos[1], var38.basePos[2]};
							double var28 = Math.sqrt(Math.pow((double)Math.abs(var38.basePos[0] - var12[0]), 2.0D) + Math.pow((double)Math.abs(var38.basePos[2] - var12[2]), 2.0D));
							double var30 = var28 * var38.branchSlope;
							if((double)var12[1] - var30 > (double)var43) {
								var52[1] = var43;
							} else {
								var52[1] = (int)((double)var12[1] - var30);
							}

							if(var38.checkBlockLine(var52, var12) == -1) {
								var42[var5][0] = var10;
								var42[var5][1] = var4;
								var42[var5][2] = var11;
								var42[var5][3] = var52[1];
								++var5;
							}
						}
					}

					--var4;
					--var7;
				}
			}

			var38.leafNodes = new int[var5][4];
			System.arraycopy(var42, 0, var38.leafNodes, 0, var5);
			var38 = this;
			var40 = 0;

			for(var3 = this.leafNodes.length; var40 < var3; ++var40) {
				var4 = var38.leafNodes[var40][0];
				var5 = var38.leafNodes[var40][1];
				var43 = var38.leafNodes[var40][2];
				int var10001 = var4;
				var4 = var43;
				int var49 = var5;
				var8 = var10001;
				WorldGenBigTree var47 = var38;
				var5 = var5;

				for(int var56 = var49 + var38.leafDistanceLimit; var5 < var56; ++var5) {
					int var22 = var5 - var49;
					float var20 = var22 >= 0 && var22 < var47.leafDistanceLimit ? (var22 != 0 && var22 != var47.leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
					boolean var53 = true;
					var53 = true;
					float var51 = var20;
					WorldGenBigTree var57 = var47;
					int var58 = (int)((double)var20 + 0.618D);
					byte var29 = otherCoordPairs[1];
					byte var59 = otherCoordPairs[4];
					int[] var31 = new int[]{var8, var5, var4};
					int[] var50 = new int[]{0, 0, 0};
					var11 = -var58;

					label134:
					for(var50[1] = var31[1]; var11 <= var58; ++var11) {
						var50[var29] = var31[var29] + var11;
						int var55 = -var58;

						while(true) {
							while(true) {
								if(var55 > var58) {
									continue label134;
								}

								double var60 = Math.sqrt(Math.pow((double)Math.abs(var11) + 0.5D, 2.0D) + Math.pow((double)Math.abs(var55) + 0.5D, 2.0D));
								if(var60 > (double)var51) {
									++var55;
								} else {
									var50[var59] = var31[var59] + var55;
									int var54 = var57.worldObj.getBlockId(var50[0], var50[1], var50[2]);
									if(var54 != 0 && var54 != 18) {
										++var55;
									} else {
										var57.worldObj.setBlock(var50[0], var50[1], var50[2], 18);
										++var55;
									}
								}
							}
						}
					}
				}
			}

			var40 = this.basePos[0];
			var3 = this.basePos[1];
			var4 = this.basePos[1] + this.height;
			var5 = this.basePos[2];
			int[] var44 = new int[]{var40, var3, var5};
			int[] var48 = new int[]{var40, var4, var5};
			this.placeBlockLine(var44, var48, 17);
			if(this.trunkSize == 2) {
				++var44[0];
				++var48[0];
				this.placeBlockLine(var44, var48, 17);
				++var44[2];
				++var48[2];
				this.placeBlockLine(var44, var48, 17);
				var44[0] += -1;
				var48[0] += -1;
				this.placeBlockLine(var44, var48, 17);
			}

			var38 = this;
			var40 = 0;
			var3 = this.leafNodes.length;

			for(int[] var45 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]}; var40 < var3; ++var40) {
				int[] var46 = var38.leafNodes[var40];
				var44 = new int[]{var46[0], var46[1], var46[2]};
				var45[1] = var46[3];
				var7 = var45[1] - var38.basePos[1];
				if((double)var7 >= (double)var38.heightLimit * 0.2D) {
					var38.placeBlockLine(var45, var44, 17);
				}
			}

			return true;
		}
	}
}
