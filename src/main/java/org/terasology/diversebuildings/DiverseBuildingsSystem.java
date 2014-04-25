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
import org.terasology.logic.console.CommandParam;
import org.terasology.logic.console.Message;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.math.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.registry.In;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;

import javax.vecmath.Vector3f;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey Yakimovich on 24.04.2014.
 */
@RegisterSystem
public class DiverseBuildingsSystem extends BaseComponentSystem {
    @In
    private BlockManager blockManager;
    @In
    private PrefabManager prefabManager;
    @In
    private WorldProvider worldProvider;

    private Map<String, BuildingTemplate> templates = new HashMap<String, BuildingTemplate>();

    @Override
    public void initialise(){
        for (Prefab prefab : prefabManager.listPrefabs(BuildingTemplatePrefabComponent.class)) {
            BuildingTemplatePrefabComponent component = prefab.getComponent(BuildingTemplatePrefabComponent.class);
            BuildingTemplate template = new BuildingTemplate(component);
            templates.put(prefab.getName(), template);
        }
    }

    @Command(shortDescription = "List available buildings.")
    public String listAvailableBuildings(){
        StringBuilder result = new StringBuilder();
        for(String name : templates.keySet()){
            result.append(name);
            result.append(Message.NEW_LINE);
        }
        return result.toString();
    }

    @Command(shortDescription = "Build nearby a building from a template.")
    public String buildNearby(@CommandParam(value = "TemplateName") String templateName, @CommandParam(value = "RotationInDegrees") String rotation){
        BuildingTemplate template = new BuildingTemplate(templates.get(templateName));
        if(rotation != null) {
            if (rotation.trim().equalsIgnoreCase("0")) {
            } else if (rotation.trim().equalsIgnoreCase("90")) {
                template.rotate90Deg();
            } else if (rotation.trim().equalsIgnoreCase("180")) {
                template.rotate90Deg();
                template.rotate90Deg();
            } else if (rotation.trim().equalsIgnoreCase("270")) {
                template.rotate90Deg();
                template.rotate90Deg();
                template.rotate90Deg();
            } else {
                return "Can't rotate on " + rotation + " degrees, must be 90, 180 or 270";
            }
        }
        if(template == null){
            return "Template " + templateName + " not found.";
        }
        Vector3i position = getRoundedPlayerPosition();
        position.add(0, 0, 2);
        setBlocksOnWorldProvider(template, position);
        return "Building from a template " + templateName + " finished.";
    }

    private Vector3i getRoundedPlayerPosition(){
        LocalPlayer localPlayer = CoreRegistry.get(LocalPlayer.class);
        Vector3f playerPosition = localPlayer.getPosition();
        int x = (int)playerPosition.getX();
        int y = (int)playerPosition.getY();
        int z = (int)playerPosition.getZ();
        return new Vector3i(x, y, z);
    }

    public void setBlocksOnWorldProvider(BuildingTemplate building, Vector3i position){
        for(Vector3i relativePos : building.getBuildingPositions()){
            Vector3i blockPosition = new Vector3i(relativePos.getX() + position.getX(),
                    relativePos.getY() + position.getY(), relativePos.getZ() + position.getZ());
            Block block = blockManager.getBlock(building.getBlockAtPosition(relativePos));
            worldProvider.setBlock(blockPosition, block);
        }
    }
}
