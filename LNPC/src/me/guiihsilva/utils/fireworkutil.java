package me.guiihsilva.utils;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class fireworkutil {

	@SuppressWarnings("unused")
	private static final int RGB_MAX = 255;
	  private static Random r = new Random();
	  
	  public static void spawnRandomFirework(Location location)
	  {
	    FireworkEffect fe = getRandomFireworkEffect();
	    
	    Firework fw = (Firework)location.getWorld().spawnEntity(location, EntityType.FIREWORK);
	    FireworkMeta fm = fw.getFireworkMeta();
	    
	    fm.addEffect(fe);
	    
	    int rp = r.nextInt(2) + 1;
	    fm.setPower(rp);
	    
	    fw.setFireworkMeta(fm);
	  }
	  
	  public static FireworkEffect getRandomFireworkEffect()
	  {
	    Color color = getRandomBukkitColor();
	    Color fade = getRandomBukkitColor();
	    
	    int rt = r.nextInt(4) + 1;
	    FireworkEffect.Type type;
	    switch (rt){
	    case 2: 
	      type = FireworkEffect.Type.BALL_LARGE;
	      break;
	    case 3: 
	      type = FireworkEffect.Type.BURST;
	      break;
	    case 4: 
	      type = FireworkEffect.Type.CREEPER;
	      break;
	    case 5: 
	      type = FireworkEffect.Type.STAR;
	      break;
	    default: 
	      type = FireworkEffect.Type.BALL;
	    }
	    if (rt == 1) {
	      type = FireworkEffect.Type.BALL;
	    }
	    if (rt == 2) {
	      type = FireworkEffect.Type.BALL_LARGE;
	    }
	    if (rt == 3) {
	      type = FireworkEffect.Type.BURST;
	    }
	    if (rt == 4) {
	      type = FireworkEffect.Type.CREEPER;
	    }
	    return FireworkEffect.builder().flicker(r.nextBoolean()).withColor(color).withFade(fade).with(type).trail(r.nextBoolean()).build();
	  }
	  
	  public static Color getRandomColor()
	  {
	    return Color.fromRGB(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	  }
	  
	  public static Color getRandomBukkitColor()
	  {
	    int rt = r.nextInt(16);
	    switch (rt)
	    {
	    case 0: 
	      return Color.WHITE;
	    case 1: 
	      return Color.SILVER;
	    case 2: 
	      return Color.GRAY;
	    case 3: 
	      return Color.BLACK;
	    case 4: 
	      return Color.RED;
	    case 5: 
	      return Color.MAROON;
	    case 6: 
	      return Color.YELLOW;
	    case 7: 
	      return Color.OLIVE;
	    case 8: 
	      return Color.LIME;
	    case 9: 
	      return Color.GREEN;
	    case 10: 
	      return Color.AQUA;
	    case 11: 
	      return Color.TEAL;
	    case 12: 
	      return Color.BLUE;
	    case 13: 
	      return Color.NAVY;
	    case 14: 
	      return Color.FUCHSIA;
	    case 15: 
	      return Color.PURPLE;
	    case 16: 
	      return Color.ORANGE;
	    }
	    return Color.AQUA;
	  }
}
