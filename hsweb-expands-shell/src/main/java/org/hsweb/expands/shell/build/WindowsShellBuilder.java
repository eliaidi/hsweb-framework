package org.hsweb.expands.shell.build;

import org.hsweb.expands.shell.Shell;

public class WindowsShellBuilder extends AbstractShellBuilder {

    @Override
    public Shell buildTextShell(String text) throws Exception {
        String file = createFile(text);
        return Shell.build(file);
    }
}
