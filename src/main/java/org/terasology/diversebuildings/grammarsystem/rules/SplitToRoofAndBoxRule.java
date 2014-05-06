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

import org.terasology.diversebuildings.grammarsystem.symbols.BoxSymbol;
import org.terasology.diversebuildings.grammarsystem.symbols.OneLevelRoofSymbol;
import org.terasology.diversebuildings.grammarsystem.symbols.StartSymbol;
import org.terasology.diversebuildings.grammarsystem.symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Yakimovich on 02.05.2014.
 */
public class SplitToRoofAndBoxRule extends Rule{
    private int boxHeight = 5;

    public void setBoxHeight(int boxHeight) {
        this.boxHeight = boxHeight;
    }

    public int getBoxHeight() {
        return boxHeight;
    }

    @Override
    public List<Symbol> apply(Symbol symbol){
        List<Symbol> result = new ArrayList<Symbol>();
        StartSymbol startSymbol = (StartSymbol)symbol;
        BoxSymbol boxSymbol = new BoxSymbol();
        boxSymbol.setxStartPosition(0);
        boxSymbol.setxSize(startSymbol.getxSize());
        boxSymbol.setzStartPosition(0);
        boxSymbol.setzSize(startSymbol.getzSize());
        boxSymbol.setyStartPosition(0);
        boxSymbol.setySize(boxHeight);
        result.add(boxSymbol);
        OneLevelRoofSymbol roof = new OneLevelRoofSymbol();
        roof.setxStartPosition(boxSymbol.getxStartPosition());
        roof.setxSize(boxSymbol.getxSize());
        roof.setzStartPosition(boxSymbol.getzStartPosition());
        roof.setzSize(boxSymbol.getzSize());
        roof.setyLevel(boxHeight);
        result.add(roof);
        return result;
    }
}
