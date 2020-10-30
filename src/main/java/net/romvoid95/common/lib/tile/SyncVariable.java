/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.romvoid95.common.lib.tile;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import net.romvoid95.common.lib.interfaces.tile.INBTSerializer;

@SuppressWarnings("rawtypes")
@Retention(RUNTIME)
@Target(FIELD)
public @interface SyncVariable {
    /**
     * The name to read/write to NBT.
     */
    String name();

    /**
     * Should the variable be loaded in {@link TileEntity#readFromNBT}?
     */
    boolean onRead() default true;

    /**
     * Should the variable be saved in {@link TileEntity#writeToNBT}?
     */
    boolean onWrite() default true;

    /**
     * Should the variable be saved in {@link TileEntity#getUpdatePacket} and {@link
     * TileEntity#getUpdateTag}?
     */
    boolean onPacket() default true;

    enum Type {
        READ, WRITE, PACKET
    }

    final class Helper {
		static final Map<Class, Function<NBTTagCompound, ?>> READERS = new HashMap<>();
        static final Map<Class, Function<?, NBTTagCompound>> WRITERS = new HashMap<>();
        static final Map<Class, INBTSerializer> SERIALIZERS = new HashMap<>();

        private Helper() {}

        public static <T> void registerSerializer(Class<T> clazz,
                                                  Function<NBTTagCompound, T> reader,
                                                  BiConsumer<NBTTagCompound, T> writer) {
            SERIALIZERS.put(clazz, new INBTSerializer<T>() {
                @Override
                public T read(NBTTagCompound tags) {
                    return reader.apply(tags);
                }

                @Override
                public void write(NBTTagCompound tags, T obj) {
                    writer.accept(tags, obj);
                }
            });
        }

        /**
         * Reads sync variables for the object. This method will attempt to read a value from NBT
         * and assign that value for any field marked with the SyncVariable annotation.
         *
         * @param obj  The object with SyncVariable fields.
         * @param tags The NBT to read values from.
         */
        public static void readSyncVars(Object obj, NBTTagCompound tags) {

            for (Field field : obj.getClass().getDeclaredFields()) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof SyncVariable) {
                        SyncVariable sync = (SyncVariable) annotation;

                        try {
                            if (!field.isAccessible())
                                field.setAccessible(true);
                            String name = sync.name();

                            if (field.getType() == int.class)
                                field.setInt(obj, tags.getInteger(name));
                            else if (field.getType() == float.class)
                                field.setFloat(obj, tags.getFloat(name));
                            else if (field.getType() == String.class)
                                field.set(obj, tags.getString(name));
                            else if (field.getType() == boolean.class)
                                field.setBoolean(obj, tags.getBoolean(name));
                            else if (field.getType() == double.class)
                                field.setDouble(obj, tags.getDouble(name));
                            else if (field.getType() == long.class)
                                field.setLong(obj, tags.getLong(name));
                            else if (field.getType() == short.class)
                                field.setShort(obj, tags.getShort(name));
                            else if (field.getType() == byte.class)
                                field.setByte(obj, tags.getByte(name));
                            else if (SERIALIZERS.containsKey(field.getType())) {
                                INBTSerializer serializer = SERIALIZERS.get(field.getType());
                                NBTTagCompound compound = tags.getCompoundTag(name);
                                field.set(obj, serializer.read(compound));
                            } else
                                throw new UnsupportedDataTypeException(
                                        "Don't know how to read type " + field.getType() + " from NBT!");
                        } catch (IllegalAccessException | UnsupportedDataTypeException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

        /**
         * Writes sync variables for the object. This method will take the values in all fields
         * marked with the SyncVariable annotation and save them to NBT.
         *
         * @param obj      The object with SyncVariable fields.
         * @param tags     The NBT to save values to.
         * @param syncType The sync type (WRITE or PACKET).
         * @return The modified tags.
         */
        @SuppressWarnings("unchecked")
		public static NBTTagCompound writeSyncVars(Object obj, NBTTagCompound tags, Type syncType) {

            for (Field field : obj.getClass().getDeclaredFields()) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof SyncVariable) {
                        SyncVariable sync = (SyncVariable) annotation;

                        if (syncType == SyncVariable.Type.WRITE && sync.onWrite()
                                || syncType == SyncVariable.Type.PACKET && sync.onPacket()) {
                            try {
                                if (!field.isAccessible())
                                    field.setAccessible(true);
                                String name = sync.name();

                                if (field.getType() == int.class)
                                    tags.setInteger(name, field.getInt(obj));
                                else if (field.getType() == float.class)
                                    tags.setFloat(name, field.getFloat(obj));
                                else if (field.getType() == String.class)
                                    tags.setString(name, (String) field.get(obj));
                                else if (field.getType() == boolean.class)
                                    tags.setBoolean(name, field.getBoolean(obj));
                                else if (field.getType() == double.class)
                                    tags.setDouble(name, field.getDouble(obj));
                                else if (field.getType() == long.class)
                                    tags.setLong(name, field.getLong(obj));
                                else if (field.getType() == short.class)
                                    tags.setShort(name, field.getShort(obj));
                                else if (field.getType() == byte.class)
                                    tags.setByte(name, field.getByte(obj));
                                else if (SERIALIZERS.containsKey(field.getType())) {
                                    NBTTagCompound compound = new NBTTagCompound();
                                    INBTSerializer<Object> serializer = SERIALIZERS.get(field.getType());
                                    serializer.write(compound, field.get(obj));
                                    tags.setTag(name, compound);
                                } else
                                    throw new UnsupportedDataTypeException(
                                            "Don't know how to write type " + field.getType() + " to NBT!");
                            } catch (IllegalAccessException | UnsupportedDataTypeException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }

            return tags;
        }
    }
}
