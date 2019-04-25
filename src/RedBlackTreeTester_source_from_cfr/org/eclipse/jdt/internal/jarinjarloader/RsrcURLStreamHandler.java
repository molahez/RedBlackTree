/*
 * Decompiled with CFR 0.139.
 */
package RedBlackTreeTester_source_from_cfr.org.eclipse.jdt.internal.jarinjarloader;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class RsrcURLStreamHandler
extends URLStreamHandler {
    private ClassLoader classLoader;

    public RsrcURLStreamHandler(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    protected URLConnection openConnection(URL u) throws IOException {
        return new RsrcURLConnection(u, this.classLoader);
    }

    protected void parseURL(URL url, String spec, int start, int limit) {
        String file = spec.startsWith("rsrc:") ? spec.substring(5) : (url.getFile().equals("./") ? spec : (url.getFile().endsWith("/") ? String.valueOf(url.getFile()) + spec : spec));
        this.setURL(url, "rsrc", "", -1, null, null, file, null, null);
    }
}

