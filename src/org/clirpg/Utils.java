package org.clirpg;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    @NotNull
    public static String removeSpacesOfEachLines(final String[] content) {
        List<String> result = new ArrayList<>();

        for (String line : content)  {
            result.add(line.trim());
        }

        return String.join("\n", result);
    }
}
