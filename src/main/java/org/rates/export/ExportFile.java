package org.rates.export;

import java.io.File;

public interface ExportFile {
    File create(String name);
}
