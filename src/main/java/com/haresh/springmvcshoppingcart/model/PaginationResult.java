/**
 * Author: Haresh Shaha
 * Date: 24-Nov-2020 12:48:24 am
 */

package com.haresh.springmvcshoppingcart.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;

public class PaginationResult<T> {
	private int totalRecords;
	private int currentPage;
	private List<T> list;
	private int maxResult;
	private int totalPages;

	private int maxNavigationPage;
	private List<Integer> navigationPages;

	public PaginationResult(Query<T> query, int page, int maxResult,
			int maxNavigationPage) {
		final int pageIndex = (page - 1) < 0 ? 0 : (page - 1);

		int fromRecordIndex = pageIndex * maxResult;
		int maxRecordIndex = fromRecordIndex + maxResult;

		ScrollableResults resultScroll = query
				.scroll(ScrollMode.SCROLL_INSENSITIVE);

		List<T> results = new ArrayList<T>();

		boolean hasResult = resultScroll.isFirst();

		if (hasResult) {
			hasResult = resultScroll.scroll(fromRecordIndex);
			if (hasResult) {
				do {
					T record = (T) resultScroll.get(0);
					results.add(record);
				} while (resultScroll.next()
						&& resultScroll.getRowNumber() >= fromRecordIndex
						&& resultScroll.getRowNumber() < maxRecordIndex);
			}
			// Go to the last record
			resultScroll.last();
		}

		// Total Records
		this.totalRecords = resultScroll.getRowNumber() + 1;
		this.currentPage = pageIndex + 1;
		this.list = results;
		this.maxResult = maxResult;

		this.totalPages = (this.totalRecords / this.maxResult) + 1;
		this.maxNavigationPage = maxNavigationPage;

		if (this.maxNavigationPage > totalPages) {
			this.maxNavigationPage = totalPages;
		}

		this.calcNavigationPages();
	}

	private void calcNavigationPages() {
		this.navigationPages = new ArrayList<Integer>();

		int current = currentPage > maxNavigationPage ? maxNavigationPage
				: currentPage;

		int begin = current - this.maxNavigationPage / 2;
		int end = current + this.maxNavigationPage / 2;

		// First page
		navigationPages.add(1);
		if (begin > 2) {
			// For '...'
			navigationPages.add(-1);
		}

		for (int i = begin; i < end; i++) {
			if (i > 1 && i < this.totalPages) {
				navigationPages.add(i);
			}
		}

		if (end < this.totalPages - 2) {
			// For '...'
			navigationPages.add(-1);
		}
		// Last page.
		navigationPages.add(this.totalPages);
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public int getTotalRecords() {
		return this.totalRecords;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public List<T> getList() {
		return this.list;
	}

	public int getMaxResult() {
		return this.maxResult;
	}

	public List<Integer> getNavigationPages() {
		return this.navigationPages;
	}
}
