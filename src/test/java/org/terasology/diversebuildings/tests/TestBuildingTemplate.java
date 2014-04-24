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
package org.terasology.diversebuildings.tests;

import junit.framework.Assert;
import org.junit.Test;
import org.terasology.diversebuildings.BuildingTemplate;
import org.terasology.diversebuildings.BuildingTemplatePrefabComponent;
import org.terasology.math.Vector3i;

/**
 * Created by Sergey Yakimovich on 24.04.2014.
 */
public class TestBuildingTemplate {
    @Test
    public void testConstructorFromBuildingTemplatePrefabComponent(){
        BuildingTemplatePrefabComponent component = new BuildingTemplatePrefabComponent();
        component.blocksMapping = "1-core:Dirt,2-core:Stone";
        component.levels = "(1,2)[1,1;0,2].(3)[2,0;0,2]";
        BuildingTemplate template = new BuildingTemplate(component);
        String block1 = template.getBlockAtPosition(new Vector3i(0, 1, 0));
        Assert.assertEquals("core:Dirt", block1);
        String block2 = template.getBlockAtPosition(new Vector3i(1, 1, 0));
        Assert.assertEquals("core:Dirt", block2);
        String block3 = template.getBlockAtPosition(new Vector3i(1, 1, 1));
        Assert.assertEquals("core:Stone", block3);
        String block4 = template.getBlockAtPosition(new Vector3i(0, 2, 0));
        Assert.assertEquals("core:Dirt", block4);
        String block5 = template.getBlockAtPosition(new Vector3i(1, 2, 0));
        Assert.assertEquals("core:Dirt", block5);
        String block6 = template.getBlockAtPosition(new Vector3i(1, 2, 1));
        Assert.assertEquals("core:Stone", block6);
        String block7 = template.getBlockAtPosition(new Vector3i(0, 3, 0));
        Assert.assertEquals("core:Stone", block7);
        String block8 = template.getBlockAtPosition(new Vector3i(1, 3, 1));
        Assert.assertEquals("core:Stone", block8);

    }
}
