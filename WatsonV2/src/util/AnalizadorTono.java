package util;

import java.util.List;

import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.Translation;
import com.ibm.watson.developer_cloud.language_translator.v3.util.Language;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class AnalizadorTono {
		public static int existeEnojo(String pTexto) {
			String textoENG = traducirTexto(pTexto);
			DocumentAnalysis tonos = getTonos(textoENG);
		
			for (int i = 0; i < tonos.getTones().size() ; i++) {
				String tono = tonos.getTones().get(i).getToneName();
				if (tono.equals("Anger")){
					return 1;
				}
			}
			return 0;
		}
		
		private static DocumentAnalysis getTonos(String pTexto) {
			ToneAnalyzer servicio = new ToneAnalyzer("2017-09-21",new IamOptions.Builder()
					.apiKey("Eo_yDYIgP7UyF88wfFHu0gIy71neSwTyIz0gh1m4Ba0p")
				    .build());

			// Call the service and get the tone
			ToneOptions toneOptions = new ToneOptions.Builder()
			  .text(pTexto)
			  .build();

			return servicio.tone(toneOptions).execute().getDocumentTone();
		}
		
		private static String traducirTexto(String pTexto) {
			LanguageTranslator traductor = new LanguageTranslator("2018-05-01", new IamOptions.Builder()
					.apiKey("uEp5sAgS4Pzc-WaRbIdKZGaXBq3cLjjOWuJpViMNi1HP")
				    .build());
			
			TranslateOptions translateOptions = new TranslateOptions.Builder()
			  .addText(pTexto)
			  .source(Language.SPANISH)
			  .target(Language.ENGLISH)
			  .build();
			
			List<Translation> translationResult = traductor.translate(translateOptions).execute().getTranslations();

			return translationResult.get(0).getTranslationOutput();
		}
}


