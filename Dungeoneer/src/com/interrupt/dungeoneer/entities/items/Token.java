package com.interrupt.dungeoneer.entities.items;

import com.interrupt.dungeoneer.Audio;
import com.interrupt.dungeoneer.annotations.EditorProperty;
import com.interrupt.dungeoneer.entities.Item;
import com.interrupt.dungeoneer.entities.Player;
import com.interrupt.dungeoneer.game.Game;
import com.interrupt.dungeoneer.game.Level;
import com.interrupt.managers.StringManager;

import java.text.MessageFormat;

public class Token extends Item {
	
	public Token() {
		tex = 88;
		artType = ArtType.item;
		name = StringManager.get("items.Token.defaultNameText");
		collidesWith = CollidesWith.staticOnly;
		dropSound = "drops/drop_gold.mp3";
		collision.x = 0.1f;
		collision.y = 0.1f;
	}
	
	@EditorProperty
	public int tokenAmount = 1;
	
	public boolean autoPickup = false;
	
	public boolean playedDropSound = false;

	public Token(float x, float y) {
		super(x, y, 0, ItemType.gold, StringManager.get("items.Token.defaultNameText"));
	}
	
	public Token(int amount) {
		this();
		tokenAmount = amount;
		this.name = StringManager.get("items.Token.defaultNameText");

		if(tokenAmount <= 0) tokenAmount = 1;
		if(tokenAmount > 5) tex = 89;
		
		pickupSound = "pu_gold.mp3";
	}

	@Override
	public String GetItemText() {
		return MessageFormat.format(StringManager.get("items.Token.goldItemText"), this.tokenAmount);
	}
	
	@Override
	public void tick(Level level, float delta)
	{
		super.tick(level, delta);
		
		if(isActive && autoPickup) {
			Player p = Game.instance.player;
			if(Math.abs(p.x + 0.5f - x) < 0.3f && Math.abs(p.y + 0.5f - y ) < 0.3f) {
				p.token++;
				isActive = false;
			}
		}
	}
	
	protected void pickup(Player player) {
		if(isActive) {
			player.token += tokenAmount;
			isActive = false;
			Audio.playSound(pickupSound, 0.3f, 1f);
			makeItemPickupAnimation(player);
		}
	}
}