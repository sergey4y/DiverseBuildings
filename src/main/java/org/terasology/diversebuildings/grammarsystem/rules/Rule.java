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

import org.terasology.diversebuildings.grammarsystem.symbols.Symbol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sergey Yakimovich on 30.04.2014.
 */
public abstract class Rule {
    private Set<Class> targetSymbols = new HashSet<Class>();

    public void addTargetSymbol(Class symbolClass){
        targetSymbols.add(symbolClass);
    }

    public boolean isAmongTargetSymbols(Symbol symbol){
        if(targetSymbols.contains(symbol.getClass())){
            return true;
        }
        return false;
    }

    public abstract List<Symbol> apply(Symbol symbol);
}
