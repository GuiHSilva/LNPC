package me.guiihsilva.utils;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.Chunk;

public class UtilBlockText {
	  
	  public static enum TextAlign {
	    ESQUERDO, 
	    DIREITA, 
	    CENTRO;
	  }
	  
	  public static HashMap<Character, int[][]> alphabet = new HashMap<Character, int[][]>();
	  
	  public static ArrayList<Location> GetTextLocations(String string, Location loc, BlockFace face)
	  {
	    if (alphabet.isEmpty()) {
	      PopulateAlphabet();
	    }
	    ArrayList<Location> locs = new ArrayList<Location>();
	    
	    Block block = loc.getBlock();
	    

	    int width = 0;
	    for (char c : string.toLowerCase().toCharArray())
	    {
	      int[][] letter = (int[][])alphabet.get(Character.valueOf(c));
	      
	      if (letter != null)
	      {

	        width += (letter[0].length + 1) * 3;
	      }
	    }
	    
	    block = block.getRelative(face, -1 * width / 2 + 1);
	    


	    World world = block.getWorld();
	    int bX = block.getX();
	    int bY = block.getY();
	    int bZ = block.getZ();
	    

	    for (char c : string.toLowerCase().toCharArray())
	    {
	      int[][] letter = (int[][])alphabet.get(Character.valueOf(c));
	      
	      if (letter != null)
	      {

	        for (int x = 0; x < letter.length; x++)
	        {
	          for (int y = 0; y < letter[x].length; y++)
	          {
	            if (letter[x][y] == 1) {
	              locs.add(new Location(world, bX, bY, bZ));
	            }
	            
	            bX += face.getModX() * 3;
	            bY += face.getModY() * 3;
	            bZ += face.getModZ() * 3;
	          }
	          

	          bX += face.getModX() * -3 * letter[x].length;
	          bY += face.getModY() * -3 * letter[x].length;
	          bZ += face.getModZ() * -3 * letter[x].length;
	          

	          bY -= 3;
	        }
	        
	        bY += 15;
	        bX += face.getModX() * (letter[0].length + 1) * 3;
	        bY += face.getModY() * (letter[0].length + 1) * 3;
	        bZ += face.getModZ() * (letter[0].length + 1) * 3;
	      }
	    }
	    return locs;
	  }
	  
	  public static Collection<Block> MakeText(String string, Location loc, BlockFace face, int id, byte data, TextAlign align, boolean b, boolean c)
	  {

	  return MakeTextexe(string, loc, face, id, data, align, true, c);
	  }
	  
	  public static Collection<Block> MakeTextexe(String string, Location loc, BlockFace face, int id, byte data, TextAlign align, boolean setAir, boolean animation)
	  {
	    HashSet<Block> changes = new HashSet<Block>();
	    
	    if (alphabet.isEmpty()) {
	      PopulateAlphabet();
	    }
	    Block block = loc.getBlock();
	    

	    int width = 0;
	    for (char c : string.toLowerCase().toCharArray())
	    {
	      int[][] letter = (int[][])alphabet.get(Character.valueOf(c));
	      
	      if (letter != null)
	      {

	        width += letter[0].length + 1;
	      }
	    }
	    
	    if ((align == TextAlign.CENTRO) || (align == TextAlign.DIREITA))
	    {
	      int divisor = 1;
	      if (align == TextAlign.CENTRO) {
	        divisor = 2;
	      }
	      block = block.getRelative(face, -1 * width / divisor + 1);
	    }
	    
	    HashSet<Chunk> chunks = new HashSet<Chunk>();
	    
	    int i;
	    if (setAir)
	    {
	      World world = loc.getWorld();
	      int bX = loc.getBlockX();
	      int bY = loc.getBlockY();
	      int bZ = loc.getBlockZ();
	      for (int y = 0; y < 5; y++)
	      {
	        if (align == TextAlign.CENTRO) {
	          for (int i1 = -64; i1 <= 64; i1++)
	          {
	        	    setBlock(world, bX + i1 * face.getModX(), bY + i1 * face.getModY(), bZ + i1 * face.getModZ(), 0, (byte)0, animation);
	          }
	        }
	        
	        if (align == TextAlign.ESQUERDO) {
	          for (int i1 = 0; i1 <= 128; i1++)
	          {
	        	    setBlock(world, bX + i1 * face.getModX(), bY + i1 * face.getModY(), bZ + i1 * face.getModZ(), 0, (byte)0, animation);
	          }
	        }
	        
	        if (align == TextAlign.CENTRO) {
	          for (i = -128; i <= 0; i++)
	          {

	           setBlock(world, bX + i * face.getModX(), bY + i * face.getModY(), bZ + i * face.getModZ(), 0, (byte)0, animation);
	          }
	        }
	        
	        bY--;
	      }
	    }
	    
	    World world = block.getWorld();
	    int bX = block.getX();
	    int bY = block.getY();
	    int bZ = block.getZ();
	    

	    for (char c : string.toLowerCase().toCharArray())
	    {
	      int[][] letter = (int[][])alphabet.get(Character.valueOf(c));
	      
	      if (letter != null)
	      {

	        for (int x = 0; x < letter.length; x++)
	        {
	          for (int y = 0; y < letter[x].length; y++)
	          {
	            if (letter[x][y] == 1)
	            {
	              changes.add(world.getBlockAt(bX, bY, bZ));
	              setBlock(world, bX, bY, bZ, id, data, animation);
	            }
	            

	            bX += face.getModX();
	            bY += face.getModY();
	            bZ += face.getModZ();
	          }
	          

	          bX += face.getModX() * -1 * letter[x].length;
	          bY += face.getModY() * -1 * letter[x].length;
	          bZ += face.getModZ() * -1 * letter[x].length;
	          

	          bY--;
	        }
	        
	        bY += 5;
	        bX += face.getModX() * (letter[0].length + 1);
	        bY += face.getModY() * (letter[0].length + 1);
	        bZ += face.getModZ() * (letter[0].length + 1);
	      }
	    }
	    for (Chunk c : chunks)
	    {
	      c.initLighting();
	    }
	    
	    
	    return changes;
	  }
	  
		private static void PopulateAlphabet()
		{
			alphabet.put('0', new int[][] {
					{1,1,1},
					{1,0,1},
					{1,0,1},
					{1,0,1},
					{1,1,1}
			});

			alphabet.put('1', new int[][] {
					{1,1,0},
					{0,1,0},
					{0,1,0},
					{0,1,0},
					{1,1,1}
			});

			alphabet.put('2', new int[][] {
					{1,1,1},
					{0,0,1},
					{1,1,1},
					{1,0,0},
					{1,1,1}
			});

			alphabet.put('3', new int[][] {
					{1,1,1},
					{0,0,1},
					{1,1,1},
					{0,0,1},
					{1,1,1}
			});

			alphabet.put('4', new int[][] {
					{1,0,1},
					{1,0,1},
					{1,1,1},
					{0,0,1},
					{0,0,1}
			});

			alphabet.put('5', new int[][] {
					{1,1,1},
					{1,0,0},
					{1,1,1},
					{0,0,1},
					{1,1,1}
			});

			alphabet.put('6', new int[][] {
					{1,1,1},
					{1,0,0},
					{1,1,1},
					{1,0,1},
					{1,1,1}
			});

			alphabet.put('7', new int[][] {
					{1,1,1},
					{0,0,1},
					{0,0,1},
					{0,0,1},
					{0,0,1}
			});

			alphabet.put('8', new int[][] {
					{1,1,1},
					{1,0,1},
					{1,1,1},
					{1,0,1},
					{1,1,1}
			});

			alphabet.put('9', new int[][] {
					{1,1,1},
					{1,0,1},
					{1,1,1},
					{0,0,1},
					{1,1,1}
			});

			alphabet.put('.', new int[][] {
					{0},
					{0},
					{0},
					{0},
					{1}
			});

			alphabet.put('!', new int[][] {
					{1},
					{1},
					{1},
					{0},
					{1}
			});

			alphabet.put(' ', new int[][] {
					{0,0},
					{0,0},
					{0,0},
					{0,0},
					{0,0}
			});

			alphabet.put('a', new int[][] {
					{1,1,1,1},
					{1,0,0,1},
					{1,1,1,1},
					{1,0,0,1},
					{1,0,0,1}
			});

			alphabet.put('b', new int[][] {
					{1,1,1,0},
					{1,0,0,1},
					{1,1,1,0},
					{1,0,0,1},
					{1,1,1,0}
			});

			alphabet.put('c', new int[][] {
					{1,1,1,1},
					{1,0,0,0},
					{1,0,0,0},
					{1,0,0,0},
					{1,1,1,1}
			});

			alphabet.put('d', new int[][] {
					{1,1,1,0},
					{1,0,0,1},
					{1,0,0,1},
					{1,0,0,1},
					{1,1,1,0}
			});

			alphabet.put('e', new int[][] {
					{1,1,1,1},
					{1,0,0,0},
					{1,1,1,0},
					{1,0,0,0},
					{1,1,1,1}
			});

			alphabet.put('f', new int[][] {
					{1,1,1,1},
					{1,0,0,0},
					{1,1,1,0},
					{1,0,0,0},
					{1,0,0,0}
			});

			alphabet.put('g', new int[][] {
					{1,1,1,1},
					{1,0,0,0},
					{1,0,1,1},
					{1,0,0,1},
					{1,1,1,1}
			});

			alphabet.put('h', new int[][] {
					{1,0,0,1},
					{1,0,0,1},
					{1,1,1,1},
					{1,0,0,1},
					{1,0,0,1}
			});

			alphabet.put('i', new int[][] {
					{1,1,1},
					{0,1,0},
					{0,1,0},
					{0,1,0},
					{1,1,1}
			});

			alphabet.put('j', new int[][] {
					{1,1,1,1},
					{0,0,1,0},
					{0,0,1,0},
					{1,0,1,0},
					{1,1,1,0}
			});

			alphabet.put('k', new int[][] {
					{1,0,0,1},
					{1,0,1,0},
					{1,1,0,0},
					{1,0,1,0},
					{1,0,0,1}
			});

			alphabet.put('l', new int[][] {
					{1,0,0,0},
					{1,0,0,0},
					{1,0,0,0},
					{1,0,0,0},
					{1,1,1,1}
			});

			alphabet.put('m', new int[][] {
					{1,1,1,1,1},
					{1,0,1,0,1},
					{1,0,1,0,1},
					{1,0,0,0,1},
					{1,0,0,0,1}
			});

			alphabet.put('n', new int[][] {
					{1,0,0,1},
					{1,1,0,1},
					{1,0,1,1},
					{1,0,0,1},
					{1,0,0,1}
			});

			alphabet.put('o', new int[][] {
					{1,1,1,1},
					{1,0,0,1},
					{1,0,0,1},
					{1,0,0,1},
					{1,1,1,1}
			});

			alphabet.put('p', new int[][] {
					{1,1,1,1},
					{1,0,0,1},
					{1,1,1,1},
					{1,0,0,0},
					{1,0,0,0}
			});

			alphabet.put('q', new int[][] {
					{1,1,1,1},
					{1,0,0,1},
					{1,0,0,1},
					{1,0,1,0},
					{1,1,0,1}
			});

			alphabet.put('r', new int[][] {
					{1,1,1,1},
					{1,0,0,1},
					{1,1,1,0},
					{1,0,0,1},
					{1,0,0,1}
			});

			alphabet.put('s', new int[][] {
					{1,1,1,1},
					{1,0,0,0},
					{1,1,1,1},
					{0,0,0,1},
					{1,1,1,1}
			});

			alphabet.put('t', new int[][] {
					{1,1,1,1,1},
					{0,0,1,0,0},
					{0,0,1,0,0},
					{0,0,1,0,0},
					{0,0,1,0,0}
			});

			alphabet.put('u', new int[][] {
					{1,0,0,1},
					{1,0,0,1},
					{1,0,0,1},
					{1,0,0,1},
					{1,1,1,1}
			});

			alphabet.put('v', new int[][] {
					{1,0,0,1},
					{1,0,0,1},
					{1,0,0,1},
					{1,0,0,1},
					{0,1,1,0}
			});

			alphabet.put('w', new int[][] {
					{1,0,0,0,1},
					{1,0,0,0,1},
					{1,0,1,0,1},
					{1,0,1,0,1},
					{1,1,1,1,1}
			});

			alphabet.put('x', new int[][] {
					{1,0,0,1},
					{1,0,0,1},
					{0,1,1,0},
					{1,0,0,1},
					{1,0,0,1}
			});

			alphabet.put('y', new int[][] {
					{1,0,0,1},
					{1,0,0,1},
					{1,1,1,1},
					{0,0,0,1},
					{1,1,1,1}
			});

			alphabet.put('z', new int[][] {
					{1,1,1,1},
					{0,0,0,1},
					{0,0,1,0},
					{0,1,0,0},
					{1,1,1,1}
			});
			alphabet.put('_', new int[][] {
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{1,1,1,1}
			});
			alphabet.put(':', new int[][] {
				{0,0,0},
				{0,1,0},
				{0,0,0},
				{0,1,0},
				{0,0,0}
			});
			alphabet.put('%', new int[][] {
				{1,0,0,1},
				{0,0,1,0},
				{0,1,0,0},
				{0,1,0,0},
				{1,0,0,1}
		});
		}
	  
	  
	  
	  @SuppressWarnings("deprecation")
	public static void setBlock(World world, int x, int y, int z, int id, byte data, boolean animation)
	  {
		  if (animation)
		   {

	 
			  Location loc = new Location(Bukkit.getWorld(world.getName()), x,y,z);
			  final FallingBlock fb = world.spawnFallingBlock(loc, id, data);
			  fb.setDropItem(false);
			  fb.setVelocity(new Vector(0.0, 1.0, 0.0));    
	   }
		  else
		  {
			  Location loc = new Location(world,x,y,z);
			  loc.getBlock().setTypeId(id);
			  loc.getBlock().setData(data);
			  
		
		  }
		  
		  
	  }

	}