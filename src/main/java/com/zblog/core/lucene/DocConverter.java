package com.zblog.core.lucene;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;

public class DocConverter {

	private DocConverter() {
	}

	public static Map<String, Object> convert(Document obj) {
		Map<String, Object> mc = new HashMap<String, Object>();
		for (IndexableField field : obj.getFields()) {
			mc.put(field.name(), field.stringValue());
		}

		return mc;
	}

	public static Map<String, Object> convert(Document obj, String... filters) {
		return convert(obj, Arrays.asList(filters));
	}

	public static Map<String, Object> convert(Document obj, Collection<String> filters) {
		Map<String, Object> mc = new HashMap<String, Object>();
		for (IndexableField field : obj.getFields()) {
			if (filters.contains(field.name()))
				continue;
			mc.put(field.name(), field.stringValue());
		}

		return mc;
	}

}
