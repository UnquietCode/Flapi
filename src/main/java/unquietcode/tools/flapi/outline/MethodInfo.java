/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.outline;


import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.Pair;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ben Fagin
 */
public class MethodInfo implements Comparable<MethodInfo> {
	private Integer minOccurrences;
	private Integer maxOccurrences;
	private String methodSignature;
	private String documentation;
	private boolean isDeprecated = false;
	private String deprecationReason;
	private boolean didTrigger = false;
	private boolean isImplicit = false;
    private Map<Object, Map<String, Object>> annotations = new LinkedHashMap<Object, Map<String, Object>>();

	public Integer getMinOccurrences() {
		return minOccurrences;
	}

	public void setMinOccurrences(Integer minOccurrences) {
		this.minOccurrences = minOccurrences;
	}

	public Integer getMaxOccurrences() {
		return maxOccurrences;
	}

	public void setMaxOccurrences(Integer maxOccurrences) {
		this.maxOccurrences = maxOccurrences;
	}

	public boolean isDeprecated() {
		return isDeprecated;
	}

	public void setDeprecated(String reason) {
		isDeprecated = true;
		deprecationReason = reason;
	}

	public String getDeprecationReason() {
		return deprecationReason;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getMethodSignature() {
		return methodSignature;
	}

	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature.trim();
	}

	public boolean didTrigger() {
		return didTrigger;
	}

	public void setTriggered() {
		didTrigger = true;
	}

	public boolean isImplicit() {
		return isImplicit;
	}

	public void isImplicit(boolean isImplicit) {
		this.isImplicit = isImplicit;
	}

	public void addAnnotation(Object annotation, Map<String, Object> params) {
		if (annotations.containsKey(annotation)) {
			throw new DescriptorBuilderException("duplicate annotation: "+annotation);
		}
		annotations.put(annotation, params);
	}

    public Map<Object, Map<String, Object>> getAnnotations() {
        return annotations;
    }

    public MethodInfo copy()  {
		MethodInfo clone = new MethodInfo();
		copy(clone);
		return clone;
	}

	protected void copy(MethodInfo other) {
		other.minOccurrences = minOccurrences;
		other.maxOccurrences = maxOccurrences;
		other.methodSignature = methodSignature;
		other.documentation = documentation;
		other.isDeprecated = isDeprecated;
		other.deprecationReason = deprecationReason;
		other.didTrigger = didTrigger;
		other.isImplicit = isImplicit;
        other.annotations.putAll(annotations);
	}

	@Override
	public String toString() {
		return methodSignature + "-" + maxOccurrences;
	}

	/*
		Used by sorted collections to provide consistent ordering.
	 */
	public @Override int compareTo(MethodInfo other) {
		return keyString().compareTo(other.keyString());
	}

	public String keyString() {
		StringBuilder sb = new StringBuilder();

		final MethodParser parser;
		try {
			parser = new MethodParser(methodSignature);
		} catch (MethodParser.ParseException e) {
			throw new DescriptorBuilderException(e);
		}

		sb.append(parser.methodName).append("_1");
		boolean first = true;

		for (Pair<MethodParser.JavaType, String> param : parser.params) {
			if (!first) { sb.append("$"); }
			else { first = false; }

			sb.append(param.first.typeName).append("_").append(param.second);
		}

		if (didTrigger) {
			sb.append("_2t");
		}

		if (isImplicit) {
			sb.append("_3t");
		}

		return sb.toString();
	}
}
