package org.homerpg.place;

import org.homerpg.content.Reader;

public class Front extends IPlace {
    public Front() {
        Reader reader = new Reader("front");
        setContent(reader.readContent());
    }
}
