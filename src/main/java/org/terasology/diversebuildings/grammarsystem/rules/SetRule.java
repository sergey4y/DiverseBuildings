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
package org.terasology.diversebuildings.grammarsystem.rules;

import org.terasology.diversebuildings.grammarsystem.symbols.SetRuleApplicable;
import org.terasology.diversebuildings.grammarsystem.symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Yakimovich on 02.05.2014.
 */
public class SetRule extends Rule{
    private String blockType = "Core:Brick";

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    @Override
    public List<Symbol> apply(Symbol symbol) {
        List<Symbol> result = new ArrayList<Symbol>();
        if(symbol instanceof SetRuleApplicable){
            SetRuleApplicable toApply = (SetRuleApplicable) symbol;
            result.add(toApply.applySetRule(blockType));
        }
        return result;
    }
}
