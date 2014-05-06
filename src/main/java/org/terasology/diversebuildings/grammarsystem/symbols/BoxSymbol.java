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
package org.terasology.diversebuildings.grammarsystem.symbols;

import org.terasology.diversebuildings.BuildingTemplate;
import org.terasology.math.Vector3i;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey Yakimovich on 02.05.2014.
 */
public class BoxSymbol extends Symbol implements SetRuleApplicable, AddDoorRuleApplicable {
    private int xStartPosition;
    private int xSize;
    private int yStartPosition;
    private int ySize;
    private int zStartPosition;
    private int zSize;
    private Set<Vector3i> removedBlocks = new HashSet<Vector3i>();

    public void removeBlock(Vector3i position){
        removedBlocks.add(position);
    }

    public int getxStartPosition() {
        return xStartPosition;
    }

    public void setxStartPosition(int xStartPosition) {
        this.xStartPosition = xStartPosition;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getyStartPosition() {
        return yStartPosition;
    }

    public void setyStartPosition(int yStartPosition) {
        this.yStartPosition = yStartPosition;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public int getzStartPosition() {
        return zStartPosition;
    }

    public void setzStartPosition(int zStartPosition) {
        this.zStartPosition = zStartPosition;
    }

    public int getzSize() {
        return zSize;
    }

    public void setzSize(int zSize) {
        this.zSize = zSize;
    }

    @Override
    public BuildingTemplate applySetRule(String blockType){
        BuildingTemplate template = new BuildingTemplate();
        for(int x = getxStartPosition(); x < getxSize(); x++){
            for(int z = getzStartPosition(); z < getzSize(); z++){
                template.setBlockAtPosition(new Vector3i(x, getyStartPosition(), z), blockType);
                template.setBlockAtPosition(new Vector3i(x, getyStartPosition()
                        + getySize() - 1, z), blockType);
            }
        }
        for(int x = getxStartPosition(); x < getxSize(); x++){
            for(int y = getyStartPosition(); y < getySize(); y++){
                template.setBlockAtPosition(new Vector3i(x, y, getzStartPosition()), blockType);
                template.setBlockAtPosition(new Vector3i(x, y, getzStartPosition()
                        + getzSize() - 1), blockType);
            }
        }
        for(int y = getyStartPosition(); y < getySize(); y++){
            for(int z = getzStartPosition(); z < getzSize(); z++){
                template.setBlockAtPosition(new Vector3i(getxStartPosition(), y, z), blockType);
                template.setBlockAtPosition(new Vector3i(getxStartPosition()
                        + getxSize() - 1, y, z), blockType);
            }
        }
        for(Vector3i removedBlock : removedBlocks){
            template.removeBlockAtPosition(removedBlock);
        }
        return template;
    }

    @Override
    public void addDoor() {
        if(zSize >= 3){
            if(ySize >= 3) {
                this.removedBlocks.add(new Vector3i(0, 1, 2));

            }
            if(ySize >= 4){
                this.removedBlocks.add(new Vector3i(0, 2, 2));
            }
        }
    }
}
