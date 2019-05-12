package com.interrupt.dungeoneer.entities.items;

import com.badlogic.gdx.utils.ArrayMap;
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

public class CraftingItem {
    // Properties
    public String item;
    public Array<CraftingRow> craftingRow;
    public static CraftingItem STEEL = new CraftingItem();

    // Constructor
    public CraftingItem() {
        // Sword
        CraftingItem craftingSword = new CraftingItem();
        craftingSword.item = "Steel Sword";

        CraftingRow newRow = new CraftingRow();
        newRow.amountNeeded = 1;
        newRow.craftingItemNeeded = CraftingItem.STEEL;

        this.craftingRow.add(newRow);
    }

}
