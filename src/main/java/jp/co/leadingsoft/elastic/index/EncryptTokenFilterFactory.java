/**
 *
 */
package jp.co.leadingsoft.elastic.index;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettingsService;

/**
 * @author zhangxj
 *
 */
public class EncryptTokenFilterFactory extends AbstractTokenFilterFactory {

	@Inject
	public EncryptTokenFilterFactory(Index index,
			IndexSettingsService indexSettingsService,
			@Assisted String name,
			@Assisted Settings settings) {
		super(index, indexSettingsService.getSettings(), name, settings);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * org.elasticsearch.index.analysis.TokenFilterFactory#create(org.apache.
	 * lucene.analysis.TokenStream)
	 */
	@Override
	public TokenStream create(TokenStream tokenStream) {
		return new EncryptTokenFilter(tokenStream);
	}

}
