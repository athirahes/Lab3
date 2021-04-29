
public class Translator {

	public String getResult(int i, String l) {
		String result = null;

		switch (l) {

		case "Bahasa Malaysia":
			String BahasaMalaysia[] = { "Selamat pagi", "Selamat malam", "Apa khabar?", "Terima kasih",
					"Selamat tinggal", "Ada apa?" };
			result = BahasaMalaysia[i];
			break;

		case "Arabic":
			String Arabic[] = { "صباح الخير", "مساء الخير", "حالك؟ كيف", "شكرا لك", "مع السلامة", "أخبارك؟ ما" };
			result = Arabic[i];
			break;

		case "Korean":
			String Korean[] = { "좋은 아침", "안녕히 주무세요", "어떻게 지내세요?", "감사합니다", "안녕", "A뭐야?" };

			result = Korean[i];
			break;
		}
			return result;
	}
}
