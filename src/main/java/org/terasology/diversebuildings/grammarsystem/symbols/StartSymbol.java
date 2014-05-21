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
package org.terasology.diversebuildings.grammarsystem.symbols;

/**
 * Created by Sergey Yakimovich on 30.04.2014.
 */
public class StartSymbol extends Symbol{
    private int xSize = DEFAULT_X_SIZE;
    private int ySize = DEFAULT_Y_SIZE;
    private int zSize = DEFAULT_Z_SIZE;

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public int getzSize() {
        return zSize;
    }

    public void setzSize(int zSize) {
        this.zSize = zSize;
    }

    public final static int DEFAULT_X_SIZE = 7;
    public final static int DEFAULT_Y_SIZE = 7;
    public final static int DEFAULT_Z_SIZE = 7;
}
