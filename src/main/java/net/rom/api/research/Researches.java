package net.rom.api.research;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Researches {
    private static final int MIN_research_BIT = 0;
    private static final int MAX_research_IDS = Short.MAX_VALUE;
    public final Random random;
    private final BitSet bitSet;
    private final Map<Integer, IResearch> researchMap;
    private final Map<IResearch, Integer> researchIntegerMap;
    private final Map<String, IResearch> stringResearchMap;

    public Researches() {
        bitSet = new BitSet(MAX_research_IDS);
        researchMap = new HashMap<>();
        researchIntegerMap = new HashMap<>();
        stringResearchMap = new HashMap<>();
        random = new Random();
    }

    public IResearch getResearchWithID(int researchID) {
        return researchMap.get(researchID);
    }

    public int getResearchID(IResearch research) {
        return researchIntegerMap.get(research);
    }

    public IResearch getResearchByName(String name) {
        return stringResearchMap.get(name);
    }

    public void registerResearch(String name, Research research) {
        if (researchIntegerMap.containsKey(research)) {
            throw new RuntimeException(name + " Research is already registered");
        }
        int id = bitSet.nextClearBit(MIN_research_BIT);
        researchMap.put(id, research);
        researchIntegerMap.put(research, id);
        stringResearchMap.put(name, research);
        bitSet.set(id, true);
    }

    public void registerResearchAt(Integer id, String name, IResearch research) {
        researchMap.put(id, research);
        researchIntegerMap.put(research, id);
        bitSet.set(id, true);
        stringResearchMap.put(name, research);
    }

    public Set<String> getAllResearchName() {
        return stringResearchMap.keySet();
    }
}
