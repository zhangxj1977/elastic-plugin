/**
 *
 */
package jp.co.leadingsoft.elastic.index;

import java.io.IOException;
import java.util.Base64;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * @author zhangxj
 *
 */
public class EncryptTokenFilter extends TokenFilter {

	private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

	public EncryptTokenFilter(TokenStream input) {
		super(input);
	}

	/* (非 Javadoc)
	 * @see org.apache.lucene.analysis.TokenStream#incrementToken()
	 */
	@Override
	public final boolean incrementToken() throws IOException {
		if (input.incrementToken()) {
			char[] curTermBuffer = new char[termAtt.length()];
			System.arraycopy(termAtt.buffer(), 0, curTermBuffer, 0, curTermBuffer.length);

			String encodedStr = Base64.getEncoder().encodeToString(
					new String(curTermBuffer).getBytes());
			int len = encodedStr.length();
			termAtt.setEmpty();
			termAtt.resizeBuffer(len);
			termAtt.copyBuffer(encodedStr.toCharArray(), 0, len);

			curTermBuffer = null;
			return true;
		} else {
			return false;
		}
	}

}
