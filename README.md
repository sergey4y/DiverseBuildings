DiverseBuildings
================

Terasology module for building creation.

Templates for buildings can be described as prefab assets.

Example:
    "BuildingTemplatePrefab" : {
        "levels" : "
        (0, 1)[
        1, 1, 0, 1, 1;
        1, 0, 0, 0, 1;
        1, 0, 0, 0, 1;
        1, 0, 0, 0, 1;
        1, 1, 1, 1, 1
        ].
        (2)[
        1, 1, 1, 1, 1;
        1, 1, 1, 1, 1;
        1, 1, 1, 1, 1;
        1, 1, 1, 1, 1;
        1, 1, 1, 1, 1
        ]",
        "blocksMapping" : "1-core:Dirt"
    }

"1-core:Dirt" creates mapping of "1" to block "core:Dirt"
"0" means absence of any block on that particular place
"." separates different levels of building.
"(0, 1)" and "(2)" determine y coordinate (or coordinates) of the level.
blocks at coordinates x and z are set in the following way:
[    x: 0  1  2  3  4
 z: 0   1, 1, 1, 1, 1;
    1   1, 1, 1, 1, 1;
    2   1, 1, 1, 1, 1;
    3   1, 1, 1, 1, 1;
    4   1, 1, 1, 1, 1
]"
