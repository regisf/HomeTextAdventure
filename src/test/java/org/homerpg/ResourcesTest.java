package org.homerpg;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {

    @Test
    @DisplayName("Create the instance")
    void getInstance() {
        Resources result = Resources.getInstance();
        assertNotNull(result);
    }
}
