/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraft.world.storage.loot.properties.EntityProperty;
import net.minecraft.world.storage.loot.properties.EntityPropertyManager;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.internal.block.BlockMetaSubtypes;
import net.rom.exoplanets.internal.block.IColorBlock;
import net.rom.exoplanets.internal.block.ITEBlock;
import net.rom.exoplanets.internal.client.ICustomMesh;
import net.rom.exoplanets.internal.client.ICustomModel;
import net.rom.exoplanets.internal.item.IColorItem;
import net.rom.exoplanets.internal.item.ItemBlockMetaSubtypes;

public class StellarRegistry {
    private static final Pattern PATTERN_REGISTRY_NAME = Pattern.compile("[^a-z0-9_]+");

    public final List<Block> blocks = NonNullList.create();
    public final List<Item> items = NonNullList.create();

    public List<Block> getBlocks() {
		return this.blocks;
	}

	public List<Item> getItems() {
		return this.items;
	}

	private final List<IAddRecipe> recipeAdders = NonNullList.create();
    private final List<Block> coloredBlocks = NonNullList.create();
    private final List<Item> coloredItems = NonNullList.create();

    private final Map<Class<? extends IForgeRegistryEntry<?>>, Consumer<StellarRegistry>> registrationHandlers = new HashMap<>();

    private Object mod;
    private final LogHelper logger;
    private final String modId;
    private final String resourcePrefix;

    @Nonnull
    private final RecipeBuilder recipes;

    @Nullable
    private CreativeTabs defaultCreativeTab = null;
    
    @Nullable
    private CreativeTabs creativeTab = null;

    /**
     * Constructor which automatically acquires the mod container to populate required fields.
     *
     */
    public StellarRegistry() {
        ModContainer mod = Objects.requireNonNull(Loader.instance().activeModContainer());
        this.modId = mod.getModId();
        this.resourcePrefix = this.modId + ":";
        this.logger = ExoplanetsMod.logger;
        this.recipes = new RecipeBuilder(this.modId);
        MinecraftForge.EVENT_BUS.register(new EventHandler(this));
    }

    public RecipeBuilder getRecipeMaker() {
        return this.recipes;
    }

    /**
     * Set the mod instance object
     */
    public void setMod(Object mod) {
        this.mod = mod;
    }

    /**
     * Adds a function that will be called when it is time to register objects for a certain class.
     * For example, adding a handler for class {@link Item} will call the function during {@link
     * net.minecraftforge.event.RegistryEvent.Register} for type {@link Item}.
     * <p>This method should be called during <em>pre-init</em> in the proper proxy.</p>
     *
     * @param registerFunction The function to call
     * @param registryClass    The registry object class
     * @throws RuntimeException if a handler for the class is already registered
     */
    public void addRegistrationHandler(Consumer<StellarRegistry> registerFunction, Class<? extends IForgeRegistryEntry<?>> registryClass) throws RuntimeException {
        if (this.registrationHandlers.containsKey(registryClass)) {
            throw new RuntimeException("Registration handler for class " + registryClass + " already registered!");
        }
        this.registrationHandlers.put(registryClass, registerFunction);
    }

    /**
     * Register a Block. Its name (registry key/name) must be provided. Uses a new ItemBlockSL.
     */
    public <T extends Block> T registerBlock(T block, String key) {
        return registerBlock(block, key, defaultItemBlock(block));
    }

    @Nonnull
    private <T extends Block> ItemBlock defaultItemBlock(T block) {
        if (block instanceof BlockMetaSubtypes) {
            return new ItemBlockMetaSubtypes((BlockMetaSubtypes) block);
        } else {
            return new ItemBlock(block);
        }
    }

    /**
     * Register a Block. Its name registry name and ItemBlock must be provided.
     */
    public <T extends Block> T registerBlock(T block, String key, ItemBlock itemBlock) {
        this.blocks.add(block);
        block.setUnlocalizedName(this.modId + "." + key);

        validateRegistryName(key);
        ResourceLocation name = new ResourceLocation(this.modId, key);
        safeSetRegistryName(block, name);
        ForgeRegistries.BLOCKS.register(block);

        safeSetRegistryName(itemBlock, name);
        ForgeRegistries.ITEMS.register(itemBlock);

        // Register TileEntity
        if (block instanceof ITEBlock) {
            Class<? extends TileEntity> clazz = ((ITEBlock) block).getTileEntityClass();
            registerTileEntity(clazz, key);
        }

        if (block instanceof IAddRecipe) {
            this.recipeAdders.add((IAddRecipe) block);
        }

        if (MCUtil.isClient() && block instanceof IColorBlock) {
            this.coloredBlocks.add(block);
        }

        if (this.creativeTab != null) {
            block.setCreativeTab(this.creativeTab);
        }

        return block;
    }

    // Item

    /**
     * Register an Item. Its name (registry key/name) must be provided.
     */
    public <T extends Item> T registerItem(T item, String key) {
        this.items.add(item);
        item.setUnlocalizedName(this.modId + "." + key.toLowerCase());
        validateRegistryName(key);
        ResourceLocation name = new ResourceLocation(this.modId, key);
        safeSetRegistryName(item, name);
        ForgeRegistries.ITEMS.register(item);

        if (item instanceof IAddRecipe) {
            this.recipeAdders.add((IAddRecipe) item);
        }

        if (MCUtil.isClient() && item instanceof IColorItem) {
            this.coloredItems.add(item);
        }

        if (this.defaultCreativeTab != null) {
            item.setCreativeTab(this.defaultCreativeTab);
        }

        return item;
    }

    // Enchantment

    public void registerEnchantment(Enchantment enchantment, String key) {
        validateRegistryName(key);
        ResourceLocation name = new ResourceLocation(this.modId, key);
        safeSetRegistryName(enchantment, name);
        enchantment.setName(name.getResourceDomain() + "." + name.getResourcePath());
        ForgeRegistries.ENCHANTMENTS.register(enchantment);
    }

    // Entity

    /**
     * Automatically incrementing ID number for registering entities.
     */
    private int lastEntityId = -1;

    public void registerEntity(Class<? extends Entity> entityClass, String key) {
        registerEntity(entityClass, key, ++this.lastEntityId, this.mod, 64, 20, true);
    }

    public void registerEntity(Class<? extends Entity> entityClass, String key, int trackingRange, int updateFrequency,
                               boolean sendsVelocityUpdates) {
        registerEntity(entityClass, key, ++this.lastEntityId, this.mod, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public void registerEntity(Class<? extends Entity> entityClass, String key, int id, Object mod, int trackingRange,
                               int updateFrequency, boolean sendsVelocityUpdates) {
        ResourceLocation resource = new ResourceLocation(this.modId, key);
        EntityRegistry.registerModEntity(resource, entityClass, key, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public void registerEntity(Class<? extends Entity> entityClass, String key, int trackingRange, int updateFrequency,
                               boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
        registerEntity(entityClass, key, ++this.lastEntityId, this.mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
    }

    public void registerEntity(Class<? extends Entity> entityClass, String key, int id, Object mod, int trackingRange,
                               int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
        ResourceLocation resource = new ResourceLocation(this.modId, key);
        EntityRegistry.registerModEntity(resource, entityClass, key, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
    }

    @SideOnly(Side.CLIENT)
    public <T extends Entity> void registerEntityRenderer(Class<T> entityClass, IRenderFactory<T> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }

    // Potion

    public void registerPotion(Potion potion, String key) {
        if (potion.getName().isEmpty()) {
            potion.setPotionName("effect." + this.modId + "." + key);
        }

        validateRegistryName(key);
        ResourceLocation name = new ResourceLocation(this.modId, key);
        safeSetRegistryName(potion, name);
        ForgeRegistries.POTIONS.register(potion);
    }

    // Sound Events

    public void registerSoundEvent(SoundEvent sound, String key) {
        validateRegistryName(key);
        ResourceLocation name = new ResourceLocation(this.modId, key);
        safeSetRegistryName(sound, name);
        ForgeRegistries.SOUND_EVENTS.register(sound);
    }

    // Loot

    public void registerLootCondition(LootCondition.Serializer<? extends LootCondition> serializer) {
        LootConditionManager.registerCondition(serializer);
    }

    public void registerLootEntityProperty(EntityProperty.Serializer<? extends EntityProperty> serializer) {
        EntityPropertyManager.registerProperty(serializer);
    }

    public void registerLootFunction(LootFunction.Serializer<? extends LootFunction> serializer) {
        LootFunctionManager.registerFunction(serializer);
    }

    public void registerLootTable(String name) {
        LootTableList.register(new ResourceLocation(this.modId, name));
    }

    /**
     * Set the object's registry name, if it has not already been set. Logs a warning if it has.
     */
    private void safeSetRegistryName(IForgeRegistryEntry<?> entry, ResourceLocation name) {
        if (entry.getRegistryName() == null) {
            entry.setRegistryName(name);
        } else {
            this.logger.formatted_Warn("Registry name for {} has already been set. Was trying to set it to {}.", entry.getRegistryName(), name);
        }
    }

    /**
     * Ensure the given name does not contain upper case letters. This is not a problem until 1.13,
     * so just log it as a warning.
     */
    private void validateRegistryName(String name) {
        if (PATTERN_REGISTRY_NAME.matcher(name).matches()) {
            this.logger.formatted_Warn("Invalid name for object: {}", name);
        }
    }

    // Advancements
    public <T extends ICriterionInstance> ICriterionTrigger<T> registerAdvancementTrigger(ICriterionTrigger<T> trigger) {
        CriteriaTriggers.register(trigger);
        return trigger;
    }

    /**
     * Register a TileEntity. "tile." + resourcePrefix is automatically prepended to the key.
     */
    public void registerTileEntity(Class<? extends TileEntity> tileClass, String key) {
        GameRegistry.registerTileEntity(tileClass, new ResourceLocation(this.modId, key));
    }

    /**
     * Registers a renderer for a TileEntity.
     */
    @SideOnly(Side.CLIENT)
    public <T extends TileEntity> void registerTileEntitySpecialRenderer(Class<T> tileClass, TileEntitySpecialRenderer<T> renderer) {
        ClientRegistry.bindTileEntitySpecialRenderer(tileClass, renderer);
    }

    // Model registration wrappers

    @SideOnly(Side.CLIENT)
    public void setModel(Block block, int meta, String modelPath) {
        setModel(Item.getItemFromBlock(block), meta, modelPath, "inventory");
    }

    @SideOnly(Side.CLIENT)
    public void setModel(Block block, int meta, String modelPath, String variant) {
        setModel(Item.getItemFromBlock(block), meta, modelPath, variant);
    }

    @SideOnly(Side.CLIENT)
    public void setModel(Item item, int meta, String modelPath) {
        setModel(item, meta, modelPath, "inventory");
    }

    @SideOnly(Side.CLIENT)
    public void setModel(Item item, int meta, String modelPath, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(this.resourcePrefix + modelPath, variant));
    }

    //endregion

    //region Initialization phases

    /*
     * Initialization phases. Calling in either your common or client proxy is recommended. "client" methods in your
     * client proxy, the rest in your common AND client proxy.
     */

    private boolean preInitDone = false;
    private boolean initDone = false;
    private boolean postInitDone = false;

    /**
     * Call in the "preInit" phase in your common proxy.
     */
    public void preInit(FMLPreInitializationEvent event) {
        if (this.preInitDone) {
            this.logger.warn("preInit called more than once!");
            return;
        }

        verifyOrFindModObject();
        this.preInitDone = true;
    }

    private void verifyOrFindModObject() {
        if (this.mod == null) {
            this.logger.formatted_Warn("Mod {} did not manually set its mod object! This is bad and may cause crashes.", this.modId);
            ModContainer container = Loader.instance().getIndexedModList().get(this.modId);
            if (container != null) {
                this.mod = container.getMod();
                this.logger.formatted_Warn("Automatically acquired mod object for {}", this.modId);
            } else {
                this.logger.formatted_Warn("Could not find mod object. The mod ID is likely incorrect.");
            }
        }
    }

    /**
     * Call in the "init" phase in your common proxy.
     */
    public void init(FMLInitializationEvent event) {
        if (this.initDone) {
            this.logger.warn("init called more than once!");
            return;
        }
        this.initDone = true;
    }

    /**
     * Call in the "postInit" phase in your common proxy.
     */
    public void postInit(FMLPostInitializationEvent event) {
        if (this.postInitDone) {
            this.logger.warn("postInit called more than once!");
            return;
        }

        int oldRecipeRegisterCount = this.recipes.getOldRecipeRegisterCount();
        if (oldRecipeRegisterCount > 0) {
            long totalRecipes = ForgeRegistries.RECIPES.getKeys().stream()
                    .map(ResourceLocation::getResourceDomain)
                    .filter(s -> s.equals(this.modId))
                    .count();
            this.logger.formatted_Warn("Mod '{}' is still registering recipes with RecipeMaker ({} recipes, out of {} total)",
                    this.modId, oldRecipeRegisterCount, totalRecipes);
        }

        this.postInitDone = true;
    }

    /**
     * Call in the "preInit" phase in your client proxy.
     */
    @SideOnly(Side.CLIENT)
    public void clientPreInit(FMLPreInitializationEvent event) {
    }

    /**
     * Call in the "init" phase in your client proxy.
     */
    @SideOnly(Side.CLIENT)
    public void clientInit(FMLInitializationEvent event) {
        for (Block block : this.blocks) {
            if (block instanceof ITEBlock) {
            	ITEBlock tileBlock = (ITEBlock) block;
                final TileEntitySpecialRenderer tesr = tileBlock.getTileRenderer();
                if (tesr != null) {
                    ClientRegistry.bindTileEntitySpecialRenderer(tileBlock.getTileEntityClass(), tesr);
                }
            }
        }
    }

    /**
     * Call in the "postInit" phase in your client proxy.
     *
     * @param event
     */
    @SideOnly(Side.CLIENT)
    public void clientPostInit(FMLPostInitializationEvent event) {
    }

    //endregion

    private void addRecipes() {
        this.recipeAdders.forEach(obj -> obj.addRecipes(this.recipes));
    }

    private void addOreDictEntries() {
        this.recipeAdders.forEach(IAddRecipe::addOreDict);
    }

    @SideOnly(Side.CLIENT)
    private void registerModels() {
        for (Block block : this.blocks) {
            if (block instanceof ICustomModel) {
                ((ICustomModel) block).registerModels();
            } else {
                ResourceLocation registryName = Objects.requireNonNull(block.getRegistryName());
                ModelResourceLocation model = new ModelResourceLocation(registryName, "inventory");
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
            }
        }
        for (Item item : this.items) {
            if (item instanceof ICustomMesh) {
            	ICustomMesh customMesh = (ICustomMesh) item;
                ModelBakery.registerItemVariants(item, customMesh.getVariants());
                ModelLoader.setCustomMeshDefinition(item, customMesh.getCustomMesh());
            } else if (item instanceof ICustomModel) {
                ((ICustomModel) item).registerModels();
            } else {
                ResourceLocation registryName = Objects.requireNonNull(item.getRegistryName());
                ModelResourceLocation model = new ModelResourceLocation(registryName, "inventory");
                ModelLoader.setCustomModelResourceLocation(item, 0, model);
            }
        }
    }

    public CreativeTabs getDefaultCreativeTab() {
		return this.defaultCreativeTab;
	}

	public void setDefaultCreativeTab(CreativeTabs defaultCreativeTab) {
		this.defaultCreativeTab = defaultCreativeTab;
	}
	
	public CreativeTabs getCreativeTabs() {
		return this.creativeTab;
	}
	
	public void setCreativeTab(CreativeTabs creativeTabs) {
		this.creativeTab = creativeTabs;
	}

	/**
     * Handles the new Forge RegistryEvents. An instance will automatically be registered when an
     * StellarRegistry is constructed.

     */
    public static class EventHandler {
        private final StellarRegistry ModRegistry;

        public EventHandler(StellarRegistry ModRegistry) {
            this.ModRegistry = ModRegistry;
        }

        private void runRegistrationHandlerIfPresent(Class<? extends IForgeRegistryEntry<?>> registryClass) {
            if (this.ModRegistry.registrationHandlers.containsKey(registryClass)) {
                this.ModRegistry.registrationHandlers.get(registryClass).accept(this.ModRegistry);
            }
        }

        @SubscribeEvent
        public void registerBlocks(RegistryEvent.Register<Block> event) {
            runRegistrationHandlerIfPresent(Block.class);
        }

        @SubscribeEvent
        public void registerItems(RegistryEvent.Register<Item> event) {
            runRegistrationHandlerIfPresent(Item.class);
            this.ModRegistry.addOreDictEntries();
        }

        @SubscribeEvent
        public void registerPotions(RegistryEvent.Register<Potion> event) {
            runRegistrationHandlerIfPresent(Potion.class);
        }

        @SubscribeEvent
        public void registerBiomes(RegistryEvent.Register<Biome> event) {
            runRegistrationHandlerIfPresent(Biome.class);
        }

        @SubscribeEvent
        public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
            runRegistrationHandlerIfPresent(SoundEvent.class);
        }

        @SubscribeEvent
        public void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
            runRegistrationHandlerIfPresent(PotionType.class);
        }

        @SubscribeEvent
        public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
            runRegistrationHandlerIfPresent(Enchantment.class);
        }

        @SubscribeEvent
        public void registerVillagerProfessions(RegistryEvent.Register<VillagerProfession> event) {
            runRegistrationHandlerIfPresent(VillagerProfession.class);
        }

        @SubscribeEvent
        public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
            runRegistrationHandlerIfPresent(EntityEntry.class);
        }

        @SubscribeEvent
        public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
            runRegistrationHandlerIfPresent(IRecipe.class);
            this.ModRegistry.addRecipes();
        }

        @SubscribeEvent
        public void registerModels(ModelRegistryEvent event) {
            this.ModRegistry.registerModels();
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void registerBlockColors(ColorHandlerEvent.Block event) {
            BlockColors blockColors = event.getBlockColors();
            for (Block block : this.ModRegistry.coloredBlocks) {
                blockColors.registerBlockColorHandler(((IColorBlock) block).getColorHandler(), block);
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void registerItemColors(ColorHandlerEvent.Item event) {
            ItemColors itemColors = event.getItemColors();
            for (Block block : this.ModRegistry.coloredBlocks) {
                itemColors.registerItemColorHandler(((IColorBlock) block).getItemColorHandler(), Item.getItemFromBlock(block));
            }
            for (Item item : this.ModRegistry.coloredItems) {
                itemColors.registerItemColorHandler(((IColorItem) item).getColorHandler(), item);
            }
        }
    }
}
