/*******************************************************************************
 * The MIT License
 *
 * Copyright (c) 2018 knokko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *  
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *******************************************************************************/
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

	public void register(int domainCode, BitProtocol<Handler> protocol) {
		protocols[domainCode] = protocol;
	}

	public void process(BitInput input, Handler handler) {
		int index = (int) input.readNumber(bitCount, false);
		if (index < 0 || index >= protocols.length || protocols[index] == null)
			throw new IllegalArgumentException("Invalid code: " + index);
		System.out.println("DomainBitProtocol.process: index is " + index);
		this.protocols[index].process(input, handler);
	}
}