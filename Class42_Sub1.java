import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.zip.CRC32;
import java.util.zip.GZIPInputStream;
import sign.signlink;

public class Class42_Sub1 extends Class42 implements Runnable {

   private int anInt1330;
   private Class19 aClass19_1331 = new Class19(169);
   private int anInt1332;
   public String aString1333 = "";
   private int anInt1334;
   private long aLong1335;
   private boolean aBoolean1336 = true;
   private int[] anIntArray1337;
   private CRC32 aCRC32_1338 = new CRC32();
   private byte[] aByteArray1339 = new byte[500];
   private int anInt1340 = 923;
   public int anInt1341;
   private byte[][] aByteArrayArray1342 = new byte[4][];
   private client aClient1343;
   private Class19 aClass19_1344 = new Class19(169);
   private static int anInt1345;
   private int anInt1346;
   private int anInt1347;
   private int[] anIntArray1348;
   public int anInt1349;
   private int[] anIntArray1350;
   private int anInt1351;
   private int anInt1352 = 13603;
   private boolean aBoolean1353 = true;
   private OutputStream anOutputStream1354;
   private boolean aBoolean1355 = false;
   private int[] anIntArray1356;
   private boolean aBoolean1357 = false;
   private Class19 aClass19_1358 = new Class19(169);
   private byte[] aByteArray1359 = new byte['\ufde8'];
   private int[] anIntArray1360;
   private Class2 aClass2_1361;
   private InputStream anInputStream1362;
   private Socket aSocket1363;
   private int[][] anIntArrayArray1364;
   private int[][] anIntArrayArray1365;
   private int anInt1366;
   private int anInt1367;
   private Class19 aClass19_1368;
   private Class30_Sub2_Sub3 aClass30_Sub2_Sub3_1369;
   private Class19 aClass19_1370;
   private int[] anIntArray1371;
   private byte[] aByteArray1372;
   private int anInt1373;


/*   private final boolean crcMatches(int var1, int var3, byte[] var4) {
      if(var4 != null && var4.length < 2) {
      return false;
      }
         int var5 = var4.length - 2;
         int var6 = ((var4[var5] & 255) << 8) + (var4[var5 + 1] & 255);
         this.aCRC32_1338.reset();
         this.aCRC32_1338.update(var4, 0, var5);
         int var7 = (int)this.aCRC32_1338.getValue();
         return var6 != var1?false:var7 == var3;
      } else {
         return false;
      }
   }
*/
	private boolean crcMatches(int type, int id, byte[] data) {
		if (data == null || data.length < 2) {
			return false;
		}
		int length = data.length - 2;
		int hash = ((data[length] & 0xFF) << 8) + (data[length + 1] & 0xFF);
		aCRC32_1338.reset();
		aCRC32_1338.update(data, 0, length);
		int crc = (int) aCRC32_1338.getValue();
		return hash == type && crc == id;
	}

   private final void method550(int var1) {
      if(var1 >= 0) {
         this.aBoolean1336 = !this.aBoolean1336;
      }

      try {
         int var2 = this.anInputStream1362.available();
         int var4;
         int var5;
         if(this.anInt1347 == 0 && var2 >= 6) {
            this.aBoolean1357 = true;

            int var3;
            for(var3 = 0; var3 < 6; var3 += this.anInputStream1362.read(this.aByteArray1339, var3, 6 - var3)) {
               ;
            }

            var3 = this.aByteArray1339[0] & 255;
            var4 = ((this.aByteArray1339[1] & 255) << 8) + (this.aByteArray1339[2] & 255);
            var5 = ((this.aByteArray1339[3] & 255) << 8) + (this.aByteArray1339[4] & 255);
            int var6 = this.aByteArray1339[5] & 255;
            this.aClass30_Sub2_Sub3_1369 = null;

            for(Class30_Sub2_Sub3 var7 = (Class30_Sub2_Sub3)this.aClass19_1331.method252(); var7 != null; var7 = (Class30_Sub2_Sub3)this.aClass19_1331.method254(false)) {
               if(var7.anInt1419 == var3 && var7.anInt1421 == var4) {
                  this.aClass30_Sub2_Sub3_1369 = var7;
               }

               if(this.aClass30_Sub2_Sub3_1369 != null) {
                  var7.anInt1423 = 0;
               }
            }

            if(this.aClass30_Sub2_Sub3_1369 != null) {
               this.anInt1373 = 0;
               if(var5 == 0) {
                  signlink.reporterror("Rej: " + var3 + "," + var4);
                  this.aClass30_Sub2_Sub3_1369.aByteArray1420 = null;
                  if(this.aClass30_Sub2_Sub3_1369.aBoolean1422) {
                     Class19 var16 = this.aClass19_1358;
                     synchronized(this.aClass19_1358) {
                        this.aClass19_1358.method249(this.aClass30_Sub2_Sub3_1369);
                     }
                  } else {
                     this.aClass30_Sub2_Sub3_1369.method329();
                  }

                  this.aClass30_Sub2_Sub3_1369 = null;
               } else {
                  if(this.aClass30_Sub2_Sub3_1369.aByteArray1420 == null && var6 == 0) {
                     this.aClass30_Sub2_Sub3_1369.aByteArray1420 = new byte[var5];
                  }

                  if(this.aClass30_Sub2_Sub3_1369.aByteArray1420 == null && var6 != 0) {
                     throw new IOException("missing start of file");
                  }
               }
            }

            this.anInt1346 = var6 * 500;
            this.anInt1347 = 500;
            if(this.anInt1347 > var5 - var6 * 500) {
               this.anInt1347 = var5 - var6 * 500;
            }
         }

         if(this.anInt1347 > 0 && var2 >= this.anInt1347) {
            this.aBoolean1357 = true;
            byte[] var14 = this.aByteArray1339;
            var4 = 0;
            if(this.aClass30_Sub2_Sub3_1369 != null) {
               var14 = this.aClass30_Sub2_Sub3_1369.aByteArray1420;
               var4 = this.anInt1346;
            }

            for(var5 = 0; var5 < this.anInt1347; var5 += this.anInputStream1362.read(var14, var5 + var4, this.anInt1347 - var5)) {
               ;
            }

            if(this.anInt1347 + this.anInt1346 >= var14.length && this.aClass30_Sub2_Sub3_1369 != null) {
               if(this.aClient1343.aClass14Array970[0] != null) {
                  this.aClient1343.aClass14Array970[this.aClass30_Sub2_Sub3_1369.anInt1419 + 1].method234(var14.length, var14, (byte)2, this.aClass30_Sub2_Sub3_1369.anInt1421);
               }

               if(!this.aClass30_Sub2_Sub3_1369.aBoolean1422 && this.aClass30_Sub2_Sub3_1369.anInt1419 == 3) {
                  this.aClass30_Sub2_Sub3_1369.aBoolean1422 = true;
                  this.aClass30_Sub2_Sub3_1369.anInt1419 = 93;
               }

               if(this.aClass30_Sub2_Sub3_1369.aBoolean1422) {
                  Class19 var15 = this.aClass19_1358;
                  synchronized(this.aClass19_1358) {
                     this.aClass19_1358.method249(this.aClass30_Sub2_Sub3_1369);
                  }
               } else {
                  this.aClass30_Sub2_Sub3_1369.method329();
               }
            }

            this.anInt1347 = 0;
            return;
         }
      } catch (IOException var13) {
         try {
            this.aSocket1363.close();
         } catch (Exception var10) {
            ;
         }

         this.aSocket1363 = null;
         this.anInputStream1362 = null;
         this.anOutputStream1354 = null;
         this.anInt1347 = 0;
      }

   }

   public final void method551(Class44 var1, client var2) {
      String[] var3 = new String[]{"model_version", "anim_version", "midi_version", "map_version"};

      byte[] var5;
      int var6;
      Class30_Sub2_Sub2 var7;
      int var8;
      for(int var4 = 0; var4 < 4; ++var4) {
         var5 = var1.method571(var3[var4], (byte[])null);
         var6 = var5.length / 2;
         var7 = new Class30_Sub2_Sub2(var5, 891);
         this.anIntArrayArray1364[var4] = new int[var6];
         this.aByteArrayArray1342[var4] = new byte[var6];

         for(var8 = 0; var8 < var6; ++var8) {
            this.anIntArrayArray1364[var4][var8] = var7.method410();
         }
      }

      String[] var10 = new String[]{"model_crc", "anim_crc", "midi_crc", "map_crc"};

      int var13;
      for(int var11 = 0; var11 < 4; ++var11) {
         byte[] var12 = var1.method571(var10[var11], (byte[])null);
         var13 = var12.length / 4;
         Class30_Sub2_Sub2 var14 = new Class30_Sub2_Sub2(var12, 891);
         this.anIntArrayArray1365[var11] = new int[var13];

         for(int var9 = 0; var9 < var13; ++var9) {
            this.anIntArrayArray1365[var11][var9] = var14.method413();
         }
      }

      var5 = var1.method571("model_index", (byte[])null);
      var6 = this.anIntArrayArray1364[0].length;
      this.aByteArray1372 = new byte[var6];

      for(var13 = 0; var13 < var6; ++var13) {
         if(var13 < var5.length) {
            this.aByteArray1372[var13] = var5[var13];
         } else {
            this.aByteArray1372[var13] = 0;
         }
      }

      var5 = var1.method571("map_index", (byte[])null);
      var7 = new Class30_Sub2_Sub2(var5, 891);
      var6 = var5.length / 7;
      this.anIntArray1371 = new int[var6];
      this.anIntArray1350 = new int[var6];
      this.anIntArray1337 = new int[var6];
      this.anIntArray1356 = new int[var6];

      for(var8 = 0; var8 < var6; ++var8) {
         this.anIntArray1371[var8] = var7.method410();
         this.anIntArray1350[var8] = var7.method410();
         this.anIntArray1337[var8] = var7.method410();
         this.anIntArray1356[var8] = var7.method408();
      }

      var5 = var1.method571("anim_index", (byte[])null);
      var7 = new Class30_Sub2_Sub2(var5, 891);
      var6 = var5.length / 2;
      this.anIntArray1360 = new int[var6];

      for(var8 = 0; var8 < var6; ++var8) {
         this.anIntArray1360[var8] = var7.method410();
      }

      var5 = var1.method571("midi_index", (byte[])null);
      var7 = new Class30_Sub2_Sub2(var5, 891);
      var6 = var5.length;
      this.anIntArray1348 = new int[var6];

      for(var8 = 0; var8 < var6; ++var8) {
         this.anIntArray1348[var8] = var7.method408();
      }

      this.aClient1343 = var2;
      this.aBoolean1353 = true;
      this.aClient1343.method12(this, 2);
   }

   public final int method552() {
      Class2 var1 = this.aClass2_1361;
      synchronized(this.aClass2_1361) {
         int var2 = this.aClass2_1361.method154();
         return var2;
      }
   }

   public final void method553() {
      this.aBoolean1353 = false;
   }

   public final void method554(boolean var1, int var2) {
      int var3 = this.anIntArray1371.length;
      if(var2 != 0) {
         anInt1345 = 20;
      }

      for(int var4 = 0; var4 < var3; ++var4) {
         if(var1 || this.anIntArray1356[var4] != 0) {
            this.method563((byte)2, 3, this.anIntArray1337[var4], (byte)8);
            this.method563((byte)2, 3, this.anIntArray1350[var4], (byte)8);
         }
      }

   }

   public final int method555(int var1, int var2) {
      if(var1 <= 0) {
         this.aBoolean1355 = !this.aBoolean1355;
      }

      int var3 = this.anIntArrayArray1364[var2].length;
      return var3;
   }

   private final void method556(int var1, Class30_Sub2_Sub3 var2) {
      if(var1 < 8 || var1 > 8) {
         this.anInt1352 = -339;
      }

      try {
         if(this.aSocket1363 == null) {
            long var3 = System.currentTimeMillis();
            if(var3 - this.aLong1335 < 4000L) {
               return;
            }

            this.aLong1335 = var3;
            this.aSocket1363 = this.aClient1343.method19(43596 + client.anInt958);
            this.anInputStream1362 = this.aSocket1363.getInputStream();
            this.anOutputStream1354 = this.aSocket1363.getOutputStream();
            this.anOutputStream1354.write(15);

            for(int var5 = 0; var5 < 8; ++var5) {
               this.anInputStream1362.read();
            }

            this.anInt1373 = 0;
         }

         this.aByteArray1339[0] = (byte)var2.anInt1419;
         this.aByteArray1339[1] = (byte)(var2.anInt1421 >> 8);
         this.aByteArray1339[2] = (byte)var2.anInt1421;
         if(var2.aBoolean1422) {
            this.aByteArray1339[3] = 2;
         } else if(!this.aClient1343.aBoolean1157) {
            this.aByteArray1339[3] = 1;
         } else {
            this.aByteArray1339[3] = 0;
         }

         this.anOutputStream1354.write(this.aByteArray1339, 0, 4);
         this.anInt1334 = 0;
         this.anInt1349 = -10000;
      } catch (IOException var7) {
         try {
            this.aSocket1363.close();
         } catch (Exception var6) {
            ;
         }

         this.aSocket1363 = null;
         this.anInputStream1362 = null;
         this.anOutputStream1354 = null;
         this.anInt1347 = 0;
         ++this.anInt1349;
      }
   }

   public final int method557(int var1) {
      if(var1 != 0) {
         this.anInt1352 = -76;
      }

      return this.anIntArray1360.length;
   }

   public final void method558(int var1, int var2) {
      if(var1 >= 0 && var1 <= this.anIntArrayArray1364.length && var2 >= 0 && var2 <= this.anIntArrayArray1364[var1].length) {
         if(this.anIntArrayArray1364[var1][var2] != 0) {
            Class2 var3 = this.aClass2_1361;
            synchronized(this.aClass2_1361) {
               Class30_Sub2_Sub3 var4;
               for(var4 = (Class30_Sub2_Sub3)this.aClass2_1361.method152(); var4 != null; var4 = (Class30_Sub2_Sub3)this.aClass2_1361.method153(false)) {
                  if(var4.anInt1419 == var1 && var4.anInt1421 == var2) {
                     return;
                  }
               }

               var4 = new Class30_Sub2_Sub3();
               var4.anInt1419 = var1;
               var4.anInt1421 = var2;
               var4.aBoolean1422 = true;
               Class19 var5 = this.aClass19_1370;
               synchronized(this.aClass19_1370) {
                  this.aClass19_1370.method249(var4);
               }

               this.aClass2_1361.method150(var4);
            }
         }
      }
   }

   public final int method559(int var1, int var2) {
      if(var2 >= 0) {
         anInt1345 = -7;
      }

      return this.aByteArray1372[var1] & 255;
   }

   public final void run() {
      try {
         while(this.aBoolean1353) {
            ++this.anInt1341;
            byte var1 = 20;
            if(this.anInt1332 == 0 && this.aClient1343.aClass14Array970[0] != null) {
               var1 = 50;
            }

            try {
               Thread.sleep((long)var1);
            } catch (Exception var6) {
               ;
            }

            this.aBoolean1357 = true;

            for(int var2 = 0; var2 < 100 && this.aBoolean1357; ++var2) {
               this.aBoolean1357 = false;
               this.method567(true);
               this.method565(false);
               if(this.anInt1366 == 0 && var2 >= 5) {
                  break;
               }

               this.method568((byte)-56);
               if(this.anInputStream1362 != null) {
                  this.method550(-369);
               }
            }

            boolean var8 = false;

            Class30_Sub2_Sub3 var3;
            for(var3 = (Class30_Sub2_Sub3)this.aClass19_1331.method252(); var3 != null; var3 = (Class30_Sub2_Sub3)this.aClass19_1331.method254(false)) {
               if(var3.aBoolean1422) {
                  var8 = true;
                  ++var3.anInt1423;
                  if(var3.anInt1423 > 50) {
                     var3.anInt1423 = 0;
                     this.method556(8, var3);
                  }
               }
            }

            if(!var8) {
               for(var3 = (Class30_Sub2_Sub3)this.aClass19_1331.method252(); var3 != null; var3 = (Class30_Sub2_Sub3)this.aClass19_1331.method254(false)) {
                  var8 = true;
                  ++var3.anInt1423;
                  if(var3.anInt1423 > 50) {
                     var3.anInt1423 = 0;
                     this.method556(8, var3);
                  }
               }
            }

            if(var8) {
               ++this.anInt1373;
               if(this.anInt1373 > 750) {
                  try {
                     this.aSocket1363.close();
                  } catch (Exception var5) {
                     ;
                  }

                  this.aSocket1363 = null;
                  this.anInputStream1362 = null;
                  this.anOutputStream1354 = null;
                  this.anInt1347 = 0;
               }
            } else {
               this.anInt1373 = 0;
               this.aString1333 = "";
            }

            if(this.aClient1343.aBoolean1157 && this.aSocket1363 != null && this.anOutputStream1354 != null && (this.anInt1332 > 0 || this.aClient1343.aClass14Array970[0] == null)) {
               ++this.anInt1334;
               if(this.anInt1334 > 500) {
                  this.anInt1334 = 0;
                  this.aByteArray1339[0] = 0;
                  this.aByteArray1339[1] = 0;
                  this.aByteArray1339[2] = 0;
                  this.aByteArray1339[3] = 10;

                  try {
                     this.anOutputStream1354.write(this.aByteArray1339, 0, 4);
                  } catch (IOException var4) {
                     this.anInt1373 = 5000;
                  }
               }
            }
         }

      } catch (Exception var7) {
         signlink.reporterror("od_ex " + var7.getMessage());
      }
   }

   public final void method560(int var1, int var2, boolean var3) {
      if(this.aClient1343.aClass14Array970[0] != null) {
         if(this.anIntArrayArray1364[var2][var1] != 0) {
            if(this.aByteArrayArray1342[var2][var1] != 0) {
               if(this.anInt1332 != 0) {
                  Class30_Sub2_Sub3 var4 = new Class30_Sub2_Sub3();
                  var4.anInt1419 = var2;
                  var4.anInt1421 = var1;
                  if(var3) {
                     anInt1345 = -423;
                  }

                  var4.aBoolean1422 = false;
                  Class19 var5 = this.aClass19_1344;
                  synchronized(this.aClass19_1344) {
                     this.aClass19_1344.method249(var4);
                  }
               }
            }
         }
      }
   }

   public final Class30_Sub2_Sub3 method561() {
      Class19 var2 = this.aClass19_1358;
      Class30_Sub2_Sub3 var1;
      synchronized(this.aClass19_1358) {
         var1 = (Class30_Sub2_Sub3)this.aClass19_1358.method251();
      }

      if(var1 == null) {
         return null;
      } else {
         Class2 var8 = this.aClass2_1361;
         synchronized(this.aClass2_1361) {
            var1.method330();
         }

         if(var1.aByteArray1420 == null) {
            return var1;
         } else {
            int var9 = 0;

            try {
               GZIPInputStream var3 = new GZIPInputStream(new ByteArrayInputStream(var1.aByteArray1420));

               while(true) {
                  if(var9 == this.aByteArray1359.length) {
                     throw new RuntimeException("buffer overflow!");
                  }

                  int var4 = var3.read(this.aByteArray1359, var9, this.aByteArray1359.length - var9);
                  if(var4 == -1) {
                     break;
                  }

                  var9 += var4;
               }
            } catch (IOException var7) {
               throw new RuntimeException("error unzipping");
            }

            var1.aByteArray1420 = new byte[var9];

            for(int var10 = 0; var10 < var9; ++var10) {
               var1.aByteArray1420[var10] = this.aByteArray1359[var10];
            }

            return var1;
         }
      }
   }

   public final int method562(int var1, int var2, int var3, int var4) {
      if(var2 != 0) {
         return anInt1345;
      } else {
         int var5 = (var4 << 8) + var3;

         for(int var6 = 0; var6 < this.anIntArray1371.length; ++var6) {
            if(this.anIntArray1371[var6] == var5) {
               if(var1 == 0) {
                  return this.anIntArray1350[var6];
               }

               return this.anIntArray1337[var6];
            }
         }

         return -1;
      }
   }

   public final void method548(int var1) {
      this.method558(0, var1);
   }

   public final void method563(byte byte0, int i, int j, byte byte1)
   {
       if(byte1 == 8)
           byte1 = 0;
       else
           anInt1340 = 237;
       if(aClient1343.aClass14Array970[0] == null)
           return;
       if(anIntArrayArray1364[i][j] == 0)
           return;
       byte abyte0[] = aClient1343.aClass14Array970[i + 1].method233(true, j);
       if(crcMatches(anIntArrayArray1364[i][j], anIntArrayArray1365[i][j], abyte0))
           return;
       aByteArrayArray1342[i][j] = byte0;
       if(byte0 > anInt1332)
           anInt1332 = byte0;
       anInt1330++;
   }

   public final boolean method564(int var1, int var2) {
      if(var2 >= 0) {
         throw new NullPointerException();
      } else {
         for(int var3 = 0; var3 < this.anIntArray1371.length; ++var3) {
            if(this.anIntArray1337[var3] == var1) {
               return true;
            }
         }

         return false;
      }
   }

   private final void method565(boolean var1) {
      this.anInt1366 = 0;
      if(!var1) {
         this.anInt1367 = 0;

         Class30_Sub2_Sub3 var2;
         for(var2 = (Class30_Sub2_Sub3)this.aClass19_1331.method252(); var2 != null; var2 = (Class30_Sub2_Sub3)this.aClass19_1331.method254(false)) {
            if(var2.aBoolean1422) {
               ++this.anInt1366;
            } else {
               ++this.anInt1367;
            }
         }

         while(this.anInt1366 < 10) {
            var2 = (Class30_Sub2_Sub3)this.aClass19_1368.method251();
            if(var2 == null) {
               break;
            }

            if(this.aByteArrayArray1342[var2.anInt1419][var2.anInt1421] != 0) {
               ++this.anInt1351;
            }

            this.aByteArrayArray1342[var2.anInt1419][var2.anInt1421] = 0;
            this.aClass19_1331.method249(var2);
            ++this.anInt1366;
            this.method556(8, var2);
            this.aBoolean1357 = true;
         }

      }
   }

   public final void method566(int var1) {
      if(var1 != 0) {
         for(int var2 = 1; var2 > 0; ++var2) {
            ;
         }
      }

      Class19 var5 = this.aClass19_1344;
      synchronized(this.aClass19_1344) {
         this.aClass19_1344.method256();
      }
   }

   private final void method567(boolean flag)
   {
       if(!flag)
           return;
       Class30_Sub2_Sub3 class30_sub2_sub3;
       synchronized(aClass19_1370)
       {
           class30_sub2_sub3 = (Class30_Sub2_Sub3)aClass19_1370.method251();
       }
       while(class30_sub2_sub3 != null) 
       {
           aBoolean1357 = true;
           byte abyte0[] = null;
           if(aClient1343.aClass14Array970[0] != null)
               abyte0 = aClient1343.aClass14Array970[class30_sub2_sub3.anInt1419 + 1].method233(true, class30_sub2_sub3.anInt1421);
           if(!crcMatches(anIntArrayArray1364[class30_sub2_sub3.anInt1419][class30_sub2_sub3.anInt1421], anIntArrayArray1365[class30_sub2_sub3.anInt1419][class30_sub2_sub3.anInt1421], abyte0))
               abyte0 = null;
           synchronized(aClass19_1370)
           {
               if(abyte0 == null)
               {
                   aClass19_1368.method249(class30_sub2_sub3);
               } else
               {
                   class30_sub2_sub3.aByteArray1420 = abyte0;
                   synchronized(aClass19_1358)
                   {
                       aClass19_1358.method249(class30_sub2_sub3);
                   }
               }
               class30_sub2_sub3 = (Class30_Sub2_Sub3)aClass19_1370.method251();
           }
       }
   }

   private final void method568(byte var1) {
      if(var1 != -56) {
         for(int var2 = 1; var2 > 0; ++var2) {
            ;
         }
      }

      while(this.anInt1366 == 0 && this.anInt1367 < 10 && this.anInt1332 != 0) {
         Class19 var3 = this.aClass19_1344;
         Class30_Sub2_Sub3 var10;
         synchronized(this.aClass19_1344) {
            var10 = (Class30_Sub2_Sub3)this.aClass19_1344.method251();
         }

         while(var10 != null) {
            if(this.aByteArrayArray1342[var10.anInt1419][var10.anInt1421] != 0) {
               this.aByteArrayArray1342[var10.anInt1419][var10.anInt1421] = 0;
               this.aClass19_1331.method249(var10);
               this.method556(8, var10);
               this.aBoolean1357 = true;
               if(this.anInt1351 < this.anInt1330) {
                  ++this.anInt1351;
               }

               this.aString1333 = "Loading extra files - " + this.anInt1351 * 100 / this.anInt1330 + "%";
               ++this.anInt1367;
               if(this.anInt1367 == 10) {
                  return;
               }
            }

            var3 = this.aClass19_1344;
            synchronized(this.aClass19_1344) {
               var10 = (Class30_Sub2_Sub3)this.aClass19_1344.method251();
            }
         }

         for(int var11 = 0; var11 < 4; ++var11) {
            byte[] var4 = this.aByteArrayArray1342[var11];
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               if(var4[var6] == this.anInt1332) {
                  var4[var6] = 0;
                  Class30_Sub2_Sub3 var7 = new Class30_Sub2_Sub3();
                  var7.anInt1419 = var11;
                  var7.anInt1421 = var6;
                  var7.aBoolean1422 = false;
                  this.aClass19_1331.method249(var7);
                  this.method556(8, var7);
                  this.aBoolean1357 = true;
                  if(this.anInt1351 < this.anInt1330) {
                     ++this.anInt1351;
                  }

                  this.aString1333 = "Loading extra files - " + this.anInt1351 * 100 / this.anInt1330 + "%";
                  ++this.anInt1367;
                  if(this.anInt1367 == 10) {
                     return;
                  }
               }
            }
         }

         --this.anInt1332;
      }

   }
	public int getChecksum(int type, int id) {
		int crc = -1;
		byte[] data = aClient1343.aClass14Array970[type + 1].method233(true, id);
		if (data != null) {
			int length = data.length - 2;
			aCRC32_1338.reset();
			aCRC32_1338.update(data, 0, length);
			crc = (int) aCRC32_1338.getValue();
		}
		return crc;
	}
		public void writeChecksumList(int type) {
	try {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(signlink.findcachedir() + type + "_crc.dat"));
		int total = 0;
		for (int index = 0; index < aClient1343.aClass14Array970[type + 1].getFileCount(); index++) {
			out.writeInt(getChecksum(type, index));
			total++;
		}
		System.out.println(type+"-"+total);
		out.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
		public int getVersion(int type, int id) {
			int version = -1;
			byte[] data = aClient1343.aClass14Array970[type + 1].method233(true, id);
			if (data != null) {
				int length = data.length - 2;
				version = ((data[length] & 0xff) << 8) + (data[length + 1] & 0xff);
			}
			return version;
		}
public void writeVersionList(int type) {
	try {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(signlink.findcachedir() + type + "_version.dat"));
		for (int index = 0; index < aClient1343.aClass14Array970[type + 1].getFileCount(); index++) {
			out.writeShort(getVersion(type, index));
		}
		out.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
   public final boolean method569(int var1, int var2) {
      if(var2 != 5) {
         anInt1345 = 169;
      }

      return this.anIntArray1348[var1] == 1;
   }
   public void writeAll() {
		for(int i = 0; i < anIntArrayArray1365.length; i++) {
			writeChecksumList(i);
			writeVersionList(i);
		}
	}
   public Class42_Sub1() {
      this.aClass2_1361 = new Class2(anInt1345);
      this.anIntArrayArray1364 = new int[4][];
      this.anIntArrayArray1365 = new int[4][];
      this.aClass19_1368 = new Class19(169);
      this.aClass19_1370 = new Class19(169);
   }
}
