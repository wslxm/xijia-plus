package io.github.wslxm.springbootplus2.core.utils;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 * 事务提交后执行
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/6/10 16:17
 */
public class XjTransactionUtil {

	/**
	 * 接口
	 */
	public interface TransactionRun {
		/**
		 * 执行
		 */
		void exec();
	}

	/**
	 * 提交后执行
	 *
	 * @param transactionRun 接口
	 */
	public static void runOfAfterCommit(TransactionRun transactionRun) {
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				public void afterCommit() {
					transactionRun.exec();
				}
			});
		} else {
			transactionRun.exec();
		}
	}
}
