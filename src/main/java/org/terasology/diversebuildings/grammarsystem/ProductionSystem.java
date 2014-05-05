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
package org.terasology.diversebuildings.grammarsystem;

import org.terasology.diversebuildings.BuildingTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Yakimovich on 30.04.2014.
 */
public class ProductionSystem {
    private List<Rule> rules = new ArrayList<Rule>();
    public ProductionSystem(){
        rules.add(new SplitToRoofAndBoxRule());
        SetRule boxSetRule = new SetRule();
        boxSetRule.setApplyTo(BoxSymbol.class);
        boxSetRule.setBlockType("Core:Brick");
        rules.add(boxSetRule);
        SetRule roofSetRule = new SetRule();
        roofSetRule.setApplyTo(OneLevelRoofSymbol.class);
        roofSetRule.setBlockType("Core:Plank");
        rules.add(roofSetRule);

    }

    public List<Symbol> produce(Symbol symbol){
        List<Symbol> symbols = new ArrayList<Symbol>();
        symbols.add(symbol);
        int rulesAppliedOnLoop = -1;
        while(rulesAppliedOnLoop != 0){
            rulesAppliedOnLoop = 0;
            List<Symbol> newSymbols = new ArrayList<Symbol>();
            for(Symbol curSymbol : symbols){
                boolean ruleApplied = false;
                for(Rule rule : rules){
                    if(rule.canBeApplied(curSymbol)){
                        newSymbols.addAll(rule.apply(curSymbol));
                        rulesAppliedOnLoop++;
                        ruleApplied = true;
                        break;
                    }
                }
                if(!ruleApplied){
                    newSymbols.add(curSymbol);
                }
            }
            symbols = newSymbols;
        }
        return symbols;
    }

    public BuildingTemplate mergeBuildingTemplateSymbols(List<Symbol> symbols){
        BuildingTemplate template = new BuildingTemplate();
        for(Symbol symbol : symbols){
            if(symbol instanceof BuildingTemplate){
                template.merge((BuildingTemplate)symbol);
            }
        }
        return template;
    }
}
