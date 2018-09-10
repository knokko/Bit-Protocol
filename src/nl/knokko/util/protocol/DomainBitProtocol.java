package nl.knokko.util.protocol;

import nl.knokko.util.bits.BitInput;

public class DomainBitProtocol<Handler> implements BitProtocol<Handler> {
	
	protected final BitProtocol<Handler>[] protocols;
	
	protected final byte bitCount;

	@SuppressWarnings("unchecked")
	public DomainBitProtocol(int amount, byte bitCount) {
		protocols = new BitProtocol[amount];
		this.bitCount = bitCount;
	}
	
	public void register(int domainCode, BitProtocol<Handler> protocol){
		protocols[domainCode] = protocol;
	}

	public void process(BitInput input, Handler handler) {
		int index = (int) input.readNumber(bitCount, false);
		if(index < 0 || index >= protocols.length || protocols[index] == null) throw new IllegalArgumentException("Invalid code: " + index);
		this.protocols[index].process(input, handler);
	}
}