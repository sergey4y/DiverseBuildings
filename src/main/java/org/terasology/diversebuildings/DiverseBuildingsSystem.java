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

import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.prefab.PrefabManager;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.Command;
import org.terasology.logic.console.Message;
import org.terasology.registry.In;
import org.terasology.world.block.BlockManager;

/**
 * Created by Sergey Yakimovich on 24.04.2014.
 */
@RegisterSystem
public class DiverseBuildingsSystem extends BaseComponentSystem {
    @In
    private BlockManager blockManager;
    @In
    private PrefabManager prefabManager;

    @Command(shortDescription = "List available buildings.")
    public String listAvailableBuildings(){
        StringBuilder result = new StringBuilder();
        for (Prefab prefab : prefabManager.listPrefabs(BuildingTemplateComponent.class)) {
            BuildingTemplateComponent template = prefab.getComponent(BuildingTemplateComponent.class);
            result.append("name: " + prefab.getName() + " template: " + template);
            result.append(Message.NEW_LINE);
        }
        return result.toString();
    }
}
