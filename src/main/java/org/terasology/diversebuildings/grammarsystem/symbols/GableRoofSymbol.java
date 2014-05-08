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

/**
 * Created by Sergey Yakimovich on 08.05.2014.
 */
public class GableRoofSymbol extends Symbol implements SetRuleApplicable {
    private int yLevel;
    private int xStartPosition;
    private int xSize;
    private int zStartPosition;
    private int zSize;

    public int getyLevel() {
        return yLevel;
    }

    public void setyLevel(int yLevel) {
        this.yLevel = yLevel;
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
    public BuildingTemplate applySetRule(String blockType) {
        BuildingTemplate template = new BuildingTemplate();
        for(int i = 0; i < 1 + (double)getzSize()/2.0; i++){
            for (int x = getxStartPosition() + i; x < getxSize() - i; x++) {
                for (int z = getzStartPosition(); z < getzSize(); z++) {
                    template.setBlockAtPosition(new Vector3i(x, getyLevel() + i, z), blockType);
                }
            }
        }
        return template;
    }
}
