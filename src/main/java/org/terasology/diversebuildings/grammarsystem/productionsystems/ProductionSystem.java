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
package org.terasology.diversebuildings.grammarsystem.productionsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.diversebuildings.BuildingTemplate;
import org.terasology.diversebuildings.grammarsystem.rules.AddDoorRule;
import org.terasology.diversebuildings.grammarsystem.rules.Rule;
import org.terasology.diversebuildings.grammarsystem.rules.SetRule;
import org.terasology.diversebuildings.grammarsystem.rules.SplitToRoofAndBoxRule;
import org.terasology.diversebuildings.grammarsystem.symbols.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Yakimovich on 30.04.2014.
 */
public class ProductionSystem {
    private final static Logger logger = LoggerFactory.getLogger(ProductionSystem.class);
    public static final int PRODUCTION_CYCLES_LIMIT = 100;

    private List<Rule> rules = new ArrayList<Rule>();
    public ProductionSystem(){
    }

    public void addRule(Rule rule){
        rules.add(rule);
    }

    public BuildingTemplate produce(Symbol symbol){
        List<Symbol> symbols = new ArrayList<Symbol>();
        symbols.add(symbol);
        int rulesAppliedOnLoop = -1;
        int numberOfCycles = 0;
        while((rulesAppliedOnLoop != 0) && (numberOfCycles <= PRODUCTION_CYCLES_LIMIT)){
            numberOfCycles++;
            rulesAppliedOnLoop = 0;
            List<Symbol> newSymbols = new ArrayList<Symbol>();
            for(Symbol curSymbol : symbols){
                boolean ruleApplied = false;
                for(Rule rule : rules){
                    if(rule.isAmongTargetSymbols(curSymbol)){
                        logger.info("using " + rule + " on " + curSymbol);
                        List<Symbol> symbolsToAdd = rule.apply(curSymbol);
                        newSymbols.addAll(symbolsToAdd);
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
        logger.info("produce finished");
        return mergeBuildingTemplateSymbols(symbols);
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
