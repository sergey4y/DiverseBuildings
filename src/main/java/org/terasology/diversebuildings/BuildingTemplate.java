/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.diversebuildings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.math.Vector3i;
import org.terasology.world.block.Block;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sergey Yakimovich on 24.04.2014.
 */
public class BuildingTemplate {
    public BuildingTemplate(){

    }

    public BuildingTemplate(BuildingTemplatePrefabComponent prefab){
        String[] blocksMappingStringParts = prefab.blocksMapping.split(",");
        Map<String, String> blocksMapping = new HashMap<String, String>();
        for(String part : blocksMappingStringParts){
            String[] subParts = part.split("-");
            blocksMapping.put(subParts[0], subParts[1]);
        }
        String[] levelsStrings = prefab.levels.split("\\.");
        for(String levelString : levelsStrings){
            String[] yFromxzParted = levelString.split("\\)\\[");
            String yString = yFromxzParted[0].replace("(", "");
            String xzString = yFromxzParted[1].replace("]", "");
            String[] yArray = yString.split(",");
            Set<Integer> ySet = new HashSet<Integer>();
            for(String yArrayPart : yArray){
                ySet.add(Integer.parseInt(yArrayPart.trim()));
            }
            String[] xStrings = xzString.split(";");
            for(int z = 0; z < xStrings.length; z++){
                String[] xArray = xStrings[z].split(",");
                for(int x = 0; x < xArray.length; x++){
                    String block = xArray[x].trim();
                    if(!block.equals("0")){
                        for(Integer y : ySet){
                            blocksPositionsData.put(new Vector3i(x, y, z), blocksMapping.get(block));
                        }
                    }
                }
            }
        }
    }

    private final static Logger logger = LoggerFactory.getLogger(BuildingTemplate.class);

    private Map<Vector3i, String> blocksPositionsData = new HashMap<Vector3i, String>();

    public String getBlockAtPosition(Vector3i position) {
        return blocksPositionsData.get(position);
    }

    public String setBlockAtPosition(Vector3i position, String block) {
        return blocksPositionsData.put(position, block);
    }

    public String removeBlockAtPosition(Vector3i position) {
        return blocksPositionsData.remove(position);
    }

    public Set<Vector3i> getBuildingPositions() {
        return blocksPositionsData.keySet();
    }

    public void rotate90Deg(){
        Map<Vector3i, String> newBlocksPositionsData = new HashMap<Vector3i, String>();
        int maxHorSize = 0;
        for(Vector3i position : blocksPositionsData.keySet()){
            if(position.getX() > maxHorSize){
                maxHorSize = position.getX();
            }
            if(position.getZ() > maxHorSize){
                maxHorSize = position.getZ();
            }
        }
        for(Vector3i position : blocksPositionsData.keySet()){
            Vector3i newPosition = new Vector3i(maxHorSize - 1 - position.getZ(), position.getY(), position.getX());
            newBlocksPositionsData.put(newPosition, blocksPositionsData.get(position));
        }
        blocksPositionsData = newBlocksPositionsData;
    }
}
