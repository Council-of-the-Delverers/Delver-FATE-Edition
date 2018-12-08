package com.interrupt.dungeoneer.entities.items;

import com.interrupt.dungeoneer.Audio;
import com.interrupt.dungeoneer.annotations.EditorProperty;
import com.interrupt.dungeoneer.entities.Item;
import com.interrupt.dungeoneer.entities.Player;
import com.interrupt.dungeoneer.game.Game;
import com.interrupt.dungeoneer.game.Level;
import com.interrupt.managers.StringManager;

import java.text.MessageFormat;

public class Shard extends Item {
	
	public Shard() {
		tex = 88;
		artType = ArtType.item;
		name = StringManager.get("items.Shard.defaultNameText");
		collidesWith = CollidesWith.staticOnly;
		dropSound = "drops/drop_gold.mp3";
		collision.x = 0.1f;
		collision.y = 0.1f;
	}
	
	@EditorProperty
	public int shardAmount = 1;
	
	public boolean autoPickup = false;
	
	public boolean playedDropSound = false;

	public Shard(float x, float y) {
		super(x, y, 0, ItemType.gold, StringManager.get("items.Shard.defaultNameText"));
	}
	
	public Shard(int amount) {
		this();
		shardAmount = amount;
		this.name = StringManager.get("items.Shard.defaultNameText");

		if(shardAmount <= 0) shardAmount = 1;
		if(shardAmount > 5) tex = 89;
		
		pickupSound = "pu_gold.mp3";
	}

	@Override
	public String GetItemText() {
		return MessageFormat.format(StringManager.get("items.Shard.goldItemText"), this.shardAmount);
	}
	
	@Override
	public void tick(Level level, float delta)
	{
		super.tick(level, delta);
		
		if(isActive && autoPickup) {
			Player p = Game.instance.player;
			if(Math.abs(p.x + 0.5f - x) < 0.3f && Math.abs(p.y + 0.5f - y ) < 0.3f) {
				p.fame++;
				isActive = false;
			}
		}
	}
	
	protected void pickup(Player player) {
		if(isActive) {
			player.shard += shardAmount;
			isActive = false;
			Audio.playSound(pickupSound, 0.3f, 1f);
			makeItemPickupAnimation(player);
		}
	}
}