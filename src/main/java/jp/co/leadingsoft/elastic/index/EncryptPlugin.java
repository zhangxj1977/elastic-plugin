/**
 *
 */
package jp.co.leadingsoft.elastic.index;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.Plugin;

/**
 * @author zhangxj
 *
 */
public class EncryptPlugin extends Plugin {

	ESLogger logger = Loggers.getLogger(EncryptPlugin.class);

	@Override
	public String description() {
		return "encrypt the token data";
	}

	@Override
	public String name() {
		return "cryptoindex";
	}

    public void onModule(AnalysisModule module) {
    	logger.info("onModule....");

    	module.addTokenFilter("encrypt", EncryptTokenFilterFactory.class);
    }

}
