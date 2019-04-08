package com.interrupt.dungeoneer.entities.triggers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Array;
import com.interrupt.dungeoneer.annotations.EditorProperty;
import com.interrupt.dungeoneer.entities.Item;
import com.interrupt.dungeoneer.entities.items.BagUpgrade;
import com.interrupt.dungeoneer.entities.items.Elixer;
import com.interrupt.dungeoneer.game.Game;
import com.interrupt.dungeoneer.game.Level;
import com.interrupt.dungeoneer.game.Progression;
import com.interrupt.dungeoneer.overlays.MessageOverlay;
import com.interrupt.dungeoneer.overlays.Overlay;
import com.interrupt.dungeoneer.overlays.OverlayManager;
import com.interrupt.dungeoneer.overlays.ShopOverlay;
import com.interrupt.dungeoneer.overlays.CraftingOverlay;
import com.interrupt.dungeoneer.rpg.Stats;
import com.interrupt.helpers.CraftItem;
import com.interrupt.helpers.Upgrade;
import com.interrupt.managers.StringManager;

public class TriggeredCrafting extends Trigger {

    //Crafting Items
    public enum ItemType { catalysts, potions, weapons, wands, armor, persistent }

    @EditorProperty
    public String messageFile = null;

    @EditorProperty
    public ItemType itemType = ItemType.armor;

    @EditorProperty
    public String title = StringManager.get("triggers.TriggeredShop.titleText");

    @EditorProperty
    public String description = StringManager.get("triggers.TriggeredShop.descriptionText");

    @EditorProperty
    public boolean pausesGame = false;

    public Array<CraftItem> items = null;

    public TriggeredCrafting() { hidden = true; spriteAtlas = "editor"; tex = 16; isSolid = true; }

    private Integer messageProgression = null;

    @Override
    public void init(Level level, Level.Source source) {
        if(source == Level.Source.LEVEL_START) {
            if(messageFile != null && messageFile.contains(",")) {
                String[] messages = messageFile.split(",");

                Integer lastShown = null;
                if(title != null) {
                    lastShown = Game.instance.progression.messagesSeen.get(title);
                }

                if(lastShown == null) {
                    lastShown = -1;
                }
                int toShow = lastShown + 1;

                toShow = Math.min(toShow, messages.length - 1);

                messageProgression = toShow;
                messageFile = messages[toShow];
            }
        }

        super.init(level, source);
    }

    @Override
    public void doTriggerEvent(String value) {

        // We saw this, do we need to update the progression?
        if(messageProgression != null && title != null) {
            Game.instance.progression.messagesSeen.put(title, messageProgression);
        }

        if(messageFile != null && !messageFile.equals("")) {
            final MessageOverlay message = new MessageOverlay(messageFile, Game.instance.player, null, null);
            message.pausesGame = pausesGame;

            message.afterAction = new Action() {
                @Override
                public boolean act(float delta) {
                   // showCraftingOverlay(message);
                    return true;
                }
            };

            OverlayManager.instance.push(message);
        }
        else {
            showCraftOverlay(null);
        }

        super.doTriggerEvent(value);
    }

    public void showCraftOverlay(Overlay previousOverlay) {
        if(items == null) {
            items = new Array<CraftItem>();
            if(itemType == ItemType.catalysts) {
                items.add(new CraftItem(Game.instance.itemManager.GetRandomRangedWeapon(7, Item.ItemCondition.normal), "SHOP_JOFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomRangedWeapon(7, Item.ItemCondition.normal), "SHOP_JOFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWeapon(7, Item.ItemCondition.normal), "SHOP_JOFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomArmor(7, Item.ItemCondition.normal), "SHOP_JOFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWand(), "SHOP_WANDS"));
                items.add(new CraftItem(new Elixer(), "SHOP_JOFF_ELIXER"));
            }
            else if(itemType == ItemType.potions) {
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll(), "SHOP_MAGIC"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll(), "SHOP_MAGIC"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll(), "SHOP_MAGIC"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll(), "SHOP_MAGIC"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll(), "SHOP_MAGIC"));
            }
            else if(itemType == ItemType.potions) {
                items.add(new CraftItem(Game.instance.itemManager.GetRandomPotion(), "SHOP_POTIONS"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomPotion(), "SHOP_POTIONS"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomPotion(), "SHOP_POTIONS"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomPotion(), "SHOP_POTIONS"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomPotion(), "SHOP_POTIONS"));
            }
            else if(itemType == ItemType.wands) {
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWand(), "SHOP_WANDS"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWand(), "SHOP_WANDS"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWand(), "SHOP_WANDS"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWand(), "SHOP_WANDS"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWand(), "SHOP_WANDS"));
            }
            else if(itemType == ItemType.weapons) {
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWeapon(7),"SHOP_JEFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomWeapon(7),"SHOP_JEFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomRangedWeapon(7),"SHOP_JEFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomRangedWeapon(7),"SHOP_JEFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomArmor(7),"SHOP_JEFF"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomArmor(7),"SHOP_JEFF"));
            }
            else if(itemType == ItemType.armor) {
                items.add(new CraftItem(Game.instance.itemManager.GetRandomArmor(7),"SHOP_ARMOR"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomArmor(7),"SHOP_ARMOR"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomArmor(7),"SHOP_ARMOR"));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomArmor(7),"SHOP_ARMOR"));
            }
        }

        if(itemType == ItemType.persistent) {
            if(items == null) items = new Array<CraftItem>();
            else items.clear();

            if(Game.instance.player.canAddInventorySlot()) {
                BagUpgrade inventoryUpgrade = new BagUpgrade(BagUpgrade.BagUpgradeType.INVENTORY, true);
                inventoryUpgrade.name = "Soulbound Bag Expansion";
                inventoryUpgrade.cost = 30;
                inventoryUpgrade.cost += (Game.instance.progression.inventoryUpgrades * Game.instance.progression.inventoryUpgrades) * (int) (inventoryUpgrade.cost * 0.75f);
                items.add(new CraftItem(inventoryUpgrade, true,"SQUID2"));
            }
            if(Game.instance.player.canAddHotbarSlot()) {
                BagUpgrade hotbarUpgrade = new BagUpgrade(BagUpgrade.BagUpgradeType.HOTBAR, true);
                hotbarUpgrade.name = "Soulbound Belt Expansion";
                hotbarUpgrade.cost = 60;
                hotbarUpgrade.cost += (Game.instance.progression.hotbarUpgrades * Game.instance.progression.hotbarUpgrades) * (int) (hotbarUpgrade.cost);
                items.add(new CraftItem(hotbarUpgrade, true, "SQUID1"));
            }

            // No upgrades, sell scrolls instead
            if(items.size == 0) {
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll()));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll()));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll()));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll()));
                items.add(new CraftItem(Game.instance.itemManager.GetRandomScroll()));
            }
        }

        //Array<CraftItem> toRemove = new Array<CraftItem>();
       // for(CraftItem item : items) {
       //     if(item.item == null && item.upgrade == null) toRemove.add(item);
       // }
        //items.removeAll(toRemove, true);

        //if(previousOverlay != null) {
        //    CraftingOverlay craftOverlay = new CraftingOverlay(Game.instance.player, null, null, items);
        //    craftOverlay.pausesGame = pausesGame;
        //    craftOverlay.timer = 1000f;
        //    OverlayManager.instance.replace(previousOverlay, craftOverlay);
        //}
        //else {
        //    CraftingOverlay craftingOverlay = new CraftingOverlay(Game.instance.player, title, description, items, itemType);
        //    craftingOverlay.pausesGame = pausesGame;
         //   OverlayManager.instance.push(craftingOverlay);
        }
    }

