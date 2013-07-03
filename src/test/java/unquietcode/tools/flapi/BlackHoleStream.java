package unquietcode.tools.flapi;

import java.io.IOException;
import java.io.OutputStream;

public final class BlackHoleStream extends OutputStream {
	public static final BlackHoleStream $ = new BlackHoleStream();

	@Override
	public final void write(int b) throws IOException {
		// nothing
	}
}
