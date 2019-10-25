package org.clirpg.place;

import org.clirpg.content.Reader;

public class Garage extends IPlace {
    public Garage() {
        Reader reader = new Reader("garage");
        setContent(reader.readContent());
    }
}
