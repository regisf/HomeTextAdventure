package org.homerpg.place;

import org.homerpg.content.Reader;

public class Garage extends IPlace {
    public Garage() {
        Reader reader = new Reader("garage");
        setContent(reader.readContent());
    }
}
