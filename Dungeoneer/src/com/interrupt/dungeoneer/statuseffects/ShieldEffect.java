package com.interrupt.dungeoneer.statuseffects;

public class ShieldEffect extends StatusEffect {
	public ShieldEffect() { }
	
	public ShieldEffect(String name, float damageMod, float magicDamageMod, float fireDamageMod, float iceDamageMod, float poisonDamageMod, int time) {
		this.name = name;
		this.timer = time;
		this.statusEffectType = StatusEffectType.SHIELD;
		
		this.damageMod = damageMod;
		this.magicDamageMod = magicDamageMod;
		this.fireDamageMod = fireDamageMod;
		this.iceDamageMod = iceDamageMod;
		this.poisonDamageMod = poisonDamageMod;
	}
}
