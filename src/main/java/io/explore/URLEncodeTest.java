package io.explore;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLEncodeTest {

	public static void main(String[] args) throws UnsupportedEncodingException {

		timeToEncode("BOL_Bengali", 1_000_000, false);
		timeToEncode("BOL_Bengali", 1_000_000, true);

		timeToEncode("BOL_AimarÃ¡", 1_000_000, false);
		timeToEncode("BOL_AimarÃ¡", 1_000_000, true);
	}

	private static void timeToEncode(String orgUriStr, long count, boolean encode) throws UnsupportedEncodingException {
		int orgLenth = orgUriStr.length();
		long startTime = System.currentTimeMillis();
		for (long i = 0; i <= count; i++) {
			StringBuilder uriStr = new StringBuilder(orgUriStr);
			String strToEncode = uriStr.append(i).toString();
			if (encode)
				URLEncoder.encode(strToEncode, StandardCharsets.UTF_8.name());
			uriStr = new StringBuilder(uriStr.substring(0, orgLenth));
		}
		System.out.println(String.format("%s %s  %d takes %s ms", (encode ? "Encoding " : "Not Encoding"), orgUriStr,
				count, (System.currentTimeMillis() - startTime)));
	}
}