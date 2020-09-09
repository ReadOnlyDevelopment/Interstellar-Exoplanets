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

package net.romvoid95.api.crafting.recipe.alloyrefinery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.romvoid95.common.utility.StackHelper;

public class AlloyRefineryRecipeObject {

  /**
   * The regex used to split the item key into the ore name and stack size components.
   */
  public static final String ITEM_KEY_SPLITTER = "\\*";

  private List<String> itemKeys = Lists.newArrayList();
  private List<ItemStack> possibleStacks = Lists.newArrayList();

  public static AlloyRefineryRecipeObject[] getFromObjectArray(Object... objects) {

    List<AlloyRefineryRecipeObject> list = Lists.newArrayList();
    for (Object obj : objects) {
      list.add(from(obj));
    }
    return list.toArray(new AlloyRefineryRecipeObject[] {});
  }

  public AlloyRefineryRecipeObject(String oreName, int count) {

    this(oreName + "*" + count);
  }

  /**
   * Construct a recipe object with one or more item keys. Typically, you will only use one key. Item keys must be in
   * the format "oreName:count" (minus the quotes).
   *
   * @param itemKeys
   */
  public AlloyRefineryRecipeObject(String... itemKeys) {

    for (String itemKey : itemKeys) {
      this.itemKeys.add(itemKey);
      possibleStacks.addAll(getStacksFromKey(itemKey));
    }
  }

  /**
   * Construct a recipe object with a specific item stack or stacks. Use this for items not in the ore dictionary, for
   * example. Don't forget to set the stack size! You may also want to copy the stack before you do that though...
   *
   * @param stacks
   */
  public AlloyRefineryRecipeObject(ItemStack... stacks) {

    possibleStacks.addAll(Arrays.asList(stacks));
  }

  public static AlloyRefineryRecipeObject from(Object obj) {

    if (obj instanceof String) {
      return new AlloyRefineryRecipeObject((String) obj);
    } else if (obj instanceof ItemStack) {
      return new AlloyRefineryRecipeObject((ItemStack) obj);
    } else if (obj instanceof AlloyRefineryRecipeObject) {
      return (AlloyRefineryRecipeObject) obj;
    } else {
      throw new IllegalArgumentException(
          "AlloyRefineryRecipeObject: don't know how to use object of type " + obj.getClass());
    }
  }

  /**
   * Determines if the input stack matches for the recipe object.
   *
   * @param inputStack
   * @return True if the stack matches, false otherwise.
   */
  public boolean matches(ItemStack inputStack) {

    return getMatchingStack(inputStack) != null;
  }

  /**
   * Gets the first stack in possible stacks list that matches the input stack.
   *
   * @param inputStack
   * @return First matching stack, or null if there is no match.
   */
  public ItemStack getMatchingStack(ItemStack inputStack) {

    if (inputStack.isEmpty())
      return null;

    for (ItemStack recipeStack : possibleStacks) {
      if (!recipeStack.isEmpty() && inputStack.isItemEqual(recipeStack) && inputStack.getCount() >= recipeStack.getCount()) {
        return recipeStack.copy();
      }
    }
    return null;
  }

  /**
   * Gets a copy of the item keys list.
   *
   * @return
   */
  public List<String> getItemKeys() {

    return Lists.newArrayList(itemKeys);
  }

  /**
   * Gets a copy of the possible item stacks list.
   *
   * @return
   */
  public List<ItemStack> getPossibleItemStacks() {

    return Lists.newArrayList(possibleStacks);
  }

  /**
   * Gets all possible stacks from the ore dictionary that match the item key. It copies the original stack and sets the
   * stack size as well.
   *
   * @param key
   * @return
   */
  private List<ItemStack> getStacksFromKey(String key) {

    String[] parts = key.split(ITEM_KEY_SPLITTER);

    if (parts.length != 2) {
      throw new IllegalArgumentException("Item key must be in the format \"oreName*count\".");
    }

    String oreName = parts[0];
    int stackSize = Integer.parseInt(parts[1]);
    if (stackSize < 0) {
      throw new IllegalArgumentException("Item count must greater than zero!");
    }
    List<ItemStack> result = new ArrayList<ItemStack>();

    ItemStack copy;
    for (ItemStack stack : StackHelper.getOres(oreName)) {
      copy = stack.copy();
      copy.setCount(stackSize);
      result.add(copy);
    }

    return result;
  }
}
