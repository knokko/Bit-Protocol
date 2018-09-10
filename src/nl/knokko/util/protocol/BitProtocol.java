package nl.knokko.util.protocol;

import nl.knokko.util.bits.BitInput;

public interface BitProtocol<Handler> {
	
	void process(BitInput input, Handler handler);
}