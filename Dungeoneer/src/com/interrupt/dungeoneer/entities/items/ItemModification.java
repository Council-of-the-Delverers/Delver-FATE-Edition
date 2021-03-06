package com.interrupt.dungeoneer.entities.items;

import com.interrupt.dungeoneer.entities.Item;
import com.interrupt.dungeoneer.entities.items.Weapon.DamageType;

public class ItemModification {
	public String name;
	protected int hpMod = 0;
    protected int mpMod = 0;

    protected int armorMod = 0;
    protected int moveSpeedMod = 0;
    protected int agilityMod = 0;
    protected int magicMod = 0;
    protected int attackMod = 0;

    protected float attackSpeedMod = 0;
    protected int damageMod = 0;
    protected float knockbackMod = 0;

    protected float magicResistMod = 0f;
    protected float fireResistMod = 0f;
    protected float iceResistMod = 0f;
    protected float poisonResistMod = 0f;

    protected int fireResist = 0;
    protected int iceResist = 0;
    //protected int lightningResist = 0;
    protected int magicResist = 0;
    protected int poisonResist = 0;
	
	public DamageType damageType = DamageType.PHYSICAL;
	
	public ItemModification() { }
	public ItemModification(String name) { this.name = name; }

	public int getHpMod(Item owner) {
	    return hpMod + (int)(hpMod * owner.itemLevel * 0.5f);
    }

    public int getMpMod(Item owner) {
	    return mpMod + (int)(mpMod * owner.itemLevel * 0.5f);
    }

    public int getArmorMod(Item owner) {
        return armorMod + (int)(armorMod * owner.itemLevel * 0.5f);
    }

    public int getMoveSpeedMod(Item owner) {
	    return moveSpeedMod;
    }

    public int getAgilityMod(Item owner) {
	    return agilityMod + (int)(agilityMod * owner.itemLevel * 0.5f);
    }

    public int getMagicMod(Item owner) {
	    return magicMod + (int)(magicMod * owner.itemLevel * 0.5f);
    }

    public int getAttackMod(Item owner) {
	    return attackMod + (int)(attackMod * owner.itemLevel * 0.5f);
    }

    public float getAttackSpeedMod(Item owner) {
	    return attackSpeedMod + (attackSpeedMod * owner.itemLevel * 0.05f);
    }

    public int getDamageMod(Item owner) {
	    return damageMod + (int)(damageMod * owner.itemLevel * 0.5f);
    }

    public float getKnockbackMod(Item owner) {
	    return knockbackMod + (knockbackMod * owner.itemLevel * 0.05f);
    }

    public void increaseAttackMod(int value) {
	    attackMod += value;
    }

    public void increaseArmorMod(int value) {
	    armorMod += value;
    }

    public float getMagicResistMod(Item owner) { return magicResistMod + (magicResistMod * owner.itemLevel * 0.05f); }

    public float getFireResistMod(Item owner) { return fireResistMod + (fireResistMod * owner.itemLevel * 0.05f); }

    public float getIceResistMod(Item owner) { return iceResistMod + (iceResistMod * owner.itemLevel * 0.05f); }

    public float getPoisonResistMod(Item owner) { return poisonResistMod + (poisonResistMod * owner.itemLevel * 0.05f); }

    public int getCostMod() {
	    int costMod = 0;
	    costMod += Math.min(hpMod * 10, 0);
        costMod += Math.min(mpMod * 10, 0);
        costMod += Math.min(armorMod * 10, 0);
        costMod += Math.min(moveSpeedMod * 10, 0);
        costMod += Math.min(agilityMod * 10, 0);
        costMod += Math.min(magicMod * 10, 0);
        costMod += Math.min(attackMod * 10, 0);
        costMod += Math.min(10 / attackSpeedMod, 0);
        costMod += Math.min(damageMod, 0);
        costMod += Math.min(25 * knockbackMod, 0);
        costMod += Math.min(1000 * magicResistMod, 0);
        costMod += Math.min(1000 * fireResistMod, 0);
        costMod += Math.min(1000 * iceResistMod, 0);
        costMod += Math.min(1000 * poisonResistMod, 0);

        if(damageType != DamageType.PHYSICAL) {
            costMod += 40;
        }

        if(damageType != DamageType.FIRE) {
            costMod += 40;
        }

        if(damageType != DamageType.ICE) {
            costMod += 40;
        }

        if(damageType != DamageType.POISON) {
            costMod += 40;
        }

        // put a sane cap on things
        if(costMod > 3000)
            costMod = 3000;

	    return costMod;
    }
}
