package com.interrupt.dungeoneer.entities.items;

import com.interrupt.dungeoneer.entities.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.interrupt.dungeoneer.Audio;
import com.interrupt.dungeoneer.GameManager;
import com.interrupt.dungeoneer.annotations.EditorProperty;
import com.interrupt.dungeoneer.entities.Entity.CollidesWith;
import com.interrupt.dungeoneer.entities.items.Bow;
import com.interrupt.dungeoneer.entities.items.ItemModification;
import com.interrupt.dungeoneer.entities.items.Weapon;
import com.interrupt.dungeoneer.entities.items.Weapon.DamageType;
import com.interrupt.dungeoneer.game.CachePools;
import com.interrupt.dungeoneer.game.Colors;
import com.interrupt.dungeoneer.game.Game;
import com.interrupt.dungeoneer.game.Level;
import com.interrupt.dungeoneer.gfx.GlRenderer;
import com.interrupt.dungeoneer.gfx.TextureAtlas;
import com.interrupt.dungeoneer.gfx.drawables.DrawableSprite;
import com.interrupt.managers.StringManager;
import com.interrupt.dungeoneer.entities.items.CraftingRow;
import java.text.MessageFormat;
import java.util.Random;

public class CraftingItem {
    public Item item;
    public Array<CraftingRow> craftingRow;

    // Bow
    CraftingItem craftingBow = new CraftingItem();
    craftingBow.item = ();

    CraftingRow newRow = new CraftingRow();
    newRow.amountNeeded = 1;
    newRow.craftingItemNeeded = CraftingItem.IRON;

    craftingBow.craftingRow.Add(newRow);

    // Sword
    CraftingItem craftingSword = new CraftingItem();
    craftingSword.item = ();

    CraftingRow newRow = new CraftingRow();
    newRow.amountNeeded = 1;
    newRow.craftingItemNeeded = CraftingItem.IRON;

    craftingSword.craftingRow.Add(newRow);

    // Wand
    CraftingItem craftingWand = new CraftingItem();
    craftingWand.item = ();

    CraftingRow newRow = new CraftingRow();
    newRow.amountNeeded = 1;
    newRow.craftingItemNeeded = CraftingItem.IRON;

    craftingWand.craftingRow.Add(newRow);

    // Armor
    CraftingItem craftingArmor = new CraftingItem();
    craftingWand.item = ();

    CraftingRow newRow = new CraftingRow();
    newRow.amountNeeded = 1;
    newRow.craftingItemNeeded = CraftingItem.IRON;

    craftingArmor.craftingRow.Add(newRow);
}
