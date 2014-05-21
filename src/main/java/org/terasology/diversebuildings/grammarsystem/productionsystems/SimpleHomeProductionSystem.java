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

import org.terasology.diversebuildings.grammarsystem.rules.AddDoorRule;
import org.terasology.diversebuildings.grammarsystem.rules.SetRule;
import org.terasology.diversebuildings.grammarsystem.rules.SplitToRoofAndBoxRule;
import org.terasology.diversebuildings.grammarsystem.symbols.*;

/**
 * Created by Sergey Yakimovich on 21.05.2014.
 */
public class SimpleHomeProductionSystem extends ProductionSystem{
    public SimpleHomeProductionSystem(){
        SplitToRoofAndBoxRule splitToRoofAndBoxRule= new SplitToRoofAndBoxRule();
        splitToRoofAndBoxRule.addTargetSymbol(StartSymbol.class);
        addRule(splitToRoofAndBoxRule);
        AddDoorRule addDoorRule = new AddDoorRule();
        addDoorRule.addTargetSymbol(BoxSymbol.class);
        addRule(addDoorRule);
        SetRule boxSetRule = new SetRule();
        boxSetRule.addTargetSymbol(BoxSymbolWithDoor.class);
        boxSetRule.setBlockType("Core:Brick");
        addRule(boxSetRule);
        SetRule roofSetRule = new SetRule();
        roofSetRule.addTargetSymbol(FlatRoofSymbol.class);
        roofSetRule.addTargetSymbol(GableRoofSymbol.class);
        roofSetRule.setBlockType("Core:Plank");
        addRule(roofSetRule);
    }
}
