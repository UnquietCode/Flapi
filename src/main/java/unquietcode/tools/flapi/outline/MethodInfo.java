/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.outline;


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

    public void addAnnotationParam(Object annotation, String param, Object value) {
	    Map<String, Object> params = annotations.get(annotation);

	    if (params == null) {
		    params = new LinkedHashMap<String, Object>();
		    annotations.put(annotation, params);
	    }

	    params.put(param, value);
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
		MethodParser parser = new MethodParser(methodSignature);

		sb.append(parser.methodName).append("$");
		boolean first = true;

		for (Pair<MethodParser.JavaType, String> param : parser.params) {
			if (!first) { sb.append("$"); }
			else { first = false; }

			sb.append(param.first.typeName).append("_").append(param.second);
		}

		sb.append("$").append(didTrigger ? "t" : "f");
		return sb.toString();
	}
}
