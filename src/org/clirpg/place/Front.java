package org.clirpg.place;

import org.clirpg.content.Reader;

public class Front extends IPlace {
    public Front() {
        Reader reader = new Reader("front");
        setContent(reader.readContent());
    }
}
