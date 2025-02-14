// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world;

import java.util.Set;
import java.util.Iterator;
import net.minecraft.nbt.NBTTagCompound;
import java.util.TreeMap;

public class GameRules
{
    private final TreeMap<String, Value> rules;
    
    public GameRules() {
        this.rules = new TreeMap<String, Value>();
        this.addGameRule("doFireTick", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("mobGriefing", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("keepInventory", "false", ValueType.BOOLEAN_VALUE);
        this.addGameRule("doMobSpawning", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("doMobLoot", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("doTileDrops", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("doEntityDrops", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("commandBlockOutput", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("naturalRegeneration", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("doDaylightCycle", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("logAdminCommands", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("showDeathMessages", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("randomTickSpeed", "3", ValueType.NUMERICAL_VALUE);
        this.addGameRule("sendCommandFeedback", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("reducedDebugInfo", "false", ValueType.BOOLEAN_VALUE);
        this.addGameRule("spectatorsGenerateChunks", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("spawnRadius", "10", ValueType.NUMERICAL_VALUE);
        this.addGameRule("disableElytraMovementCheck", "false", ValueType.BOOLEAN_VALUE);
        this.addGameRule("maxEntityCramming", "24", ValueType.NUMERICAL_VALUE);
        this.addGameRule("doWeatherCycle", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("doLimitedCrafting", "false", ValueType.BOOLEAN_VALUE);
        this.addGameRule("maxCommandChainLength", "65536", ValueType.NUMERICAL_VALUE);
        this.addGameRule("announceAdvancements", "true", ValueType.BOOLEAN_VALUE);
        this.addGameRule("gameLoopFunction", "-", ValueType.FUNCTION);
    }
    
    public void addGameRule(final String key, final String value, final ValueType type) {
        this.rules.put(key, new Value(value, type));
    }
    
    public void setOrCreateGameRule(final String key, final String ruleValue) {
        final Value gamerules$value = this.rules.get(key);
        if (gamerules$value != null) {
            gamerules$value.setValue(ruleValue);
        }
        else {
            this.addGameRule(key, ruleValue, ValueType.ANY_VALUE);
        }
    }
    
    public String getString(final String name) {
        final Value gamerules$value = this.rules.get(name);
        return (gamerules$value != null) ? gamerules$value.getString() : "";
    }
    
    public boolean getBoolean(final String name) {
        final Value gamerules$value = this.rules.get(name);
        return gamerules$value != null && gamerules$value.getBoolean();
    }
    
    public int getInt(final String name) {
        final Value gamerules$value = this.rules.get(name);
        return (gamerules$value != null) ? gamerules$value.getInt() : 0;
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound nbttagcompound = new NBTTagCompound();
        for (final String s : this.rules.keySet()) {
            final Value gamerules$value = this.rules.get(s);
            nbttagcompound.setString(s, gamerules$value.getString());
        }
        return nbttagcompound;
    }
    
    public void readFromNBT(final NBTTagCompound nbt) {
        for (final String s : nbt.getKeySet()) {
            this.setOrCreateGameRule(s, nbt.getString(s));
        }
    }
    
    public String[] getRules() {
        final Set<String> set = this.rules.keySet();
        return set.toArray(new String[set.size()]);
    }
    
    public boolean hasRule(final String name) {
        return this.rules.containsKey(name);
    }
    
    public boolean areSameType(final String key, final ValueType otherValue) {
        final Value gamerules$value = this.rules.get(key);
        return gamerules$value != null && (gamerules$value.getType() == otherValue || otherValue == ValueType.ANY_VALUE);
    }
    
    static class Value
    {
        private String valueString;
        private boolean valueBoolean;
        private int valueInteger;
        private double valueDouble;
        private final ValueType type;
        
        public Value(final String value, final ValueType type) {
            this.type = type;
            this.setValue(value);
        }
        
        public void setValue(final String value) {
            this.valueString = value;
            if (value != null) {
                if (value.equals("false")) {
                    this.valueBoolean = false;
                    return;
                }
                if (value.equals("true")) {
                    this.valueBoolean = true;
                    return;
                }
            }
            this.valueBoolean = Boolean.parseBoolean(value);
            this.valueInteger = (this.valueBoolean ? 1 : 0);
            try {
                this.valueInteger = Integer.parseInt(value);
            }
            catch (final NumberFormatException ex) {}
            try {
                this.valueDouble = Double.parseDouble(value);
            }
            catch (final NumberFormatException ex2) {}
        }
        
        public String getString() {
            return this.valueString;
        }
        
        public boolean getBoolean() {
            return this.valueBoolean;
        }
        
        public int getInt() {
            return this.valueInteger;
        }
        
        public ValueType getType() {
            return this.type;
        }
    }
    
    public enum ValueType
    {
        ANY_VALUE, 
        BOOLEAN_VALUE, 
        NUMERICAL_VALUE, 
        FUNCTION;
    }
}
